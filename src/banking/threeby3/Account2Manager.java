package banking.threeby3;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;

public class Account2Manager {
	private HashSet<Account2> Account2s;
	AutoSaver autosave;
	
	public Account2Manager(int size) {
		Account2s = new HashSet<Account2>();
	}
	//두가지 계좌 옵션 중 선택하는 메뉴
	public void newAccount2Menu() {
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.println("3.특판계좌");
		System.out.print("선택:");
	}

	public void makeAccount2() {    // 계좌개설을 위한 함수
		newAccount2Menu();
		int choice = BankingSystemMain.sc.nextInt();
		BankingSystemMain.sc.nextLine();
		if(choice != 1 && choice != 2 && choice != 3) {
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
		
		Account2 newAcc = null;
		if(choice == 1) {
			newAcc = new NormalAccount(accnum, accname, money, interest);
		}
		else if(choice==2) {
			System.out.print("신용등급(A,B,C등급):");
			String rank=BankingSystemMain.sc.nextLine();
			newAcc = new HighCreditAccount(accnum, accname, money, interest,rank);
		}
		else if(choice == 3) {
			newAcc = new SpecialAccount(accnum, accname, money, interest);
		}
		if(! Account2s.add(newAcc)) { //계좌번호가 같은 중복된 객체 발견
			System.out.println("중복계좌발견됨.\n덮어쓸까요?(y or n)");
			String dup=BankingSystemMain.sc.nextLine();
			if(dup.equalsIgnoreCase("y")) {
				//삭제
				Account2s.remove(newAcc); //기존에 Account2안에 있던 객체 제거
				//덮어쓰기
				Account2s.add(newAcc);	//new로 새로 만든 객체 추가
				System.out.println("새로운 정보로 갱신되었습니다.");
			}
			else if(dup.equalsIgnoreCase("n")) {
				System.out.println("덮어쓰기를 진행하지 않습니다.");
				return;
			}
		}
		System.out.println("계좌개설이 완료되었습니다.\n");
	}
	
	public void depositMoney() {    // 입    금
		System.out.println("***입  금***");
		System.out.println("계좌번호와 입금할 금액을 입력하시오.");
		System.out.print("계좌번호:");
		String accnum=BankingSystemMain.sc.nextLine();
		try {
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
			Iterator<Account2> itr = Account2s.iterator();
			while(itr.hasNext()) {
				Account2 curracc = itr.next();
				if(accnum.equals(curracc.getAccount2Num())) {
					curracc.setMoney(curracc.depositMoneyInterest(money));
				}
			}
			System.out.println("입금이 완료되었습니다.");			
		}
		catch (InputMismatchException e) {
			System.out.println("입금 금액은 정수만 가능합니다.");
		}
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
		Iterator<Account2> itr = Account2s.iterator();
		while(itr.hasNext()) {
			Account2 curracc = itr.next();
			if(accnum.equals(curracc.getAccount2Num())) {
				if(curracc.getMoney() < money) {
					System.out.println("잔고가 부족합니다. 금액 전체를 출금할까요?");
					System.out.println("YES : 금액전체 출금처리\nNO : 출금요청취소");
					System.out.print("선택(YES 또는 NO로만 입력하세요.):");
					BankingSystemMain.sc.nextLine();
					String choice = BankingSystemMain.sc.nextLine();
					if(choice.equalsIgnoreCase("YES")) {
						System.out.print("잔고가 모두 출금됩니다.");							
						curracc.setMoney(0);
					}
					else if(choice.equalsIgnoreCase("NO")) {
						System.out.println("출금 요청이 취소되었습니다.");
						return;
					}
				}
				else {
					int res=curracc.getMoney() - money;
					curracc.setMoney(res);
				}
			}
		}
		System.out.println("출금이 완료되었습니다.\n");
	}
	
	public void deleteAccount2() { //계좌정보삭제
		System.out.println("***계좌정보삭제***");
		System.out.println("삭제할 계좌번호를 입력하세요");
		System.out.print("계좌번호:");
		String accnum=BankingSystemMain.sc.nextLine();
		boolean flag = false;
		for(Account2 curracc : Account2s) {
			if(accnum.equals(curracc.getAccount2Num())) {
				Account2s.remove(curracc);
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
		for(Account2 acc : Account2s) {
			System.out.println("------------------------------");
			acc.showAccData();;
			System.out.println("------------------------------");			
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.\n");
	}
	
	public void saveAccount2Info() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/banking/Account2Info.obj"));
			for(Account2 ac : Account2s) {
				out.writeObject(ac);
			}
			System.out.println("Account2Info.obj 파일로 저장되었습니다.");
			out.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Account2Info.obj 파일없음");
		}
		catch (IOException e) {
			System.out.println("예외발생");
		}
	}
	
	public void readAccount2Info() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/banking/Account2Info.obj"));
			System.out.println("Account2Info.obj 복원완료");
			while(true) {
				try {
					Account2 ac = (Account2)in.readObject();
					Account2s.add(ac);
				}
				catch (EOFException e){
					break;
				}
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("클래스 없음");
		}
		catch (FileNotFoundException e) {
			System.out.println("Account2Info.obj 파일없음");
		}
		catch (IOException e) {
			System.out.println("IO 예외 발생");
			e.printStackTrace();
		}
	}
	
	public void autoSave() {
		System.out.println("***자동저장을 시작합니다***");
		try {
			System.out.printf("쓰레드=Thread[%s,5,main]",autosave.getName());			
		}
		catch (Exception e) {
			System.out.println("쓰레드 없음 예외발생");
		}
		System.out.println("저장옵션을 선택하세요.\n1.자동저장On, 2.자동저장Off");
		System.out.print("선택:");
		int choice = BankingSystemMain.sc.nextInt();
		BankingSystemMain.sc.nextLine();
		if(choice == 1) {
			if(autosave==null) {
				System.out.println("자동저장을 시작합니다.");
				autosave = new AutoSaver(Account2s);	
				autosave.setDaemon(true);
			}
			if(autosave.isAlive()) {
				System.out.println("이미 자동저장이 실행중입니다.");				
				return;
			}
			autosave.start();
		}
		else if(choice == 2) {
			autosave.interrupt();
			autosave=null;
			System.out.println("자동저장을 종료합니다.");
		}
		
	}
}
