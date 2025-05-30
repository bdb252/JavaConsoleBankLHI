package banking.threeby3;

import java.util.Objects;

public class HighCreditAccount extends Account2{
	private int interest;
	private String rank;
	
	public HighCreditAccount(String accountNum, String name, int money, int interest, String rank) {
		super(accountNum, name, money);
		this.interest=interest;
		this.rank=rank;
	}
	
	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public double rankInterest(String rank) {
		if(rank.equals("A")) {
			return ICustomDefine.A_INT;			
		}
		else if(rank.equals("B")) {
			return ICustomDefine.B_INT;			
		}
		else if(rank.equals("C")) {
			return ICustomDefine.C_INT;
		}
		return 0;
	}
	@Override
	public int depositMoneyInterest(int money) {
		double i = interest*0.01;
		double plusInterest = rankInterest(this.rank);
		return super.depositMoneyInterest(money)+(int)(super.getMoney()*i)+(int)(super.getMoney()*plusInterest);
	}
	@Override
	public String toString() {
		return "[신용신뢰계좌]"+super.toString()+", 기본이자="+this.interest+"%, 신용등급="+this.rank;
	}
	@Override
	public void showAccData() {
		super.showAccData();
		System.out.println("기본이자>"+interest+"%");
		System.out.println("신용등급>"+rank);
	}
}
