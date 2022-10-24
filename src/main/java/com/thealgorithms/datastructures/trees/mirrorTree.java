public static void mirror(Node node){
    
    for(Node child: node.children){
      mirror(child);
    }
    

    int li = 0;
    int ri = node.children.size() - 1;

    while(li < ri){
      Node left = node.children.get(li);
      Node right = node.children.get(ri);

      node.children.set(li, right);
      node.children.set(ri, left);

      li++;
      ri--;
    }

    //Below statement is doing the same work as above
    
    Collections.reverse(node.children); //this will reverse order of ArrayList , Array or Linked List

  }