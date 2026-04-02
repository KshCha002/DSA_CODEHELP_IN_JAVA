class Solution {
    public boolean rotateString(String s, String goal) {
        if(s.length()!=goal.length()){return false;}
        if(s.equals(goal)){return true;}
       int i=0;
        while(i<(s.length()-1))
        {
            String sb= s.substring(i+1)+s.substring(0,i+1);
            System.out.println(sb);
            if(sb.equals(goal)){return true;}
            i++;
        }
        return false;
        
    }
}