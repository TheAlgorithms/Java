import java.util.*;
/**
 * @author Furaha Damien
 */

class KClosestPointsToOrigin{
    
    //Helper inner class
    public class Point{
        int x;
        int y;
        int distance;
        
        public Point(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    
    public int[][] kClosest(int[][] points, int K) {
        
        PriorityQueue<Point> que = new PriorityQueue<Point>((a, b)->(a.distance - b.distance));
        int [][] res = new int[K][2];
        
        for(int [] temp : points){
            int dist = (temp[0]*temp[0] + temp[1]*temp[1]);
            que.offer(new Point(temp[0], temp[1], dist));
        }
        for(int i = 0; i < K; i++){
            Point curr = que.poll();
            res[i][0] = curr.x;
            res[i][1] = curr.y;
            
        }
        return res;
        
    }
}