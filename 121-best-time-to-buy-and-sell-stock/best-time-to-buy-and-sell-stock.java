class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int [] minBuy =new int[n];
        int maxprofit=Integer.MIN_VALUE;;
  
        int mini=Integer.MAX_VALUE;
        for(int i=0;i<n;i++)
        {

            minBuy[i]=Math.min(mini,prices[i]);
            mini=minBuy[i];

        }
        for(int i=0;i<n;i++)
        {
            int cost=prices[i]-minBuy[i];
            maxprofit=Math.max(maxprofit,cost);

        }
        return maxprofit;
    }
}