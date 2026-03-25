class Solution {
    public int reverse(int x) {
        long ans=0;
        if(x<Integer.MIN_VALUE || x>Integer.MAX_VALUE)
        {
            return 0;
        }


        boolean sign = (x<0)?true:false ;
        if(sign){x=-x;}
        while(x>0)
        {
            int mod=x%10;
            ans=(ans*10)+mod;
            x=x/10;

        }
        
       

 if(sign){ans=-ans;}

        if(ans<Integer.MIN_VALUE || ans>Integer.MAX_VALUE)
        {
            return 0;
        }
        return (int)ans;
    }
}