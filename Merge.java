public class Merge {
    
    public static void main(String[] args) {
        int max = 100; // 랜덤 수의 최대 가능한 범위
        int length = 20; // 랜덤 수 추출 길이
        int[] randomNumbers = AlgorithmUtil.generateRandomNumbers(max, length);
        int[] numbers = new int[length];
        numbers = randomNumbers.clone();
        /* START 병합 정렬 알고리즘 */
        mergeSort(numbers, 0, length - 1);
        /* END 병합 정렬 알고리즘 */
        AlgorithmUtil.printResult(numbers);
    }

    /**
     * 병합 정렬 알고리즘
     * @param numbers 타겟 정렬 배열
     * @param p 시작 지점
     * @param r 종료 지점
     * @return
     */
    public static void mergeSort(int[] numbers, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(numbers, p, q); // 반을 분할하고 앞 지점
            mergeSort(numbers, q+1, r); // 반을 분할하고 뒷 지점
            merge(numbers, p, q, r); // 모든 분할이 끝나고 병합하는 지점
        }
    }

    /**
     * 실제로 병합할 수 있도록 도와주는 함수
     * 정렬된 상태로 병합할 수 있도록 한다.
     * @param numbers 타겟 정렬 배열
     * @param p 시작 지점
     * @param q 중간 지점 (p + r) / 2
     * @param r 종료 지점
     */
    public static void merge(int[] numbers, int p, int q, int r) {
        int[] tmp = new int[r - p + 1]; // 병합하려고 하는 전체 요소 수로 tmp를 제작
        int i = p; // 시작 지점의 포인터
        int j = q + 1; // 중간 지점의 포인터
        int t = 0; // tmp를 가리키는 포인터

        /**
         * i가 중간 지점을 넘어서지 않았거나, j가 끝 지점을 넘어서지 않을 경우
         * 반복문을 통해 서로의 수를 비교하고 tmp에 저장한다.
         * numbers는 지속적으로 값을 비교해야하기 때문에 직접적으로 값을 대입하지 않는다.
         */
        while (i <= q && j <= r) {
            if (numbers[i] <= numbers[j]) {
                tmp[t++] = numbers[i++];
            } else {
                tmp[t++] = numbers[j++];
            }
        }
        /**
         * 앞서 하나의 포인터가 목표한 지점을 넘을 경우 다른 한 쪽의 포인터가 끝까지 가지 않았을 수 있기 때문에
         * 다른 하나의 포인터가 끝까지 갈 수 있게 한다.
         */
        while (i <= q) {
            tmp[t++] = numbers[i++];
        }
        while (j <= r) {
            tmp[t++] = numbers[j++];
        }

        /**
         * 전체 과정이 끝나면 tmp에 있는 배열요소를 numbers에 대입하기 위한 부분
         * i의 포인터는 다시 시작 지점을 가리키고, tmp포인터도 처음을 가리켜 순차적으로 값을 대입한다.
         */
        i = p; t = 0;
        while (i <= r) {
            numbers[i++] = tmp[t++];
        }
    }
}
