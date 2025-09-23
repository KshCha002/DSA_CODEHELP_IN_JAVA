class Solution {
    public int lengthOfLongestSubstring(String s) {
         Map<Character, Boolean> h = new HashMap<Character, Boolean>();
         int l=0,r=0;
         int maxL=0;
      while(r < s.length())
         { 
            if(h.get(s.charAt(r))==null)
            {
                h.put(s.charAt(r),true);
                maxL=Math.max(maxL,(r-l+1));
                r++;

            }
            else
            {
            h.remove(s.charAt(l));
            l++;
            }
         }

    return maxL;
    
    }
}