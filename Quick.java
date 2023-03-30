public class Quick {
    
    public static void main(String[] args) {
        int max = 100; // 랜덤 수의 최대 가능한 범위
        int length = 20; // 랜덤 수 추출 길이
        int[] randomNumbers = AlgorithmUtil.generateRandomNumbers(max, length);
        int[] numbers = new int[length];
        numbers = randomNumbers.clone();
        /* START 퀵 정렬 알고리즘 */
        quickSort(numbers, 0, length - 1);
        /* END 퀵 정렬 알고리즘 */
        AlgorithmUtil.printResult(numbers);
    }

    /**
     * 퀵 정렬 알고리즘
     * @param numbers 타겟 정렬 배열
     * @param p 시작 지점
     * @param r 종료 지점 (종료 지점이 각 수와 비교하게 되는 타겟 지점이다)
     */
    public static void quickSort(int[] numbers, int p, int r) {
        if (p < r) {
            int q = partition(numbers, p, r); // r에 위치한 요소가 배열에 어디 위치하는지 찾는다.
            quickSort(numbers, p, q-1); // 타겟 지점으로부터 앞 부분의 정렬을 시도한다.
            quickSort(numbers, q+1, r); // 타겟 지점으로부터 뒷 부분의 정렬을 시도한다.
        }
    }

    public static int partition(int[] numbers, int p, int r) {
        int x = numbers[r]; // 마지막 요소이자 계속 비교하는 타겟 요소
        int i = p - 1; // i의 pointer
        /**
         * j가 i보다 한 칸 앞서있다.
         * 타겟 값과 j 인덱스에 있는 배열 요소를 비교한다.
         * 타겟 값보다 검사하려고 하는 인덱스의 값이 더 클 경우 j의 포인터만 증가시킨다.
         * 타겟 값보다 검사하려고 하는 인덱스의 값이 더 작을 경우 i의 포인터와 j의 포인터가 동시에 증가하고 자리변환이 일어난다.
         */
        for (int j = p; j < r; j++) {
            if (numbers[j] <= x) {
                swapByIndex(numbers, ++i, j);
            }
        }

        /**
         * 전체 검사 결과가 끝나면 i의 포인터의 다음 위치에 타겟 요소를 넣는다.
         */
        swapByIndex(numbers, i+1, r);
        return i+1;
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
