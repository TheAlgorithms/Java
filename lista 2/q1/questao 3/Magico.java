import java.util.Scanner;

public class Magico {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[][] matriz = new int[N][N];

       
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matriz[i][j] = s.nextInt();
            }
        }

        
        int somaMagica = 0;
        for (int j = 0; j < N; j++) {
            somaMagica += matriz[0][j];
        }

        
        for (int i = 1; i < N; i++) {
            int somaLinha = 0;
            for (int j = 0; j < N; j++) {
                somaLinha += matriz[i][j];
            }
            if (somaLinha != somaMagica) {
                System.out.println(-1);
                return;
            }
        }

       
        for (int j = 0; j < N; j++) {
            int somaColuna = 0;
            for (int i = 0; i < N; i++) {
                somaColuna += matriz[i][j];
            }
            if (somaColuna != somaMagica) {
                System.out.println(-1);
                return;
            }
        }

        
        int somaDiagonalPrincipal = 0;
        for (int i = 0; i < N; i++) {
            somaDiagonalPrincipal += matriz[i][i];
        }
        if (somaDiagonalPrincipal != somaMagica) {
            System.out.println(-1);
            return;
        }

        
        int somaDiagonalSecundaria = 0;
        for (int i = 0; i < N; i++) {
            somaDiagonalSecundaria += matriz[i][N - 1 - i];
        }
        if (somaDiagonalSecundaria != somaMagica) {
            System.out.println(-1);
            return;
        }

        
        System.out.println(somaMagica);
    }
}

