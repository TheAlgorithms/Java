public class BiparteGraph {
    
    public boolean isBiparte(Graph<Integer> graph){
        
        Set<Vertex<Integer>> redSet = new HashSet<Vertex<Integer>>();
        Set<Vertex<Integer>> blueSet = new HashSet<Vertex<Integer>>();
        
        Queue<Vertex<Integer>> queue = new LinkedList<Vertex<Integer>>();
        
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            if(!redSet.contains(vertex) && !blueSet.contains(vertex)){
                queue.add(vertex);
                redSet.add(vertex);
                while(!queue.isEmpty()){
                    vertex = queue.poll();
                    for(Vertex<Integer> v : vertex.getAdjacentVertexes()){
                        if(redSet.contains(vertex)){
                            if(redSet.contains(v)){
                                return false;
                            }if(blueSet.contains(v)){
                                continue;
                            }
                            blueSet.add(v);
                        }
                        else if(blueSet.contains(vertex)){
                            if(blueSet.contains(v)){
                                return false;
                            }if(redSet.contains(v)){
                                continue;
                            }
                            redSet.add(v);
                        }
                        queue.add(v);
                    }
                }
            }
        }
        System.out.println(redSet);
        System.out.println(blueSet);
        return true;
    }

    public boolean isBiparteDFS(Graph<Integer> graph){
        Set<Vertex<Integer>> redSet = new HashSet<Vertex<Integer>>();
        Set<Vertex<Integer>> blueSet = new HashSet<Vertex<Integer>>();
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            if(redSet.contains(vertex) || blueSet.contains(vertex)){
                continue;
            }
            boolean flag = isBiparteDFS(vertex, redSet, blueSet, true);
            if(!flag){
                return false;
            }
        }
        return true;
    }
    
    private boolean isBiparteDFS(Vertex<Integer> vertex, Set<Vertex<Integer>> redSet, Set<Vertex<Integer>> blueSet,boolean wasRed){
    
        if(wasRed){
            if(redSet.contains(vertex)){
                return false;
            }
            else if(blueSet.contains(vertex)){
                return true;
            }
            blueSet.add(vertex);
        }

        else if(!wasRed){
            if(blueSet.contains(vertex)){
                return false;
            }
            if(redSet.contains(vertex)){
                return true;
            }
            redSet.add(vertex);
        }
        
        for(Vertex<Integer> adj : vertex.getAdjacentVertexes()){
            boolean flag = isBiparteDFS(adj, redSet, blueSet, !wasRed);
            if(!flag){
                return false;
            }
        }
        return true;
        
    }
    
    public static void main(String argsp[]){
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(7, 9);
        graph.addEdge(7, 8);
        BiparteGraph bi = new BiparteGraph();
        boolean result = bi.isBiparteDFS(graph);
        System.out.print(result);
    }
}
