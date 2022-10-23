package ability.createDB;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;
public class Run extends ConnectDB implements Data{
	private String name; 
	private int year,month,day;
	private double run100,sBhB,fBtB,hBsB,hBfB;
	private ArrayList<Double> hBfBArray = new ArrayList();
	private ArrayList<Double> run100Array = new ArrayList();
	private ArrayList<Double> sBhBArray = new ArrayList();
	private ArrayList<Double> fBtBArray = new ArrayList();
	private ArrayList<Double> hBsBArray = new ArrayList();
	private ArrayList<String> dateArray = new ArrayList();
	Scanner input = new Scanner(System.in);
	public Run(String username) {
		super("run");
		super.setInsertdbSQL("INSERT INTO " + username + " VALUES(?,?,?,?,?,?,?,?)");
		this.name = username;
	}
	
	public void setArray() {
		try 
		{ 
			stat = con.createStatement(); 
			rs = stat.executeQuery("select * from " + name+" order by year ASC,month ASC, day ASC"); 
			while(rs.next()) 
			{ 
				//System.out.println(rs.getString("ball")+"\t\t"+rs.getString("ground")); 
				hBfBArray.add(rs.getDouble("0to1"));
				run100Array.add(rs.getDouble("100m"));
				sBhBArray.add(rs.getDouble("2to0"));
				fBtBArray.add(rs.getDouble("1to3"));
				hBsBArray.add(rs.getDouble("0to2"));
				dateArray.add(rs.getInt("year")+"/"+rs.getInt("month")+"/"+rs.getInt("day"));
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
	@Override
	public void  inputData(String year,String month,String day,String target,Double data) {
		/*
		while(true) {
			System.out.printf("date?");
			month = input.nextInt();
			System.out.printf("本壘到一壘?");
			hBfB = input.nextDouble();
			System.out.printf("本壘到二壘?");
			hBsB = input.nextDouble();
			System.out.printf("一壘到三壘?");
			fBtB = input.nextDouble();
			System.out.printf("二壘到本壘?");
			sBhB = input.nextDouble();
			System.out.printf("100 m?");
			run100 = input.nextDouble();
			try 
			{ 
				pst = con.prepareStatement(super.getInsertdbSQL()); 
				//pst.setString(1, name); 
				pst.setInt(1, month);
				pst.setInt(2, day);
				pst.setDouble(3, hBfB); 
				pst.setDouble(4, hBsB);
				pst.setDouble(5, fBtB);
				pst.setDouble(6, sBhB);
				pst.setDouble(7, run100);
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
			System.out.printf("1:繼續 -1:不要", null);
			int choice = input.nextInt();
			if(choice ==-1)
				break;
		}
		*/
		try {
			  stat = con.createStatement();
			  stat.execute("UPDATE " + name + " SET " + target + " = '" + data+"' WHERE year = '"+year+"' AND month = '"+month+"' AND day='"+day+"'");
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
	
	@Override
	public double getData(String target,String year,String month,String day) {
		double result = 0;
		try 
		{ 
			stat = con.createStatement(); 
			rs = stat.executeQuery("SELECT "+ target +" FROM " + name + " WHERE year = '"+year+"' AND month='" + month + "' AND day='" + day+"'");  
			while(rs.next()) 
			{ 
				result = rs.getDouble(target);
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
		return result;
	}
	/*
	@Override
	public void showLineChartData() {
		setArray();
		while(true) {
			System.out.printf("1:本壘到一壘? 2:本壘到二壘? 3:100公尺 -1:結束", null);
			int options = input.nextInt();
			 if(options==-1)
				 break;
			if(options==1) {
				LineChart_AWT chart = new LineChart_AWT("Home to First Base", "Home to First Base" ,dateArray,hBfBArray,"Seconds of Home to First Base");
			    chart.pack( );          
			    RefineryUtilities.centerFrameOnScreen( chart );          
			    chart.setVisible( true );
			    System.out.printf("-1:結束圖表", null);
				options = input.nextInt();
			    if(options==-1)
			    	chart.setVisible( false );
			}
			else if(options==2) {
				LineChart_AWT chart = new LineChart_AWT("Home to Seconf Base", "Home to Second Base" ,dateArray,hBsBArray,"Seconds of Home to Second Base");
			    chart.pack( );          
			    RefineryUtilities.centerFrameOnScreen( chart );          
			    chart.setVisible( true );
			    System.out.printf("-1:結束圖表", null);
				options = input.nextInt();
			    if(options==-1)
			    	chart.setVisible( false );
			}
			else if(options==3) {
				LineChart_AWT chart = new LineChart_AWT("100 M", "100 M (s)" ,dateArray,run100Array,"Secnd of 100 M");
			    chart.pack( );          
			    RefineryUtilities.centerFrameOnScreen( chart );          
			    chart.setVisible( true );
			    System.out.printf("-1:結束圖表", null);
				options = input.nextInt();
			    if(options==-1)
			    	chart.setVisible( false );
			}
		}
	}
	*/
	@Override
	public JPanel showLineChartData(int options) {
		JPanel panel = null;
		setArray();
		
		if(options==1) {
		
			LineChart t = new LineChart("Home to First Base", "Seconds (s)" ,dateArray,hBfBArray,"");
			panel = t.getJPanel();
		}
		else if(options==2) {
			LineChart t = new LineChart("Home to Second Base", "Seconds (s)" ,dateArray,hBsBArray,"");
			panel = t.getJPanel();
			
		}
		else if(options==3) {
			LineChart t = new LineChart("First Base to Third Base", "Seconds (s)" ,dateArray,fBtBArray,"");
			panel = t.getJPanel();
			
		}
		else if(options==4) {
			LineChart t = new LineChart("Second Base to Home", "Seconds (s)" ,dateArray,sBhBArray,"");
			panel = t.getJPanel();
			
		}
		else if(options==5) {
			
			LineChart t = new LineChart("100 M", "100 M (s)" ,dateArray,run100Array,"Secnd of 100 M");
			panel = t.getJPanel();
		}
		return panel;
	}
}
