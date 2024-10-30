import java.util.*;
public class Prims {
public static void main(String[] args) {
int cost[][] = new int[10][10];
int i, j, mincost = 0;
Scanner sc = new Scanner(System.in);
System.out.println("Enter the number of vertices: ");
int n = sc.nextInt();
System.out.println("Enter the cost matrix: ");
for (i = 1; i <= n; i++) {
for (j = 1; j <= n; j++) {
 cost[i][j] = sc.nextInt();
}
}
System.out.println("Min Tree edges are");
 mincost = pr(cost, n, mincost);
System.out.println("Minimum cost: " + mincost);
}
static int pr(int[][] cost, int n, int mincost) {
int nearV[] = new int[10], t[][] = new int[10][10], u =0, i, j, k;
for (i = 2; i <= n; i++)
 nearV[i] = 1;
 nearV[1] = 0;
for (i = 1; i < n; i++) {
int min = 999;
for (j = 1; j <= n; j++) {
if (nearV[j] != 0 && cost[j][nearV[j]] < min) {
 min = cost[j][nearV[j]];
 u = j;
}
}
 t[i][1] = u;
 t[i][2] = nearV[u];
 mincost += min;
 nearV[u] = 0;
for (k = 1; k <= n; k++) {
if (nearV[k] != 0 && cost[u][k] < cost[k]
[nearV[k]])
 nearV[k] = u;
}
System.out.println(i + ": Minimum edge is <" + t[i]
[2] + ", " + t[i][1] + ">\tCost: " + min);
}
return mincost;
}
}