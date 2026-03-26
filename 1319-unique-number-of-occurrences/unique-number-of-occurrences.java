class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer,Integer> freqMap= new HashMap<Integer,Integer>();
        for(int i: arr){
            freqMap.put(i,freqMap.getOrDefault(i,0)+1);
            }

        int n=arr.length;
Set<Integer> s =new HashSet<>();

        for(int i:freqMap.values())
        {
 
if(s.add(i)==false)
return false;
        }
        return true;
    }
}