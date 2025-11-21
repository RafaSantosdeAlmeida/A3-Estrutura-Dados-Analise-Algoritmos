public class Main {

    public static int[] gerarVetorAleatorio(int tamanho, int valorMaximoElementos) {
        java.util.Random r = new java.util.Random();
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = r.nextInt(valorMaximoElementos + 1);
        }
        return vetor;
    }

    public static void main(String[] args) {

        int repeticoes = 3;
        int[] tamanhos = {1000, 5000, 10000, 50000, 100000, 500000};

        System.out.println("====================================================");
        System.out.println("   MÉDIA DE TEMPO / COMPARAÇÕES / MOVIMENTAÇÕES" );
        System.out.println("   PARA CADA ORDENADOR EM CADA TAMANHO DE VETOR" );
        System.out.println("====================================================");

        for (int tamanho : tamanhos) {

            long somaTempoCount = 0, somaTempoInsertion = 0, somaTempoMerge = 0, somaTempoQuick = 0;
            long somaCompCount = 0, somaCompInsertion = 0, somaCompMerge = 0, somaCompQuick = 0;
            long somaMovCount = 0, somaMovInsertion = 0, somaMovMerge = 0, somaMovQuick = 0;

            for (int r = 0; r < repeticoes; r++) {

                int[] vetorBase = gerarVetorAleatorio(tamanho, 1000);

                // COUNT SORT
                CountSort.resetContadores();
                int[] v1 = vetorBase.clone();
                long inicio = System.nanoTime();
                CountSort.ordenarCountingSort(v1);
                long tempo = (System.nanoTime() - inicio);
                somaTempoCount += tempo;
                somaCompCount += CountSort.getCountComparacoes();
                somaMovCount += CountSort.getCountMovimentacoes();

                // INSERTION SORT
                InsertionSort.resetContadores();
                int[] v2 = vetorBase.clone();
                inicio = System.nanoTime();
                InsertionSort.ordenarInsertionSort(v2);
                tempo = (System.nanoTime() - inicio);
                somaTempoInsertion += tempo;
                somaCompInsertion += InsertionSort.getComparacoes();
                somaMovInsertion += InsertionSort.getMovimentacoes();

                // MERGE SORT
                MergeSort.resetContadores();
                int[] v3 = vetorBase.clone();
                inicio = System.nanoTime();
                MergeSort.mergeSort(v3);
                tempo = (System.nanoTime() - inicio);
                somaTempoMerge += tempo;
                somaCompMerge += MergeSort.getComparacoes();
                somaMovMerge += MergeSort.getMovimentacoes();

                // QUICK SORT
                QuickSort.resetContadores();
                int[] v4 = vetorBase.clone();
                inicio = System.nanoTime();
                QuickSort.quickSort(v4, 0, v4.length - 1);
                tempo = (System.nanoTime() - inicio);
                somaTempoQuick += tempo;
                somaCompQuick += QuickSort.getComparacoes();
                somaMovQuick += QuickSort.getMovimentacoes();
            }

            System.out.println("TAMANHO DO VETOR: " + tamanho);
            System.out.println("----------------------------------------------------");
            System.out.printf("%-15s %-12s %-15s %-15s%n", "ALGORITMO", "TEMPO(ns)", "COMPARAÇÕES", "MOVIMENTAÇÕES");

            System.out.printf("%-15s %-12d %-15d %-15d%n", "COUNT SORT", somaTempoCount/repeticoes, somaCompCount/repeticoes, somaMovCount/repeticoes);
            System.out.printf("%-15s %-12d %-15d %-15d%n", "INSERTION", somaTempoInsertion/repeticoes, somaCompInsertion/repeticoes, somaMovInsertion/repeticoes);
            System.out.printf("%-15s %-12d %-15d %-15d%n", "MERGE", somaTempoMerge/repeticoes, somaCompMerge/repeticoes, somaMovMerge/repeticoes);
            System.out.printf("%-15s %-12d %-15d %-15d%n", "QUICK", somaTempoQuick/repeticoes, somaCompQuick/repeticoes, somaMovQuick/repeticoes);

            System.out.println("----------------------------------------------------");
        }

        // int[] vetorBase = gerarVetorAleatorio(7, 10);

        // QuickSort.quickSort(vetorBase, 0, vetorBase.length - 1);

        // System.out.print(vetorBase[0]);
        // for (int i = 1; i < vetorBase.length; i++) {
        //     System.out.print(", " + vetorBase[i]);
        // }
    }
}