public class Introsort implements Sorter {

    @Override
    public String getName() {
        return "Introsort";
    }

    @Override
    public void sort(int[] array) {
        // Profundidade máxima baseada no logaritmo do tamanho
        int depthLimit = (int) (2 * Math.floor(Math.log(array.length) / Math.log(2)));
        introSortLoop(array, 0, array.length - 1, depthLimit);
    }

    private void introSortLoop(int[] array, int start, int end, int depthLimit) {
        int size = end - start + 1;

        // Se a partição for pequena, Insertion Sort é mais rápido (Otimização padrão do Introsort)
        if (size < 16) {
            insertionSort(array, start, end);
            return;
        }

        // Se a profundidade zerar, mudamos para HeapSort para evitar o pior caso do Quick
        if (depthLimit == 0) {
            HeapSort.sortSubArray(array, start, end);
            return;
        }

        // Caso contrário, continuamos com a lógica do QuickSort (particionamento)
        int pivot = QuickSort.partition(array, start, end);
        introSortLoop(array, start, pivot - 1, depthLimit - 1);
        introSortLoop(array, pivot + 1, end, depthLimit - 1);
    }

    private void insertionSort(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= start && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}