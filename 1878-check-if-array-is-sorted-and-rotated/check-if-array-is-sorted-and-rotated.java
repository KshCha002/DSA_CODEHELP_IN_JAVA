class Solution {
   public boolean check(int[] nums) {
        int x = 0;
    
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                break;
            }
            x++;
        }
        if ((x + 1) == nums.length) {
            return true;
        }
        int[] temp = new int[nums.length];
    
        for (int i = x+1 ; i < nums.length-1 ; i++) {
            if (nums[i] > nums[i +1]) {
                return false;
            }
        
        }
        for (int i = x+1 ; i < nums.length ; i++) {
            temp[i - x -1] = nums[i];
        }
        
        for (int i = 0; i < x+1; i++) {
            temp[nums.length-x-1+i] = nums[i];
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (temp[i] > temp[i + 1]) {
                return false;
            }
        }
    
        return true;
    }
}