import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Algorithm 풀이에 집중할 수 있도록 알고리즘을 제외한 다른 필요 메소드를
 * 한 곳에 정리해두었습니다.
 * 이 클래스에서는 랜덤 수 발생기, 배열 출력 함수를 갖고 있습니다.
 */
public class AlgorithmUtil {
    /**
     * 랜덤한 수 발생기
     * @param max 랜덤한 수의 범위
     * @param length 랜덤 추출 개수
     * @return
     */
    public static int[] generateRandomNumbers(int max, int length) {
        Random random = new Random();
        Set<Integer> set = new HashSet<>(); // HashSet을 사용하여 중복되지 않도록 한다.
        while (set.size() < length) {
            set.add(random.nextInt(max));
        }
        int[] numbers = new int[length];
        int index = 0;
        for (int number : set) {
            numbers[index++] = number;
        }
        return numbers;
    }

    /**
     * 배열을 출력한다.
     * @param numbers 출력할 배열
     */
    public static void printResult(int[] numbers) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("\n----------------------------------------------------------------------------------------\n");
        sb.append("결과 값: ");
        for (int number: numbers) {
            if (index++ == numbers.length - 1) sb.append(String.valueOf(number)); // 마지막 원소일 경우 쉼표를 붙이지 않고 출력한다.
            else sb.append(String.valueOf(number) + ", ");
        }
        sb.append("\n----------------------------------------------------------------------------------------\n");
        System.out.println(sb);
    }
}
