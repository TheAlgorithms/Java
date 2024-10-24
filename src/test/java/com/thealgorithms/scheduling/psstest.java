import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ProportionalShareSchedulingTest {

    private List<ProportionalShareScheduling.Process> processList;

    @BeforeEach
    public void setUp() {
        // Initialize the list of processes for testing
        processList = new ArrayList<>();
        processList.add(new ProportionalShareScheduling.Process("P1", 5, 10, 0));
        processList.add(new ProportionalShareScheduling.Process("P2", 3, 8, 1));
        processList.add(new ProportionalShareScheduling.Process("P3", 2, 12, 2));
    }

    @Test
    public void testProportionalShareScheduling() {
        // Create an instance of ProportionalShareScheduling
        ProportionalShareScheduling scheduler = new ProportionalShareScheduling(processList);

        // Call the scheduling method and check the returned processes
        List<ProportionalShareScheduling.Process> executedProcesses = scheduler.scheduleProcesses(20);

        // Assert that all processes are executed
        assertEquals(3, executedProcesses.size(), "All processes should be executed");

        // Verify each process received CPU time as expected
        ProportionalShareScheduling.Process p1 = executedProcesses.get(0);
        assertEquals("P1", p1.getProcessId(), "Process P1 should execute first");
        assertEquals(10, p1.getTimeReceived(), "Process P1 should receive 10 units of time");

        ProportionalShareScheduling.Process p2 = executedProcesses.get(1);
        assertEquals("P2", p2.getProcessId(), "Process P2 should execute next");
        assertEquals(8, p2.getTimeReceived(), "Process P2 should receive 8 units of time");

        ProportionalShareScheduling.Process p3 = executedProcesses.get(2);
        assertEquals("P3", p3.getProcessId(), "Process P3 should execute last");
        assertEquals(12, p3.getTimeReceived(), "Process P3 should receive 12 units of time");
    }

    @Test
    public void testStarvationPrevention() {
        // Create a process list with one high-weight and one low-weight process
        processList = new ArrayList<>();
        processList.add(new ProportionalShareScheduling.Process("P1", 10, 10, 0)); // High-weight process
        processList.add(new ProportionalShareScheduling.Process("P2", 1, 5, 1));   // Low-weight process

        // Create an instance of ProportionalShareScheduling
        ProportionalShareScheduling scheduler = new ProportionalShareScheduling(processList);

        // Schedule processes
        List<ProportionalShareScheduling.Process> executedProcesses = scheduler.scheduleProcesses(30);

        // Assert that both processes have been executed and starvation has been prevented
        ProportionalShareScheduling.Process p1 = executedProcesses.get(0);
        ProportionalShareScheduling.Process p2 = executedProcesses.get(1);

        assertTrue(p2.getPriorityBoost() > 0, "Starvation prevention should increase priority boost for low-weight process");
        assertEquals(5, p2.getTimeReceived(), "Low-weight process P2 should receive 5 units of time");
        assertEquals(10, p1.getTimeReceived(), "High-weight process P1 should receive its full burst time");
    }
}
