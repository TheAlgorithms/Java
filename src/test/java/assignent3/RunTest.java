package assignent3;

import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

public class RunTest {
	public static void main(String args[]) {
		System.out.println("###### TEST BubbleSort ######");
		Result r = JUnitCore.runClasses(JUnitTestSort.class);
		int runCount = r.getRunCount();
		int failCount = r.getFailures().size();
		System.out.println("Run count = " + runCount);
		System.out.println("Failure count = " + failCount);
		System.out.println("===================================");
		int i = 0;
		for(Failure failure : r.getFailures()) {
			i++;
			System.out.println("Failure number " + i + " at " + failure.toString());
		}
		System.out.println("===================================");
		System.out.println("Passed: " + (runCount - failCount) + "/" + runCount);
		System.out.println("Result JUnitTestSort: " + r.wasSuccessful() + ", run times: " + r.getRunTime() + "(ms)");
		System.out.println("");
		
		
		System.out.println("###### TEST MathFloor ######");
		Result r2 = JUnitCore.runClasses(JUnitTestFloor.class);
		int runCount2 = r2.getRunCount();
		int failCount2 = r2.getFailures().size();
		System.out.println("Run count = " + runCount2);
		System.out.println("Failure count = " + failCount2);
		System.out.println("===================================");
		int i2 = 0;
		for(Failure failure : r2.getFailures()) {
			i2++;
			System.out.println("Failure number " + i2 + " at " + failure.toString());
		}
		System.out.println("===================================");
		System.out.println("Passed: " + (runCount2 - failCount2) + "/" + runCount2);
		System.out.println("Result JUnitTestFloor: " + r2.wasSuccessful() + ", run times: " + r2.getRunTime() + "(ms)");
	}
}