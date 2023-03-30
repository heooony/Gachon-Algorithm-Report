public class Selection {

    public static void main(String[] args) {
        int max = 100; // 랜덤 수의 최대 가능한 범위
        int length = 20; // 랜덤 수 추출 길이
        int[] randomNumbers = AlgorithmUtil.generateRandomNumbers(max, length);
        /* START 선택 정렬 알고리즘 */
        int[] numbers = selectionSort(randomNumbers, length);
        /* END 선택 정렬 알고리즘 */
        AlgorithmUtil.printResult(numbers);
    }

    /**
     * 선택 정렬 알고리즘
     * @param numbers 타겟 정렬 배열
     * @param n 배열의 길이
     * @return
     */
    public static int[] selectionSort(int[] numbers, int n) {
        for (int i = 0; i < n-1; i++) {
            int maxIndex = theLargest(numbers, n-i);
            swapByIndex(numbers, maxIndex, n-i-1);
        }
        return numbers;
    }

    /**
     * 가장 큰 값을 가진 요소의 인덱스를 반환한다.
     * @param numbers 타겟 정렬 배열
     * @param last 배열 마지막 범위
     * @return
     */
    public static int theLargest(int[] numbers, int last) {
        int maxIndex = 0;
        for (int i = 1; i < last; i++) {
            if (numbers[i] > numbers[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * 양 수의 위치를 바꾼다.
     * @param numbers 타겟 정렬 배열
     * @param maxIndex 가장 큰 요소가 담긴 인덱스
     * @param last 현재 정렬 범위 중 가장 뒤의 인덱스
     */
    public static void swapByIndex(int[] numbers, int maxIndex, int last) {
        int max = numbers[maxIndex];
        numbers[maxIndex] = numbers[last];
        numbers[last] = max;
    }
}
