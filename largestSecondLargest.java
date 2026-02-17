public class largestSecondLargest
{
    public static void main (String[] args)
    {
            int [] n=new int[]{1,2,3,5,6,7,23,12,0};
            largestSecondLargest obj=new largestSecondLargest();
            obj.printArr(n);
            int l=(obj.secondlargestElement(n))[0];
            System.out.println("Largest: "+l);
            int secondLargest= (obj.secondlargestElement(n))[1];
            System.out.println("SecondLarges : "+secondLargest);
            int xs=(obj.secondSmallElement(n))[0];
            System.out.println("Smallest: "+xs);
            int s= (obj.secondSmallElement(n))[1];
            System.out.println("Second smallest : "+s);


    }
    
    public void printArr(int [] arr)
    {
        for (int a : arr)
        {
            System.out.print(a+ ", ");
        }
        System.out.println();
    }
       
        public int[] secondlargestElement(int[] nums) {
            
            int largest =nums[0];
            int seclarge =Integer.MIN_VALUE;
            int n=nums.length;
            for(int i=0;i<(n);i++)
                {
            if(nums[i]>largest) 
            {
                seclarge =largest;
                largest=nums[i];}
            
            else if((nums[i]<largest) &&(nums[i]>seclarge))
                {
                    seclarge=nums[i];
                }
            
            
            }
            int[] ans={0,0};
            ans[0]=largest;
            ans[1]=seclarge;
            return ans;
            }

            public int[] secondSmallElement(int[] nums) {
            
                int small =nums[0];
                int secsmall =Integer.MAX_VALUE;
                int n=nums.length;
                for(int i=0;i<(n);i++)
                    {
                if(nums[i]<small) 
                {
                    secsmall =small;
                    small=nums[i];}
                
                else if((nums[i]>small) &&(nums[i]<secsmall))
                    {
                        secsmall=nums[i];
                    }
                
                
                }
                int[] ans={0,0};
                ans[0]=small;
                ans[1]=secsmall;
                return ans;
                }
    
}