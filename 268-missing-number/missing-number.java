class Solution {
    public int missingNumber(int[] nums) {
        int n=nums.length;
        int shouldSum =(int)((n*(n+1))/2);
        int actual= nums[0];
        for(int i=1;i<n;i++)
        {
            actual = actual + nums[i];
        }
        
        return (shouldSum - actual);
        
    }
}