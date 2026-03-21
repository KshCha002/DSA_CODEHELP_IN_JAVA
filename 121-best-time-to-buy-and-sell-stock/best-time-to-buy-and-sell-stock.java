class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int [] minBuy =new int[n];
        int maxprofit=Integer.MIN_VALUE;;
  
        int mini=Integer.MAX_VALUE;
        // for(int i=0;i<n;i++)
        // {

        //     minBuy[i]=
        //     minBuy[i];

        // }
        for(int i=0;i<n;i++)
        {
            mini=Math.min(mini,prices[i]);
            int cost=prices[i]-mini;
            maxprofit=Math.max(maxprofit,cost);

        }
        return maxprofit;
    }
}