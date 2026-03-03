class Solution {
  public int maximumWealth(int[][] accounts) {
            int maxSum=Integer.MIN_VALUE;
            for (int[] i:accounts)
            {   int sum= i[0];
                for (int j=1;j<i.length;j++)
                {
                    sum+=i[j];
                }
                if (maxSum<sum)
                {
                    maxSum=sum;
                }
            }
            return maxSum;
            
        }
    }