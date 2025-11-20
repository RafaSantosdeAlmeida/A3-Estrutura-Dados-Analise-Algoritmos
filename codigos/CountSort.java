import java.util.Random;

public class CountSort {

    // Classe para armazenar estatísticas da ordenação
    public static class SortStats {
        public long countComparacoes = 0;
        public long countMovimentacoes = 0;
        public long tempoMiliseg = 0;
    }

    public static int[] gerarVetorAleatorio(int tamanho) {
        Random random = new Random();
        int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(1000000);
        }

        return vetor;
    }

    /**
     * @param vetor     // vetor de inteiros a ser ordenado
     * @param crescente // define a ordem, se será crescente (true) ou decrescente
     *                  (false)
     * @param estat     // objeto que armazena estatísticas da ordenação
     * @return // vetor ordenado (nova cópia)
     */

    public static int[] ordenarCountingSort(int[] vetor, boolean crescente, SortStats estat) {

        long inicio = System.currentTimeMillis(); // inicializa o tempo

        int n = vetor.length;
        if (n == 0)
            return vetor;

        int min = vetor[0];
        int max = vetor[0];

        // Encontra mínimo e máximo
        for (int i = 1; i < n; i++) {
            estat.countComparacoes++;
            if (vetor[i] < min)
                min = vetor[i];

            estat.countComparacoes++;
            if (vetor[i] > max)
                max = vetor[i];
        }

        int intervalo = max - min + 1;

        int[] contagem = new int[intervalo]; // array de contagem

        // Conta frequência
        for (int valor : vetor) {
            contagem[valor - min]++;
            estat.countMovimentacoes++;
        }

        // Ajusta posições (crescente ou decrescente)
        if (crescente == true) {
            for (int i = 1; i < intervalo; i++) {
                contagem[i] += contagem[i - 1];
                estat.countMovimentacoes++;
            }
        } else {
            for (int i = intervalo - 2; i >= 0; i--) {
                contagem[i] += contagem[i + 1];
                estat.countMovimentacoes++;
            }
        }

        int[] ordenado = new int[n];

        // Preenche vetor final
        for (int i = n - 1; i >= 0; i--) {
            int valor = vetor[i];
            int pos = contagem[valor - min] - 1;

            ordenado[pos] = valor;
            contagem[valor - min]--;

            estat.countMovimentacoes += 2; // adiciono um valor no array ordenado e decremento no array contagem
        }

        estat.tempoMiliseg = System.currentTimeMillis() - inicio; // tempo total = tempo final - tempo inicial

        return ordenado;
    }

    public static void main(String[] args) {

        int[] tamanhos = {
                1000,
                5000,
                10000,
                50000,
                100000,
                500000,
                1000000
        };

        for (int tamanho : tamanhos) {

            System.out.println("\n---------------------------");
            System.out.println(" VETOR DE TAMANHO: " + tamanho);
            System.out.println("---------------------------\n");

            // gera vetor aleatório
            int[] vetor = CountSort.gerarVetorAleatorio(tamanho);

            // estatísticas
            CountSort.SortStats estatCresc = new CountSort.SortStats();
            CountSort.SortStats estatDesc = new CountSort.SortStats();

            // ordenações
            CountSort.ordenarCountingSort(vetor.clone(), true, estatCresc);
            CountSort.ordenarCountingSort(vetor.clone(), false, estatDesc);

            // resultados
            System.out.println("COUNTING SORT (CRESCENTE)");
            System.out.println("Comparações:   " + estatCresc.countComparacoes);
            System.out.println("Movimentações: " + estatCresc.countMovimentacoes);
            System.out.println("Tempo (ms):    " + estatCresc.tempoMiliseg);

            System.out.println("\nCOUNTING SORT (DECRESCENTE)");
            System.out.println("Comparações:   " + estatDesc.countComparacoes);
            System.out.println("Movimentações: " + estatDesc.countMovimentacoes);
            System.out.println("Tempo (ms):    " + estatDesc.tempoMiliseg);
        }
    }
}
