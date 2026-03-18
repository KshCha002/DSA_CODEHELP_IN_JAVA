class Solution {
    public boolean containsDuplicate(int[] nums) {
        int i=0;
        int j=i+1;
        Arrays.sort(nums);
        int n=nums.length;
        while(j<n)
        {
            if(nums[i]==nums[j]){return true;}
        i++;
        j++;
        }
        return false;
    } 
    }

