import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int ordem = s.nextInt();

        Matriz m = new Matriz(ordem);
        m.preencher(s);

        if (m.ehQuadradoMagico()) {
            System.out.println(m.getSomaMagica());
        } else {
            System.out.println(-1);
        }

        s.close();
    }
}

