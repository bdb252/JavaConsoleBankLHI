package banking;

import java.util.Objects;

abstract public class Account implements IAccount{
	String accountNum;
	String name;
	int money;
	
	public Account(String accountNum, String name, int money) {
		this.accountNum = accountNum;
		this.name = name;
		this.money = money;
	}	
	@Override
	public void showAccData() {
		System.out.println("계좌번호>"+accountNum);
		System.out.println("고객이름>"+name);
		System.out.println("잔고>"+money);
	}
	@Override
	public int hashCode() {
		int returnCode = Objects.hash(accountNum);
		return returnCode;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if(obj == null)
			return false;
		
		Account acc = (Account)obj;
		if(this.accountNum.equals(acc.accountNum)) {
			return true;
		}
		else {
			return false;
		}
	}
}
