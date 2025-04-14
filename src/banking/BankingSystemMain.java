package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {
	public static Scanner sc = new Scanner(System.in);
	public static void showMenu() {// 메뉴출력
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입  금");
		System.out.println("3.출  금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.계좌정보삭제");
		System.out.println("6.프로그램종료");
	}
	
	public static void main(String[] args) {
		AccountManager acc = new AccountManager(50);
		while(true) {
			showMenu();
			try {
				System.out.print("메뉴선택:");
				int choice= readChoice();
				sc.nextLine();
				switch(choice) {
				case ICustomDefine.MAKE:
					acc.makeAccount();
					break;
				case ICustomDefine.DEPOSIT:
					acc.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					acc.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					acc.showAccInfo();
					break;
				case ICustomDefine.DELETE:
					acc.deleteAccount();
					break;
				case ICustomDefine.EXIT:
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);;
				}
			}
			catch (MenuSelectException e) {
				System.out.println("메뉴 입력 예외 발생");
				sc.nextLine();
			}
		}
	}
	public static int readChoice() throws MenuSelectException{
		int res = 0;
		try {
			res = sc.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println("메뉴는 정수만 입력해주세요.");
//			e.printStackTrace();
		}
		if(res < 1 || res > 5) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
		}
		return res;
	}

}
