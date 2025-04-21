package banking.threeby3;

import java.util.Random;
import java.util.Scanner;

public class Threeby3Game {
	 Scanner sc = new Scanner(System.in);
	 Random rand = new Random();
	 char[][] num = new char[3][3];
	 char[][] correctNum = new char[3][3];
	 int x_row;
	 int x_col;
	
	public Threeby3Game() {
		System.out.println("게임을 시작합니다.");
		do {
			resetGame();			
		}while(checkSet());
//		executeGame();
	}
	
	public  void resetGame() {
		//x의 위치, numset초기화
		int x = rand.nextInt(9)+1;
		x_row=(x-1)/3;	//x=4이면 3/3=1
		x_col=(x-1)%3; // x=4이면 3%3=0
		char c = '1';
		
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				num[i][j]=c;
				correctNum[i][j]=c++;
				if(i == x_row && j == x_col) {
					num[i][j] = 'x';
					correctNum[i][j] = 'x';
				}
			}
		}
		//num 셔플
		int shuffleNum = 100;
		for(int i = 0; i<shuffleNum; i++) {
			int dir = rand.nextInt(4);
			switch(dir) {
			case 0:
				if(x_col+1<=2) {
					swapX(x_row, x_col, x_row, x_col+1);
					x_col++;
				}
				break;
			case 1:
				if(x_col-1>=0) {
					swapX(x_row, x_col, x_row, x_col-1);
					x_col--;
				}
				break;
			case 2:
				if(x_row+1<=2) {
					swapX(x_row, x_col, x_row+1, x_col);
					x_row++;
				}
				break;
			case 3:
				if(x_row-1>=0) {
					swapX(x_row, x_col, x_row-1, x_col);
					x_row--;
				}
				break;
			}
		}
		
		
	}
	
	public  void gameSet() {
		System.out.println("=========");
		for(int i =0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				System.out.print(num[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("=========");
		System.out.println("[ 이동 ] a:Left d:Right w:Up s:Down");
		System.out.println("[ 종료 ] x:Exit");
	}
	
	public  void swapX(int i, int j, int new_i, int new_j) {
		num[i][j]=num[new_i][new_j];
		num[new_i][new_j]='x';
	}
	
	public  void cannotMove() {
		System.out.println("xxxxxxxxxxxxxxx");
		System.out.println("xxxxx이동불가xxxxx");
		System.out.println("xxxxxxxxxxxxxxx");
	}
	
	public  boolean checkSet() {
		boolean flag = true;
		for(int i =0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				if(correctNum[i][j]!=num[i][j]) {
					flag = false;
				}
			}
		}
		return flag;
	}
	
	public  boolean regameAsk() {
		if(checkSet()) { //정답이면
			System.out.println("==!!^^정답입니다^^!!==");
			System.out.println("=========");
			for(int i =0; i<3; i++) {
				for(int j = 0; j<3; j++) {
					System.out.print(num[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("=========");
			System.out.println("재시작하겠습니까?(y누르면 재시작, 나머지는 종료)");
			String regame = sc.nextLine();
			if(regame.equalsIgnoreCase("y")) {
				System.out.println("게임을 재시작합니다.");
				do {
					resetGame();			
				}while(checkSet());
			}
			else {
				System.out.println("게임을 종료합니다.");
				return true; //게임 종료
//				System.exit(0);
			}
		}
		//정답이 아니므로 
		return false;	//게임 계속
	}
	
	public  void executeGame() {
		while(true) {
			gameSet();
			System.out.print("키를 입력해주세요 : ");
			String key = sc.nextLine();
			//left
			if(key.equalsIgnoreCase("a")) {
				if(x_col+1<=2) {
					swapX(x_row, x_col, x_row, x_col+1);
					x_col++;
					if(regameAsk()) {
						break;
					}
				}
				else {
					cannotMove();
				}
			}
			//right
			else if(key.equalsIgnoreCase("d")) {
				if(x_col-1>=0) {
					swapX(x_row, x_col, x_row, x_col-1);
					x_col--;
					if(regameAsk()) {
						break;
					}
				}
				else {
					cannotMove();
				}
			}
			//up
			else if(key.equalsIgnoreCase("w")) {
				if(x_row+1<=2) {
					swapX(x_row, x_col, x_row+1, x_col);
					x_row++;
					if(regameAsk()) {
						break;
					}
				}
				else {
					cannotMove();
				}
			}
			//down
			else if(key.equalsIgnoreCase("s")) {
				if(x_row-1>=0) {
					swapX(x_row, x_col, x_row-1, x_col);
					x_row--;
					if(regameAsk()) {
						break;
					}
				}
				else {
					cannotMove();
				}
			}
			else if(key.equalsIgnoreCase("x")) {
				System.out.println("게임을 종료합니다.");
				break;
			}
		}
	}
	
//	public static void main(String[] args) {
//		new Threeby3Game();
//		executeGame();
//	}
}
