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

    /**
     * 힙을 구성한다.
     * @param numbers 정렬 타겟 배열
     * @param n 배열의 길이
     */
    public static void buildHeap(int[] numbers, int n) {
        /**
         * 전체 길이를 2로 나누었을 때 자식이 있는 노드의 가장 마지막에 접근할 수 있다.
         * 루트 노드까지 실행한다.
         */
        for (int i = n/2-1; i >= 0; i--) {
            heapify(numbers, i, n);
        }
    }

    /**
     * 힙을 만족하는지에 대해 판단한다.
     * @param numbers 정렬 타겟 배열
     * @param k 자식이 있는 현재 노드의 인덱스
     * @param n 전체 길이
     */
    public static void heapify(int[] numbers, int k, int n) {

        int smaller; // 가장 작은 수의 인덱스
        int left = 2 * k + 1; // 왼쪽 자식 노드
        int right = 2 * k + 2; // 오른쪽 자식 노드

        /**
         * 오른쪽 자식 노드가 있는 경우
         */
        if (right < n) {

            /**
             * 값 비교를 통해 더 작은 수를 찾는다.
             */
            if (numbers[left] < numbers[right]) {
                smaller = left;
            } else {
                smaller = right;
            }
        
        /**
         * 왼쪽 자식 노드만 있는 경우
         */
        } else if (left <= n) {
            smaller = left;
        } else {
            return;
        }

        /**
         * 현재 노드의 값 보다 더 작은 값이 존재한다면
         * 순서를 바꾸고, 다시 힙을 만족하는지 그 자식으로부터 시작한다.
         */
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