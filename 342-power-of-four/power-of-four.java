class Solution {
    public boolean isPowerOfFour(int n) {
       int temp=n;
  
     if(temp==0){
        return false;
     }
      while(temp%4==0){
        temp=temp/4;
      }
      if(temp==1){
        return true;
      }
      return false;
    
    
    }
}