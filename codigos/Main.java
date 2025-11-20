import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] vetorAleatorio = gerarVetorAleatorio(100000000, 10000000);

        QuickSort.resetContadores();

        // Marca o tempo inicial
        long inicio = System.nanoTime();

        // Executa a ordenação com o algoritmo Quick Sort
        QuickSort.quickSort(vetorAleatorio, 0, vetorAleatorio.length - 1);

        // Marca o tempo final
        long fim = System.nanoTime();

        long tempoExecucao = fim - inicio; // nanossegundos

        // Exibe os resultados
        System.out.println("Comparações: " + QuickSort.getComparacoes());
        System.out.println("Movimentações: " + QuickSort.getMovimentacoes());
        System.out.println("Tempo (ms): " + tempoExecucao / 1_000_000.0);

        // System.out.print(vetorAleatorio[0]);
        // for (int i = 1; i < vetorAleatorio.length; i++) {
        //     System.out.print(", " + vetorAleatorio[i]);
        // }

    }

    public static int[] gerarVetorAleatorio(int tamanho, int valorMaximoElementos) {
        Random random = new Random();
        int[] vetor = new int[tamanho];
        
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(valorMaximoElementos); 
        }
        
        return vetor;
    }

}
