package banking.threeby3;

import java.io.Serializable;
import java.util.Objects;

abstract public class Account2 implements Serializable{
	private String Account2Num;
	private String name;
	private int money;
	
	public Account2(String Account2Num, String name, int money) {
		this.Account2Num = Account2Num;
		this.name = name;
		this.money = money;
	}	
	
	public String getAccount2Num() {
		return Account2Num;
	}

	public void setAccount2Num(String Account2Num) {
		this.Account2Num = Account2Num;
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
		System.out.println("계좌번호>"+Account2Num);
		System.out.println("고객이름>"+name);
		System.out.println("잔고>"+money);
	}
	
	public int depositMoneyInterest(int money) {
		return this.money + money;
	}
	
	@Override
	public String toString() {
		String str = "계좌번호="+this.Account2Num+", 이름="+this.name+", 잔고="+this.money;
		return str;
	}
	@Override
	public int hashCode() {
		int returnCode = Objects.hash(Account2Num);
		return returnCode;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if(obj == null)
			return false;
		
		Account2 acc = (Account2)obj;
		if(this.Account2Num.equals(acc.Account2Num)) {
			return true;
		}
		else {
			return false;
		}
	}
}
