public class Matriz {
    private int[][] dados;
    private int ordem;

    public Matriz(int ordem) {
        this.ordem = ordem;
        this.dados = new int[ordem][ordem];
    }

    public void preencher(Scanner s) {
        for (int i = 0; i < ordem; i++) {
            for (int j = 0; j < ordem; j++) {
                dados[i][j] = s.nextInt();
            }
        }
    }

    public int somaLinha(int linha) {
        int soma = 0;
        for (int j = 0; j < ordem; j++) {
            soma += dados[linha][j];
        }
        return soma;
    }

    public int somaColuna(int coluna) {
        int soma = 0;
        for (int i = 0; i < ordem; i++) {
            soma += dados[i][coluna];
        }
        return soma;
    }

    public int somaDiagonalPrincipal() {
        int soma = 0;
        for (int i = 0; i < ordem; i++) {
            soma += dados[i][i];
        }
        return soma;
    }

    public int somaDiagonalSecundaria() {
        int soma = 0;
        for (int i = 0; i < ordem; i++) {
            soma += dados[i][ordem - 1 - i];
        }
        return soma;
    }

    public boolean ehQuadradoMagico() {
        int referencia = somaLinha(0);

        for (int i = 1; i < ordem; i++) {
            if (somaLinha(i) != referencia) return false;
        }

        for (int j = 0; j < ordem; j++) {
            if (somaColuna(j) != referencia) return false;
        }

        if (somaDiagonalPrincipal() != referencia || somaDiagonalSecundaria() != referencia)
            return false;

        return true;
    }

    public int getSomaMagica() {
        return somaLinha(0);
    }
}
