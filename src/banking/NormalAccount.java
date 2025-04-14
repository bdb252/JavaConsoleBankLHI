package banking;

import java.util.Objects;

public class NormalAccount extends Account{
	int interest;

	public NormalAccount(String accountNum, String name, int money, int interest) {
		super(accountNum, name, money);
		this.interest=interest;
	}
	@Override
	public void showAccData() {
		super.showAccData();
		System.out.println("기본이자>"+interest+"%");
	}
}
