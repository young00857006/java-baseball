package ability.createDB;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;
public class InField extends ConnectDB implements Data{
	private String name; 
	private int year,month,day,ball,ground,correct;
	private double process;
	Scanner input = new Scanner(System.in);
	private ArrayList<Integer> ballArray = new ArrayList();
	private ArrayList<Integer> groundArray = new ArrayList();
	private ArrayList<Integer> correctArray = new ArrayList();
	private ArrayList<String> dateArray = new ArrayList();
	private ArrayList<Double> processArray = new ArrayList();
	public InField(String username) {
		super("infield");
		super.setInsertdbSQL("INSERT INTO " + username + " VALUES(?,?,?,?,?,?,?)");
		this.name = username;
	}
	public void setArray() {
		try 
		{ 
			stat = con.createStatement(); 
			rs = stat.executeQuery("select * from " + name +" order by year ASC,month ASC, day ASC"); 
			while(rs.next()) 
			{ 
				//System.out.println(rs.getString("ball")+"\t\t"+rs.getString("ground")); 
				ballArray.add(rs.getInt("ball"));
				groundArray.add(rs.getInt("ground"));
				correctArray.add(rs.getInt("correct"));
				dateArray.add(rs.getInt("year")+"/"+rs.getInt("month")+"/"+rs.getInt("day"));
				processArray.add(rs.getDouble("process"));
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
			System.out.printf("date? ball? ground? process? correct?%n");
			month = input.nextInt();
			ball = input.nextInt();
			ground = input.nextInt();
			process = input.nextDouble();
			correct = input.nextInt();
			try 
			{ 
				pst = con.prepareStatement(super.getInsertdbSQL()); 
				pst.setInt(1, month);
				pst.setInt(2, day);
				pst.setInt(3, ball); 
				pst.setInt(4, ground);
				pst.setDouble(5, process);
				pst.setInt(6, correct);
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
		      stat.execute("UPDATE " + name + " SET " + target + " = '" + data+"' WHERE year = '"+year+"' AND  month = '"+month+"' AND day='"+day+"'");
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
		ArrayList<Double> percentGBArray = new ArrayList();
		ArrayList<Double> percentTBArray = new ArrayList();
	
		for(int i=0;i<ballArray.size();i++) {
			percentGBArray.add( (double)(groundArray.get(i)) / (double)(ballArray.get(i)));
			percentTBArray.add( (double)(correctArray.get(i)) / (double)(ballArray.get(i)));
			
		}
		while(true) {
			System.out.printf("1:滾地接球率? 2:內野傳球準確率 3:內野起身平均傳球時間 -1:結束", null);
			int options = input.nextInt();
			 if(options==-1)
				 break;
			if(options==1) {
				LineChart_AWT chart = new LineChart_AWT("Catch Ground Ball", "Catch Ground Ball %" ,dateArray,percentGBArray,"Percent of Catch Ground Ball");
			    chart.pack( );          
			    RefineryUtilities.centerFrameOnScreen( chart );          
			    chart.setVisible( true );
			    System.out.printf("-1:結束圖表", null);
				options = input.nextInt();
			    if(options==-1)
			    	chart.setVisible( false );
			}
			else if(options==2) {
				LineChart_AWT chart = new LineChart_AWT("Throw Ball", "Throw Ball(s)" ,dateArray,percentTBArray,"Seconds of Throw Ball");
			    chart.pack( );          
			    RefineryUtilities.centerFrameOnScreen( chart );          
			    chart.setVisible( true );
			    System.out.printf("-1:結束圖表", null);
				options = input.nextInt();
			    if(options==-1)
			    	chart.setVisible( false );
			}
			else if(options==3) {
				LineChart_AWT chart = new LineChart_AWT("Process Ball", "Process Ball(s)" ,dateArray,processArray,"Seconds of Process Ball");
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
	public JPanel showLineChartData(int options) {
		JPanel panel = null;
		setArray();
		ArrayList<Double> percentGBArray = new ArrayList();
		ArrayList<Double> percentTBArray = new ArrayList();
	
		for(int i=0;i<ballArray.size();i++) {
			percentGBArray.add( (double)(groundArray.get(i)) / (double)(ballArray.get(i)));
			percentTBArray.add( (double)(correctArray.get(i)) / (double)(ballArray.get(i)));
			
		}
		
			if(options==1) {
			    LineChart t = new LineChart("Catch Ground Ball", "Catch Ground Ball %" ,dateArray,percentGBArray,"Percent of Catch Ground Ball");
				panel = t.getJPanel();
			}
			else if(options==2) {
				LineChart t = new LineChart("Throw Ball", "Throw Ball(s)" ,dateArray,percentTBArray,"Seconds of Throw Ball");
				panel = t.getJPanel();
			}
			else if(options==3) {
				LineChart t = new LineChart("Process Ball", "Process Ball(s)" ,dateArray,processArray,"Seconds of Process Ball");
				panel = t.getJPanel();
			}
			return panel;
	}
}
