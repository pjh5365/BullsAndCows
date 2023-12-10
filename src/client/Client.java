package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			System.out.println("서버로 접속시도중입니다.");
			Socket socket = new Socket("localhost", 10000);	// 로컬호스트 10000 포트로 접속
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));	// 키보드로 부터 읽기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));	// 서버로부터 읽기
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));	// 서버로 전송하기 
			
			String inputLine;
			String readLine;
			System.out.println(br.readLine());	// 서버에 접속하면 서버로부터 최초 메시지 입력받아 출력 
			int count = 0;
			while((inputLine = keyboard.readLine()) != null) {
				count++;
				bw.write(inputLine);
				bw.newLine();
				bw.flush();
				readLine = br.readLine();
				
				if(readLine.matches(inputLine)) {	// 정답을 맞춘 경우
					System.out.println(count + " 회 만에 정답을 맞췄습니다. 게임을 종료합니다.");
					break;
				}
				if(inputLine.matches("exit")) {	// exit 를 입력하는 경우
					System.out.println("게임을 종료합니다.");
					break;
				}
				if(count == 9) {
					System.out.println(readLine);	// 클라이언트의 입력에 대한 서버의 응답 출력
					System.out.println(br.readLine());
					break;
				}
				System.out.println(readLine);	// 클라이언트의 입력에 대한 서버의 응답 출력
			}
			
			keyboard.close();
			br.close();
			bw.close();
			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
