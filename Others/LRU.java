class LRU
{
    // Method to find page faults using indexes
    static int pageFaults(int pages[], int capacity)
    {
        int faults = 0;

        //This variable is used to show the current time. First process is put into the memory in time=1,
        //second process in time=2 and so on...
        int time = 0;

        //Initializing the array that imitates the memory. It is used to show which processes are in the memory
        //This is a 2d array with (capacity X 2) dimensions. In each row first element is the index of the process
        //and the second element is the time it
        int memory[][] = new int[capacity][2];
        for (int i=0; i<capacity; i++)
            for (int j=0; j<2; j++)
                memory[i][j] = -1;

        //These two variables are used to show the index and the time of the LRU process in the memory.
        int LRUIndex;
        int LRUTime;

        //variable used to show if a process is already in memory or instead we have a page fault
        boolean flag;

        for (int i=0; i<pages.length; i++){
            //checking if the current process is already in the memory
            flag = false;
            for (int j=0; j<capacity && !flag; j++) {
                if (memory[j][0] == pages[i]) {
                    memory[j][1] = time++;
                    flag = true;
                }
            }

            if (!flag){
                //if not, the number of faults is increased
                faults++;

                //checking which is the LRU process and then replacing it with the current process
                LRUIndex = 0;
                LRUTime = memory[0][1];
                for (int j=1; j<capacity; j++){
                    if (memory[j][1] < LRUTime){
                        LRUTime = memory[j][1];
                        LRUIndex = j;
                    }
                }
                memory[LRUIndex][0] = pages[i];
                memory[LRUIndex][1] = time++;
            }

        }

        //returning the number of page faults
        return faults;
    }

    // Driver Method to test your algorithm with a simple example
    public static void main(String args[])
    {
        /*
         * This is an array that holds the reference string for all
         * page requests.
         */
        int pages[] = {5, 1, 0, 3, 2, 3, 0, 4, 2, 3, 0, 3, 5, 2};

        // This is the number of available page frames
        int memoryCapacity = 3;

        int faults = pageFaults(pages, memoryCapacity);
        System.out.println(faults);
    }
} 