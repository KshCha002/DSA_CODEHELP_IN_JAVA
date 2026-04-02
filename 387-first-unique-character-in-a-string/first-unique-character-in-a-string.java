class Solution {
    public int firstUniqChar(String s) {
        int ans=-1;
        char u;
        Map<Character,Integer> m= new HashMap<>();
        for (int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            m.put(c,m.getOrDefault(c,0)+1);
        }
        if (m.containsValue(1))
        {
            for (int i = 0; i < s.length(); i++) {
    if (m.get(s.charAt(i)) == 1) return i; // ← iterate string not map
}
        }

        return ans;

        
    }
}