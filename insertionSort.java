

public class insertionSort {
    
    public static void main(String[] args)
    {
        int[] arr =new int [7];
        arr=new int[] {2,1,4,-10,2,-13,-3};
        // System.out.println("enter numbers");
        // Scanner sc =new Scanner(System.in);
        // for (int i=0;i<7;i++)
        // {
        //     int x= sc.nextInt();
        //     arr[i]=x;
        // } 
        insertionSort obj =new insertionSort();
        System.out.println("before");
        obj.printArr(arr);
        obj.insertionSSort(arr);
        System.out.println("after");
        obj.printArr(arr);

    }
    
    
    public void insertionSSort(int[] nums) {
        int n = nums.length;
        
        for (int i=1;i<n;i++)
            {
                int j=i;
                while(( j>0 && nums[j-1]>nums[j]))
               {
                 swap(nums,j,j-1);
                j--;
            }
            System.out.println(i+"   hi");
            }   
    
        }
    
        public void swap(int[] arr,int i,int min)
        {
            int temp =arr[i];
            arr[i]=arr[min];
            arr[min]=temp;
        }

    public void printArr(int [] arr)
    {
        for (int a : arr)
        {
            System.out.print(a+ ", ");
        }
        System.out.println();
    }

}
//logic
/*
start from 2nd index and keep placing element i in correct position in subarray 0 to i-1
complexity worst,avg case =O(n^2) and best case O(n)
*/