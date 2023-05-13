package recursive;

/**
 * << 문 제 >> =========================================================================================================
 * 하노이의 탑 시작 막대에서 다른 막대로 모든 원반을 옮겨라.
 * ====================================================================================================================
 */
public class HanoiTowers {
    public static void moveDisks(int n, char origin, char target, char intermediate, int[] diskCount) {
        // 1. 원반의 개수가 유효하지 않는 경우
        if (n <= 0) {
            return;
        }

        // 2. 원반의 개수가 유효하고 1개인 경우
        if (n == 1) {
            System.out.println(origin + " 막대에서 " + target + " 막대로 1 개의 원반을 이동했습니다.");
            diskCount[origin - 'A']--;
            diskCount[target - 'A']++;

            System.out.println("========= 원반 현황 =========" +
                    "\nA 막대 원반 개수: " + diskCount[origin - 'A'] +
                    "\nB 막대 원반 개수: " + diskCount[intermediate - 'A'] +
                    "\nC 막대 원반 개수: " + diskCount[target - 'A'] +
                    "\n===========================");
            return;
        }

        // 3. 원반의 개수가 유효하고 n개인 경우

        // 원점 막대의 상위 n − 1개 원반을 중간 막대로 옮김
        // 대상 막대를 중간 막대 역할로 활용
        moveDisks(n - 1, origin, intermediate, target, diskCount);
        System.out.println(origin + " 막대에서 " + target + " 막대로 " + n + " 개의 원반을 이동했습니다.");
        diskCount[origin - 'A']--;
        diskCount[intermediate - 'A']++;

        System.out.println("========= 원반 현황 =========" +
                "\nA 막대 원반 개수: " + diskCount[origin - 'A'] +
                "\nB 막대 원반 개수: " + diskCount[intermediate - 'A'] +
                "\nC 막대 원반 개수: " + diskCount[target - 'A'] +
                "\n===========================");

        // 중간 막대의 상위 n − 1개 원반을 대상 막대로 옮김
        // 원점 막대를 중간 막대 역할로 활용
        moveDisks(n - 1, intermediate, target, origin, diskCount);
        diskCount[intermediate - 'A']--;
        diskCount[target - 'A']++;

        System.out.println("========= 원반 현황 =========" +
                "\nA 막대 원반 개수: " + diskCount[origin - 'A'] +
                "\nB 막대 원반 개수: " + diskCount[intermediate - 'A'] +
                "\nC 막대 원반 개수: " + diskCount[target - 'A'] +
                "\n===========================");
    }

    public static void main(String[] args) {
        // 원반 개수
        int n = 3;

        // A - 원점(또는 원본) 막대
        // B - 중간(또는 보조) 막대
        // C - 대상(또는 목표) 막대
//        HanoiTowers.moveDisks(n, 'A', 'C', 'B');

        char origin = 'A', target = 'C', intermediate = 'B';
        int[] diskCount = new int[3];
        diskCount[origin - 'A'] = n;
        moveDisks(n, origin, target, intermediate, diskCount);
    }
}
