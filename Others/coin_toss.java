import java.util.ArrayList;

public class coin_toss {

	public static void main(String[] args) {
		System.out.println(coin(2));
	}

	public static ArrayList<String> coin(int n) {
		if (n == 0) {
			ArrayList<String> a = new ArrayList<>();
			a.add("");
			return a;
		}
		ArrayList<String> br = coin(n - 1);
		ArrayList<String> mr = new ArrayList<>();

		for (String val : br) {
			mr.add("H" + val);
			mr.add("T" + val);
		}
		return mr;
	}
}
