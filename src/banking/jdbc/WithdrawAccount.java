package banking.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WithdrawAccount extends BankingConnection{
	public PreparedStatement psmt_m;
	public WithdrawAccount() {
		super("education", "1234");
	}
	String sql;
	
	@Override
	public void dbExecute() {
		try {
			sql="update banking set money=? where account_num=?";
			psmt = con.prepareStatement(sql);
			while(true) {
				String str = inputValue("입금할 계좌번호");
				psmt.setString(1, str);
//				money=accMoney();
				String sql_mi = "select money from banking where account_num=?";
				psmt_m=con.prepareStatement(sql_mi);
				psmt_m.setString(1, sql_mi);
				rs=psmt_m.executeQuery();
				int acc_money=0;
				while(rs.next()) {
					acc_money=rs.getInt("money");
				}
				System.out.print("입금금액:");
				int money = BankingConnection.scan.nextInt();
				BankingConnection.scan.nextLine();
				money -= acc_money; 
				
				psmt.setLong(2, money);
			}
		}
		catch (SQLException e) {
			System.out.println("쿼리 실행 중 예외발생");
			e.printStackTrace();
		}
	}
}
