public class CountSort {


    private static long countComparacoes = 0;
    private static long countMovimentacoes = 0;

    public static long getCountComparacoes() {
        return countComparacoes;
    }

    public static long getCountMovimentacoes() {
        return countMovimentacoes;
    }

    public static void resetContadores() {
        countComparacoes = 0;
        countMovimentacoes = 0;
    }

    /**
     * Implementação do algoritmo CountSort.
     * @param vetor     // vetor de inteiros a ser ordenado.
     * @return // vetor ordenado.
     */
    public static int[] ordenarCountingSort(int[] vetor) {

        long inicio = System.currentTimeMillis(); // inicializa o tempo

        int n = vetor.length;
        if (n == 0)
            return vetor;

        int min = vetor[0];
        int max = vetor[0];

        // Encontra mínimo e máximo
        for (int i = 1; i < n; i++) {
            int v = vetor[i];

            countComparacoes++;
            if (v < min)
                min = v;

            countComparacoes++;
            if (v > max)
                max = v;
        }

        int intervalo = max - min + 1;

        int[] contagem = new int[intervalo]; // array de contagem

        // Conta frequência
        for (int valor : vetor) {
            contagem[valor - min]++;
            countMovimentacoes++;
        }

        // Acumula a contagem
        for (int i = 1; i < intervalo; i++) {
            contagem[i] += contagem[i - 1];
            countMovimentacoes++;
        }

        int[] ordenado = new int[n];

        // Preenche vetor final
        for (int i = n - 1; i >= 0; i--) {
            int valor = vetor[i];
            int idx = valor - min;

            int pos = contagem[idx] - 1;

            ordenado[pos] = valor;
            contagem[idx]--;

            countMovimentacoes += 2;
        }

        return ordenado;
    }
}
