class Solution {
    public int maxSubArray(int[] nums) {
        int n=nums.length;
        int anss=-1;
        int ansEnd=-1;
        int max=Integer.MIN_VALUE;
        int sum=0;
        int start=0;
        for(int i=0;i<n;i++)
        {
            
            
            if(sum<0)
            {
                sum=0;
                start=i;
            }
            sum=sum+nums[i];

            if(sum>max)
            {
                max=sum;
                anss=start;
                ansEnd=i;
            }
        }
        return max;
        
    }
}
// class Solution {
//     public int maxSubArray(int[] nums) {
//         int res = nums[0];
//         int total = 0;

//         for (int n : nums) {
//             if (total < 0) {
//                 total = 0;
//             }

//             total += n;
//             res = Math.max(res, total);
//         }

//         return res;        
//     }
// }