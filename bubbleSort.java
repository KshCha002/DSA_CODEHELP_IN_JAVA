public class bubbleSort {
    
    
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
        bubbleSort obj =new bubbleSort();
        System.out.println("before");
        obj.printArr(arr);
        obj.bubbleSSort(arr);
        System.out.println("after");
        obj.printArr(arr);

    }
    public int[] bubbleSSort(int[] arr)
    { 
        for(int i=0;i<(arr.length-1);i++)
        {  
           for(int j=0;j<(arr.length-1-i);j++)
           {
            if(arr[j]>arr[j+1])
            {
                swap(arr, j+1, j);
            }
           }
        }
        
        return arr;
    }
    
    public void swap(int[] arr,int a,int b)
    {
        int temp =arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
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

try shifting the largest element to the extreme left by checking alternate elements if the arr[j]>arr[j+1] swap;
runs till length-1 interations as last element need not be swapped.
  TC -> worst - O(N^2)  best - O(N)

*/