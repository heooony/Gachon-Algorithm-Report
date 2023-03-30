public class Insertion {

    public static void main(String[] args) {
        int max = 100; // 랜덤 수의 최대 가능한 범위
        int length = 20; // 랜덤 수 추출 길이
        int[] randomNumbers = AlgorithmUtil.generateRandomNumbers(max, length);
        /* START 삽입 정렬 알고리즘 */
        int[] numbers = insertionSort(randomNumbers, length);
        /* END 삽입 정렬 알고리즘 */
        AlgorithmUtil.printResult(numbers);
    }

    /**
     * 삽입 정렬 알고리즘
     * @param numbers 타겟 정렬 배열
     * @param n 배열의 길이
     * @return
     */
    public static int[] insertionSort(int[] numbers, int n) {
        for (int i = 1; i < n; i++) {
            int loc = i - 1; // 현재 검사 중인 인덱스의 바로 앞 인덱스
            int item = numbers[i]; // 현재 검사 중인 요소의 값
            
            /**
             * 바로 앞에 위치한 요소가 현재 값보다 클 경우
             * 앞에 위치한 요소를 뒤로 한 칸 밀어버린다.
             * 이 과정을 계속 반복한다.
             */
            while (loc >= 0 && item < numbers[loc]) {
                numbers[loc+1] = numbers[loc];
                loc--;
            }

            /**
             * 앞 과정이 끝나는 경우 검사하고 있는 요소보다 앞 요소가 작은 경우 이므로
             * 그 인덱스에 검사 중이었던 요소의 값을 대입한다.
             */
            numbers[loc+1] = item;
        }
        return numbers;
    }
}
