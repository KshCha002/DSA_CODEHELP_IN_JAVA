class Solution {
    public int reverse(int x) {
        if(x<Integer.MIN_VALUE || x>Integer.MAX_VALUE )
        {
            return 0;
            }

         boolean neg =false;
     
        int signedInt = x;
        if(signedInt< 0)
        {
            neg =true;
            signedInt= - signedInt;
        } 
        long ans =0;
        
        //ans=ans*mod;
        while(signedInt>0)
        {
            int mod =signedInt%10;
            ans= (ans*10)+mod;
            signedInt =signedInt/10;
            
        }
        if(neg)
        {
            ans=-ans;
        }
         if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
        return 0; // Return 0 if overflow occurs
    }
        return (int)ans;
    }
}