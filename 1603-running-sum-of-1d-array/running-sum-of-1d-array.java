class Solution {
    public int[] runningSum(int[] nums) {
       
       int []ans=new int[nums.length];
       int sum=nums[0];
       ans[0]=sum;
      if( nums.length==1)
      {return ans;}
       for(int i=1;i<nums.length;i++){
        ans[i]=sum+nums[i];
        sum=ans[i];
    }
    return ans;
    }
}