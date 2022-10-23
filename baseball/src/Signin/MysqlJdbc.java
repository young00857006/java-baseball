package Signin;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;

public class MysqlJdbc { 
	private Connection con = null; //Database objects 
	//連接object 
	private Statement stat = null;
	private Statement stat2 = null;
	//執行,傳入之sql為完整字串 
	private ResultSet rs = null;
	private ResultSet rs2 = null;
	//結果集 
	private PreparedStatement pst = null; 
	//執行,傳入之sql為預儲之字申,需要傳入變數之位置 
	//先利用?來做標示
	private String dropdbSQL = "DROP TABLE user ";
	private String createdbSQL = "CREATE TABLE user (" + 
			"    account     VARCHAR(20) " +
			"  , password    VARCHAR(20) " +
			"  , name        VARCHAR(20) " +
			"  , mail        VARCHAR(256))";
	private String insertdbSQL = "INSERT INTO user VALUES(?,?,?,?)";
	private String selectSQL = "select * from user "; 
	
	public MysqlJdbc() 
	{ 
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			//註冊driver 
			con = DriverManager.getConnection( 
			"jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8", 
			"root","Warren0702"); //00857006為密碼
			//取得connection
			//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
			//localhost是主機名,test是database名
			//useUnicode=true&characterEncoding=Big5使用的編碼 
		} 
		catch(ClassNotFoundException e) 
		{ 
			System.out.println("DriverClassNotFound :"+e.toString()); 
		}//有可能會產生sqlexception 
		catch(SQLException x) { 
			System.out.println("Exception :"+x.toString()); 
		} 
	} 
	//建立table的方式 
	//可以看看Statement的使用方式 
	public void createTable() 
	{ 
		try 
		{ 
			stat = con.createStatement(); 
			stat.executeUpdate(createdbSQL); 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("CreateDB Exception :" + e.toString()); 
		} 
		finally 
		{ 
			Close(); 
		} 
	}
	public void insertTable(String account,String password, String name, String mail)
	{ 
		try 
		{ 
			pst = con.prepareStatement(insertdbSQL); 
			//pst.setString(1, name); 
			pst.setString(1, account);
			pst.setString(2, password);
			pst.setString(3, name);
			pst.setString(4, mail);
			pst.executeUpdate(); 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("InsertDB Exception :" + e.toString()); 
		} 
		finally 
		{ 
			Close(); 
		} 
	} 
	//刪除Table, 
	//跟建立table很像
	public void dropTable() 
	{ 
		try 
		{ 
		stat = con.createStatement();
		stat.executeUpdate(dropdbSQL);
		} 
		catch(SQLException e) 
		{ 
			System.out.println("DropDB Exception :" + e.toString()); 
		} 
		finally 
		{ 
			Close(); 
		} 
	}

	//刪除某一欄
	public void delcol(String account)
	{
		try
		{
		stat = con.createStatement();
		stat.executeUpdate("DELETE FROM user WHERE account='"+account+"'"); //DELETE FROM 表的名字 WHERE 名稱='名稱的value';
		}
		catch(SQLException e)
		{
			System.out.println("DropDB Exception :" + e.toString());
		}
		finally
		{
			Close();
		}
	}
	//---------------登入的帳號密碼確認--------------------------------
	public String checkAccount(String account, String password) throws SQLException {
		String result = null;
		try {
			stat = con.createStatement();
			rs = stat.executeQuery("select * from user where account = '"+account+"'");
			while(rs.next()) {
				//System.out.printf("輸入的%s\n", password);
				//System.out.printf("資料庫的%s\n", rs.getString("password"));
				if(password.equals(rs.getString("password"))){
					result=rs.getString("password");
				}
				else {
					result = null;
				}
			}
		}
		catch(SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		}
		finally {
			Close();
		}
		return result;
	}
	//---------------註冊的帳號重複性確認--------------------------------
	public String checkAccount2(String account, String password) throws SQLException {
		String result2 = null;
		try {
			stat2 = con.createStatement();
			rs2 = stat2.executeQuery("select * from user where account = '"+account+"'");
			while(rs2.next()) {
				result2 = rs2.getString("account");
			}
		}
		catch(SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		}
		finally {
			Close();
		}
		return result2;
	}
	//查詢資料
	//可以看看回傳結果集及取得資料方式
	public void SelectTable() 
	{ 
		try 
		{ 
			stat = con.createStatement(); 
			rs = stat.executeQuery(selectSQL);
			System.out.println("account  password  name mail");
			System.out.println("-----------------------");
			while(rs.next()) 
			{
				System.out.println(rs.getString("account")+"     "+rs.getString("password")+"      "+rs.getString("name")+"      "+rs.getString("mail"));
			} 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("DropDB Exception :" + e.toString()); 
		} 
		finally 
		{ 
			Close(); 
		} 
	} 
		//完整使用完資料庫後,記得要關閉所有Object 
		//否則在等待Timeout時,可能會有Connection poor的狀況 
		private void Close() 
		{ 
			try 
			{ 
				if(rs!=null) 
				{ 
					rs.close(); 
					rs = null; 
				} 
				if(stat!=null) 
				{ 
					stat.close(); 
					stat = null; 
				} 
				if(pst!=null) 
				{ 
					pst.close(); 
					pst = null; 
				} 
			} 
			catch(SQLException e) 
			{ 
				System.out.println("Close Exception :" + e.toString()); 
			} 
	} 
		
	public void start()
	{ 
		//測看看是否正常 
		MysqlJdbc test = new MysqlJdbc(); 
		//test.dropTable();//此為刪除所有資料庫內容功能
		System.out.print("---建表測試---\n");
		test.createTable();
		test.insertTable("test","test","test", "aaa@gmail.com");
		test.SelectTable();
		System.out.print("---刪除測試---\n");
		test.delcol("test");
		test.SelectTable();
		SignInGUI test1 = new SignInGUI();


	}
}