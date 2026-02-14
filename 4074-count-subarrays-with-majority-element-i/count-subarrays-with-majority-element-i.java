class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int valsubarr=0;
        for(int i=0;i<(nums.length);i++)
        {
            int c=0;
            int j=i;
            for (;j<(nums.length);j++)
            {
                if(nums[j]==target)
                {
                    c++;
                }
                if((j-i+1)/2<c)
            {
             valsubarr++;   
            }
            }
            

        }  
        return valsubarr;    
    }
}