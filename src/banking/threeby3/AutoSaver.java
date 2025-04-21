package banking.threeby3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;

public class AutoSaver extends Thread{
	HashSet<Account2> Account2s;

	public AutoSaver(HashSet<Account2> Account2s) {
		this.Account2s = Account2s;
	}

	@Override
	public void run() {
		while(true) {
			try (BufferedWriter out = new BufferedWriter(new FileWriter("src/banking/threeby3/AutoSaveAccount2.txt"));){			
				for(Account2 acc : Account2s) {
					out.write(acc.toString());
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
