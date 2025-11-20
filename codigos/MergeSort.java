public class MergeSort {

    // Contadores globais
    private static int comparacoes = 0;
    private static int movimentacoes = 0;

    public static int[] mergeSort(int[] array, boolean ascending) {
        comparacoes = 0;
        movimentacoes = 0;

        if (array == null || array.length < 2) {
            return array;
        }

        mergeSortRecursive(array, 0, array.length - 1, ascending);

        // Printando os contadores
        System.out.println("Total de comparações: " + comparacoes);
        System.out.println("Total de movimentações: " + movimentacoes);

        return array;
    }

    private static void mergeSortRecursive(int[] array, int left, int right, boolean ascending) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortRecursive(array, left, mid, ascending);
            mergeSortRecursive(array, mid + 1, right, ascending);

            merge(array, left, mid, right, ascending);
        }
    }

    private static void merge(int[] array, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = array[left + i];
            movimentacoes++;
        }

        for (int j = 0; j < n2; j++) {
            R[j] = array[mid + 1 + j];
            movimentacoes++;
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            comparacoes++;
            if (ascending) {
                if (L[i] <= R[j]) {
                    array[k++] = L[i++];
                } else {
                    array[k++] = R[j++];
                }
            } else {
                if (L[i] >= R[j]) {
                    array[k++] = L[i++];
                } else {
                    array[k++] = R[j++];
                }
            }
            movimentacoes++;
        }

        while (i < n1) {
            array[k++] = L[i++];
            movimentacoes++;
        }

        while (j < n2) {
            array[k++] = R[j++];
            movimentacoes++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 5, 4, 7, 6, 1, 2};
        System.out.println("Array original:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();

        mergeSort(arr, true);

        System.out.println("Array ordenado:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }
}
