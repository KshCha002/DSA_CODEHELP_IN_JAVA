class Solution {
    public boolean isPalindrome(String s) {
        String copyString= new String();
        
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)>='A'&& s.charAt(i)<='Z')
            {
                copyString+=(char)(s.charAt(i)+32);
            }
            if(s.charAt(i)>='0'&& s.charAt(i)<='9')
            {
                copyString+=(char)(s.charAt(i));
            }
            if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
            copyString += (char)(s.charAt(i));
            }
        }
        System.out.println(copyString);
        int l=0;
        int r=copyString.length()-1;
        while(l<r)
        {
            if(copyString.charAt(l)!=copyString.charAt(r))
            {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}