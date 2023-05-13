package string;

import java.util.Scanner;

public class WordInSentence {
    /**
     * 방법1> 단어 별로 자른 후, 가장 긴 길이의 단어를 for 문으로 확인
     */
    public String solution1(String str){
        String answer="";
        int m = Integer.MIN_VALUE;

        String[] s=str.split(" ");

        for(String x:s){
            int len=x.length();
            if(len>m){
                m=len;
                answer=x;
            }
        }

        return answer;
    }

    /**
     * 방법2> indexOf()를 활용하여 인덱스 값으로 길이를 확인
     */
    public String solution2(String str){
        String answer="";
        // 정수 변수 2개 설정
        int m=Integer.MIN_VALUE, pos;

        // 띄어쓰기 -> -1 반환, 알파벳 -> -1이 아닌 수 반환
        while ((pos=str.indexOf(' ')) != -1){

            // 0부터 pos 전까지 자르기
            String tmp=str.substring(0, pos);

            int len=tmp.length();
            if(len>m){ // 가장 앞쪽에 위치한 가장 긴 단어이므로 =를 넣으면 뒤쪽에서 발견 가능
                m=len;
                answer=tmp;
            }
            str=str.substring(pos+1);
        }
        //만약 마지막 단어가 제일 긴 단어일 경우를 체크하기
        if(str.length()>m) answer=str;

        return answer;
    }

    public static void main(String[] args){
        WordInSentence T = new WordInSentence();
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
//        System.out.print(T.solution1(str));
        System.out.print(T.solution2(str));
    }
}
