import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    private static long comparacoes = 0;
    private static long movimentacoes = 0;

    public static long getComparacoes() {
        return comparacoes;
    }

    public static long getMovimentacoes() {
        return movimentacoes;
    }

    public static void resetContadores() {
        comparacoes = 0;
        movimentacoes = 0;
    }

    /**
     * Faz a partição do vetor usando o pivô.
     * @param vetor Vetor a ser particionado.
     * @param inicio Índice inicial.
     * @param fim Índice final.
     * @return Posição final do pivô após a partição.
     */
    private static int separar(int[] vetor, int inicio, int fim) {

        // Escolhe um pivô aleatório e troca com a posição final
        int indiceAleatorio = ThreadLocalRandom.current().nextInt(inicio, fim + 1);
        int temp = vetor[indiceAleatorio];
        vetor[indiceAleatorio] = vetor[fim];
        vetor[fim] = temp;

        movimentacoes += 3; // troca = 3 atribuições

        int pivo = vetor[fim];
        int indiceMenores = inicio;

        // Loop de particionamento
        for (int i = inicio; i < fim; i++) {

            comparacoes++;
            if (vetor[i] <= pivo) {

                // troca vetor[indiceMenores] com vetor[i]
                temp = vetor[indiceMenores];
                vetor[indiceMenores] = vetor[i];
                vetor[i] = temp;

                movimentacoes += 3;
                indiceMenores++;
            }
        }

        // Troca final do pivô
        temp = vetor[indiceMenores];
        vetor[indiceMenores] = vetor[fim];
        vetor[fim] = temp;

        movimentacoes += 3;

        return indiceMenores;
    }

    /**
     * Implementação do algoritmo QuickSort.
     * @param vetor Vetor a ser ordenado.
     * @param inicio Índice inicial.
     * @param fim Índice final.
     */
    public static void quickSort(int[] vetor, int inicio, int fim) {

        comparacoes++; // comparação do "inicio < fim"

        if (inicio < fim) {

            int posPivo = separar(vetor, inicio, fim);

            quickSort(vetor, inicio, posPivo - 1);
            quickSort(vetor, posPivo + 1, fim);
        }
    }

}