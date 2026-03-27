
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
        {
            return false;
        }
        int[] charFreq = new int [26];
for(int i=0;i<s.length();i++)
{
    int index= s.charAt(i)-'a';
   charFreq[index]++;
}
for(int i=0;i<t.length();i++)
{
    int index= t.charAt(i)-'a';
  if( charFreq[index]==0){return false;}
  else
   charFreq[index]--;
}


        return true;
    }
}