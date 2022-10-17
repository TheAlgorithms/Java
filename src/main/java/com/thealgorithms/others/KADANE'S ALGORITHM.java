long maxSubarraySum(int arr[], int n) 
    {
        long maxsum=Integer.MIN_VALUE;//stores maximum sum of contigous subarray found so far
        long max_endingsum=0; //stores maximum sum of of contigous subarray till the current index.
        for(int i=0;i<n;i++)
        {
            max_endingsum+=arr[i];
            if(max_endingsum>maxsum)
                maxsum=max_endingsum;
            if(max_endingsum<0)
                max_endingsum=0;
        }
        return maxsum;
    }
//to know more about Kadane's Algorithm follow the following link: https://en.wikipedia.org/wiki/Maximum_subarray_problem
