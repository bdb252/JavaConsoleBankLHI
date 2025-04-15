package banking;

import java.io.Serializable;
import java.util.Objects;

abstract public class Account implements Serializable{
	private String accountNum;
	private String name;
	private int money;
	
	public Account(String accountNum, String name, int money) {
		this.accountNum = accountNum;
		this.name = name;
		this.money = money;
	}	
	
	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void showAccData() {
		System.out.println("계좌번호>"+accountNum);
		System.out.println("고객이름>"+name);
		System.out.println("잔고>"+money);
	}
	
	public int depositMoneyInterest(int money) {
		return this.money + money;
	}
	
	@Override
	public String toString() {
		String str = "계좌번호="+this.accountNum+", 이름="+this.name+", 잔고="+this.money;
		return str;
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
