class Solution {
    public boolean find132pattern(int[] nums) {
       int third=Integer.MIN_VALUE;
          Stack<Integer> stack = new Stack<>();
        

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < third) {
                return true; 
            }
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                third = stack.pop(); // nums[k] candidate
            }
            stack.push(nums[i]); // nums[j] candidate
        }
        
        return false;
    }
}