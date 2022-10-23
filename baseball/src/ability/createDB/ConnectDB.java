package ability.createDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	protected Connection con = null; //Database objects  
	protected Statement stat = null; 
	protected ResultSet rs = null; 
	protected PreparedStatement pst = null; 
	private String dropdbSQL = "DROP TABLE user "; 
	/*
	private String createdbSQL = "CREATE TABLE user (" + 
	"    id     INTEGER " + 
	"  , name    VARCHAR(20) " + 
	"  , passwd  VARCHAR(20))"; 
	private String insertdbSQL = "insert into user(id,name,passwd) " + 
	"select ifNULL(max(id),0)+1,?,? FROM user"; 
	*/
	/*
	private String createdbSQL = "CREATE TABLE user (" + 
			"  name    VARCHAR(20) " + 
			"  , passwd  VARCHAR(20))"; 
	
	private String insertdbSQL = "insert into user(name,passwd) " + 
			"select ?,? FROM user"; 
	*/
	/*
	private String createdbSQL = "CREATE TABLE user (" + 
								 "    ID     INTEGER" + 
								 "  , Date  VARCHAR(20))"; 
	private String insertdbSQL = "INSERT INTO user VALUES(?,?)"; 
	private String selectSQL = "select * from user "; 
	*/
	private String tableName,createdbSQL,insertdbSQL,selectSQL,updateSQL;
	
	
	public ConnectDB(String tablename) {
		this.tableName = tablename;
		connectMysql();
	}
	
	public ConnectDB(String tableName,String createdbSQL) {
		this.tableName = tableName;
		this.createdbSQL = createdbSQL;
		connectMysql();
		createTable();
	}
	/*
	public ConnectDB(String tableName,String updateSQL, String insertdbSQL) {
		this.tableName = tableName;
		this.updateSQL = updateSQL;
		this.insertdbSQL = insertdbSQL;
		connectMysql();
	}
*/
	public void setInsertdbSQL(String insertdbSQL) {
		this.insertdbSQL = insertdbSQL;
	}
	public String getInsertdbSQL() {
		return insertdbSQL;
	}
	
	public void connectMysql() 
	{ 
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			//註�?�driver 
			con = DriverManager.getConnection( 
			"jdbc:mysql://localhost/"+tableName+"?useUnicode=true&characterEncoding=utf-8", 
			"root","Warren0702");
		} 
		catch(ClassNotFoundException e) 
		{ 
			System.out.println("DriverClassNotFound :"+e.toString()); 
		}
		catch(SQLException x) { 
			System.out.println("Exception :"+x.toString()); 
		} 
	} 
	
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
	
	
	public void delcol(int id) 
	{ 
		try 
		{ 
		stat = con.createStatement(); 
		stat.executeUpdate("DELETE FROM user WHERE ID='"+id+"'"); 
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
	
	
	public void SelectTable() 
	{ 
		try 
		{ 
			stat = con.createStatement(); 
			rs = stat.executeQuery(selectSQL); 
			System.out.println("ID\t\tDate"); 
			while(rs.next()) 
			{ 
				System.out.println(rs.getString("ID")+"\t\t"+rs.getString("Date")); 
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
	public int CreateTraing(String tableName,int year,int day,int month) {
		int check=0;
		try {
			stat = con.createStatement();
		    rs = stat.executeQuery("SELECT year,month,day FROM " +tableName);
	    	while(rs.next()) {
	    		System.out.println(rs.getInt("month"));
	    		System.out.println(rs.getInt("day"));
	    		if(rs.getInt("year")==year && rs.getInt("day")==day && rs.getInt("month")==month) {
	    			check=1;
	    			break;
	    		}
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
		return check;
	}
	public void Close() 
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
}
