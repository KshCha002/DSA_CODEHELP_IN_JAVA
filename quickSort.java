import java.util.ArrayList;
import java.util.List;

public class quickSort {
    
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
        quickSort obj =new quickSort();
        System.out.println("before");
        obj.printArr(arr);
        obj.quickSSort(arr,0,(arr.length-1));
        System.out.println("after");
        obj.printArr(arr);

    }
    

    
    public void quickSSort(int[] nums,int low,int high) {

        if(low>=high)
        {return;}
        int partition= partition(nums,low,high);
        quickSSort(nums, low, partition-1);
        quickSSort(nums, partition+1, high);
        }
    
    public int  partition(int[] nums,int low,int high)
    {
        int pivot = nums[low];
        int i = low+1;
        int j =high;
        while(i<j)
        {
            while((nums[i]<=pivot) && i<=high) 
                {i++;}
            while((nums[j]>pivot) && j>=low)
                { 
                    j--;
                }
            if(i<j)
                {swap(nums,i,j);}
                
        }
        swap(nums,low,j);
        return j;
                
    }            
                
                    
                
private void swap(int[] nums, int i, int j) {
    int temp=nums[i];
    nums[i]=nums[j];
    nums[j]=temp;
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

recursively call quickSort on subarray by low to mid and md+1 to high till single element sorted array are acchieved,return and merge both sorted array ,
merge using a temp ar to mer 2 different sorted array using var mid,low,high
  TC -> worst - O(N^2)  best - O(N^2)
*/