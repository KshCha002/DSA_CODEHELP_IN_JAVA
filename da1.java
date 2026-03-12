import java.util.Scanner;
class Main {
    public static void main(String[] args) {

    int [] ar2= new int [10];
            ar2=new int [] {1,0,3};
    int [][] an= new int[][]{{1,2},{3,2}}; 
    
    Main a =new Main();
    //int sum= a.maximumWealth(an);
    //int []shuffle=a.shuffle(ar2,3);
    //int gp =a.numIdenticalPairs(ar2);
    int m=a.missingNumber(ar2);
    System.out.printf("m= %d ",m);
           // a.reverseArr(ar2,0);
           // a.printAr(shuffle);
            // int x= a.fib(2);
            // System.out.printf("x= %d ",x);

}
    void printAr(int []ar)
    {
        int n =ar.length;
        //System.out.println("\n Array elements are: ");
        for (int i=0;i<n;i++)
        {
            System.out.printf(" "+ar[i]+",");
        }
    }
    void reverseArr(int []ar,int i)
    {
        int n=ar.length;
        if (i>(n/2))
            return ;
        Swap(ar,i,(n-1-i));
        reverseArr(ar, i+1);
        return;
        
    }

    void Swap(int []ar ,int i,int j)
    {
        int temp=ar[i];
        ar[i]=ar[j];
        ar[j]=temp;
    }
    int fib(int n)
    {int sum =0;
    if (n<=1)
        return n;
    int left =fib(n-1);
    int right = fib(n-2);
     sum=left +right;
     return sum ;
    }



        public int maximumWealth(int[][] accounts) {
            int maxSum=Integer.MIN_VALUE;
            for (int[] i:accounts)
            {   int sum= i[0];
                for (int j=1;j<i.length;j++)
                {
                    sum+=i[j];
                }
                if (maxSum<sum)
                {
                    maxSum=sum;
                }
            }
            return maxSum;
            
        }
    



        public int[] shuffle(int[] nums, int n) {
            int [] ans =new int[nums.length];
    int k=0;
            for (int i=0;i<n;i++)
            {
                ans[k]=nums[i];
                ans[k+1]=nums[i+n];
                k=k+2;
                System.out.println("\nAAAAA");
                printAr(ans);
                
            }
    return ans;
        }
        public int numIdenticalPairs(int[] nums) {
            int gp=0;
            for (int i=0;i<nums.length-1;i++)
            {
                for (int j=i+1;j<nums.length;j++)
            {

                System.out.println("aaaa"+i+","+j);
                if(nums[i]!=nums[j])
                {
                    continue;
                }
                System.out.println("bbb"+i+","+j);
                gp++;
            }
            }
            return gp;
        }
        public int missingNumber(int[] nums) {
            int n=nums.length;
            int shouldSum =(int)((n*(n+1))/2);
            int actual= nums[0];
            for(int i=1;i<n;i++)
            {
                actual = actual + nums[i];
            }
            System.out.println(" "+shouldSum+" "+actual);
            return (shouldSum - actual);
            
        }
    }
