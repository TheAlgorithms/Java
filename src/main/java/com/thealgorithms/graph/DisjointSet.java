package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * Disjoint Set (Union-Find) Data Structure
 * -----------------------------------------
 * This class implements the Disjoint Set Union (DSU) or Union-Find structure,
 * which efficiently supports two main operations:
 * 
 * 1. findLeader(x) – Find the representative (leader) of the set that contains element x.
 * 2. merge(x, y) – Merge (union) the sets that contain x and y.
 * 
 * Time Complexity (amortized): O(α(N)), where α(N) is the inverse Ackermann function,
 * which is very close to constant for all practical input sizes.
 */

public class DisjointSet {

    int[] parent;  // parent[i] stores the parent (or leader) of node i
    int[] rank;    // rank[i] stores the approximate depth of the tree rooted at i
    
    /**
     * Constructor: Initializes N disjoint sets (each element is its own leader).
     *
     * @param n the number of elements
     */
    public DisjointSet(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * Finds the leader (representative) of the set that contains x.
     * Uses Path Compression to speed up future lookups.
     *
     * @param x the element whose leader is to be found
     * @return the leader of the set containing x
     */

    int findLeader(int x){
        // If x is its own parent, it is the leader of its set
        if(parent[x]==x){
            return parent[x];
        }

        // Recursively find the leader and compress the path
        int leader = findLeader(parent[x]);
        parent[x] = leader; // Path compression: directly connect x to its leader
        return leader;
    }

    /**
     * Checks if two elements belong to the same set.
     *
     * @param x first element
     * @param y second element
     * @return true if x and y belong to the same set; false otherwise
     */

    boolean isSame(int x, int y){
        return findLeader(x)==findLeader(y);
    }

    /**
     * Merges (unions) the sets containing x and y.
     * Uses Union by Rank to attach the smaller tree to the root of the larger one.
     *
     * @param x first element
     * @param y second element
     */

    void merge(int x, int y){
        int xLeader = findLeader(x);
        int yLeader = findLeader(y);
        // If they already belong to the same set, no action is needed
        if(xLeader != yLeader){
            // Attach smaller rank tree under larger rank tree
            if(rank[xLeader]<rank[yLeader]){
                parent[xLeader] = yLeader;
            }else{
                parent[yLeader] = xLeader;

                // If ranks are equal, increment the rank of the new root
                if(rank[xLeader] == rank[yLeader]){
                    rank[xLeader] += 1;
                }
            }
        }
    }

    /**
     * this is the code to merge email accounts
     * if two accounts share at least one email, they belong to same person.
     * @param accountList
     * @return
     */ 
    public List<List<String>> accountsMerge(List<List<String>> accountList) {
        int accountListSize = accountList.size();
        DisjointSet dsu = new DisjointSet(accountListSize);
        
        // Maps email to their component index
        Map<String, Integer> emailGroup = new HashMap<>();
        
        for (int i = 0; i < accountListSize; i++) {
            int accountSize = accountList.get(i).size();
            
            for (int j = 1; j < accountSize; j++) {
                String email = accountList.get(i).get(j);
                // String accountName = accountList.get(i).get(0);
                
                // If this is the first time seeing this email then
                // assign component group as the account index
                if (!emailGroup.containsKey(email)) {
                    emailGroup.put(email, i);
                } else {
                    // If we have seen this email before then union this
                    // group with the previous group of the email
                    dsu.merge(i, emailGroup.get(email));
                }
            }
        }
        
        // Store emails corresponding to the component's representative
        Map<Integer, List<String>> components = new HashMap<Integer, List<String>>();
        for (String email : emailGroup.keySet()) {
            int group = emailGroup.get(email);
            int groupRep = dsu.findLeader(group);
            
            if (!components.containsKey(groupRep)) {
                components.put(groupRep, new ArrayList<String>());
            }
            
            components.get(groupRep).add(email);
        }
        
        // Sort the components and add the account name
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (int group : components.keySet()) {
            List<String> component = components.get(group);
            Collections.sort(component); 
            component.add(0, accountList.get(group).get(0));
            mergedAccounts.add(component);
        }
        
        return mergedAccounts;
    }

}
