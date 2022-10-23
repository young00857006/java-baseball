package ability.createDB;

import java.util.ArrayList;

import javax.swing.JPanel;

public  interface  Data {
	public abstract void inputData(String year,String month,String day,String target,Double data);
	public abstract JPanel showLineChartData(int option);
	public  double getData(String target,String year,String month,String day);

}
