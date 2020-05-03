/**
 * This class implements a hillclimber algorithm
 * that finds the local maximum on a 3-dimensional map
 * with a given start position.
 */
public class Hillclimber {

    private Vector2D currPos;
    private int stepCount;
    private int[][] map;

    /**
     * This constructor initializes the startposition
     * of the climber, the map that he is on and starts
     * the hillclimbing algorithm.
     *
     * @param startPos Position that the climber starts on.
     * @param map The map the climber is on. Needs to be rectangular.
     * @throws IllegalArgumentException when the startposition is not on the map.
     */
    public Hillclimber(Vector2D startPos,int[][] map){

        if(startPos.getX() < 0
                || startPos.getX() >= map[startPos.getY()].length
                || startPos.getY() < 0
                || startPos.getY() >= map.length){
            throw new IllegalArgumentException("Startposition is not on the map!");
        }

        currPos = startPos;
        this.map = map;
        stepCount = 0;
        hillclimb();
    }

    /**
     * Utility function for printing out the map.
     * The climber is represented as an X.
     */
    private void printMap(){
        StringBuilder strb = new StringBuilder();
        strb.append("Current value: " + map[currPos.getY()][currPos.getX()] + "\n");
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length;j++){
                if(i==currPos.getY() && j==currPos.getX()){
                    strb.append("X   ");
                } else {
                    strb.append(map[i][j] + "   ");
                }
            }
            strb.append("\n\n");
        }
        strb.append("\n\n\n");
        System.out.println(strb.toString());
    }

    /**
     * This function implements the hillclimber-algorithm recursively.
     * The variable dir represents the direction the climber is going to climb.
     * If neither up, down, left or right have a greater value than the
     * currPos on the map, then the climber has found the local maximum
     * and the algorithm stops.
     */
    private void hillclimb(){
        printMap();

        Vector2D dir    = currPos;
        Vector2D up     = new Vector2D(currPos.getX(),currPos.getY() - 1);
        Vector2D down   = new Vector2D(currPos.getX(),currPos.getY() + 1);
        Vector2D left   = new Vector2D(currPos.getX() - 1, currPos.getY());
        Vector2D right  = new Vector2D(currPos.getX() + 1, currPos.getY());

        if(up.getY() >= 0 && map[up.getY()][up.getX()] >= map[dir.getY()][dir.getX()]){
            dir = up;
        }
        if(down.getY() < map.length && map[down.getY()][down.getX()] >= map[dir.getY()][dir.getX()]){
            dir = down;
        }
        if(left.getX() >= 0 && map[left.getY()][left.getX()] >= map[dir.getY()][dir.getX()]){
            dir = left;
        }
        if(right.getX() < map[right.getY()].length && map[right.getY()][right.getX()] >= map[dir.getY()][dir.getX()]){
            dir = right;
        }

        if(!dir.equals(currPos)){
            stepCount++;
            currPos = dir;
            hillclimb();
        }

    }

    /**
     * Utility function for accessing the amount of steps the hillclimber had to take,
     * to reach the local maximum.
     * @return amount of steps the climber took
     */
    public int getStepCount() {
        return stepCount;
    }

    /**
     * Utility function for accessing the position of the climber after he is done,
     * which is the local maximum.
     * @return the local maximum
     */
    public Vector2D getCurrPos() {
        return currPos;
    }
}

/**
 * Utility class for easier handling of 2D coordinates.
 */
class Vector2D{
    private int x,y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return x == vector2D.x &&
                y == vector2D.y;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
