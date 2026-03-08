class Solution {
    public String findDifferentBinaryString(String[] nums) {
        char[] ar= new char[nums.length];
        int i=0;
        for (String num: nums )
        {
            char ch = num.charAt(i);
            ar[i]= (ch=='0')?'1':'0';
            i++;
        }
    String ar1= new String (ar);
        return ar1;
        
    }
}