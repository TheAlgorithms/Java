 public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = {4, 2, 1, 5, 6, 7, 1, 4, 7};
        for(int i = 0; i< arr.length; i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }
            else{
                map.put(arr[i] , 1);
            }
        }

        for(Map.Entry<Integer,Integer> e: map.entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());

        }

    }
