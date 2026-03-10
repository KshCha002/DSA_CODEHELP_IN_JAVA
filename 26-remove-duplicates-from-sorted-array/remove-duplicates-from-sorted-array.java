class Solution {
    public int removeDuplicates(int[] nums) {
        Set<Integer> m = new TreeSet<>();
        int i = 0;
        while (i < nums.length) {
            m.add(nums[i]);
            i++;
        }
        // int n= m.keySet().size();
        int j = 0;
        for (int n : m) {
            nums[j] = n;
            j++;
        }
        return j;
    }
}