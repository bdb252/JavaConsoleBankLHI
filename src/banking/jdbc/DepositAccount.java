package banking.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepositAccount extends BankingConnection{
	public PreparedStatement psmt_mi;
	public DepositAccount() {
		super("education", "1234");
	}

	String sql;
	int res;
	
	@Override
	public void dbExecute() {
		try {
			sql="update banking set money=? where account_num=?";
			psmt = con.prepareStatement(sql);
			while(true) {
				String str = inputValue("입금할 계좌번호");
				psmt.setString(1, str);
//				money=depMoney();
				String sql_mi = "select money, interest from banking where account_num=?";
				psmt_mi=con.prepareStatement(sql_mi);
				psmt_mi.setString(1, sql_mi);
				rs=psmt_mi.executeQuery();
				int acc_money=0;
				int acc_interset=0;
				while(rs.next()) {
					acc_money=rs.getInt("money");
					acc_interset=rs.getInt("interest");
				}
				System.out.print("입금금액:");
				int money = BankingConnection.scan.nextInt();
				BankingConnection.scan.nextLine();
				double i = 0.01 * acc_interset;
				money = money + (int)(money * i) + acc_money; 
				
				psmt.setLong(2, money);
			}
		}
		catch (SQLException e) {
			System.out.println("쿼리 실행 중 예외발생");
			e.printStackTrace();
		}
	}
}
