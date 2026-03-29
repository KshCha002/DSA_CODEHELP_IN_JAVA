class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans= new ArrayList<>();
         HashMap<String,List<String>> frqmap= new HashMap<>();
         for(String s: strs )
         {
            int [] fr= new int[26];
            for(int i =0;i<s.length();i++)
            {
                char ch= s.charAt(i);
                fr[ch-'a']++;
            }
            String frcount = Arrays.toString(fr);
            System.out.println(frcount);
            frqmap.putIfAbsent(frcount,new ArrayList<>());
            frqmap.get(frcount).add(s);

         }


       
     return new ArrayList<>(frqmap.values()); 
    }
}