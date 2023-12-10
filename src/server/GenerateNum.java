package server;

import java.util.Random;

public class GenerateNum {
	// 숫자야구를 위해 랜덤 숫자를 만들어 반환하는 메서드 
	public static String generate() {
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++) {	// 3자리 숫자까지 만들기 
			sb.append(random.nextInt(9));	// 0 ~ 9 까지의 숫자 랜덤으로 뽑기
			for(int j = 0; j < i; j++) {
				if(sb.charAt(j) == sb.charAt(i)) {	// 중복방지
					i--;
				}
			}
		}
		
		return sb.toString();	// 완성된 3개의 숫자 반환
	}
}
