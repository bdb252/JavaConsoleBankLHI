package banking;

import java.util.Objects;

public class HighCreditAccount extends Account{
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
			return 0.07;			
		}
		else if(rank.equals("B")) {
			return 0.04;			
		}
		else if(rank.equals("C")) {
			return 0.02;			
		}
		return 0;
	}
	@Override
	public int depositInterest(int money) {
		double i = interest*0.01;
		double plusInterest = rankInterest(this.rank);
		return super.depositInterest(money)+(int)(super.getMoney()*i)+(int)(super.getMoney()*plusInterest);
	}
	@Override
	public String makeTxt() {
		return "[신용신뢰계좌]"+super.makeTxt()+", 기본이자="+getInterest()+", 신용등급="+getRank();
	}
	@Override
	public void showAccData() {
		super.showAccData();
		System.out.println("기본이자>"+interest+"%");
		System.out.println("신용등급>"+rank);
	}
}
