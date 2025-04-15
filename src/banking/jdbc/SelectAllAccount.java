package banking.jdbc;

import java.sql.SQLException;

public class SelectAllAccount extends BankingConnection{

	public SelectAllAccount() {
		super("education", "1234");
	}
	String sql;
	
	@Override
	public void dbExecute() {
		try {
			stmt=con.createStatement();
			sql="select acc_idx, account_num, name, money, interest from banking";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String acc_idx=rs.getString(1);
				String name=rs.getString("name");
				String accountnum=rs.getString("account_num");
				int money=rs.getInt("money");
				int interest=rs.getInt("interest");
				
				System.out.println("일련번호:"+acc_idx
						+ "\n계좌번호:"+accountnum
						+ "\n이름:"+name
						+ "\n잔액:"+money
						+ "\n이자율:"+interest+"%");
				System.out.println("-----------------------------------------");
			}
		}
		catch (SQLException e) {
			System.out.println("쿼리 실행 중 예외발생");
			e.printStackTrace();
		}
	}
}
