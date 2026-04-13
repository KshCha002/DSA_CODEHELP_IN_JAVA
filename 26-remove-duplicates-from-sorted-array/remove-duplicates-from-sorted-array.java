class Solution {
    public int removeDuplicates(int[] nums) {
        int i=1;int j=1;
        while(j<nums.length){
            if(nums[i-1]==nums[j]){
            j++;
            }
            else{
                swap(nums,i,j);
                i++;
                j++;
            }
        }

        return i;
        
    }

    public void swap(int [] nums, int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}