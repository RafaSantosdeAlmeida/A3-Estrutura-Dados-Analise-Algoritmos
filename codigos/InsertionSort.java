public class InsertionSort {

    private static long countComparacoes = 0;
    private static long countMovimentacoes = 0;

    public static long getComparacoes() {
        return countComparacoes;
    }

    public static long getMovimentacoes() {
        return countMovimentacoes;
    }

    public static void resetContadores() {
        countComparacoes = 0;
        countMovimentacoes = 0;
    }

    /**
     * Implementação do algoritmo Insertion Sort.
     * @param vetor // vetor original
     */
    public static void ordenarInsertionSort(int[] vetor) {

        int n = vetor.length;

        for (int i = 1; i < n; i++) {

            int chave = vetor[i];
            int j = i - 1;

            // Comparação inicial da entrada no loop
            countComparacoes++;

            while (j >= 0 && vetor[j] > chave) {

                countComparacoes++; // comparação do while

                vetor[j + 1] = vetor[j];
                countMovimentacoes++; // movimentação

                j--;
            }

            vetor[j + 1] = chave;
            countMovimentacoes++; // inserção da chave
        }
    }
}
