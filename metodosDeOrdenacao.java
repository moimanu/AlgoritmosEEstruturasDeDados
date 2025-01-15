package metodosDeOrdenacao;

import java.util.Scanner;

public class MetodosDeOrdenacao {

    public static void imprimirVetor(int vetor[], int tamanhoVetor) {
        for (int i = 0; i < tamanhoVetor; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
    }

    public static void insertionSort(int vetor[], int tam) {
        int i;
        int j = 1;
        int aux;
        while (j < tam) {
            aux = vetor[j]; //aux = proximo item
            i = j - 1; //i = indice do item
            while ((i >= 0) && (vetor[i] > aux)) { //enquanto indice "existir" && for maior que o próximo
                vetor[i + 1] = vetor[i]; //o proximo se torna o atual
                i = i - 1; //e o indice diminui em 1
            }
            vetor[i + 1] = aux;
            j = j + 1;
        }
        
        //Imprimindo vetor ordenado:
        System.out.print("Lista ordenada: ");
        imprimirVetor(vetor, tam);
        System.out.println();
    }

    public static void selectionSort(int vetor[], int tam) {
        int i, j, min, aux;
        for (i = 0; i < tam - 1; i++) {
            min = i;
            for (j = i + 1; j < tam; j++) {
                if (vetor[j] < vetor[min]) {
                    min = j;
                }
            }
            if (vetor[i] != vetor[min]) {
                aux = vetor[i];
                vetor[i] = vetor[min];
                vetor[min] = aux;
            }
        }
        
        //Imprimindo vetor ordenado:
        System.out.print("Lista ordenada: ");
        imprimirVetor(vetor, tam);
        System.out.println();
    }

    public static void merge(int vetor[], int ini, int meio, int fim) {
        int com1 = ini, com2 = (meio + 1), comAux = 0;
        int vetAux[] = new int[fim - ini + 1];
        while (com1 <= meio && com2 <= fim) {
            if (vetor[com1] <= vetor[com2]) {
                vetAux[comAux] = vetor[com1];
                com1++;
            } else {
                vetAux[comAux] = vetor[com2];
                com2++;
            }
            comAux++;
        }
        while (com1 <= meio) {
            vetAux[comAux] = vetor[com1];
            comAux++;
            com1++;
        }
        while (com2 <= fim) {
            vetAux[comAux] = vetor[com2];
            comAux++;
            com2++;
        }
        for (comAux = ini; comAux <= fim; comAux++) {
            vetor[comAux] = vetAux[comAux - ini];
        }
    }

    public static void mergeSortFunction(int vetor[], int ini, int fim) {
        if (ini < fim) { //Se o vetor de entrada tiver mais de um elemento
            int meio = (ini + fim) / 2;
            mergeSortFunction(vetor, ini, meio);
            mergeSortFunction(vetor, meio + 1, fim);
            merge(vetor, ini, meio, fim);
        }
    }

    public static void mergeSort(int vetor[], int ini, int fim) {
        //Iniciando a recursão:
        mergeSortFunction(vetor, ini, fim);
        
        //Imprimindo vetor ordenado:
        System.out.print("Lista ordenada: ");
        imprimirVetor(vetor, vetor.length);
        System.out.println();
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
        for (int i = 0; i < tamanhoVetor; i++) {
            vetor[i] = t.nextInt();
        }
        System.out.println();
        
        //Imprimir vetor original
        System.out.print("Lista desordenada: ");
        imprimirVetor(vetor, tamanhoVetor);
        System.out.println();

        //Menu
        System.out.println("Como quer ordenar?\n1 - InsertionSort\n2 - SelectionSort\n3 - MergeSort");
        int opcao = t.nextInt();
        System.out.println();
        switch (opcao) {
            case 1 ->
                insertionSort(vetor, tamanhoVetor);
            case 2 ->
                selectionSort(vetor, tamanhoVetor);
            case 3 ->
                mergeSort(vetor, 0, tamanhoVetor - 1);
            default ->
                System.out.println("Opcao invalida...");
        }
    }
}
