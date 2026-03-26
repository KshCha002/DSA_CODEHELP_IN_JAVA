class Solution {
    public static boolean closeStrings(String word1, String word2) {
    if(word1.length()!=word2.length())
    {
        return false;
    }
    char [] charArray1=word1.toCharArray();
    char [] charArray2=word2.toCharArray();
    for(char r:word1.toCharArray())
    {
        if(word2.indexOf(r)==-1)
        {
            return false;
        }
    }
HashMap<Character,Integer> word1freq= new HashMap<>();
HashMap<Character,Integer> word2freq= new HashMap<>();
for(char i: charArray1){
        word1freq.put(i,word1freq.getOrDefault(i,0)+1);
        }

for(char i: charArray2){
        word2freq.put(i,word2freq.getOrDefault(i,0)+1);
        }
List<Integer> values1 = new ArrayList<>(word1freq.values());
List<Integer> values2 = new ArrayList<>(word2freq.values());
Collections.sort(values1);
Collections.sort(values2);
System.out.println(values1.toString());
System.out.println(values2.toString());
return values1.equals(values2);

}
}
