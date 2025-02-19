package metodosdeordenacao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class MetodosDeOrdenacao {
    
    private final static long seed = 987654321012345678L;
    private final static Random r = new Random(seed);

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
    
    public static long quickSort(int[] A, int p, int r) {
        long tInicial = System.nanoTime();
        if (p < r) {
            int q = partition(A, p, r);
            quickSort(A, p, q - 1);
            quickSort(A, q + 1, r);
        }
        long tFinal = System.nanoTime();
        return tFinal - tInicial;
    }

    public static int partition(int[] A, int p, int r) {
        int aux;
        int pivo = A[r];
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (A[j] <= pivo) {
                i++;
                aux = A[i];
                A[i] = A[j];
                A[j] = aux;
            }
        }
        // Troca final para posicionar o pivô corretamente
        aux = A[i + 1];
        A[i + 1] = A[r];
        A[r] = aux;
        return i + 1;
    }

    public static void main(String[] args) throws IOException {
        
        int tamanhos[] = {100, 500, 1000, 5000, 20000, 50000, 100000, 500000};

        //Nome do arquivo
        String arquivo = "dados.csv";

        //Linhas do arquivo
        String cabecalho = "";
        String linhaInsertion = "InsertionSort";
        String linhaSelection = "SelectionSort";
        String linhaMerge = "MergeSort";
        String linhaBubble = "BubbleSort";
        String linhaQuick = "QuickSort";
        
        //Para cada tipo de vetor (1,2 ou 3)
        for(int i = 1; i < 4; i++) {

            //Imprimindo
            String tipoVetor = "";
            switch(i){
                case 1 -> tipoVetor = "crescente";
                case 2 -> tipoVetor = "decrescente";
                case 3 -> tipoVetor = "aleatorio";
            }
            System.out.println("\n" + i + ") Executando ordenacao para vetor " + tipoVetor + "\n");

            //Para cada tamanho de vetor
            for(int tamanho : tamanhos){

                int tamanhoVetor = tamanho;
                int vetor[] = gerarVetor(tamanhoVetor, i);

                System.out.println("Tamanho do vetor: " + tamanhoVetor);
                
                //Copiando os vetores
                int vetorInsertion[] = vetor.clone();
                int vetorSelection[] = vetor.clone();
                int vetorMerge[] = vetor.clone();
                int vetorBubble[] = vetor.clone();
                int vetorQuick[] = vetor.clone();

                //Aumentando as colunas
                cabecalho += ", V" + tamanho + " " + tipoVetor;
                
                System.out.print("insertionSort ---");
                linhaInsertion += "," + insertionSort(vetorInsertion, tamanhoVetor);
                System.out.println("-> Concluido");

                System.out.print("selectionSort ---");
                linhaSelection += "," + selectionSort(vetorSelection, tamanhoVetor);
                System.out.println("-> Concluido");
                
                System.out.print("mergeSort -------");
                linhaMerge += "," + mergeSort(vetorMerge, 0, tamanhoVetor - 1);
                System.out.println("-> Concluido");
                
                System.out.print("bubbleSort ------");
                linhaBubble += "," + bubbleSort(vetorBubble, tamanhoVetor);
                System.out.println("-> Concluido");

                System.out.print("quickSort -------");
                linhaQuick += "," + quickSort(vetorQuick, 0, tamanhoVetor - 1);
                System.out.println("-> Concluido");
            
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
            writer.println(linhaQuick);

            File file = new File(arquivo);
            System.out.println("Arquivo criado em: " + file.getAbsolutePath());
        } catch(IOException e){
            System.err.println("Erro ao criar arquivo.");
        }
    }
}