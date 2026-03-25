class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int l=0;
        int r=0;
        int n =nums.length;
        long sum =0;
        long maxSum=sum;
        Set<Integer> s= new HashSet<>();
        while(r<n)
        {
            while(s.contains(nums[r]))
            {
                 sum -= nums[l];
                s.remove(nums[l]);
                l++;
            }

            if(r-l+1>k)
            {
                sum -= nums[l];
                s.remove(nums[l]);
                l++; 
            }


            sum=sum+nums[r];
            s.add(nums[r]);
           
            if(r-l+1==k)
            {
                maxSum=Math.max(maxSum,sum);
            }
             r++;

        }

        return maxSum;
    }
}