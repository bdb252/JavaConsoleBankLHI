package banking.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BankingConnection implements IConnect{

	public Connection con;			//DB 연결
	public ResultSet rs;			//select의 실행결과 반환
	public Statement stmt;			//정적 쿼리문 실행
	public PreparedStatement psmt;	//동적 쿼리문 실행
	public CallableStatement csmt;  //프로시저 실행
	public static Scanner scan = new Scanner(System.in);
	
	public BankingConnection(String user, String pass) {
		try {
			Class.forName(ORACLE_DRIVER);
			con = DriverManager.getConnection(ORACLE_URL,user,pass);
		}
		catch (Exception e) {
			System.out.println("DB 커넥션 예외발생");
			e.printStackTrace();
		}
	}
	@Override
	public void dbExecute() {
		
	}
	@Override
	public void dbClose() {
		try {
			if(con!=null) con.close();
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			if(csmt!=null) csmt.close();
			System.out.println("DB 자원 반납");
		}
		catch (Exception e) {
			System.out.println("DB 자원반납시 예외발생");
			e.printStackTrace();
		}
	}
	@Override
	public String inputValue(String title) {
		System.out.print(title+"을(를) 입력(exit->종료):");
		String inputStr = scan.nextLine();
		if("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");
			dbClose();
			System.exit(0);
		}
		return inputStr;
	}

}
