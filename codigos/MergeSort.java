public class MergeSort {

    // Contadores globais
    private static int comparacoes = 0;
    private static int movimentacoes = 0;

    public static int getComparacoes() {
        return comparacoes;
    }

    public static int getMovimentacoes() {
        return movimentacoes;
    }

    public static void resetContadores() {
        comparacoes = 0;
        movimentacoes = 0;
    }

    public static void mergeSort(int[] array) {

        if (!(array == null || array.length < 2)) {
            mergeSortRecursive(array, 0, array.length - 1);
        }
        
    }

    private static void mergeSortRecursive(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortRecursive(array, left, mid);
            mergeSortRecursive(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copia para arrays auxiliares
        for (int i = 0; i < n1; i++) {
            L[i] = array[left + i];
            movimentacoes++;
        }
        for (int i = 0; i < n2; i++) {
            R[i] = array[mid + 1 + i];
            movimentacoes++;
        }

        int i = 0, j = 0;
        int k = left;

        // Mescla
        while (i < n1 && j < n2) {
            comparacoes++;

            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            movimentacoes++;
            k++;
        }

        // Copia elementos restantes
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
            movimentacoes++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
            movimentacoes++;
        }
    }
}
