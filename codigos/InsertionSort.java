import java.util.Random;

public class InsertionSort {

    // Classe para armazenar estatísticas da ordenação
    public static class SortStats {
        public long countComparacoes = 0;
        public long countMovimentacoes = 0;
        public long tempoMiliseg = 0;
    }

    // Gera vetor aleatório
    public static int[] gerarVetorAleatorio(int tamanho) {
        Random random = new Random();
        int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(1000000);
        }
        return vetor;
    }

    /**
     * INSERTION SORT
     * @param vetor // vetor original
     * @param crescente // true = crescente, false = decrescente
     * @param estat // estatísticas
     */
    public static int[] ordenarInsertionSort(int[] vetor, boolean crescente, SortStats estat) {

        long inicio = System.currentTimeMillis();

        int n = vetor.length;
        int[] arr = vetor.clone();

        for (int i = 1; i < n; i++) {

            int chave = arr[i];
            estat.countMovimentacoes++; // armazenar chave

            int j = i - 1;

            // Crescente
            if (crescente == true) {

                while (j >= 0) {
                    estat.countComparacoes++;

                    if (arr[j] > chave) {
                        arr[j + 1] = arr[j];
                        estat.countMovimentacoes++;
                        j--;
                    } else {
                        break;
                    }
                }

            } else { // Decrescente

                while (j >= 0) {
                    estat.countComparacoes++;

                    if (arr[j] < chave) {
                        arr[j + 1] = arr[j];
                        estat.countMovimentacoes++;
                        j--;
                    } else {
                        break;
                    }
                }
            }

            arr[j + 1] = chave; // coloca a chave no lugar
            estat.countMovimentacoes++;
        }

        estat.tempoMiliseg = System.currentTimeMillis() - inicio;
        return arr;
    }

    // MAIN DE TESTES
    public static void main(String[] args) {

        int[] tamanhos = {
                1000,
                5000,
                10000,
                50000,
                100000
        };

        for (int tamanho : tamanhos) {

            System.out.println("\n---------------------------");
            System.out.println(" VETOR DE TAMANHO: " + tamanho);
            System.out.println("---------------------------\n");

            int[] vetor = gerarVetorAleatorio(tamanho);

            SortStats estatCresc = new SortStats();
            SortStats estatDesc = new SortStats();

            // Execuções
            ordenarInsertionSort(vetor.clone(), true, estatCresc);
            ordenarInsertionSort(vetor.clone(), false, estatDesc);

            // Resultados
            System.out.println("INSERTION SORT (CRESCENTE)");
            System.out.println("Comparações:   " + estatCresc.countComparacoes);
            System.out.println("Movimentações: " + estatCresc.countMovimentacoes);
            System.out.println("Tempo (ms):    " + estatCresc.tempoMiliseg);

            System.out.println("\nINSERTION SORT (DECRESCENTE)");
            System.out.println("Comparações:   " + estatDesc.countComparacoes);
            System.out.println("Movimentações: " + estatDesc.countMovimentacoes);
            System.out.println("Tempo (ms):    " + estatDesc.tempoMiliseg);
        }
    }
}
