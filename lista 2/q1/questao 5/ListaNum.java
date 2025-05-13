/*5. Escreva um código que:
• Crie um ArrayList de inteiros. ArrayList<Integer>
• Adicione 6 números aleatórios à lista.
• Exiba a soma dos elementos da lista.
• Exiba a média dos elementos da lista.
• Exiba o menor elemento da lista
• Exiba o maior elemento da lista
• Exiba a lista ordenada em ordem crescente
• Exiba a lista ordenada em ordem decrescente
• Peça ao usuário para digitar um número e informe se o número foi
encontrado e, se foi, quantas vezes ele ocorre na lista*/

import java.util.ArrayList;
import java.util.Scanner;


public class ListaNum {
    public static void main(String[] args) {
    
    ArrayList <Integer> Lista = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Lista.add(1);
    Lista.add(3);
    Lista.add(2);
    Lista.add(4);
    Lista.add(1);
    Lista.add(6);
    System.out.println("lista:"+ Lista);


    int soma=0;
    for(int i=0; i<Lista.size(); i++) {
        soma += Lista.get(i);

    }
    System.out.println("\nSoma de todos os números:"+ soma);

    int media=0;
    
    media = soma/Lista.size();
    System.out.println("\nmedia:"+ media);

    int menor=10000;
    for(int i=0; i<Lista.size(); i++) {
        if(Lista.get(i)<menor){
            menor=Lista.get(i);
        }

    }

    System.out.println("menor numero:" + menor);


    int maior= -10000;
    for(int i=0; i<Lista.size(); i++) {
        if(Lista.get(i)> maior){
            maior=Lista.get(i);
        }

    }

    System.out.println("maior numero:" + maior);


ArrayList<Integer> decrescente = new ArrayList<>(Lista);
    for (int i = 0; i < decrescente.size(); i++) {
        for (int j = i + 1; j < decrescente.size(); j++) {
            if (decrescente.get(i) < decrescente.get(j)) {
                int temp = decrescente.get(i);
                decrescente.set(i, decrescente.get(j));
                decrescente.set(j, temp);
            }
        }
    }
     System.out.println("Lista em ordem decrescente: " + decrescente);

ArrayList<Integer> crescente = new ArrayList<>(Lista);
    for (int i = 0; i < crescente.size(); i++) {
        for (int j = i + 1; j < crescente.size(); j++) {
            if (crescente.get(i) > crescente.get(j)) {
                int temp = crescente.get(i);
                crescente.set(i, crescente.get(j));
                crescente.set(j, temp);
            }
        }
    }
    System.out.println("Lista em ordem crescente: " + crescente);


    System.out.println("Digite um número para buscar: ");
    int numeroBusca = scanner.nextInt();
    int contador = 0;

    for (int i = 0; i < Lista.size(); i++) {
        if (Lista.get(i) == numeroBusca) {
            contador++;
        }
    }
    if (contador > 0) {
        System.out.println("O número " + numeroBusca + " aparece " + contador + " vez(es) na lista!:)");
    } else {
        System.out.println("O número " + numeroBusca + " não foi encontrado na lista:(");
        }
    }
     
}
