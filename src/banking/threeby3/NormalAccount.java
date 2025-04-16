package banking.threeby3;

import java.util.Objects;

public class NormalAccount extends Account2{
	private int interest;

	public NormalAccount(String accountNum, String name, int money, int interest) {
		super(accountNum, name, money);
		this.interest=interest;
	}
	
	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}
	@Override
	public String toString() {
		return "[보통계좌]"+super.toString()+", 기본이자="+this.interest+"%";		
	}
	@Override
	public int depositMoneyInterest(int money) {
		double i = interest*0.01;
		return super.depositMoneyInterest(money)+(int)(super.getMoney()*i);
	}
	@Override
	public void showAccData() {
		super.showAccData();
		System.out.println("기본이자>"+interest+"%");
	}
}
