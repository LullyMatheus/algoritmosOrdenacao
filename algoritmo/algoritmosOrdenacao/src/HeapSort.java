public class HeapSort implements Sorter {

    @Override
    public String getName() {
        return "HeapSort";
    }

    @Override
    public void sort(int[] array) {
        int n = array.length;
        // Construir heap (rearranjar array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);

        // Extrair um por um elementos do heap
        for (int i = n - 1; i > 0; i--) {
            // Mover raiz atual para o fim
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Chamar max heapify na heap reduzida
            heapify(array, i, 0);
        }
    }

    // Método auxiliar para ordenar apenas uma sub-parte (usado pelo Introsort)
    public static void sortSubArray(int[] array, int start, int end) {
        int n = end - start + 1;
        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyOffset(array, n, i, start);

        // Extract
        for (int i = n - 1; i > 0; i--) {
            int temp = array[start];
            array[start] = array[start + i];
            array[start + i] = temp;
            heapifyOffset(array, i, 0, start);
        }
    }

    // Heapify padrão
    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest])
            largest = left;

        if (right < n && array[right] > array[largest])
            largest = right;

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapify(array, n, largest);
        }
    }

    // Heapify com offset para funcionar em sub-arrays (necessário para o Introsort)
    private static void heapifyOffset(int[] array, int n, int i, int offset) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[offset + left] > array[offset + largest])
            largest = left;

        if (right < n && array[offset + right] > array[offset + largest])
            largest = right;

        if (largest != i) {
            int swap = array[offset + i];
            array[offset + i] = array[offset + largest];
            array[offset + largest] = swap;
            heapifyOffset(array, n, largest, offset);
        }
    }
}