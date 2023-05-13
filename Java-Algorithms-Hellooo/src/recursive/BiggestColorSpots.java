package recursive;

import java.util.Random;

/**
 * << 문 제 >> =========================================================================================================
 * rows x cols 크기의 격자 지도가 있다. 각 칸은 색이 칠해져 있으며 color 개의 숫자로 표현된다.
 * 행 또는 열이 연결된 상태로 각 칸이 이동할 수 있는 칸의 집합을 총 범위라고 정의하며, 각 범위는 동일한 색이 칠해져 있다.
 * 이 문제의 목표는 최대로 ㅇ녀결된 칸 집합의 색과 포함된 칸의 개수를 구하느 것이다.
 * 즉, 가장 큰 색 영역을 구해라.
 *
 * << 풀이 아이디어 >> ===================================================================================================
 * 1. 5 x 5 격자에 3가지 색상이 랜덤으로 칠해져 있다고 가정한다.
 * 2. 전체 칸을 순회하며 연결된 칸의 크기를 계산할 수 있다.
 * 3. 캐시를 적용하면 어떨까?
 *    -> 방문한 칸에는 값을 -로 변경해서 동일한 영역을 여러 번 계산하는 것을 피하자!
 * ====================================================================================================================
 */
public class BiggestColorSpots {
    private int currentColorSpot;

    void determineBiggestColorSpot(int cols, int rows, int a[][]) {
        if (a == null) {
            throw new IllegalArgumentException("matrix 는 null 이 될 수 없습니다.");
        }

        if (cols <= 0 || rows <= 0) {
            throw new IllegalArgumentException("격자는 0 개의 행 또는 0 개의 열을 가질 수 없습니다.");
        }

        // 우리의 목표
        int biggestColorSpot = 0;
        int color = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (a[i][j] > 0) {
                    currentColorSpot = 0;
                    computeColorSpot(i, j, cols, rows, a, a[i][j]);

                    // 최대 영역 구하기
                    if (currentColorSpot > biggestColorSpot) {
                        biggestColorSpot = currentColorSpot;
                        color = a[i][j] * (-1);
                    }
                }
            }
        }
        System.out.println("\n가장 큰 영역의 색상: " + color + ", 영역: " + biggestColorSpot);
    }

    private void computeColorSpot(int i, int j, int cols, int rows, int a[][], int color) {
        a[i][j] = -a[i][j]; // 방문 기록
        currentColorSpot++; // 영역 개수 세기

        // 서쪽으로 이동
        if (i > 1 && a[i - 1][j] == color) {
            computeColorSpot(i - 1, j, cols, rows, a, color);
        }

        // 동쪽으로 이동
        if ((i + 1) < rows && a[i + 1][j] == color) {
            computeColorSpot(i + 1, j, cols, rows, a, color);
        }

        // 남쪽으로 이동
        if (j > 1 && a[i][j - 1] == color) {
            computeColorSpot(i, j - 1, cols, rows, a, color);
        }

        // 북쪽으로 이동
        if ((j + 1) < cols && a[i][j + 1] == color) {
            computeColorSpot(i, j + 1, cols, rows, a, color);
        }
    }

    public static void main(String[] args) {
        int cols = 5;
        int rows = 5;
        int color = 3;

        Random rnd = new Random();

        int[][] a = new int[rows][cols];

        // 랜덤으로 색 추가
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                a[i][j] = rnd.nextInt(color) + 1;
            }
        }

        // 화면에 영역 색 표시
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(a[i][j] + "  ");
            }
            System.out.println();
        }

        BiggestColorSpots spots = new BiggestColorSpots();
        spots.determineBiggestColorSpot(cols, rows, a);
    }
}
