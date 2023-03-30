public class Bubble {

    public static void main(String[] args) {
        int max = 100; // 랜덤 수의 최대 가능한 범위
        int length = 20; // 랜덤 수 추출 길이
        int[] randomNumbers = AlgorithmUtil.generateRandomNumbers(max, length);
        /* START 버블 정렬 알고리즘 */
        int[] numbers = bubbleSort(randomNumbers, length);
        /* END 버블 정렬 알고리즘 */
        AlgorithmUtil.printResult(numbers);
    }

    /**
     * 버블 정렬 알고리즘
     * @param numbers 타겟 정렬 배열
     * @param n 배열의 길이
     * @return
     */
    public static int[] bubbleSort(int[] numbers, int n) {
        // 배열의 개수가 n개일 경우 반복은 총 n-1번만 하면 된다.
        for (int i = 0; i < n-1; i++) {
            // 한번 수행시마다 맨 마지막 요소는 정렬이 되므로 
            for (int j = 0; j < n-i-1; j++) {
                if (numbers[j] > numbers[j+1]) {
                    swapByIndex(numbers, j, j+1);
                }
            }
        }
        return numbers;
    }

    /**
     * 양 수의 위치를 바꾼다.
     * @param numbers 타겟 정렬 배열
     * @param front 버블 정렬 중 앞 요소
     * @param back 버블 정렬 중 뒷 요소
     */
    public static void swapByIndex(int[] numbers, int front, int back) {
        int temp = numbers[front];
        numbers[front] = numbers[back];
        numbers[back] = temp;
    }
}