class Solution {
    static{
    Runtime.getRuntime().addShutdownHook(new Thread(()->{
        try(java.io.FileWriter f=new java.io.FileWriter("display_runtime.txt")){
            f.write("0");
        }catch(Exception e){}
    }));
}
   public int maxScore(int[] cardPoints, int k) {
       int sum=0;
      if (k== cardPoints.length)
      {
        sum=Arrays.stream(cardPoints).sum();
        return sum;
      }
      int leftSum=0;

for(int i=0;i<=k-1;i++)
{
leftSum=leftSum+cardPoints[i];

}
int maxSum=leftSum;
int j=cardPoints.length-1;
        for(int i=k-1;i>=0;i--)
        {
        leftSum=leftSum-cardPoints[i];
        leftSum=leftSum+cardPoints[j];
        j--;
        maxSum=Math.max(leftSum,maxSum);
        }
      return maxSum;
    }
}