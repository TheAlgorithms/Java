class FIFO
{
    // Method to find page faults using indexes
    static int pageFaults(int pages[], int capacity)
    {
        int faults = 0;
        int counter = 0;

        //Initializing the array that imitates the memory. It is used to show which processes are in the memory
        int memory[] = new int[capacity];
        for (int i=0; i<capacity; i++)
                memory[i] = -1;

        //variable used to show if a process is already in memory or instead we have a page fault
        boolean flag;

        for (int i=0; i<pages.length; i++) {
            //checking if the current process is already in the memory
            flag = false;
            for (int j = 0; j < capacity && !flag; j++)
                if (memory[j] == pages[i])
                    flag = true;

            if (!flag){
                //if not, the number of faults is increased
                faults++;
                /*
                    Use of variable counter : counter is initially 0. Let's suppose capacity = 3.
                    Initially the memory is empty. First process goes to frame 1, second to frame 2 and third to frame 3.
                    Then fourth process to frame 1 again, the fifth to frame 2 and so on. So that's why we increase counter
                    by 1 and then MOD  with capacity. Also, frame 1 = memory[0], frame 2 = memory[1], frame 3 = memory[2].
                    That's why after the MOD operation the value of the counter is in the array boundaries.
                */
                memory[counter] = pages[i];
                counter = (counter + 1) % capacity;
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