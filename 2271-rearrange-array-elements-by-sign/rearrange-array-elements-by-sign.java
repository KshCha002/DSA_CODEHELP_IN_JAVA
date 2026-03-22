class Solution {
    public int[] rearrangeArray(int[] A)  {
        int n = A.length;

        // Result array initialized to size n
        int[] ans = new int[n];

        // posIndex for even indices (positive), negIndex for odd (negative)
        int posIndex = 0, negIndex = 1;

        // Traverse input array
        for (int i = 0; i < n; i++) {
            if (A[i] < 0) {
                // Place negative number at odd index
                ans[negIndex] = A[i];
                negIndex += 2;
            } else {
                // Place positive number at even index
                ans[posIndex] = A[i];
                posIndex += 2;
            }
        }

        return ans;
    }
}