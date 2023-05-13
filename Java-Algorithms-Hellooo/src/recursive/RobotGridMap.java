package recursive;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * << 문 제 >> =========================================================================================================
 * mxn 크기의 격자 지도에 로봇 하나가 이 격자 지도의 왼쪽 위 칸에 높여 있습니다. 이 로봇은 한 번에 오른쪽이나 아래로만 한 칸씩 움직일 수 있으며 특정 칸으로는 이동할 수 없습니다.
 * 로봇의 목표는 격자 지도의 왼쪽 맨 위칸에서 오른쪽 맨 아래 칸까지 가는 경로를 찾는 것입니다.
 *
 * << 풀이 아이디어 >> ====================================================================================================
 * 1. 격자 지도를 (m,n) 개의 좌표칸이라고 생각하자.
 *   a. maze[][] 행렬
 *   R - 로봇, # - 미로, T - 도달 위치
 *         (m, n) +           +
 *                |R| | | | | |
 *                |R|R| | | | |
 *                |#|R|R|R|R| |
 *                |#|#|#|#|R| |
 *                | | | | |R| |
 *                | | | | |R|T|
 *                +           + (0, 0)
 *
 * 2. 로봇의 움직임 목표는 (m,n) -> (0,0) 경로로 가는 것
 *   a. 로봇은 아래나 오른쪽으로만 갈 수 있으니까 한 번에 (m-1,n) or (m,n-1) 칸 움직일 수 있다.
 *   b. 이동할 수 없는 칸은 true로 설정
 * 3. 재귀 알고리즘으로 경로를 추가하며 확인
 * ====================================================================================================================
 */
public class RobotGridMap {
    public static boolean computePath(int m, int n, boolean[][] maze, Set<Point> path) {
        if (path == null || maze == null) {
            throw new IllegalArgumentException("Path and maze cannot be null");
        }

        // 격자 지도에서 벗어났기 때문에 반환
        if (m < 0 || n < 0) {
            return false;
        }

        // 출발점으로는 이동 불가
        if (maze[m][n]) {
            return false;
        }

        // 목표에 도착(목표는 오른쪽 아래 칸입니다).
        if (((m == 0) && (n == 0))
                || computePath(m, n - 1, maze, path) // 또는 오른쪽으로 이동 가능
                || computePath(m - 1, n, maze, path)) { // 또는 아래쪽으로 이동 가능
            // 현재 칸을 경로에 추가
            path.add(new Point(m, n));

            return true;
        }

        return false;
    }

    public static boolean computePath(int m, int n, boolean[][] maze, Set<Point> path, Set<Point> visitFailed) {
        if (path == null || maze ==null || visitFailed == null) {
            throw new IllegalArgumentException("Path, maze and visitFailed cannot be null");
        }

        // 격자 지도에서 벗어났기 때문에 반환
        if (m < 0 || n < 0) {
            return false;
        }

        // 출발점으로는 이동 불가
        if (maze[m][n]) {
            return false;
        }

        Point cell = new Point(m, n);

        // 이미 해당 칸을 방문한 적이 있는지 확인
        if (visitFailed.contains(cell)) {
            return false;
        }

        // 목표에 도착(목표는 오른쪽 아래 칸입니다).
        if (((m == 0) && (n == 0))
                || computePath(m, n - 1, maze, path, visitFailed) // 또는 오른쪽으로 이동할 수 있습니다.
                || computePath(m - 1, n, maze, path, visitFailed)) { // 또는 아래쪽으로 이동할 수 있습니다.
            // 현재 칸을 경로에 추가
            path.add(cell);

            return true;
        }

        visitFailed.add(cell);

        return false;
    }

    public static void main(String[] args) {
        // LinkedHashSet에 경로를 저장
        Set<Point> path = new LinkedHashSet<>();

        // 미로를 정의
        boolean[][] maze  = new boolean[6][6];
        maze[2][0]=true;
        maze[3][0]=true;
        maze[3][1]=true;
        maze[3][2]=true;
        maze[3][3]=true;

        /**
         * 일반 재귀 알고리즘
         * -> 실행 시간은 O(2^(m+n)) 이다. 유효한 선택지가 2개인 상태에서 2^m * 2^n 번 반복한다.
         */
        RobotGridMap.computePath(5, 5, maze, path);

        System.out.println("Computed path (plain recursion):");
        path.forEach(System.out::println);

        /**
         * 메모이제이션
         * -> 일반 재귀 알고리즘에서 이동할 수 없는 칸을 캐싱한다면 실행 시간을 O(nm)으로 단축할 수 있다.
         */
        Set<Point> visited = new HashSet<>();
        RobotGridMap.computePath(5, 5, maze, path, visited);

        System.out.println("\nComputed path (Memoization):");
        path.forEach(System.out::println);
    }
}
