package banking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;

public class AutoSaver extends Thread{
	HashSet<Account> accounts;

	public AutoSaver(HashSet<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public void run() {
		while(true) {
			try (BufferedWriter out = new BufferedWriter(new FileWriter("src/banking/AutoSaveAccount.txt"));){			
				for(Account acc : accounts) {
					out.write(acc.makeTxt());
					out.newLine();
				}
				System.out.println("계좌정보가 텍스트로 자동저장되었습니다.");
			}				
			catch (Exception e) {
				System.out.println("자동저장 오류 발생");
				e.printStackTrace();
			}
			try {
				sleep(5000);
			}
			catch (InterruptedException e) {
				System.out.println("자동저장 중지");
				break;
			}
		}
	}
}
