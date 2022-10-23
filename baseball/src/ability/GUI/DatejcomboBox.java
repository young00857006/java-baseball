package ability.GUI;
import java.sql.SQLException;
import java.util.ArrayList;

import ability.createDB.*;
public class DatejcomboBox extends ConnectDB{
	private int year,day,month;
	private String tableName;
	private ArrayList<Integer> yearList = new ArrayList();
	private ArrayList<Integer> monthList = new ArrayList();
	private ArrayList<Integer> dayList = new ArrayList();
	public DatejcomboBox(String place,String name) {
		super(place);
		this.tableName = place;
		try {
			stat = con.createStatement();
		    rs = stat.executeQuery("SELECT year,month,day FROM " +tableName+"."+name+" order by year DESC,month DESC, day DESC");
	    	while(rs.next()) {
	    		yearList.add(rs.getInt("year"));
	    		monthList.add(rs.getInt("month"));
	    		dayList.add(rs.getInt("day"));
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
	public ArrayList<Integer> getYearList() {
		return yearList;
	}
	public ArrayList<Integer> getMonthList() {
		return monthList;
	}
	public ArrayList<Integer> getDayList() {
		return dayList;
	}
}
