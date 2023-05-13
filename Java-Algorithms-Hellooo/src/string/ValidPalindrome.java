package string;

import java.util.Scanner;

/**
 * 기존 Palindrome 프로그램과 동일하지만 조건 추가
 * 단, 회문을 검사할 때 알파벳만 가지고 회문을 검사하며, 대소문자를 구분하지 않음
 */
public class ValidPalindrome {
    public String solution(String s){
        String answer="NO";
        // replace 는 정규식 불가
        // 알파벳 대문자가 아닌 것들은 모두 제거
        s=s.toUpperCase().replaceAll("[^A-Z]", "");
        String tmp = new StringBuilder(s).reverse().toString();
        if (s.equals(tmp)) return "YES";

        return answer;
    }

    public static void main(String[] args){
        ValidPalindrome T = new ValidPalindrome();
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.println(T.solution(str));
    }
}
