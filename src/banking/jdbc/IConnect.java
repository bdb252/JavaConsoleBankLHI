package banking.jdbc;

public interface IConnect {
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	void dbExecute(); //쿼리문실행
	void dbClose(); //DB 자원반납
	String inputValue(String title);
}
