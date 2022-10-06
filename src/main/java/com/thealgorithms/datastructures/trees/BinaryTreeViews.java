package com.thealgorithms.datastructures.trees;

import java.util.*;
import java.io.*;

class BinaryTreeViews 
{
    // Class to define a node of a tree
    class Node 
    {
        int data;
        Node left, right;

        public Node(int val) 
        {
            data = val;
            left = null;
            right = null;
        }
    }

    //  Utility class to define a pair
    class Pair
    {
        Node node;
        int dist;
        Pair(Node n, int d)
        {
            node = n;
            dist = d;
        }
    }
    
    // Converts a user input to a binary tree
    Node buildTree(String s)
    {
        Node null_node = null;
        if(s.length() == 0 || s.charAt(0) == 'N')
            return null_node;

        String input[] = s.split((" "));
        Node root = new Node(Integer.parseInt(input[0]));

        Queue <Node> q = new LinkedList<Node>();
        q.add(root);

        int i=1;
        while(q.size() > 0 && i < input.length)
        {
            Node curr = q.remove();
            String val = input[i];

            if(val.equals("N") != true)
            {
                curr.left = new Node(Integer.parseInt(val));
                q.add(curr.left);
            }

            i++;
            if(i >= input.length)
                break;

            val = input[i];
            if(val.equals("N") != true)
            {
                curr.right = new Node(Integer.parseInt(val));
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    ArrayList<Integer> getLeftView(Node root)
    {
        ArrayList <Integer> left = new ArrayList <>();
        Queue <Node> q = new LinkedList<>();
        Node curr;
        
        if(root == null)
            return left;
        
        q.add(root);
        while(q.isEmpty() != true)
        {
            int count = q.size();

            for(int i = 0; i < count; i++)
            {
                curr = q.remove();
                if(i == 0)
                    left.add(curr.data);
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
        }
        return left;
    }

    ArrayList<Integer> getRightView(Node root)
    {
        ArrayList <Integer> right = new ArrayList <>();
        Queue <Node> q = new LinkedList<>();
        Node curr;

        if(root == null)
            return right;

        q.add(root);
        while(q.isEmpty() != true)
        {
            int count = q.size();

            for(int i = 0; i < count; i++)
            {
                curr = q.remove();
                if(i == count-1)
                    right.add(curr.data);
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
        }
        return right;
    }

    ArrayList<Integer> getTopView(Node root)
    {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(root, 0));
        while(q.isEmpty() != true)
        {
            Pair p = q.remove();
            Node temp = p.node;
            int h_dist = p.dist;

            if(tm.containsKey(h_dist) == false)
                tm.put(h_dist, temp.data);
            if(temp.left != null)
                q.add(new Pair(temp.left, h_dist-1));
            if(temp.right != null)
                q.add(new Pair(temp.right, h_dist+1));
        }

        ArrayList<Integer> top = new ArrayList<>();
        for(Map.Entry<Integer, Integer> e: tm.entrySet())
            top.add(e.getValue());

        return top;
    }

    ArrayList <Integer> getBottomView(Node root)
    {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(root, 0));
        while(q.isEmpty() != true)
        {
            Pair p = q.remove();
            Node temp = p.node;
            int h_dist = p.dist;

            tm.put(h_dist, temp.data);
            if(temp.left != null)
                q.add(new Pair(temp.left, h_dist-1));
            if(temp.right != null)
                q.add(new Pair(temp.right, h_dist+1));
        }

        ArrayList<Integer> bottom = new ArrayList<>();
        for(Map.Entry<Integer, Integer> e: tm.entrySet())
            bottom.add(e.getValue());
        
        return bottom;
    }
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine()); //Sample inputs to be given
        BinaryTreeViews btv = new BinaryTreeViews();
        while(cases > 0)
        {
            String s = br.readLine();
            Node root = btv.buildTree(s);
            System.out.println(btv.getLeftView(root).toString());
            System.out.println(btv.getRightView(root).toString());
            System.out.println(btv.getTopView(root).toString());
            System.out.println(btv.getBottomView(root).toString());
            cases--;
        }
    }
}
