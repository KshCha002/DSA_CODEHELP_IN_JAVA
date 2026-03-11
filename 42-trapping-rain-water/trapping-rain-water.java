class Solution {
    public int trap(int[] height) {
    int n =height.length;
    int [] leftmax= leftmaxcal(height,n);
    int [] rightmax= righmaxcal(height,n);
    int count =0;
    for (int i=0;i<n;i++)
    {
        int mina= Math.min(leftmax[i],rightmax[i]);
        if(mina>height[i])
        {
            int water= mina-height[i];
            count =count+water;
        }
    }

return count;

    }


   public int[] leftmaxcal(int [] arr, int n)
    {
        int [] leftmax= new int[n];
        int max= arr[0];
        leftmax[0]= max;
        for(int i=1;i<n;i++)
        {
        max= Math.max(max,arr[i]);
        leftmax[i]= max;
        }
        return leftmax;

    }
    public int[] righmaxcal(int [] arr, int n)
    {
        int [] rightmax= new int[n];
        int max= arr[n-1];
        rightmax[n-1]= max;
        for(int i=n-2;i>=0;i--)
        {
        max= Math.max(max,arr[i]);
        rightmax[i]= max;
        System.out.println("here"+rightmax[i]);
        }
        return rightmax;

    }
}