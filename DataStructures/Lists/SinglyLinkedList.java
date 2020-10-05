#include <stdio.h>
#include<stdlib.h>
typedef struct node
{
    int data;
    struct node* next;
}NODE;
NODE* st=NULL;
//Function to create new node
NODE* createNode(int d)
{
    NODE *a;
    a=(NODE*)malloc(sizeof(NODE));
    a->data=d;
    a->next=NULL;
    return a;
}

//Function to retrive last node address
NODE *getLastNode()
{
    NODE *a=st;
    while(a->next!=NULL)
        a=a->next;
    return a;
}

//Function to display Linked List
void display()
{
    NODE *a=st;
    if(st==NULL)
    {
        printf("Empty List\n");
        return;
    }
    printf("List: \n");
    while(a!=NULL)
    {
        printf("%5d",a->data);
        a=a->next;
    }
}

//Function to Count Nodes from list
int count()
{
    int c=0;
    NODE *a=st;
    while(a!=NULL)
    {
        c++;
        a=a->next;
    }
    return c;
}

//Function to compute data sum of list
int sum()
{
    NODE *a=st;
    int tot=0;
    while(a!=NULL)
    {
        tot+=a->data;
        a=a->next;
    }
    return tot;
}

//Function to add new node at end
void addEnd(int d)
{
    NODE *a,*b;
    a=createNode(d);
    if(st==NULL){
        st=a;
        return;
    }
    b=getLastNode();
    b->next=a;
}

//Function to addd new node at begin
void addBeg(int d)
{
    NODE *a;
    a=createNode(d);
    a->next=st;
    st=a;
}

//Function to  find maximum data from list

int getMax()
{
    //Considering list is not empty
    NODE *a=st;
    int mx=a->data;
    a=a->next;
    while(a!=NULL)
    {
        if(a->data>mx)
            mx=a->data;
        a=a->next;
    }
    return mx;
}

//Function to get Minimum in list
int getMin()
{
    //Considering list is not empty
    NODE *a=st;
    int mn=a->data;
    a=a->next;
    while(a!=NULL)
    {
        if(a->data<mn)
            mn=a->data;
        a=a->next;
    }
    return mn;
}

int main()
{
    int opt,d,res;
    while(1)
    {
        printf("\nSingly Linked List\n");
        printf("Menu:\n1.Add at End\n2.Add at Begin\n3.Display\n4.Count Nodes\n5.Sum\n6.Get Minimum\n7.Get Maximum\n8.Exit\nOption : ");
        scanf("%d",&opt);
        if(opt>7)
            break;
        switch (opt) {
            case 1:
                printf("\nEnter Data at End : ");
                scanf("%d",&d);
                addEnd(d);
                break;
            case 2:
                printf("\nEnter Data at Begin: ");
                scanf("%d",&d);
                addBeg(d);
                break;
            case 3:
                display();
                break;
            case 4:
                res=count();
                printf("\nNodes : %d",res);
                break;
            case 5:
                res=sum();
                printf("\nSum : %d",res);
                break;
            case 6:
                printf("\nMimimum Data value : %d",getMin());
                break;
            case 7:
                printf("\nMaximum Data Value : %d",getMax());

        }
    }
    return 0;
}
