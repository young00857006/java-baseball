package ability.createDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateTraing extends ConnectDB{
	private int year,month,day;
	private String context;
	private Connection con1=null;
	private Statement stat1=null;
	public CreateTraing(String context,int year,int Month,int Day)  {
		super(context);
		this.year = year;
		this.month = Month;
		this.day = Day;
		this.context = context;
		try {
			stat = con.createStatement();
		    rs = stat.executeQuery("Show tables");
		    while(rs.next()) {
		    	if(check( rs.getString(1))){
		    		System.out.printf("break\n", null);	
		    	}
		    	else {
			    	switch(context) {
			    		case "infield":
			    			//super.setInsertdbSQL("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?,?)");
			    			pst = con.prepareStatement("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?,?,?)"); 
			    			pst.setInt(1, year);
			    			pst.setInt(2, month);
							pst.setInt(3, day);
							pst.setInt(4, 0); 
							pst.setInt(5, 0);
							pst.setDouble(6, 0);
							pst.setInt(7, 0);
							pst.executeUpdate(); 
			    			break;
			    		case "outfield":
			    			//super.setInsertdbSQL("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?,?)");
			    			pst = con.prepareStatement("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?,?,?)"); 
			    			pst.setInt(1, year);
			    			pst.setInt(2, month); 
							pst.setInt(3, day);
							pst.setInt(4, 0); 
							pst.setInt(5, 0);
							pst.setDouble(6, 0);
							pst.setInt(7, 0);
							pst.executeUpdate(); 
			    			break;
			    		case "run":
			    			//super.setInsertdbSQL("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?,?,?)");
			    			pst = con.prepareStatement("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?,?,?,?)"); 
			    			pst.setInt(1, year);
			    			pst.setInt(2, month);
							pst.setInt(3, day);
							pst.setDouble(4, 0); 
							pst.setDouble(5, 0);
							pst.setDouble(6, 0);
							pst.setDouble(7, 0);
							pst.setDouble(8, 0);
							pst.executeUpdate(); 
			    			break;
			    		case "hit":
			    			//super.setInsertdbSQL("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?)");
			    			pst = con.prepareStatement("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?,?)"); 
			    			pst.setInt(1, year);
			    			pst.setInt(2, month);
							pst.setInt(3, day);
							pst.setInt(4, 0); 
							pst.setInt(5, 0);
							pst.setDouble(6, 0);
							pst.executeUpdate(); 
			    			break;
			    		case "sidehit":
			    			//super.setInsertdbSQL("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?)");
			    			pst = con.prepareStatement("INSERT INTO " + rs.getString(1) + " VALUES(?,?,?,?,?,?)"); 
			    			pst.setInt(1, year);
			    			pst.setInt(2, month);
							pst.setInt(3, day);
							pst.setInt(4, 0); 
							pst.setInt(5, 0);
							pst.setDouble(6, 0);
							pst.executeUpdate(); 
			    			break;
			    	}
		    	}
		    	
		    }
		}
		catch(SQLException e) {
			
		}
		finally {
			Close();
		}
	}
	public CreateTraing(String context,int year,int Month,int Day,int t) throws SQLException  {
		super(context);
		this.year = year;
		this.month = Month;
		this.day = Day;
		  try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            this.con1 = DriverManager.getConnection("jdbc:mysql://localhost/"+context+"?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
		  }
		  catch(ClassNotFoundException e) { System.out.println("DriverClassNotFound :"+e.toString()); }//有可能會產生sqlexception
	}
	public void delcol() {
		try {
			stat1 = con1.createStatement();
			stat = con.createStatement();
		    rs = stat.executeQuery("Show tables");
		    while(rs.next()) {
		    	
		    	stat1.executeUpdate("delete from "+rs.getString(1)+" where year="+year+" and month="+month+" and day="+day);  			
		    }
		}
		catch(SQLException e) {
			System.out.println("dsjkflkdgfl:" + e.toString()); 
		}
		finally {
			Close();
		}
	
	}
	public boolean check(String tableName)  {
			ConnectDB tmp = new ConnectDB(context);
			return tmp.CreateTraing(tableName,year, day, month)==1;
	}
}
