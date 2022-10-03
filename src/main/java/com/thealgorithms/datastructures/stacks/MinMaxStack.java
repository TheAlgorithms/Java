package com.thealgorithms.datastructures.stacks;
import java.util.Stack;

public class MinMaxStack {

    public static void main(String[] args) {

        MinMaxStack st = new MinMaxStack();

        st.push(3);
        st.push(1);
        st.push(4);

        System.out.println("The maximum element of stack is: "+st.getMax());
        System.out.println("The minimum element of stack is: "+st.getMin());
        st.pop();
        System.out.println("The maximum element of stack is: "+st.getMax());
    }


    Stack<StackNode> st;

    public MinMaxStack() {
        st = new Stack<>();
    }

    public void push(int x) {
        System.out.println("Pushed "+x+ " into the min-max stack");
        StackNode node = new StackNode(x);
        if(!st.isEmpty()) {
            node.max = Math.max(node.max, st.peek().max);
            node.min = Math.min(node.min, st.peek().min);
        }
        st.push(node);
    }

    public int pop() {
        int poppedValue = st.pop().val;
        System.out.println("Popped "+ poppedValue+" from the min-max stack");
        return st.pop().val;
    }

    public int getMax() {
        return st.peek().max;
    }

    public int getMin() {
        return st.peek().min;
    }
}

class StackNode {
    int val;
    int min;
    int max;

    StackNode(int val) {
        this.val = val;
        max = val;
        min = val;
    }
}
