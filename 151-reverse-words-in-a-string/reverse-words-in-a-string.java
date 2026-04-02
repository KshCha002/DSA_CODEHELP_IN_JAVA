class Solution {
    public String reverseWords(String s) {
        //System.out.println("stringA before trim"+s);
        s=s.trim();
        //System.out.println("stringA after trim"+s);
        String [] sta= s.split(" ");
        String [] stb= s.split("\\s+");
        StringBuilder srev= new StringBuilder();
        srev.append(stb[stb.length-1]);
        for (int i=stb.length-2;i>=0;i--)
        {
            //System.out.println("string is:"+sta[i]+"/");

            
             srev.append(" ");
              srev.append(stb[i]);
        }
        return srev.toString();
    }
}