package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(10000);	// 포트 번호는 10000으로 설정
			System.out.println("클라이언트의 접속을 대기중입니다.");
			Socket socket = server.accept();
			System.out.println("클라이언트가 접속하였습니다.");
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));	// 서버에서 클라이언트로 출력을 담당 
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));	// 클라이언트에서 서버로 입력을 담당
			
			bw.write("숫자 야구에 오신걸 환영합니다. 입력은 세자리의 숫자중 중복이 없이 입력해주세요.");
			bw.newLine();
			bw.flush();
			
			String inputLine;
			
			String num = GenerateNum.generate();
			System.out.println("생성된 난수는 " + num + " 입니다. \n");
			
			int strike = 0;
			int ball = 0;
			int count = 0;
			
			while((inputLine = br.readLine()) != null) {
				if(inputLine.matches("exit")) {	// exit 가 입력된 경우
					bw.write("정답은 " + num + " 이었습니다. 게임을 종료합니다.");
					bw.newLine();
					bw.flush();
					
					System.out.println("\n\n게임을 종료했습니다.");
					
					break;
				}
				
				System.out.println("클라이언트의 입력 : " + inputLine);
				if(inputLine.matches(num)) {	// 정답인 경우
					count++;
					System.out.println("클라이언트가 " + count + " 회 만에 정답을 맞췄습니다.");
					bw.write(num);	// 정답을 넘겨 클라이언트에서 종료처리 따로 하도록
					bw.newLine();
					bw.flush();
					break;
				}
				else {
					count++;	// 1회 마다 수 증가
					strike = 0;
					ball = 0;
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(num.charAt(i) == inputLine.charAt(j) && i == j) 	// 값이 같고 인덱스가 같은경우 스트라이크 
								strike++;
							
							else if(num.charAt(i) == inputLine.charAt(j) && i != j) 	// 값이 같지만 인덱스가 다른경우 볼
								ball++;
						}
					}
					System.out.println("클라이언트의 " + count + " 회 결과 : " + strike + " 스트라이크, " + ball + " 볼 입니다.");
					bw.write(count + " 회 결과 : " + strike + " 스트라이크, " + ball + " 볼 입니다.");
					bw.newLine();
					bw.flush();
				}
				
				if(count == 9) {	// 9회가 종료되었을 경우
					bw.write("정답은 " + num + " 이었습니다. 게임을 종료합니다.");
					bw.newLine();
					bw.flush();
					
					System.out.println("\n\n게임을 종료했습니다.");
					
					break;
				}
			}
			
			// 사용했던 객체들 닫기  
			bw.close();
			br.close();
			socket.close();
			server.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
