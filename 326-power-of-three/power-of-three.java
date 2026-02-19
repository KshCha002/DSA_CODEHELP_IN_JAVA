class Solution {
    public boolean isPowerOfThree(int n) {
         if (n<=0)
        {return false;}
        boolean f = false;
        f=checkPow (n);
        return f;
    }
    public boolean checkPow (int a )
    {
        int r = a%3;
        if( a==1)
        {
            return true;
            }
        if(r!=0) 
        {return false;
        }
        
      
        return checkPow( a/3);
         
    }
}