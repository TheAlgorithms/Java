package syntax_checking;

public class SyntaxCheckerMain {
	public static void main(String[] args) {
		SyntaxChecker sc = new SyntaxChecker();
		
		String code = "<<<[]>>>";
		
		System.out.println(sc.syntaxChecker(code));
	}
}
