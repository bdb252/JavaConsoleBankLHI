package banking.jdbc;

import java.util.Scanner;

public class BankingSystemMain {
	public static Scanner sc = new Scanner(System.in);
	public static void showMenu() {// 메뉴출력
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입  금");
		System.out.println("3.출  금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.지정계좌정보출력");
		System.out.println("6.계좌정보삭제");
		System.out.println("7.프로그램종료");
		System.out.println("---------------");
	}
	
	public static void main(String[] args) {
		while(true) {
			showMenu();
			System.out.print("메뉴선택:");
			int choice=sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case ICustomDefine.MAKE:
				new MakeAccount().dbExecute();
				break;
			case ICustomDefine.DEPOSIT:
				new DepositAccount().dbExecute();
				break;
			case ICustomDefine.WITHDRAW:
				new WithdrawAccount().dbExecute();
				break;
			case ICustomDefine.SELECTALL:
				new SelectAllAccount().dbExecute();
				break;
			case ICustomDefine.SELECT:
				new SelectAccount().dbExecute();
				break;
			case ICustomDefine.DELETE:
				new DeleteAccount().dbExecute();
				break;
			case ICustomDefine.EXIT:
				//jdbc에서 하면 자동으로 커밋됨.
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}
}

