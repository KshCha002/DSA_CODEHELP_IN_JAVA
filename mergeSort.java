import java.util.ArrayList;
import java.util.List;

public class mergeSort {
    
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
        mergeSort obj =new mergeSort();
        System.out.println("before");
        obj.printArr(arr);
        obj.mergeSSort(arr,0,(arr.length-1));
        System.out.println("after");
        obj.printArr(arr);

    }
    

    
    public void mergeSSort(int[] nums,int low,int high) {

        if(low>=high)
        {return;}
        int mid= (low+high)/2;
        System.out.println("mid"+mid);
        mergeSSort(nums, low, mid);
        mergeSSort(nums, mid+1, high);
        merge(nums,low,mid,high);

        }
    
    public void merge(int[] nums,int low,int mid,int high)
    {
        List<Integer> list = new ArrayList<>();
        int left = low;
        int right =mid+1;
        while(left<=mid && right<=high)
        {
            if(nums[left]<=nums[right])
            {
                list.add(nums[left]);
                left++;
            }
            else
                {
                    list.add(nums[right]);
                    right++;
                }
        }
        while(left<=mid)
            { 
                list.add(nums[left]);
                left++;
            }

        while(right<=high)
        {
            list.add(nums[right]);
            right++;  
        }

        for (int i =low;i<=high;i++)
            {
                nums[i]=list.get(i-low);
            }

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

recursively call mergesort on subarray by low to mid and md+1 to high till single element sorted array are acchieved,return and merge both sorted array ,
merge using a temp ar to mer 2 different sorted array using var mid,low,high
  TC -> worst - O(N^2)  best - O(N^2)
*/