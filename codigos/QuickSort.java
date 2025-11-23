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
     * Calcula e retorna o indice mediano de três do vetor.
     * @param vetor Vetor a ser calculado o indice mediano.
     * @param inicio Índice inicial.
     * @param fim Índice final.
     * @return Indice mediano.
     */
    private static int medianaTres(int[] vetor, int inicio, int fim) {
        int meio = (inicio + fim) / 2;

        int a = vetor[inicio];
        int b = vetor[meio];
        int c = vetor[fim];

        comparacoes += 2;   // Média entre 1 e 3 comparações
        if ((a > b) != (a > c)) return inicio;
        else if ((b > a) != (b > c)) return meio;
        else return fim;
    }

    /**
     * Faz a partição do vetor usando o método de Hoare.
     * @param vetor Vetor a ser particionado.
     * @param inicio Índice inicial.
     * @param fim Índice final.
     * @return Posição final do pivô após a partição.
     */
    private static int separar(int[] vetor, int inicio, int fim) {

        int temp;
        
        int indicePivo = medianaTres(vetor, inicio, fim);   // Calcula o indice do pivô

        // Troca o pivô pelo primeiro elemento do vetor
        temp = vetor[indicePivo];
        vetor[indicePivo] = vetor[inicio];
        vetor[inicio] = temp;
        movimentacoes += 2;

        int pivo = vetor[inicio];       // Escolha do pivô

        int indiceEsquerda = inicio - 1;    // Indice esquerda
        int indiceDireita = fim + 1;        // Indice direita

        while (true) { 

            do { 
                indiceEsquerda++;
            } while (vetor[indiceEsquerda] < pivo);

            do { 
                indiceDireita--;
            } while (vetor[indiceDireita] > pivo);

            if (indiceEsquerda >= indiceDireita) return indiceDireita;
            comparacoes++;

            // Troca o elemento no indice da esquerda com o elemento do indice da direita
            temp = vetor[indiceEsquerda];
            vetor[indiceEsquerda] = vetor[indiceDireita];
            vetor[indiceDireita] = temp;
            movimentacoes += 2;

        }

    }

    /**
     * Implementação do algoritmo QuickSort.
     * @param vetor Vetor a ser ordenado.
     * @param inicio Índice inicial.
     * @param fim Índice final.
     */
    public static void quickSort(int[] vetor, int inicio, int fim) {

        comparacoes++;

        if (inicio < fim) {

            int posPivo = separar(vetor, inicio, fim);

            quickSort(vetor, inicio, posPivo);// quickSort(vetor, inicio, posPivo - 1);
            quickSort(vetor, posPivo + 1, fim);// quickSort(vetor, posPivo + 1, fim);
        }
    }

}