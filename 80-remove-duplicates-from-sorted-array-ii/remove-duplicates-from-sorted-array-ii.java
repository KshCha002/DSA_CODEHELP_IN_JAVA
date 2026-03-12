class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i=2;
        int j=2;
        while(j<n)
        {
            if(nums[i-2]==nums[j])
            {
                j++;
            }else
            {
                nums[i]=nums[j];
                i++;
                j++;
            }
        }
        return i;
    }
}