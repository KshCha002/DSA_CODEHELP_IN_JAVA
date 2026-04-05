class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st= new Stack<>();
        for(String s:tokens )
        {
          
            if("*-+/".contains(s))
            {
                int a= st.pop();
                int b= st.pop();
                int ans=0;
                System.out.println("a="+a+"b="+b);
                char c= s.charAt(0);
                switch (c)
                {case '*':
                ans= b*a;
                break;
                case '+':
                ans= b+a;
                break;
                case '-':
                ans= b-a;
                break;
                case '/':
                ans= b/a;
                break;
                default:
                System.out.println("default");
            }
                
               
                st.push(ans);

            }else{
                st.push(Integer.parseInt(s));
            }

            
        }
        if(!st.isEmpty())
        {
            int res=st.pop();
            return res;
        }
        return -1;
    }
}
