class Solution {

    //moore's voting algo
    public int majorityElement(int[] nums) {
        int maxCount = 0;
        int count=1;
        int maxElement = nums[0];
        for (int i=1;i<nums.length;i++)
        {
            int n= nums[i];
            
            if(count==0)
            {
                maxElement=n;
            }
            count=count+(maxElement==n?1:-1); 
        }
        return maxElement;
       
        // int count =1;
        
        // int maj =nums[0];
        // int n=nums.length;
        // for(int i=1;i<n;i++)
        // {
        //     if(count==0){
        //         maj=nums[i];
        //     }
        //     count=count+(nums[i]==maj?1:-1);
        // }
        // return maj;
    
    }
}