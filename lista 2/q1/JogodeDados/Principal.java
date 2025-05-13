public class Principal {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.inserirJogadores();
        jogo.inserirApostas();
        jogo.lancarDados();
        jogo.mostrarResultado();
        jogo.mostrarVencedor();
    }
}
