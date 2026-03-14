class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int i=0;
        int j=0;
        int sum=0;
        int minlength=Integer.MAX_VALUE;
       while(j<nums.length)
       {
        sum= sum+ nums[j];
        while(sum>=target)
        {
            minlength= Math.min(minlength,j-i+1);
            sum=sum-nums[i];
            i++;
        }
        j++;
        }
        if(minlength == Integer.MAX_VALUE)
        return 0;
return minlength;
    }

}