package string;

import java.util.Scanner;

/**
 * 영어 알파벳과 특수문자로 구성된 문자열이 주어지면 영어 알파벳만 뒤집고,
 * 특수문자는 자기 자리에 그대로 있는 문자열을 만들어 출력하는 프로그램
 * a$b@c%d#e -> e$d@c%b#a
 *
 * ~ main idea ~
 * lt,rt 로 양쪽에서 특수문자인지 먼저 하나씩 체크하고
 * 영어일 경우에만 서로의 위치를 변경해주자
 *  역전될 경우를 위해 조건을 추가해야 함
 */
public class FlipSpecificWord {
    public String solution(String str){
        String answer;
        // 문자 배열로 변환하여 뒤집기 진행
        char[] s = str.toCharArray();
        int lt=0, rt=str.length()-1;
        while(lt<rt){
            if (!Character.isAlphabetic(s[lt])) lt++;
            else if (!Character.isAlphabetic(s[rt])) lt++;
            else {
                char tmp=s[lt];
                s[lt] = s[rt];
                lt++;
                rt--;
            }
        }

        // 변환했던 문자열을 다시 문자로 되돌리기
        answer=String.valueOf(s);

        return answer;
    }

    public static void main(String[] args){
        FlipSpecificWord T = new FlipSpecificWord();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(T.solution(str));
    }
}
