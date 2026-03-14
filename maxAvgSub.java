public class maxAvgSub {
    public static void main(String[] args) {
        int [] nums= new int[]{1,12,-5,-6,50,3};
        maxAvgSub obj =new maxAvgSub();
        double am=obj.findMaxAverage(nums, 4);
        System.out.println(am);
    }



    public double findMaxAverage(int[] nums, int k) {
        double max= 0;
        int i=0;
        int sum=0;
        while(i<k)
        {
            sum=sum+nums[i];
            i++;

        }
        System.out.println(sum);
        max= (double)sum/i;
        System.out.println(max);
        int j=i;
        i=0;
        while(j<nums.length){
            System.out.println(i+","+j);
            sum=sum + nums[j];
            sum= sum - nums[i];
            i++;
            j++;
            double m= (double) sum/k;
           // System.out.println(m);
            if(m>max)
            {
                max=m;
            }
        }

        return max;
    }
}



//maintain max after initializing maxavg with initial window