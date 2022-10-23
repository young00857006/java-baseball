package ability.createDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;



public class Hit extends ConnectDB implements Data{
	Scanner input = new Scanner(System.in);
	private String name;
	private int year,month,day,ball,hit;
	private double distance;
	private ArrayList<Integer> ballArray = new ArrayList();
	private ArrayList<Integer> hitArray = new ArrayList();
	private ArrayList<String> dateArray = new ArrayList();
	private ArrayList<Double> distanceArray = new ArrayList();
	public Hit(String username) {
		super("hit");
		super.setInsertdbSQL("INSERT INTO " + username + " VALUES(?,?,?,?,?,?)");
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
				ballArray.add(rs.getInt("ball"));
				hitArray.add(rs.getInt("hit"));
				dateArray.add(rs.getInt("year")+"/"+rs.getInt("month")+"/"+rs.getInt("day"));
				distanceArray.add(rs.getDouble("distance"));
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
			System.out.printf("ball?");
			ball = input.nextInt();
			System.out.printf("hit?");
			hit = input.nextInt();
			System.out.printf("distance?");
			distance = input.nextDouble();
		
			try 
			{ 
				pst = con.prepareStatement(super.getInsertdbSQL()); 
				//pst.setString(1, name); 
				pst.setInt(1, month);
				pst.setInt(2, day);
				pst.setInt(3, ball); 
				pst.setInt(4, hit);
				pst.setDouble(5, distance);
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
			rs = stat.executeQuery("SELECT "+ target +" FROM " + name + " WHERE year='" + year + "' AND month='" + month + "' AND day='" + day+"'"); 
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
		ArrayList<Double> percentArray = new ArrayList();
		
		for(int i=0;i<ballArray.size();i++)
			percentArray.add( (double)(hitArray.get(i)) / (double)(ballArray.get(i)));
		while(true) {
			System.out.printf("1:正拋擊球率? 2:正拋擊遠 -1:結束", null);
			int options = input.nextInt();
			 if(options==-1)
				 break;
			if(options==1) {
				LineChart_AWT chart = new LineChart_AWT("Hit", "Hit %" ,dateArray,percentArray,"Percent of Hit");
			    chart.pack( );          
			    RefineryUtilities.centerFrameOnScreen( chart );          
			    chart.setVisible( true );
			    System.out.printf("-1:結束圖表", null);
				options = input.nextInt();
			    if(options==-1)
			    	chart.setVisible( false );
			}
			else if(options==2) {
				LineChart_AWT chart = new LineChart_AWT("Distance of Hit", "Hit(m) " ,dateArray,distanceArray,"Distance of Hit");
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
		ArrayList<Double> percentArray = new ArrayList();
		
		for(int i=0;i<ballArray.size();i++) {
			percentArray.add( (double)(hitArray.get(i)) / (double)(ballArray.get(i)));
		}
		if(options==1) {
			/*
			SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LineChart("Hit", "Hit %" ,dateArray,percentArray,"Percent of Hit").setVisible(true);
			}
			});
			*/
			
			LineChart t = new LineChart("Hit", "Hit %" ,dateArray,percentArray,"Percent of Hit");
			panel = t.getJPanel();
		}
		else if(options==2) {
			LineChart t = new LineChart("Distance of Hit", "Hit(m) " ,dateArray,distanceArray,"Distance of Hit");
			panel = t.getJPanel();
		}
		return panel;
		
	}
}

