package banking.jdbc;

import java.sql.SQLException;

public class MakeAccount extends BankingConnection{

	public MakeAccount() {
		super("education","1234");
	}
	String sql;
	int res;
	
	@Override
	public void dbExecute() {
		try {
			sql="insert into banking values (seq_banking_idx.nextval, ?, ?, ?, ?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, inputValue("계좌번호"));
			psmt.setString(2, inputValue("이름"));
			psmt.setString(3, inputValue("잔액"));
			psmt.setString(4, inputValue("이자율"));
			res = psmt.executeUpdate();
			System.out.println(res+"행 입력됨");
		}
		catch (SQLException e) {
			System.out.println("쿼리 실행 중 예외발생");
			e.printStackTrace();
		}
	}
}
