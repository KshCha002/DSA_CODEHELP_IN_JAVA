import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class mergeSortedarr {
    public static void main(String args[]) {
        int [] nums3= new int[]{-3,-1,0,0,0,3,3};
        int[] nums1 = new int[] { 0 };
        int[] nums2 = new int[] { 1,2,3,4,5,6 };
        mergeSortedarr obj = new mergeSortedarr();
        // obj.printArr(nums1);
        //obj.mergeNew(nums1, nums2, (nums1.length), (nums2.length));
        //obj.printArr(nums1);
       // int x= obj.removeDuplicates(nums3);
       //obj.reverse(nums2, 1, 4);
        System.out.println(2%(-1));
        //obj.printArr(nums2);


    }

    public void printArr(int[] arr) {
        for (int a : arr) {
            System.out.print(a + ", ");
        }
        System.out.println();
    }

    public void mergeNew(int[] nums1, int[] nums2, int m, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] ans = new int[m];

        while (i < m - n && j < n) {
            if (nums1[i] <= nums2[j]) {
                ans[k] = nums1[i];
                k++;
                i++;
            } else {
                ans[k] = nums2[j];
                k++;
                j++;

            }
        }
        while (i < m - n) {

            ans[k] = nums1[i];
            k++;
            i++;
        }

        while (j < n) {
            ans[k] = nums2[j];
            k++;
            j++;

        }
        for (int x = 0; x < m; x++) {
            nums1[x] = ans[x];
        }

    }

    public int removeDuplicates(int[] nums) {
        Set<Integer> m = new TreeSet<>();
        int i = 0;
        while (i < nums.length) {
            m.add(nums[i]);
            i++;
        }
        // int n= m.keySet().size();
        int j = 0;
        for (int n : m) {
            nums[j] = n;
            j++;
        }
        return j;
    }

    public void reverse(int[] nums,int a, int b)
{
    int i =a;
    int j=b;
    while(i<j)
    {
       swap(nums,i,j);
       i++;
       j--;

    }
}
public void swap(int[] nums,int a, int b)
{
    int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
}
}
