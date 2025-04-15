package banking;

import java.util.Objects;

public class NormalAccount extends Account{
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
	public String makeTxt() {
		return "[보통계좌]"+super.makeTxt()+", 기본이자="+getInterest()+"%";
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
