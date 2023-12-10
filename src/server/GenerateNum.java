package server;

import java.util.Random;

public class GenerateNum {
	// 숫자야구를 위해 랜덤 숫자를 만들어 반환하는 메서드 
	// 기존 방법은 같은 숫자가 연속적으로 뽑힐 경우 OutOfMemoryError 가 발생할 수 있기 때문에 다시 뽑는 방법 말고 앞의 숫자에서 -1을 하여 중복을 제거함 
	public static String generate() {
		Random random = new Random();
		
		int[] s = new int[3];
		for(int i = 0; i < 3; i++) {	// 3자리 숫자까지 만들기 
			s[i] = random.nextInt(9);	// 0 ~ 9 까지의 숫자 랜덤으로 뽑기
			for(int j = 0; j < i; j++) {
				if(s[j] == s[i]) {	// 중복방지
					s[i] = s[j]-1;
					if(s[i] < 0)
						s[i] = 9;
				}
			}
		}
		
		String st = new String();
		for(int i = 0; i < 3; i++)
			st += s[i];
		
		return st;	// 완성된 3개의 숫자 반환
	}
}
