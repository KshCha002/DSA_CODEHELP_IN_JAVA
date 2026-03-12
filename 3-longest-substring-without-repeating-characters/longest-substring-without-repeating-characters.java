class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l=0;int r=0;
        int maxLength=0;
        Map <Character,Integer> m =new HashMap<>();
        while(r<s.length())
        {
            if(m.containsKey(s.charAt(r)))
            {
                if(m.get(s.charAt(r))>=l)
                {
                    l=m.get(s.charAt(r))+1;
                   
                }
                
            }
             m.put(s.charAt(r),r);
                int len=r-l+1;
            maxLength=Math.max(maxLength,len);
            r++;
        }

        return maxLength;
    }
}