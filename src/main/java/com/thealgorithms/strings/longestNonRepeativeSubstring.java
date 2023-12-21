package nonrepetivesubstring;

import java.util.HashMap;

public class Substring {

	public static int main(String[] args) {
		// TODO Auto-generated method stub
		String tr="wassidssapi";
		int start=0,asl=0,i=0;
		
		HashMap<Character,Integer> storage = new HashMap<>();
		
		while(i< tr.length())
			{
			char temp= tr.charAt(i);
			
			if(!storage.containsKey(temp)) storage.put(temp,0);
			
			else if(tr.charAt(start)== temp) start++;
			
			else if(tr.charAt(i-1) == temp) {
			if (asl< storage.size()) {
				asl= storage.size();
				storage= new HashMap<>();
				start=i;
				i--;}
			else
			{
			if(asl<storage.size()) asl= storage.size(); 
			
			while(tr.charAt(start)!= temp) {
				storage.remove(tr.charAt(start));
				start++;
			}start++;
			}
			i++;
						
				
			}
				
			}
			
		
	
			
			
			
			
			
			if (asl<storage.size()) asl=storage.size();
			return asl;

		}	}

}
