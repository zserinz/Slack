package string;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * N개의 단어가 주어지면 각 단어를 뒤집어 출력하는 프로그램
 */
public class FlipWord {
    public ArrayList<String> solution(int n, String[] str){
        ArrayList<String> answer=new ArrayList<>();
        for (String x:str){
            String tmp=new StringBuilder(x).reverse().toString();
            answer.add(tmp);
        }

        return answer;
    }

    public static void main(String[] args){
        FlipWord T = new FlipWord();
        Scanner kb = new Scanner(System.in);
        // 입력 받기
        int n=kb.nextInt();
        String[] str=new String[n];
        for (int i=0; i<n; i++){
            str[i]=kb.next();
        }
        // 출력
        for(String x: T.solution(n, str)){
            System.out.println(x);
        }
    }
}
