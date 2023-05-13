package string;

import java.util.Scanner;

/**
 * 소문자로 된 한개의 문자열이 입력되면 중복된 문자를 제거하고 출력하는 프로그램
 * 단, 중복이 제거된 문자열의 각 문자는 원래 문자열의 순서는 유지
 */
public class RemoveDuplicateChar {
    public String solution(String str){
        String answer="";
        for (int i=0; i<str.length(); i++){
            // 중복된 문자는 처음 발견된 위치로 출력이 된다.
            //ksekkset
            //k 0 0
            //s 1 1
            //e 2 2
            //k 3 0
            //k 4 0
            //s 5 1
            //e 6 2
            //t 7 7
//            System.out.println(str.charAt(i)+" "+i+" "+str.indexOf(str.charAt(i)));

            // 즉, 기본 인덱스 값(i)과 해당 위치의 문자가 가지고 있는 맨 처음 인덱스값이 동일하다면 최초 문자
            //    동일하지 않다면 중복된 문자 이다.
            if (str.indexOf(str.charAt(i))==i) answer+=str.charAt(i);
        }

        return answer;
    }

    public static void main(String[] args){
        RemoveDuplicateChar T = new RemoveDuplicateChar();
        Scanner kb = new Scanner(System.in);
        String str=kb.next();
        System.out.println(T.solution(str));
    }
}
