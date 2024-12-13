package atividadesaeds;

import java.util.Scanner;

public class AtividadesAeds {
    
    public static void imprimirVetor(int vetor[], int tamanhoVetor){
        for(int i = 0; i < tamanhoVetor; i++){
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
    }

    public static int insertionSort(int vetor[], int tam){
        
        int cont = 0;
        int i;
        int j = 1;
        int aux;
        
        while(j < tam){
            
            aux = vetor[j]; //aux = proximo item
            i = j - 1; //i = indice do item
            
            while((i >= 0) && (vetor[i] > aux)){ //enquanto indice "existir" && for maior que o próximo
                vetor[i + 1] = vetor[i]; //o proximo se torna o atual
                i = i - 1; //e o indice diminui em 1
                
                cont++;
            }
            
            vetor[i + 1] = aux;
            j = j + 1;
        }
        return cont;
    }
    
    public static void main(String[] args) {

        Scanner t = new Scanner(System.in);
        
        //Definindo tamanho do vetor
        System.out.print("Informe o tamanho do seu vetor: ");
        int tamanhoVetor = t.nextInt();
        int vetor[] = new int[tamanhoVetor];
        System.out.println();
        
        //Entrada de itens no vetor
        System.out.println("Preencha o vetor: ");
        for(int i = 0; i < tamanhoVetor; i++){
            vetor[i] = t.nextInt();
        }
        System.out.println();
        
        //Imprimir vetor original
        System.out.print("Lista desordenada: ");
        imprimirVetor(vetor, tamanhoVetor);
        
        //InsertionSort
        int cont = insertionSort(vetor, tamanhoVetor);
        
        //Imprimir vetor ordenado
        System.out.print("Lista ordenada: ");
        imprimirVetor(vetor, tamanhoVetor);
        
        //Imprimir quantidade de comparacoes
        System.out.println();
        System.out.println("Quantidade de comparacoes: " + cont);
        
    }
    
}
