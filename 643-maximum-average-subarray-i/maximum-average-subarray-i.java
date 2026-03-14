class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double max= 0;
        int i=0;
        int sum=0;
        while(i<k)
        {
            sum=sum+nums[i];
            i++;

        }
        max= (double)sum/i;
        int j=i;
        i=0;
        while(j<nums.length){
            sum=sum + nums[j];
            sum= sum - nums[i];
            i++;
            j++;
            double m= (double) sum/k;
            if(m>max)
            {
                max=m;
            }
        }

        return max;
    }
}