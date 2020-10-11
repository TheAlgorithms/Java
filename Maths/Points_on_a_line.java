public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        /* 
        * Sets can only contain unique objects
        * by placing each int into a set, we can 
        * later check if the set size is larger than 1
        * if so, there is more than one unique position
        */
        //create x and y sets
        Set<Integer> setX = new HashSet<Integer>();
        Set<Integer> setY = new HashSet<Integer>();
        //for each line, add x and y to respective sets
        for (int i = 0; i < n; i ++) {
            setX.add(in.nextInt());
            setY.add(in.nextInt());
        }
        if (setX.size() == 1) {
            System.out.println("YES");
        }
        else if (setY.size() == 1 ) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
    }
