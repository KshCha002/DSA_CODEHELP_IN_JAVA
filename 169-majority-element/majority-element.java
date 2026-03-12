class Solution {
    public int majorityElement(int[] nums) {
        int count =1;
        
        int maj =nums[0];
        int n=nums.length;
        for(int i=1;i<n;i++)
        {
            if(count==0){
                maj=nums[i];
            }
            count=count+(nums[i]==maj?1:-1);
        }
        return maj;
    }
}