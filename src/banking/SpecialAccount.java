package banking;

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
		System.out.println(cnt);
		int basicmoney = super.depositMoneyInterest(money);
		if(cnt%2==0) {
			return basicmoney+500;
		}
		else {
			return basicmoney;			
		}
	}
}
