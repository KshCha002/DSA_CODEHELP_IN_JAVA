class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int j=0;
        int avg=0;
        int i=0;
        
    while(j<k)
    {
        avg=avg+nums[j];
        j++;
    }
    
double max=(double)avg/k;
if(k==nums.length){return max;}   

while(j<nums.length)
{
    avg+=nums[j];
    avg-=nums[i];
max=Math.max(max,(double)avg/k);

    i++;
    j++;
}
return max;
    }
}