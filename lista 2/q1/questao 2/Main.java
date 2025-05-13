import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        int op;

        do {
            System.out.println("\n----------- Menu -----------");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar notas");
            System.out.println("3 - Calcular médias");
            System.out.println("4 - Informar situações");
            System.out.println("5 - Informar dados de um aluno");
            System.out.println("6 - Alterar nota");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");
            op = s.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Quantos alunos deseja cadastrar? ");
                    int qtd = s.nextInt();
                    for (int i = 0; i < qtd; i++) {
                        Aluno novoAluno = new Aluno();
                        System.out.print("Digite a matrícula: ");
                        int matricula = s.nextInt();
                        s.nextLine(); 
                        System.out.print("Digite o nome: ");
                        String nome = s.nextLine();
                        novoAluno.setMatricula(matricula);
                        novoAluno.setNome(nome);
                        listaAlunos.add(novoAluno);
                    }
                    break;

                case 2:
                    for (Aluno a : listaAlunos) {
                        System.out.println("Digite as 3 notas do aluno " + a.getNome());
                        float n1 = s.nextFloat();
                        float n2 = s.nextFloat();
                        float n3 = s.nextFloat();
                        a.setNota1(n1);
                        a.setNota2(n2);
                        a.setNota3(n3);
                        a.calcularMedia();
                    }
                    break;

                case 3:
                    for (Aluno a : listaAlunos) {
                        System.out.println("Aluno: " + a.getNome() + " - Média: " + a.getMedia());
                    }
                    break;

                case 4:
                    for (Aluno a : listaAlunos) {
                        System.out.println("Aluno: " + a.getNome() + " - Situação: " + a.getSituacao());
                    }
                    break;

                case 5:
                    System.out.print("digite a matrícula do aluno: ");
                    int mat = s.nextInt();
                    boolean encontrado = false;
                    for (Aluno a : listaAlunos) {
                        if (a.getMatricula() == mat) {
                            a.exibirDados();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("aluno não encontrado");
                    }
                    break;

                case 6:
                    System.out.print("Digite a matrícula do aluno: ");
                    int matriculaAlt = s.nextInt();
                    boolean alunoEncontrado = false;

                    for (Aluno a : listaAlunos) {
                        if (a.getMatricula() == matriculaAlt) {
                            alunoEncontrado = true;

                            System.out.println (a.getNome());
                            System.out.println("1 - Nota 1\n2 - Nota 2\n3 - Nota 3");
                            System.out.print("qual nota deseja alterar? ");
                            int qualNota = s.nextInt();

                            if (qualNota < 1 || qualNota > 3) {
                                System.out.println("numero de nota invalido!");
                                break;
                            }

                            System.out.println("digite o novo valor da nota: ");
                            float novaNota = s.nextFloat();

                            a.alterarNota(qualNota, novaNota);
                            System.out.println("\n nota atualizada!");
                            System.out.println("nova média: " + a.getMedia());
                            System.out.println("nova situação: " + a.getSituacao());
                            break;
                        }
                    }

                    if (!alunoEncontrado) {
                        System.out.println("aluno não encontrado.");
                    }
                    break;

                case 7:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (op != 7);

    }
}

