public class Aluno {
    private String nome;
    private int matricula;
    private float nota1, nota2, nota3;
    private float media;
    private String situacao;

    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) {
        this.nome = nome; 
    }

    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) { 
        this.matricula = matricula; 
    }

    public float getNota1() { 
        return nota1; 
    }
    public void setNota1(float nota1) {
        this.nota1 = nota1; 
    }

    public float getNota2() {
        return nota2; 
    }
    public void setNota2(float nota2) {
        this.nota2 = nota2; 
    }

    public float getNota3() {
        return nota3; 
    }
    public void setNota3(float nota3) {
        this.nota3 = nota3; 
    }

    public float getMedia() {
        return media;
    }
    public String getSituacao() {
        return situacao; 
    }

    public void calcularMedia() {
        media = (nota1 + nota2 + nota3) / 3;
        if (media >= 7) {
            situacao = "aprovado";
        }else
             if (media >= 5) {
                situacao = "recuperaçao";
            } else if (media < 3) {
                situacao = "reprovado1";
        }
    }

    public void alterarNota(int codNota, float valorNota) {
        switch (codNota) {
            case 1 -> nota1 = valorNota;
            case 2 -> nota2 = valorNota;
            case 3 -> nota3 = valorNota;
        }
        calcularMedia();
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Notas: " + nota1 + ", " + nota2 + ", " + nota3);
        System.out.println("Média: " + media);
        System.out.println("Situação: " + situacao);
    }
}



