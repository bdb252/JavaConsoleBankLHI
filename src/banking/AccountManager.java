package banking;

import java.util.HashSet;
import java.util.Iterator;

public class AccountManager {
	private HashSet<Account> accounts;
	
	public AccountManager(int size) {
		accounts = new HashSet<Account>();
	}
	//두가지 계좌 옵션 중 선택하는 메뉴
	public void newAccountMenu() {
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택:");
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
	public void makeAccount() {    // 계좌개설을 위한 함수
		newAccountMenu();
		int choice = BankingSystemMain.sc.nextInt();
		BankingSystemMain.sc.nextLine();
		if(choice != 1 && choice != 2) {
			System.out.println("메뉴화면으로 돌아갑니다.");
			return;
		}
		System.out.print("계좌번호:");
		String accnum=BankingSystemMain.sc.nextLine();
		System.out.print("고객이름:");
		String accname=BankingSystemMain.sc.nextLine();
		System.out.print("잔고:");
		int money=BankingSystemMain.sc.nextInt();		
		System.out.print("기본이자%(정수형태로 입력):");
		int interest=BankingSystemMain.sc.nextInt();
		BankingSystemMain.sc.nextLine();
		
		if(choice == 1) {
			if(! accounts.add(new NormalAccount(accnum, accname, money, interest))) {
				System.out.println("중복계좌발견됨.\n덮어쓸까요?(y or n)");
				String dup=BankingSystemMain.sc.nextLine();
				if(dup.equals("y")) {
					//삭제
					for(Account curracc : accounts) {
						if(accnum.equals(curracc.accountNum)) {
							accounts.remove(curracc);
							break;
						}
					}
					//덮어쓰기
					NormalAccount nAcc = new NormalAccount(accnum, accname, money, interest);
					accounts.add(nAcc);					
					System.out.println("새로운 정보로 갱신되었습니다.");
				}
				else if(dup.equals("n")) {
					System.out.println("덮어쓰기를 진행하지 않습니다.");
					return;
				}
			}
		}
		else if(choice==2) {
			System.out.print("신용등급(A,B,C등급):");
			String rank=BankingSystemMain.sc.nextLine();
			
			HighCreditAccount hcAcc = new HighCreditAccount(accnum, accname, money, interest,rank);
			accounts.add(hcAcc);
		}
		System.out.println("계좌개설이 완료되었습니다.\n");
	}
	
	public void depositMoney() {    // 입    금
		System.out.println("***입  금***");
		System.out.println("계좌번호와 입금할 금액을 입력하시오.");
		System.out.print("계좌번호:");
		String accnum=BankingSystemMain.sc.nextLine();
		System.out.print("입금액:");
		int money=BankingSystemMain.sc.nextInt();
		if(money < 0) {
			System.out.println("음수는 입금불가");
			return;
		}
		if(money % 500 != 0) {
			System.out.println("500원 단위로 입금 가능함");
			return;
		}
		Iterator<Account> itr = accounts.iterator();
		while(itr.hasNext()) {
			Account curracc = itr.next();
			if(accnum.equals(curracc.accountNum)) {
				if(curracc instanceof NormalAccount) {
					int currmoney = ((NormalAccount)curracc).money;
					double interest = ((NormalAccount)curracc).interest * 0.01;
					((NormalAccount)curracc).money= currmoney+(int)(currmoney*interest)+money;					
				}
				else if(curracc instanceof HighCreditAccount) {
					int currmoney = ((HighCreditAccount)curracc).money;
					double interest = ((HighCreditAccount)curracc).interest * 0.01;
					double plusInterest = rankInterest(((HighCreditAccount)curracc).rank);
					((HighCreditAccount)curracc).money= currmoney+(int)(currmoney*interest)+
						(int)(currmoney*plusInterest)+money;
				}
			}
		}
		
		System.out.println("입금이 완료되었습니다.");
	}
	public void withdrawMoney() { // 출    금
		System.out.println("***출  금***");
		System.out.println("계좌번호와 출금할 금액을 입력하시오.");
		System.out.print("계좌번호:");
		String accnum=BankingSystemMain.sc.nextLine();
		System.out.print("출금액:");
		int money=BankingSystemMain.sc.nextInt();
		if(money < 0) {
			System.out.println("음수는 출금불가");
			return;
		}
		if(money % 1000 != 0) {
			System.out.println("출금은 1000원단위로만 출금이 가능합니다.");
			return;
		}
		Iterator<Account> itr = accounts.iterator();
		while(itr.hasNext()) {
			Account curracc = itr.next();
			if(accnum.equals(curracc.accountNum)) {
				if(curracc.money < money) {
					System.out.println("잔고가 부족합니다. 금액 전체를 출금할까요?");
					System.out.println("YES : 금액전체 출금처리\nNO : 출금요청취소");
					System.out.print("선택(YES 또는 NO로만 입력하세요.):");
					BankingSystemMain.sc.nextLine();
					String choice = BankingSystemMain.sc.nextLine();
					if(choice.equalsIgnoreCase("YES")) {
						System.out.print("잔고가 모두 출금됩니다.");							
						curracc.money = 0;
					}
					else if(choice.equalsIgnoreCase("NO")) {
						System.out.println("출금 요청이 취소되었습니다.");
						return;
					}
				}
				else {
					curracc.money -= money;						
				}
			}
		}
		System.out.println("출금이 완료되었습니다.\n");
	}
	
	public void deleteAccount() { //계좌정보삭제
		System.out.println("***계좌정보삭제***");
		System.out.println("삭제할 계좌번호를 입력하세요");
		System.out.print("계좌번호:");
		String accnum=BankingSystemMain.sc.nextLine();
		boolean flag = false;
		for(Account curracc : accounts) {
			if(accnum.equals(curracc.accountNum)) {
				accounts.remove(curracc);
				flag = true;
				break;
			}
		}
		if(flag) {
			System.out.println("계좌를 삭제하였습니다.");
			System.out.println("****************************");
		}
		else {
			System.out.println("일치하는 계좌가 없습니다.");
		}
	}
	
	public void showAccInfo() {  // 전체계좌정보출력
		System.out.println("***계좌정보출력***");
		for(Account acc : accounts) {
			System.out.println("------------------------------");
			acc.showAccData();;
			System.out.println("------------------------------");			
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.\n");
	}
}
