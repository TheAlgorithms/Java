package bubblesort;

public class BubbleSort {

public static void main(String[] args) {
int list[] = {1, 8, 4, 6, 0, 3, 5, 2, 7, 9};
int tmp;
int i, j;
boolean swapped;
int loop = 1;
for (i = 0; i < list.length; i++) {
System.out.print(list[i] + " ");
}
System.out.println("\n");

for (i = list.length - 1; i > 0; i--) {
swapped = false;
System.out.println("iteration" + (loop++));
for (int k = 0; k < list.length; k++) {
System.out.print(list[k] + " ");
}
System.out.println("\n");
for (j = 0; j < list.length - 1; j++) {
if (list[j] < list[j + 1]) {
System.out.println("[" + list[j] + "," + list[j + 1] + "]" + "=" + "not swapped");
}
if (list[j] > list[j + 1]) {
swapped = true;
tmp = list[j];
list[j] = list[j + 1];
list[j + 1] = tmp;
System.out.println("[" + list[j + 1] + "," + list[j] + "]" + "=" + "swapped" + "[" + list[j] + "," + list[j + 1] + "]");

}
}
System.out.println("\n");
if (!swapped) {
//return;
break;

}
return;
}
System.out.print("Sorted array : ");
for (i = 0; i < list.length; i++) {
System.out.print(list[i] + " ");

}
}
}
