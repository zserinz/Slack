package string;

import java.util.Scanner;

public class UpperLower {
    public String solution(String str) {
        String answer = "";
        for(char x : str.toCharArray()){
            // ASCII Number 로도 확인 가능
            if(Character.isLowerCase(x)) answer += Character.toUpperCase(x);
            else answer += Character.toLowerCase(x);
        }
        return answer;
    }

    public static void main(String[] args){
        UpperLower T = new UpperLower();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.print(T.solution(str));
    }
}
