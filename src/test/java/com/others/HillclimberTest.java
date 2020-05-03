import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class HillclimberTest {

    private final int[][] map1 = {{1,1,1,1},
                                 {1,2,2,2},
                                 {1,2,3,3},
                                 {1,2,3,4}};

    private final int[][] map2 = {{1,4,3,5,9},
                                  {3,1,2,3,8},
                                  {3,4,2,1,2},
                                  {3,4,5,7,8}};

    /**
     * Tests if the climber stays on the same stop, if he already has reached the local maximum.
     */
    @Test
    public void testStartOnHighestPoint(){
        Vector2D playerPos = new Vector2D(3,3);
        Hillclimber hc = new Hillclimber(playerPos, map1);

        Assertions.assertEquals(playerPos,hc.getCurrPos());
        Assertions.assertEquals(0,hc.getStepCount());
    }

    /**
     * Tests if the climber finds it way to the local maximum.
     */
    @Test
    public void testStartOnLowest(){
        Vector2D playerPos = new Vector2D(0,0);
        Hillclimber hc = new Hillclimber(playerPos, map1);

        Assertions.assertEquals(new Vector2D(3,3),hc.getCurrPos());
        Assertions.assertEquals(6,hc.getStepCount());
    }

    /**
     * Tests if the climber finds the local maximum, but not the global maximum.
     */
    @Test
    public void testFindLocalMaxButNotGlobalMax(){
        Vector2D playerPos = new Vector2D(0,3);
        Hillclimber hc = new Hillclimber(playerPos,map2);

        Assertions.assertEquals(new Vector2D(4,3),hc.getCurrPos());
        Assertions.assertEquals(4,hc.getStepCount());
    }
}
