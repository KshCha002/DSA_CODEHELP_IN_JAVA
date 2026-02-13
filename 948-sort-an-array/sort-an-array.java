class Solution {
    public int[] sortArray(int[] nums) {
   mergeSSort(nums,0,(nums.length-1));
        return nums;
    }
    public void mergeSSort(int[] nums,int low,int high)
    {
        if(low==high)
        {
            return;
            }
        int mid=(low+high)/2;
        mergeSSort(nums,low,mid);
        mergeSSort(nums,mid+1,high);
        merge(nums,low,mid,high);
        return;
    }

    public void merge(int[] nums,int low,int mid,int high)
    {
        int left=low;
        int right=mid+1;
        List<Integer> l= new ArrayList<>();

        while(left<=mid && right<=high )
        {
            if(nums[left]<=nums[right])
           { l.add(nums[left]);
            left++;}
            else
            {
                l.add(nums[right]);
            right++;
            }
        }

        while(left<=mid )
        {
            l.add(nums[left]);
            left++;
        }
        
        while(right<=high )
        {
            l.add(nums[right]);
            right++;
        }

        for(int i=low;i<=high;i++)
        {
            nums[i]=l.get(i-low);
        }
    }
}