public class Heap {

    public static void main(String[] args) {
        int max = 100; // 랜덤 수의 최대 가능한 범위
        int length = 20; // 랜덤 수 추출 길이
        int[] randomNumbers = AlgorithmUtil.generateRandomNumbers(max, length);
        int[] numbers = new int[length];
        numbers = randomNumbers.clone();
        /* START 힙 정렬 알고리즘 */
        heapSort(numbers, length);
        /* END 힙 정렬 알고리즘 */
        AlgorithmUtil.printResult(numbers);
    }

    /**
     * 힙 정렬 알고리즘
     * @param numbers 정렬 타겟 배열
     * @param n 배열의 길이
     */
    public static void heapSort(int[] numbers, int n) {
        buildHeap(numbers, n); // 힙을 정렬합니다.
        /**
         * 루트 요소를 맨 마지막의 요소와 변경한다.
         * 맨 마지막 요소는 힙 특징에 의해 가장 작은 수로 차곡차곡 쌓인다.
         * 변경이 되면 heapify를 통해 해당 트리가 힙 성질을 만족할 수 있도록 정렬한다.
         */
        for (int i = n-1; i > 0; i--) {
            swapByIndex(numbers, 0, i);
            heapify(numbers, 0, i-1);
        }
    }

    public static void buildHeap(int[] numbers, int n) {
        for (int i = n/2-1; i >= 0; i--) {
            heapify(numbers, i, n);
        }
    }

    public static void heapify(int[] numbers, int k, int n) {
        int smaller;
        int left = 2 * k + 1;
        int right = 2 * k + 2;

        if (right < n) {
            if (numbers[left] < numbers[right]) {
                smaller = left;
            } else {
                smaller = right;
            }
        } else if (left <= n) {
            smaller = left;
        } else {
            return;
        }

        if (numbers[smaller] < numbers[k]) {
            swapByIndex(numbers, smaller, k);
            heapify(numbers, smaller, n);
        }
    }

    /**
     * 인덱스로 값을 변환한다.
     * @param numbers 타겟 정렬 배열
     * @param i 값을 바꾸려고 하는 인덱스 1
     * @param j 값을 바꾸려고 하는 인덱스 2
     */
    public static void swapByIndex(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

}