package banking.jdbc;

import java.sql.SQLException;
import java.sql.Types;

public class DeleteAccount extends BankingConnection{

	public DeleteAccount() {
		super("education", "1234");
	}
	
	String sql;
	
	@Override
	public void dbExecute() {
		try {
			csmt=con.prepareCall("{call DeleteAccount(?, ?)");
			csmt.setString(1, inputValue("삭제할 계좌번호"));
			csmt.registerOutParameter(2, Types.VARCHAR);
			csmt.execute();
			System.out.println(csmt.getString(2));
		}
		catch (SQLException e) {
			System.out.println("쿼리 실행 중 예외발생");
			e.printStackTrace();
		}
		finally {
			dbClose();
		}
	}
}
