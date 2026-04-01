class MinStack {
 private Stack<Integer> st;
 private Stack<Integer> min;
 
    public MinStack() {
       st = new Stack<>();
       min=new Stack<>();
    }
    
   public void push(int i){
      st.push(i);
      if(min.isEmpty()){min.push(i);}
      else
      {
        min.push(Math.min(i,min.peek()));
      }
    }
    public void  pop(){
        if(!st.isEmpty())
        { 
            st.pop();
            min.pop();
        
        }else{return;}
    }

    
    public int top() {
      return st.peek(); // Just return, don't modify
    }

    
    public int getMin() {
        return min.peek();
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */