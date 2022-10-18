# https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
from collections import deque
def bottomView(root):
    if (root == None):
        return
    # Initialize a variable 'hd' with 0
    # for the root element.
    hd = 0

    # Store minimum and maximum horizontal distance
    # so that we do not have to sort keys at the end
    min_hd, max_hd = 0, 0

    hd_dict = dict()

    # Queue to store tree nodes in level
    # order traversal
    q = deque()

    # Assign initialized horizontal distance
    # value to root node and add it to the queue.
    root.hd = hd
    q.append(root)

    # Loop until the queue is empty (standard
    # level order loop)
    while q:
        curr_node = q.popleft()

        # Extract the horizontal distance value
        # from the dequeued tree node.
        hd = curr_node.hd

        # Update the minimum and maximum hd
        min_hd = min(min_hd, hd)
        max_hd = max(max_hd, hd)

        # Put the dequeued tree node to dictionary
        # having key as horizontal distance. Every
        # time we find a node having same horizontal
        # distance we need to update the value in
        # the map.
        hd_dict[hd] = curr_node.data

        # If the dequeued node has a left child, add
        # it to the queue with a horizontal distance hd-1.
        if curr_node.left:
            curr_node.left.hd = hd - 1
            q.append(curr_node.left)

        # If the dequeued node has a right child, add
        # it to the queue with a horizontal distance
        # hd+1.
        if curr_node.right:
            curr_node.right.hd = hd + 1
            q.append(curr_node.right)

    # Traverse the map from least horizontal distance to
    # most horizontal distance.
    print(hd_dict)
    for i in range(min_hd, max_hd+1):
        print(hd_dict[i], end = ' ')