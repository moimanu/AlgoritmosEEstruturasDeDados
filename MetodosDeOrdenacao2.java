package metodosdeordenacao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class MetodosDeOrdenacao {
    
    private final static Scanner t = new Scanner(System.in);
    private final static Random r = new Random();

    public static int[] gerarVetor(int tam, int opcao){
        int vetor[] = new int[tam];
        
        switch (opcao){
            case 1 -> {
                for(int i = 0; i < tam; i++){
                    vetor[i] = i + 1;
                }
            }
            case 2 -> {
                for(int i = tam; i > 0; i--){
                    vetor[tam - i] = i;
                }
            }
            case 3 -> {
                for(int i = 0; i < tam; i++){
                    vetor[i] = r.nextInt(tam);
                }
            }
            default -> {
                System.err.println("Opcao fora da range determinada...");
            }
        }
        
        return vetor;
    }

    public static long bubbleSort(int vetor[], int tam){
        long tInicial = System.nanoTime();
        int i, fim, aux;
        for(fim = tam - 1; fim > 0; fim--){
            for(i = 0; i < fim; i++) {
                if(vetor[i] > vetor[i + 1]){
                    aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                }
            }
        }
        long tFinal = System.nanoTime();
        return tFinal - tInicial;
    }
    
    public static long insertionSort(int vetor[], int tam) {
        long tInicial = System.nanoTime();
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
        long tFinal = System.nanoTime();
        return tFinal - tInicial;
    }

    public static long selectionSort(int vetor[], int tam) {
        long tInicial = System.nanoTime();
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
        long tFinal = System.nanoTime();
        return tFinal - tInicial;
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

    public static long mergeSort(int vetor[], int ini, int fim) {
        long tInicial = System.nanoTime();
        //Iniciando a recursão:
        mergeSortFunction(vetor, ini, fim);
        long tFinal = System.nanoTime();
        return tFinal - tInicial;
    }

    public static void main(String[] args) throws IOException {
        
        int tamanhos[] = {100, 500, 1000, 5000, 20000, 50000, 500000};

        String arquivo = "dados.csv";

        String cabecalho = "";
        String linhaInsertion = "InsertionSort";
        String linhaSelection = "SelectionSort";
        String linhaMerge = "MergeSort";
        String linhaBubble = "BubbleSort";
        String tipoVetor = "";
        
        for(int i = 1; i < 4; i++) {

            System.out.println();
            switch(i){
                case 1 -> tipoVetor = "Crescente";
                case 2 -> tipoVetor = "Decrescente";
                case 3 -> tipoVetor = "Aleatorio";
            }
            System.out.println("1) Executando ordenacao para vetor " + tipoVetor);

            for(int tamanho : tamanhos){
                //Definindo tamanho do vetor
                int tamanhoVetor = tamanho;
                int vetor[] = gerarVetor(tamanhoVetor, i);

                //Copiando os vetores
                int vetorInsertion[] = vetor.clone();
                int vetorSelection[] = vetor.clone();
                int vetorMerge[] = vetor.clone();
                int vetorBubble[] = vetor.clone();

                //Aumentando as colunas e imprimindo a execucao
                cabecalho += ", Tam" + tamanho;
                System.out.print("insertionSort --- tamV: " + tamanho);
                linhaInsertion += "," + insertionSort(vetorInsertion, tamanhoVetor);
                System.out.println(" -> Concluido");
                
                System.out.print("selectionSort --- tamV: " + tamanho);
                linhaSelection += "," + selectionSort(vetorSelection, tamanhoVetor);
                System.out.println(" -> Concluido");
                
                System.out.print("mergeSort ------- tamV: " + tamanho);
                linhaMerge += "," + mergeSort(vetorMerge, 0, tamanhoVetor - 1);
                System.out.println(" -> Concluido");
                
                System.out.print("bubbleSort ------ tamV: " + tamanho);
                linhaBubble += "," + bubbleSort(vetorBubble, tamanhoVetor);
                System.out.println(" -> Concluido");
            
                System.out.println();
            }
        }
            
        try(PrintWriter writer = new PrintWriter(new FileWriter(arquivo))){
            writer.println();
            writer.println(cabecalho);
            writer.println(linhaInsertion);
            writer.println(linhaSelection);
            writer.println(linhaMerge);
            writer.println(linhaBubble);

            File file = new File(arquivo);
            System.out.println("Arquivo criado em: " + file.getAbsolutePath());
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
