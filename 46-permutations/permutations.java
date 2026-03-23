class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans= new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        int[] isMapped =new int [nums.length];
        repermute(nums,ans,ds,isMapped);
        return ans;
    }


    public void repermute(int[] nums,List<List<Integer>> ans,List<Integer> ds,int[] isMapped)
    {
        if(ds.size()==nums.length)
        {
        ans.add(new ArrayList<>(ds));
        return ;
        }
        for(int i=0;i<nums.length;i++)
        {
            if (isMapped[i]==0)
            {
                isMapped[i]=1;
                ds.add(nums[i]);
                repermute(nums,ans,ds,isMapped);
                ds.remove(ds.size()-1);
                isMapped[i]=0;
            }
        }
    }
}