/*O sistema consiste de 2 dados e uma quantidade X de jogadores
informada ao iniciar o jogo. Cada jogador escolhe um valor para
apostar, após todos os jogadores informarem sua aposta os dados
são lançados. O sistema apresenta o resultado: Se a soma do valor
das faces dos dados for igual ao valor de uma das apostas, o sistema
informa qual o jogador vencedor, caso nenhum jogador acerte o
valor, é informado que o computador venceu. */
import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private int resultado;
    private Dado dado1;
    private Dado dado2;
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public void inserirJogadores() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a quantidade de jogadores: ");
        int qtdJog = sc.nextInt();
        sc.nextLine(); // consumir a quebra de linha

        for (int i = 0; i < qtdJog; i++) {
            Jogador jogador = new Jogador();
            System.out.print("Digite o nome do jogador " + (i + 1) + ": ");
            jogador.setNome(sc.nextLine());
            jogadores.add(jogador);
        }
    }

    public void inserirApostas() {
        Scanner sc = new Scanner(System.in);
        for (Jogador jogador : jogadores) {
            System.out.print("Digite a aposta de " + jogador.getNome() + ": ");
            int aposta = sc.nextInt();
            jogador.setValorAposta(aposta);
        }
    }

    public void lancarDados() {
        dado1 = new Dado();
        dado2 = new Dado();
        dado1.setValorFace();
        dado2.setValorFace();
    }

    public void mostrarResultado() {
        resultado = dado1.getValorFace() + dado2.getValorFace();
        System.out.println("Resultado dos dados: " + dado1.getValorFace() + " + " + dado2.getValorFace());
        System.out.println("Soma = " + resultado);
    }

    public void mostrarVencedor() {
        boolean houveVencedor = false;
        for (Jogador jogador : jogadores) {
            if (jogador.getValorAposta() == resultado) {
                System.out.println(jogador.getNome() + " venceu!");
                houveVencedor = true;
                break;
            }
        }
        if (!houveVencedor) {
            System.out.println("Máquina venceu!");
        }
    }
}

    