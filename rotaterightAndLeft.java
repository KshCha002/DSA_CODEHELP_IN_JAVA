class rotaterightAndLeft {
    public static void main(String[] args) {
        int[] nums = {2,1,3,4};
        //  int k=3;
        // int n =nums.length;
        // k=k%n;
        // int[] temp =new int[k];
        // int j=0;
        // for(int i=(n-k);i<(n);i++)
        // {
        //     temp[j]=nums[i];
           
        //     //System.out.println( temp[j]);
        //      j++;
        // }
        // for(int i=(n-k-1);i>=0;i--)
        // {
        //     nums[i+k]=nums[i];   
        //     //System.out.println(" hiii");
        // }
        // for(int nn:nums)
        // {
        // //System.out.println(nn);
        // }
        rotaterightAndLeft obj = new rotaterightAndLeft();
        
        boolean ans = obj.check(nums);
        System.out.println("ans"+ans);
    }



    public void printArr(int [] arr)
    {
        for (int a : arr)
        {
            System.out.print(a+ ", ");
        }
        System.out.println();
    }
    
    public boolean check(int[] nums) {

        int n=nums.length;
        int count=0;
        for(int i=0;i<n;i++){
            if(nums[i]>nums[(i+1) % n]){
                System.out.println("[(i+1) % n]="+nums[(i+1) % n]);
                System.out.println(nums[i]+">"+nums[(i+1) % n]);
                count++;
            }
        }
        if(count==1||count==0)
        return true;
        else
        return false;

        // int x = 0;
    
        // for (int i = 0; i < nums.length - 1; i++) {
        //     if (nums[i] > nums[i + 1]) {
        //         break;
        //     }
        //     x++;
        // }
        // if ((x + 1) == nums.length) {
        //     return true;
        // }
        // int[] temp = new int[nums.length];
    
        // for (int i = x+1 ; i < nums.length-1 ; i++) {
        //     if (nums[i] > nums[i +1]) {
        //         System.out.println(nums[i]+">"+nums[i+1]);
        //         System.out.println("idhar1");
        //         return false;
        //     }
        
        // }
        // for (int i = x+1 ; i < nums.length ; i++) {
        //     temp[i - x -1] = nums[i];
        // }
        // printArr(temp);
        // for (int i = 0; i < x+1; i++) {
        //     temp[nums.length-x-1+i] = nums[i];
        // }
        // printArr(temp);
        // for (int i = 0; i < nums.length - 1; i++) {
        //     if (temp[i] > temp[i + 1]) {
        //         return false;
        //     }
        // }
    
        // return true;
    }
}