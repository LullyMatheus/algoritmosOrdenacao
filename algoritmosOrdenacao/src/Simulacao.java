import java.util.Random;
import java.util.Arrays;

public class Simulacao {

    public static void main(String[] args) {
        // Configurações da simulação
        int tamanhoInicial = 1000;
        int incremento = 1000;
        int numeroDeIteracoes = 500; //DEFINIR NUMERO DE ITERACOES
        
        // Instancia os algoritmos
        Sorter quick = new QuickSort();
        Sorter heap = new HeapSort();
        Sorter intro = new Introsort();

        // Cabeçalho para o Excel (CSV usando ponto e vírgula)
        System.out.println("Tamanho;QuickSort(ms);HeapSort(ms);Introsort(ms)");

        for (int i = 0; i < numeroDeIteracoes; i++) {
            int tamanhoAtual = tamanhoInicial + (i * incremento);
            
            // 1. Gerar vetor aleatório
            int[] vetorOriginal = gerarVetorAleatorio(tamanhoAtual);
            
            // 2. Criar cópias para que todos ordenem EXATAMENTE os mesmos dados
            int[] copiaQuick = Arrays.copyOf(vetorOriginal, vetorOriginal.length);
            int[] copiaHeap = Arrays.copyOf(vetorOriginal, vetorOriginal.length);
            int[] copiaIntro = Arrays.copyOf(vetorOriginal, vetorOriginal.length);
            
            // 3. Executar e medir o tempo (em nanosegundos convertido para milissegundos)
            double tempoQuick = medirTempo(quick, copiaQuick);
            double tempoHeap = medirTempo(heap, copiaHeap);
            double tempoIntro = medirTempo(intro, copiaIntro);
            
            // 4. Imprimir resultado formatado
            // O formato %.4f imprime com 4 casas decimais. A vírgula é usada como separador decimal no Brasil.
            System.out.printf("%d;%.4f;%.4f;%.4f%n", tamanhoAtual, tempoQuick, tempoHeap, tempoIntro);
        }
    }

    private static int[] gerarVetorAleatorio(int tamanho) {
        Random rand = new Random();
        int[] vetor = new int[tamanho];
        for (int j = 0; j < tamanho; j++) {
            vetor[j] = rand.nextInt(1000000); // Números aleatórios de 0 a 1 milhão
        }
        return vetor;
    }

    private static double medirTempo(Sorter algoritmo, int[] vetor) {
        long inicio = System.nanoTime();
        algoritmo.sort(vetor);
        long fim = System.nanoTime();
        
        // Retorna o tempo em milissegundos (divisão por 1.000.000)
        return (fim - inicio) / 1_000_000.0;
    }
}