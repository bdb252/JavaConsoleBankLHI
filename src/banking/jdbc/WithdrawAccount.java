package banking.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WithdrawAccount extends BankingConnection{
	public PreparedStatement psmt_m;
	public WithdrawAccount() {
		super("education", "1234");
	}
	String sql;
	int res;
	
	public int accMoney(String str) {
		int acc_money=0;
		try {
			String sql_m = "select money from banking where account_num=?";
			psmt_m=con.prepareStatement(sql_m);
			psmt_m.setString(1, str);
			rs=psmt_m.executeQuery();
			
			while(rs.next()) {
				acc_money=rs.getInt("money");
			}
		}
		catch (SQLException e) {
			System.out.println("쿼리 실행 중 예외발생");
			e.printStackTrace();
		}
		System.out.print("출금금액:");
		int money = BankingConnection.scan.nextInt();
		BankingConnection.scan.nextLine();
		if(money<0) {
			acc_money=0;
			return acc_money;
		}
		if(acc_money<money) {
			acc_money=-1;
		}
		else {
			acc_money -= money; 			
		}
		return acc_money;
	}
	@Override
	public void dbExecute() {
		try {
			sql="update banking set money=? where account_num=?";
			psmt = con.prepareStatement(sql);
			while(true) {
				String str = inputValue("출금할 계좌번호");
				psmt.setString(2, str);
				
				int money=accMoney(str);
				if(money == 0) {
					System.out.println("음수금액은 출금불가입니다.");
					return;
				}
				else if(money == -1) {
					System.out.println("잔액보다 큰 금액은 출금불가합니다.");
					return;
				}
				psmt.setInt(1, money);
				res=psmt.executeUpdate();
				System.out.println("출금이 완료되었습니다.");
				break;
			}
		}
		catch (SQLException e) {
			System.out.println("쿼리 실행 중 예외발생");
			e.printStackTrace();
		}
	}
}
