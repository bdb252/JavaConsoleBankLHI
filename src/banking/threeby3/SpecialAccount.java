package banking.threeby3;

public class SpecialAccount extends NormalAccount{
	int cnt;
	
	public SpecialAccount(String accountNum, String name, int money, int interest) {
		super(accountNum, name, money, interest);
		cnt = 0;
	}
	
	@Override
	public int depositMoneyInterest(int money) {
		double i = super.getInterest()*0.01;
		cnt++;
		System.out.println("입금횟수:"+cnt);
		int basicmoney = super.depositMoneyInterest(money);
		if(cnt%2==0) {
			return basicmoney+ICustomDefine.BONUS;
		}
		else {
			return basicmoney;			
		}
	}
	@Override
	public String toString() {
		return "[특판계좌]"+super.toString();
	}
}
