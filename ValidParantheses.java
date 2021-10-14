// Simple java solution :

public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
		st.push(s.charAt(0));
        for(int i =1; i<s.length();i++)
        {
            char ch = s.charAt(i);
            
            if(st.empty() ){
				if(i != s.length()-1){
					st.push(ch);
					continue;
				}else{
					return false;
				}
			}
			if(st.peek() == '(' ){
				if(ch == ')'){
					st.pop();
				}else{
					st.push(ch);
				}
			}
			else if(st.peek() == '{'){
				if(ch == '}'){
					st.pop();
				}else{
					st.push(ch);
				}
			}else if(st.peek() == '['){
				if(ch == ']'){
					st.pop();
				}else{
					st.push(ch);
				}
			}
        }
        if(st.empty()){
            return true;
        }
        return false;
        
    }
