class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n=nums.length;
         Arrays.sort(nums); 
    List<List<Integer>> l =new ArrayList<>();
    //Set<List<Integer>> s =new HashSet<List<Integer>>();//check set in java
    if(nums.length==3)
            {
                if( (nums[0]+nums[1]+nums[2])==0)
                {   
                 List<Integer> l1 = new ArrayList<>(Arrays.asList(nums[0],nums[1],nums[2]));

                    l.add(l1);
                    return l;
                }
                else
                {
                   
                   return l;
                    }
            }


int i=0;
for(;i<n;i++)
{
    if(i>0 && nums[i]==nums[i-1])
    continue;
    int j= i+1;
    int k=n-1;
    while(j<n && j<k)
    {
        int sum =nums[i]+nums[j]+nums[k];
        if(sum ==0)
        {
            l.add(Arrays.asList(nums[i],nums[j],nums[k]));
            //s.add(Arrays.asList(nums[i],nums[j],nums[k]));
            j++;
            k--;
            while(j<k && nums[j]==nums[j-1]){j++;}
            while(j<k && nums[k]==nums[k+1]){k--;}

        }
        else if(sum>0)
        {
            k--;
           // while(j<k && nums[k]==nums[k+1]){k--;}
        }
        else if(sum<0)
        {
        j++;
        while(j<k && nums[j]==nums[j-1]){j++;}
        }

    }

}

//List<List<Integer>> list = new ArrayList<>(s);
return l;
    }
}