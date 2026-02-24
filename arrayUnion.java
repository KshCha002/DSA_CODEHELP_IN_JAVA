import java.util.ArrayList;

public class arrayUnion {
    
public static void main(String[] args)
{
int [] nums1={1,1,2,3,3,4,5};
int [] nums2={1,1,2,2,3,7};
arrayUnion obj =new arrayUnion();

int[] ans=obj.unionArray(nums1, nums2);
obj.printArr(ans);


}
public void printArr(int [] arr)
{
    for (int a : arr)
    {
        System.out.print(a+ ", ");
    }
    System.out.println();
}


    public int[] unionArray(int[] nums1, int[] nums2) {
        int n1=nums1.length;
        int n2=nums2.length;
        int i=0;
        int j=0;
        ArrayList<Integer> ans =new ArrayList<Integer>();
        int z=-1;
        while(i<n1 && j<n2)
        {
            if(nums1[i]<=nums2[j])
            {
                if(ans.size()==0 ||  ans.get(z)!=nums1[i])
                {
                    ans.add(nums1[i]);
                    z++;
                }
                i++;
            }
            else
            {
                if(ans.size()==0 || ans.get(z)!=nums2[j])
                {
                    ans.add(nums2[j]);
                    z++;
                }
                j++;
            }
            
        }

        while(i<n1)
        {
                if(ans.size()==0 ||  ans.get(z)!=nums1[i])
                {
                    ans.add(nums1[i]);
                    z++;
                }
                i++;
               
            }
        while(j<n2)
        {
                if(ans.size()==0 ||  ans.get(z)!=nums1[j])
                {
                    ans.add(nums2[j]);
                    z++;
                }
                j++;
                
            }
            int ans1[] = new int[ans.size()];
            int index=0;
        for(int x:ans )
            ans1[index++]=x;
        return ans1;
    }
}