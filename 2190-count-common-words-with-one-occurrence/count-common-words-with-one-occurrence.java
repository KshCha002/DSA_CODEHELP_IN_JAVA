class Solution {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        int n1 = words1.length;
        int n2 = words2.length;
        //System.out.println("n1="+n1 +",n2="+n2);
        int count = 0;

        for (int i = 0; i < n1; i++) {
            map1.put(words1[i], (map1.getOrDefault(words1[i], 0)) + 1);
        }
        for (int i = 0; i < n2; i++) {
            if(map1.containsKey(words2[i]) && map1.get(words2[i])<=1 )
            {
                map1.put(words2[i],(map1.getOrDefault(words2[i], 0)) - 1);
            }
        }


       // System.out.println("map1="+map1.keySet().size() +",val2="+map2.keySet().size());

         for (String key : map1.keySet()){
            if(map1.get(key)==0)
            {count++;}
         }
         return count;
    }
}