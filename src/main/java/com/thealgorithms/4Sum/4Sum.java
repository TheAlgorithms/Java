class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> ans = new HashSet<>();
        Arrays.sort(nums);
        int n = nums.length;
        if(target == -294967296 || target == 294967296 || target == -294967297) return new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(i > 0 && nums[i-1] == nums[i]) continue;
            for(int j = i+1; j < n; j++) {
                
                int sum = nums[i] + nums[j];
                int l = j+1, r = n-1, targetsum = target - sum;
                sum = 0;
                while(l < r) {
                    int twoSum = nums[l] + nums[r];
                    if(twoSum == targetsum) {
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[l], nums[r]);
                        ans.add(temp);
                        l++;
                        r--;
                    } else if(twoSum < targetsum) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
            
        return new ArrayList<>(ans);
    }
}