//MAIN:
import java.util.ArrayList;
import java.util.Scanner;



public class Principal {
    public static void main(String[] args) {
        

    Scanner ler= new Scanner(System.in);
    int resp, i;
    String nomeBusca;
    boolean achou = false;

    ArrayList <Pessoa> agenda = new ArrayList<Pessoa>();
    
        System.out.println("\n op 1: Inserindo contatos"); //cadastrando
        do { 
            Pessoa p = new Pessoa();
            System.out.println("Nome:\n");
            p.setNome(ler.nextLine());
            System.out.println("Telefone:\n");
            p.setTelefone(ler.nextLine());
            agenda.add(p); //insere na array
            System.out.println("Deseja inserir outro contato? 1-Sim/2-Não");
            resp=ler.nextInt();
            ler.nextLine();
        } while (resp==1);
        System.out.println("op 2: Buscando contato");
        System.out.println("Digite o nome que deseja buscar");
        nomeBusca=ler.nextLine();
        for(i=0; i<agenda.size(); i++){
            if(agenda.get(i).getNome().equals(nomeBusca)){
                System.out.println("contato localizado");
                System.out.println("Telefone:" + agenda.get(i).getTelefone() );
                achou= true;
                System.out.println("\n Op 3: alterando");
                System.out.println("Digite o novo telefone");
                agenda.get(i).setTelefone(ler.nextLine());
                System.out.println("operação 4: Excluindo");
                System.out.println("Deseja excluir o contato? 1-Sim/2-Não");
                if(ler.nextInt()==1)
                    agenda.remove(agenda.get(i));

            }
        }
        if(!achou)
            System.out.println("Contato não localizado");

        System.out.println("Operação 5: Exibindo todos os contatos");
        for(i=0; i<agenda.size (); i++){
            System.out.println("Contato: "+i);
            System.out.println("\nNome:"+ agenda.get(i).getNome());
            System.out.println("\nTelefone:"+ agenda.get(i).getTelefone());
            System.out.println("-------------------------------");
        }
        System.out.println("Fim de programa");
    }

}
