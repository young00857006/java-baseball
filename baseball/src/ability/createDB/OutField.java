package ability.createDB;

import java.sql.SQLException;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;
public class OutField extends ConnectDB implements Data{
	Scanner input = new Scanner(System.in);
	private String name;
	private int year,month,day,ball,fly,correct;
	private double process;
	private ArrayList<Integer> ballArray = new ArrayList();
	private ArrayList<Integer> flyArray = new ArrayList();
	private ArrayList<Integer> correctArray = new ArrayList();
	private ArrayList<String> dateArray = new ArrayList();
	private ArrayList<Double> processArray = new ArrayList();
	public OutField(String username) {
		super("outfield");
		super.setInsertdbSQL("INSERT INTO " + username + " VALUES(?,?,?,?,?,?,?)");
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
				flyArray.add(rs.getInt("fly"));
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
			System.out.printf("date?");
			month = input.nextInt();
			System.out.printf("ball?");
			ball = input.nextInt();
			System.out.printf("fly?");
			fly = input.nextInt();
			System.out.printf("process?");
			process = input.nextDouble();
			System.out.printf("correct?");
			correct = input.nextInt();
			try 
			{ 
				pst = con.prepareStatement(super.getInsertdbSQL()); 
				pst.setInt(1, month);
				pst.setInt(2, day);
				pst.setInt(3, ball); 
				pst.setInt(4, fly);
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
		ArrayList<Double> percentFBArray = new ArrayList();
		ArrayList<Double> percentTBArray = new ArrayList();
		
		for(int i=0;i<ballArray.size();i++) {
			percentFBArray.add( (double)(flyArray.get(i)) / (double)(ballArray.get(i)));
			percentTBArray.add( (double)(correctArray.get(i)) / (double)(ballArray.get(i)));
		}
		while(true) {
			System.out.printf("1:飛球接球率? 2:外野傳球準確率 3:外野處理球時間 -1:結束", null);
			int options = input.nextInt();
			 if(options==-1)
				 break;
			if(options==1) {
				LineChart_AWT chart = new LineChart_AWT("Catch Ground Ball", "Catch Ground Ball %" ,dateArray,percentFBArray,"Percent of Catch Ground Ball");
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
	@Override
	public JPanel showLineChartData(int options) {
		JPanel panel = null;
		setArray();
		ArrayList<Double> percentFBArray = new ArrayList();
		ArrayList<Double> percentTBArray = new ArrayList();
		
		for(int i=0;i<ballArray.size();i++) {
			percentFBArray.add( (double)(flyArray.get(i)) / (double)(ballArray.get(i)));
			percentTBArray.add( (double)(correctArray.get(i)) / (double)(ballArray.get(i)));
		}
		
			
			if(options==1) {
				/*
				SwingUtilities.invokeLater(new Runnable() {
		             @Override
		             public void run() {
		                 new LineChart("Catch Ground Ball", "Catch Ground Ball %" ,dateArray,percentFBArray,"Percent of Catch Ground Ball").setVisible(true);
		             }
		         });
		         */
				LineChart t = new LineChart("Catch Ground Ball", "Catch Ground Ball %" ,dateArray,percentFBArray,"Percent of Catch Ground Ball");
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
