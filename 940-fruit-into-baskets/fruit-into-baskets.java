class Solution {
    public int totalFruit(int[] s) {
        int k=2;
        // Edge case
        if (k == 0 || s.length == 0) return 0;

        // Frequency map to track characters
        Map<Integer, Integer> freq = new HashMap<>();

        // Initialize sliding window pointers
        int left = 0;
        int maxLen = 0;

        // Loop through string
        for (int right = 0; right < s.length; right++) {
            int c = s[right];
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            // Shrink window if more than k distinct chars
            while (freq.size() > k) {
                int leftChar = s[left];
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }
                left++;
            }

           if(freq.size() <= k)
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

