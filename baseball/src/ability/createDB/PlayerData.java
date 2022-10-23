package ability.createDB;
import java.util.*;

import javax.swing.JPanel;
public class PlayerData {
	Scanner input = new Scanner(System.in);
	private String name;
	public PlayerData(String name) {
		this.name =name;
	}
	public void collectInputData(int option ,String year,String month,String day,String target,Double data) {
			switch(option) {
				case 1:
					inputPlayerData(new InField(name),year,month, day, target, data);
					break;
				case 2:
					inputPlayerData(new OutField(name),year,month, day, target, data);
					break;
				case 3:
					inputPlayerData(new Run(name),year,month, day, target, data);
					break;
				case 4:
					inputPlayerData(new SideHit(name),year,month, day, target, data);
					break;
				case 5:
					inputPlayerData(new Hit(name),year,month, day, target, data);
					break;
			}
		
	}
	
	public void inputPlayerData(Data data,String year,String month,String day,String target,Double Data) {
		data.inputData(year,month, day, target, Data);
	}
	/*
	public void collectData(int option) {
		
			switch(option) {
				case 1:
					collectPlayerData(new InField(name));
					break;
				case 2:
					collectPlayerData(new OutField(name));
					break;
				case 3:
					collectPlayerData(new Run(name));
					break;
				case 4:
					collectPlayerData(new SideHit(name));
					break;
				case 5:
					collectPlayerData(new Hit(name));
					break;
			}
		
	}
	*/
	public JPanel collectPlayerData(Data data,int option) {
		return data.showLineChartData(option);
	}
	
	public double collectsingleData(int option,String target,String year,String month,String day) {
		double result=0;
			switch(option) {
				case 1:
					result = CollectSingleData(new InField(name),target,year,month,day);
					break;
				case 2:
					result = CollectSingleData(new OutField(name),target,year,month,day);
					break;
				case 3:
					result = CollectSingleData(new Run(name),target,year,month,day);
					break;
				case 4:
					result = CollectSingleData(new SideHit(name),target,year,month,day);
					break;
				case 5:
					result = CollectSingleData(new Hit(name),target,year,month,day);
					break;
			}
		return result;
		
	}
	public double CollectSingleData(Data data,String target,String year,String month,String day) {
		 return data.getData(target,year, month, day);
	}
}
