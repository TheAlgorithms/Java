package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TaskRunnerTest {
	TaskRunner taskRunner;
	@Before
	public void setUp() {
		taskRunner = new TaskRunner();
	}
	
	@After
	public void tearDown() {
		taskRunner.tearDown();
	}
	
	@Test
	public void testExecutionSleep() throws InterruptedException {
		// Generate Sample Tasks
		List<Task> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			tasks.add(new Task("Task " + i));
		}
		
		taskRunner.executeTasks(tasks);
		
		// Give the tasks sufficient time to finish
		Thread.sleep(2000);
		
		for (Task task : tasks) {
			assertTrue(task.hasExecuted());
		}
	}
		
	@Test(timeout=2000)
	public void testExecutionLatch() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(10);
		List<Task> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			// Create latched tasks that countdowns the latch when it finishes
			tasks.add(new Task("Task " + i) {
				@Override
				public void run() {
					super.run();
					latch.countDown();
				}
			});
		}
		
		taskRunner.executeTasks(tasks);
		
		// wait for all tasks to finish
		latch.await();  // alternatively, latch.await(2, TimeUnit.SECONDS);
		
		for (Task task : tasks) {
			assertTrue(task.hasExecuted());
		}
	}
	
	
	class Task implements Runnable {
		private String taskId;
		private boolean executed;
		
		public Task(String taskId) {
			this.taskId = taskId;
		}
		
		@Override
		public void run() {
			System.out.println("Performed task " + taskId);
			executed = true;
		}
		
		public boolean hasExecuted() {
			return executed;
		}
	}
				
	class TaskRunner {
		ExecutorService executor = Executors.newCachedThreadPool();
	
		public void executeTasks(List<Task> tasks) {
			for (Task task : tasks) {
				executor.submit(task);
			}
		}
		
		public void tearDown() {
			executor.shutdown();
		}
	}
	
}
