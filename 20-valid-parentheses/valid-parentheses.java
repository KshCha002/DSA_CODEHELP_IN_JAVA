class Solution {
    public boolean isValid(String s) {
        if(s.length()%2!=0){return false;}
        Stack<Character> st= new Stack<>();
        
        for (char c: s.toCharArray())
        {
            if(c=='('||c=='['||c=='{'){
                st.push(c);
            }else if((c==')'||c==']'||c=='}') && !st.isEmpty() ) {
                char pop =st.pop();
                if(c==')' && pop!='(' ){return false;}
                if(c=='}' && pop!='{' ){return false;}
                if(c==']' && pop!='[' ){return false;}

            }
            else if((c==')'||c==']'||c=='}') && st.isEmpty()){return false;}
            
        }
        
        return st.isEmpty();
    }
}