

public class selectionSort {
    
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
        selectionSort obj =new selectionSort();
        System.out.println("before");
        obj.printArr(arr);
        obj.selectionSSort(arr);
        System.out.println("after");
        obj.printArr(arr);

    }
    
    public int findmin(int[] arr,int i,int min)
    {
        for (int j=i;j<(arr.length);j++){
            if(arr[j]<arr[min] )
            {
                min=j;
            }
        }
        return min ;
    }
    public void selectionSSort(int[] nums) {
        int n = nums.length;
        System.out.println("n="+n);
        for (int i=0;i<n-2;i++)
            {
                int min = i;
                for(int j=i+1;j<n;j++)
                {
                    if(nums[j]<nums[min])
                    min=j;
                }
                swap(nums,i,min);
            System.out.println(i+"   arr");
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

in each iteration select the min element of the subarray or array(1st iteration) and place it in the starting;
for ith iteration you will try finding the min element in subarray (ar[i]to ar[last])
  TC -> worst - O(N^2)  best - O(N^2)
*/