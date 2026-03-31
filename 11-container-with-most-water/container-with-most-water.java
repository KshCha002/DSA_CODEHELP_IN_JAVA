class Solution {
    public int maxArea(int[] height) {
       int l =0;
       int r=height.length-1;
      int  maxA= Integer.MIN_VALUE;
       while(l<r)
       {

        int area=(r-l)*Math.min(height[l],height[r]);
        maxA=Math.max(maxA,area);
        if(height[l]<height[r])
        {
            l++;
        }
        else {r--;}

       } 


       return maxA;
    }
}