import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_DFS {
  private static int count = 0;

  private void bfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Integer> distance, char start) {
    Queue<Character> queue = new LinkedList<Character>();
    queue.add(start);
    distance.put(start, 0);
    int i = 0;
    while (!queue.isEmpty()) {
      char head = queue.poll();
      i++;
      System.out.println("The " + i + "th element:" + head);
      int d = distance.get(head) + 1;
      for (Character c : graph.get(head)) {
        if (!distance.containsKey(c)) {
          distance.put(c, d);
          queue.add(c);
        }
      }
    }
  }

  private static void dfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited) {
    visit(graph, visited, 's');
  }

  private static void visit(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited,
      char start) {
    if (!visited.containsKey(start)) {
      count++;
      System.out.println("The time into element " + start + ": " + count);
      visited.put(start, true);
      for (Character c : graph.get(start)) {
        if (!visited.containsKey(c)) {
          visit(graph, visited, c);
        }
      }
      count++;
      System.out.println("The time out element " + start + ": " + count);
    }
  }

}
