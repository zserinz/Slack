package string;

import java.util.Scanner;

/**
 * 회문 문자열 = 앞에서 읽을 때나 뒤에서 읽을 때나 같은 문자열
 * 문자열이 입력되면 해당 문자열이 회문 문자열이면 "YES", 회문 문자열이 아니면 “NO"를 출력하는 프로그램
 * (단, 회문을 검사할 때 대소문자를 구분하지 않음)
 *
 * ~ main idea ~
 * 양 극단 문자값 하나씩 체크해 나가자.
 * 길이가 짝수인 경우 - 전체 길이의 절반까지 체크
 * 길이가 홀수인 경우 - 전체 길이의 절반 바로 이전까지 체크
 */
public class Palindrome {
    /**
     * 방법1. 하나씩 비교
     */
    public String solution1(String str){
        String answer = "YES";
        str=str.toUpperCase();
        int len=str.length();
        for(int i=0; i<len/2; i++){
            if (str.charAt(i) != str.charAt((len-i)-1)) return "NO";
        }

        return answer;
    }

    /**
     * 방법2. stringbuilder reverse 사용
     * equalsIgnoreCase 로 대소문자 통합 가능
     */
    public String solution2(String str) {
        String answer="NO";
        String tmp=new StringBuilder(str).reverse().toString();
        if (str.equalsIgnoreCase(tmp)) return "YES";

        return answer;
    }

    public static void main(String[] args){
        Palindrome T = new Palindrome();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(T.solution1(str));
    }
}
