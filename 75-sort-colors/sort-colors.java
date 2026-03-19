class Solution {
 
//     public void sortColors(int[] nums) {
        
//         int n= nums.length;
//         int mid=0;
//         int low=0;
//         int high=n-1;
//         while(mid<high)
//         {
//             if(nums[mid]==0)
//             {
//                 swap (nums,mid,low);
//                 low++;
//                 mid++;
//             }
//             if(nums[mid]==1)
//             {
//                 mid++;
//             }
//             if(nums[mid]==0)
//             {
//                 swap (nums,mid,high);
//                 high++;
//             }
//         }

       
       
        

// }


public void sortColors(int[] nums) {
        
        int n= nums.length;
        int i=0;
        int c0=0;
        int c1=0;
        int c2=0;
        while(i<n)
        {
            int j= nums[i];
            if(j==0)
            {c0++;
            }
            if(j==1)
            {c1++;
            }
            if(j==2)
            {c2++;
            }


            i++;
        }
        i=0;
        int j=0;
System.out.println(c0+","+c1+","+c2);
        while(j<c0)
        {
            nums[i]=0;
            i++;
            j++;

        }

        j=0;
        while(j<c1)
        {
            nums[i]=1;
            i++;
            j++;

        }

        j=0;
        while(j<c2)
        {
            nums[i]=2;
            i++;
            j++;

        }
        }

 public void swap(int[] arr,int a,int b)
    {
        int temp =arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }
}