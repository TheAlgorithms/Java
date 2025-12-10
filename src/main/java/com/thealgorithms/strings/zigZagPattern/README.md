# About

convert string into a zig-zagged string

for example :

    string = "123456789" , numRows = 3
    ans = "159246837"
    explanation
        1   5   9
        2 4 6 8
        3   7

    string = "HelloWorldKotlin" , k = 4
    ans = "HoteWrollolKildn"
    explanation
        H     o     t
        e   W r   o l
        l o   l K   i
        l     d     n

# working

    if string size is smaller than numRows or numRows is smaller than 2
    than we can return string because it will make no changes to string.
    If not than
    we initiate three variable depth which is equalvalent to numRows ,
    height which starts with 1 and start with starting index of string.
    than we generate spaces to skip using formula 2 + (( n - 1 ) * 2 )
    for both height and depth
    with each iteration we decrement depth and increate height and start 
    by one also we keep contantating character on to new string with first
    depth spaces and later height spaces that we generated using formula
    if not zero

# beats 80% of submission on leetcode