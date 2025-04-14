package banking;

import java.util.Objects;

public class HighCreditAccount extends Account{
	int interest;
	String rank;
	public HighCreditAccount(String accountNum, String name, int money, int interest, String rank) {
		super(accountNum, name, money);
		this.interest=interest;
		this.rank=rank;
	}
	@Override
	public void showAccData() {
		super.showAccData();
		System.out.println("기본이자>"+interest);
		System.out.println("신용등급>"+rank);
	}
}
