
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
if (k == nums.length) return nums;
        // Step 1: frequency map
        HashMap<Integer, Integer> h = new HashMap<>();
        for (int num : nums) {
            h.put(num, h.getOrDefault(num, 0) + 1);
        }

        // Step 2: min-heap based on frequency
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> h.get(a) - h.get(b)
        );

        // Step 3: keep top k elements
        for (int key : h.keySet()) {
            pq.add(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Step 4: build result
        int[] result = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            result[i++] = pq.poll();
        }

        return result;
    }
}
