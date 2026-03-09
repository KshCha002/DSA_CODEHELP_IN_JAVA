class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n=nums.length;
        Set<List<Integer>> l= new HashSet<List<Integer>>();
   //Set<Integer> s = new HashSet<Integer>();
        for(int i=0;i<n;i++)
        {
             Set<Integer> s = new HashSet<Integer>();
            int j=i+1;
            while(j<n)
            {
                int tar= -(nums[j]+nums[i]);
                if(s.contains(tar))
                {
                   List<Integer> arr= new ArrayList(Arrays.asList(nums[i],nums[j],tar));
                    Collections.sort(arr);
                    l.add(arr);
                }
            s.add(nums[j]);
            j++;
            }
        }

        
        return new ArrayList(l);

    }
}