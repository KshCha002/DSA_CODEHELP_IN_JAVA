class Solution {
    public String reverseWords(String s) {
        //System.out.println("stringA before trim"+s);
        s=s.trim();
        //System.out.println("stringA after trim"+s);
        String [] sta= s.split(" ");
        String [] stb= s.split("\\s+");
        StringBuilder srev= new StringBuilder();
        //srev.append(stb[stb.length-1]);
        // for (int i=stb.length-2;i>=0;i--)
        // {
        //     //System.out.println("string is:"+sta[i]+"/");

            
        //      srev.append(" ");
        //       srev.append(stb[i]);
        // }
int i=0;
int j= stb.length-1;
        while(i<j){
            swap(stb,i,j);
            i++;
            j--;
        }
        return String.join(" ",stb);
    }
    public static void swap(String [] stb,int i,int j)
    {
        String temp= stb[i];
        stb[i]=stb[j];
        stb[j]=temp;
    }
}