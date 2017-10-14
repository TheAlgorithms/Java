
public class TriesClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tries tr= new Tries();
		tr.add("art");
		tr.add("arts");
		tr.add("boy");
		tr.add("bug");
		tr.add("see");
		tr.add("sea");
		tr.add("seen");
		tr.display();
		System.out.println("Number of Words: "+tr.numWords());
		System.out.println("-------------------------------");
		System.out.println(tr.search("qea"));

	}

}
