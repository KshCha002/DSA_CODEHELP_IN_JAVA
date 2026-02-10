import java.util.Scanner;

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
    public int[] selectionSSort(int[] arr)
    { 
        for(int i=0;i<(arr.length-1);i++)
        {  int min=i;

            min= findmin(arr,i,min);
            swap(arr,i,min);
        }
        
        return arr;
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
    }

}
//logic
/*

in each iteration select the min element of the subarray or array(1st iteration) and place it in the starting;
for ith iteration you will try finding the min element in subarray (ar[i]to ar[last])
  TC -> worst - O(N^2)  best - O(N^2)
*/