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
	
	public int depMoney(String str) {
		int acc_money=0;
		int acc_interset=0;
		try {
			String sql_mi = "select money, interest from banking where account_num=?";
			psmt_mi=con.prepareStatement(sql_mi);
			psmt_mi.setString(1, str);
			rs=psmt_mi.executeQuery();
			
			while(rs.next()) {
				acc_money=rs.getInt("money");
				acc_interset=rs.getInt("interest");
			}
		}
		catch (SQLException e) {
			System.out.println("쿼리 실행 중 예외발생");
			e.printStackTrace();
		}		
		System.out.print("입금금액:");
		int depositmoney = BankingConnection.scan.nextInt();
		BankingConnection.scan.nextLine();
		double i = 0.01 * acc_interset;
		return acc_money + (int)(acc_money * i) + depositmoney; 
	}
	
	@Override
	public void dbExecute() {
		try {
			sql="update banking set money=? where account_num=?";
			psmt = con.prepareStatement(sql);
			while(true) {
				String str = inputValue("입금할 계좌번호");
				psmt.setString(2, str);
				
				int money=depMoney(str);
				
				psmt.setInt(1, money);	
				res=psmt.executeUpdate();
				System.out.println("입금이 완료되었습니다.");
				break;
			}
		}
		catch (SQLException e) {
			System.out.println("쿼리 실행 중 예외발생");
			e.printStackTrace();
		}
	}
}
