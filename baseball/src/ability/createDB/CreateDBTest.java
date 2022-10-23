package ability.createDB;
import java.util.*;
//程式進入點
public class CreateDBTest {
	
	public static void main(String [] args) {
		
		String userName;
		ConnectDB infieldDB,outfieldDB,runDB,sidehitDB,hitDB;
		Scanner input = new Scanner(System.in);
		
		//模擬register
		System.out.printf("Please input user's name?");
		userName = input.nextLine();
		
		//創建屬於這位使用者的table 模擬register;
		infieldDB = new ConnectDB("infield","CREATE TABLE " + userName + " ( year INTEGER,month INTEGER,day INTEGER, ball INTEGER, ground INTEGER, process DOUBLE, correct INTEGER)");
		outfieldDB = new ConnectDB("outfield","CREATE TABLE " + userName + " (year INTEGER,month INTEGER,day INTEGER, ball INTEGER, fly INTEGER, process DOUBLE, correct INTEGER)");
		runDB = new ConnectDB("run","CREATE TABLE " + userName + " (year INTEGER,month INTEGER,day INTEGER, 0to1 DOUBLE, 0to2 DOUBLE, 1to3 DOUBLE, 2to0 DOUBLE, 100m DOUBLE)");
		sidehitDB = new ConnectDB("sidehit","CREATE TABLE " + userName + " (year INTEGER,month INTEGER,day INTEGER, ball INTEGER, hit INTEGER, distance DOUBLE )");
		hitDB = new ConnectDB("hit","CREATE TABLE " + userName + " (year INTEGER,month INTEGER,day INTEGER, ball INTEGER, hit INTEGER, distance DOUBLE )");
	
		PlayerData playerdata = new PlayerData(userName);
		//playerdata.collectInputData(,month, day, target, data);
		//playerdata.collectData();
		
	}
}
