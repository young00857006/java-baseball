package ability.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import ability.createDB.Hit;
import ability.createDB.InField;
import ability.createDB.LineChart;
import ability.createDB.OutField;
import ability.createDB.PlayerData;
import ability.createDB.Run;
import ability.createDB.SideHit;

public class PlayerDataGUI {
	
	private  JFrame frame;
	private  JPanel ItemjComboBoxpanel,Buttonpanel,funtionpanel,introducepanel,panel;
	protected JPanel Dopanel,textpanel;
	private  JComboBox ItemjComboBox,DatejComboBox;
	private  JButton update,insert,search;
	private  JTextArea text;
	private  JLabel name;
	private PlayerData playerdata;
	
	private JLabel titleLabel,hitLabel,distanceLabel,ballLabel,flyLabel,groundLabel,correctLabel,processLabel,Label0to1,Label0to2,Label1to3
					,Label2to0,Label100m;;
	private JTextField ballField,hitField, distanceField,flyField,groundField,processField,correctField,Field0to1,Field0to2,Field1to3
						,Field2to0,Field100m;
	private JButton button;
	private ArrayList<Integer> yearList = new ArrayList();
	private ArrayList<Integer> monthList = new ArrayList();
	private ArrayList<Integer> dayList = new ArrayList();
	private String username;
	
	public PlayerDataGUI() {}
	public PlayerDataGUI(String username) {
		playerdata = new PlayerData(username);
		this.username = username;
		frame = new JFrame("球員能力");
		funtionpanel = new JPanel(new BorderLayout(3,1));
		ItemjComboBoxpanel = new JPanel();
		Buttonpanel = new JPanel(new GridLayout(1,2));
		textpanel = new JPanel();
		Dopanel = new JPanel();
		introducepanel = new JPanel();
		panel = new JPanel(new GridLayout(1,2));
		name = new JLabel(username);
		name.setFont(new Font("",Font.BOLD,30));
		
		ItemjComboBox = new JComboBox();
		ItemjComboBox.addItem("請選擇");
		ItemjComboBox.addItem("內野");
		ItemjComboBox.addItem("外野");
		ItemjComboBox.addItem("跑壘");
		ItemjComboBox.addItem("側拋");
		ItemjComboBox.addItem("正拋");
		
		ItemjComboBox.addActionListener(new ActionListener() {
			
			//String itemSel = (String) ItemjComboBox.getSelectedItem();//get the selected item
			//String place;
		    	public void actionPerformed(ActionEvent e) { 
		    		String itemSel = (String) ItemjComboBox.getSelectedItem();//get the selected item
					if(itemSel=="請選擇")
						JOptionPane.showMessageDialog(frame,"請先選擇查看內容");
					else {
						getDate();
					}
				} 
			
	    	});
		
		
		DatejComboBox = new JComboBox();
		
		
		
		text = new JTextArea();
		text.setFont(new Font("",Font.BOLD,15));
		update = new JButton("新增");
		//insert = new JButton("新增");
		search = new JButton("查詢");
		
		introducepanel.add(name,BorderLayout.CENTER);
		introducepanel.add(DatejComboBox,BorderLayout.CENTER);
		introducepanel.setBorder(BorderFactory.createTitledBorder("個人資料"));
		
		//updateinsert.addActionListener(new ButtonClickListener()); 
		update.addActionListener(new ButtonClickListener());
		//insert.addActionListener(new ButtonClickListener());
		search.addActionListener(new ButtonClickListener());
		
		update.setActionCommand("新增");
		//insert.setActionCommand("新增");
		search.setActionCommand("查詢");
		
		Buttonpanel.add(update);
		//Buttonpanel.add(insert);
		Buttonpanel.add(search);
		
		funtionpanel.add(ItemjComboBox,BorderLayout.NORTH);
		funtionpanel.add(text,BorderLayout.CENTER);
		funtionpanel.add(Buttonpanel,BorderLayout.SOUTH);
		funtionpanel.setBorder(BorderFactory.createTitledBorder("功能"));
		
		
		Dopanel.setBorder(BorderFactory.createTitledBorder(""));
		
		
		frame.add(introducepanel,BorderLayout.NORTH);
		frame.add(funtionpanel,BorderLayout.WEST);
		frame.add(Dopanel,BorderLayout.CENTER);
		/**/
		
		
		
	}
	
	
	 
	private  class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();  
			String itemSel = (String) ItemjComboBox.getSelectedItem();//get the selected item
			if(itemSel=="請選擇")
				JOptionPane.showMessageDialog(frame,"請先選擇查看內容");
			else {
	    	String monthdaySel = (String) DatejComboBox.getSelectedItem();//get the selected item
	    	String[] monthday = monthdaySel.split("/",3);
	    	String year = monthday[0];
	    	String month = monthday[1];
	    	String day = monthday[2];
	    	Dopanel.removeAll();
			
	    	switch(itemSel) {
	    		case "請選擇":
	    			JOptionPane.showMessageDialog(frame,"請先選擇查看內容");
	    			break;
	    		case "正拋":
	    	  		if( command.equals( "新增" ))  {
	    	  			text.setText("");
	    	  			frame.revalidate();
	    	  			button = new JButton("更新");
	    	  			button.setActionCommand("更新");
	    	  			button.addActionListener(new ActionListener() { 
	    	  			  public void actionPerformed(ActionEvent e) { 
	    	  				if(e.getActionCommand() == "更新");
		    	  				try {
		    	  					playerdata.collectInputData(5,year, month, day, "ball",Double.valueOf(ballField.getText()));
		    	  					playerdata.collectInputData(5,year, month, day, "hit",Double.valueOf(hitField.getText()));
		    	  					playerdata.collectInputData(5, year, month, day, "distance",Double.valueOf(distanceField.getText()));
		    	  					
		    	  					text.setText(year+"/"+month+"/"+day+'\n'+"正拋打擊擊球率 = " + (int)playerdata.collectsingleData(5, "hit",year, month, day) + " / " + (int)(playerdata.collectsingleData(5, "ball",year,month, day))
		        	  						+'\n'+'\n'+"擊球距離 = "+playerdata.collectsingleData(5, "distance",year, month, day)
		        	  					);
		    	  				}
		    	  				catch(Exception ee) {
	    	  						JOptionPane.showMessageDialog(frame,"Invalid Input!!");
	    	  					    ee.printStackTrace();
	    	  					}
	    	  			  } 
	    	  			} );
	    	  			
	    	  			ballField = new JTextField(String.valueOf((int)playerdata.collectsingleData(5, "ball",year,month, day)));
		  		     	hitField = new JTextField(String.valueOf((int)playerdata.collectsingleData(5, "hit", year,month, day)));
		  		     	distanceField = new JTextField(String.valueOf(playerdata.collectsingleData(5, "distance",year, month, day)));
		  		     	
	    	  			JPanel panel1,panel2,panel3;
		  	        	titleLabel = new JLabel("正拋打擊"); 
		  		     	hitLabel = new JLabel("正拋擊中 = "); 
		  		     	ballLabel = new JLabel("多少球 = ");
		  		     	distanceLabel = new JLabel("擊球距離 = ");

		  		     	
		  		     	
		  		     	panel1 = new JPanel(new GridLayout(1,2));
		  		     	panel2 = new JPanel(new GridLayout(1,2));
		  		     	panel3 = new JPanel(new GridLayout(1,2));
		  		     	
		  		     	panel1.add(ballLabel);
		  		     	panel1.add(ballField);
		  		     	panel2.add(hitLabel);
		  		     	panel2.add(hitField);
		  		     	panel3.add(distanceLabel);
		  		     	panel3.add(distanceField);
		  		     	Dopanel.removeAll();
		  		     	Dopanel.setLayout(new GridLayout(5, 1));
		  	     		Dopanel.add(titleLabel);
		  	     		Dopanel.add(panel1);
		  	     		Dopanel.add(panel2);
		  	     		Dopanel.add(panel3);
		  	     		Dopanel.add(button);
		  	        	frame.revalidate();
	    	  		} 
	    	  		else if(command.equals( "查詢" )){
	    	  			text.setText(year+"/"+month+"/"+day+'\n'+"正拋打擊擊球率 = " + (int)playerdata.collectsingleData(5, "hit",year, month, day) + " / " + (int)(playerdata.collectsingleData(5, "ball",year, month, day))
	    	  						+'\n'+'\n'+"擊球距離 = "+playerdata.collectsingleData(5, "distance",year, month, day)
	    	  					);
	    	  			ballField = new JTextField(String.valueOf((int)playerdata.collectsingleData(5, "ball",year, month, day)));
		  		     	hitField = new JTextField(String.valueOf((int)playerdata.collectsingleData(5, "hit",year, month, day)));
		  		     	distanceField = new JTextField(String.valueOf(playerdata.collectsingleData(5, "distance",year, month, day)));
		  		     	Dopanel.removeAll();
		  		     	Dopanel.setLayout(new GridLayout(2, 1));
		  		     	Dopanel.add(playerdata.collectPlayerData(new Hit(username),1));
		  		     	Dopanel.add(playerdata.collectPlayerData(new Hit(username),2));
	    	  			frame.revalidate();
	    	  		}  
	    	  	  break;
	    		case "側拋":
	    	  		if( command.equals( "新增" ))  {
	    	  			text.setText("");
	    	  			frame.revalidate();
	    	  			button = new JButton("更新");
	    	  			button.setActionCommand("更新");
	    	  			button.addActionListener(new ActionListener() { 
	    	  			  public void actionPerformed(ActionEvent e) { 
	    	  				if(e.getActionCommand() == "更新");
		    	  				try {
		    	  					playerdata.collectInputData(4,year, month, day, "ball",Double.valueOf(ballField.getText()));
		    	  					playerdata.collectInputData(4,year, month, day, "hit",Double.valueOf(hitField.getText()));
		    	  					playerdata.collectInputData(4,year, month, day, "distance",Double.valueOf(distanceField.getText()));
		    	  					text.setText(year+"/"+month+"/"+day+'\n'+"側拋拋打擊擊球率 = " + (int)playerdata.collectsingleData(4, "hit",year, month, day) + " / " + (int)playerdata.collectsingleData(4, "ball",year, month, day)
	  								+'\n'+'\n'+"擊球距離 = "+playerdata.collectsingleData(4, "distance",year, month, day));
		    	  				}
		    	  				catch(Exception ee) {
	    	  						JOptionPane.showMessageDialog(frame,"Invalid Input!!");
	    	  					    ee.printStackTrace();
	    	  					}
	    	  			  } 
	    	  			} );
	    	  			
	    	  			ballField = new JTextField(String.valueOf((int)playerdata.collectsingleData(4, "ball",year, month, day)));
		  		     	hitField = new JTextField(String.valueOf((int)playerdata.collectsingleData(4, "hit",year, month, day)));
		  		     	distanceField = new JTextField(String.valueOf(playerdata.collectsingleData(4, "distance",year, month, day)));
		  		     	
	    	  			
	    	  			JPanel panel1,panel2,panel3;
		  	        	titleLabel = new JLabel(year+"/"+month+"/"+day+"測拋打擊"); 
		  		     	hitLabel = new JLabel("側拋擊中 = "); 
		  		     	ballLabel = new JLabel("多少球 = ");
		  		     	distanceLabel = new JLabel("擊球距離 = ");
		  		     	
			  		  	panel1 = new JPanel(new GridLayout(1,2));
		  		     	panel2 = new JPanel(new GridLayout(1,2));
		  		     	panel3 = new JPanel(new GridLayout(1,2));
			  		     	
		  		     	panel1.add(ballLabel);
		  		     	panel1.add(ballField);
		  		     	panel2.add(hitLabel);
		  		     	panel2.add(hitField);
		  		     	panel3.add(distanceLabel);
		  		     	panel3.add(distanceField);
		  		     	Dopanel.removeAll();
		  		     	Dopanel.setLayout(new GridLayout(5, 1));
		  	     		Dopanel.add(titleLabel,BorderLayout.NORTH);
		  	     		Dopanel.add(panel1);
		  	     		Dopanel.add(panel2);
		  	     		Dopanel.add(panel3);
		  	     		Dopanel.add(button,BorderLayout.SOUTH);
		  	        	frame.revalidate();
	    	  			
	    	  		}  
	    	  		else if(command.equals( "查詢" )){
	    	  			text.setText(year+"/"+month+"/"+day+'\n'+"側拋打擊擊球率 = " + (int)playerdata.collectsingleData(4, "hit",year, month, day) + " / " + (int)playerdata.collectsingleData(4, "ball",year, month, day)
  								+'\n'+'\n'+"擊球距離 = "+playerdata.collectsingleData(4, "distance",year, month, day));
	    	  			Dopanel.removeAll();
	    	  			Dopanel.setLayout(new GridLayout(2, 1));
	    	  			Dopanel.add(playerdata.collectPlayerData(new SideHit(username),1));
	    	  			Dopanel.add(playerdata.collectPlayerData(new SideHit(username),2));
	    	  			frame.revalidate();
	    	  			
	    	  		}  
	    	  	  break;
	    	  	   
	    		case "外野":
	    	  		if( command.equals( "新增" ))  {
	    	  			text.setText("");
	    	  			frame.revalidate();
	    	  			button = new JButton("更新");
	    	  			button.setActionCommand("更新");
	    	  			button.addActionListener(new ActionListener() { 
	    	  			  public void actionPerformed(ActionEvent e) { 
	    	  				if(e.getActionCommand() == "更新");
	    	  					try {
		    	  					playerdata.collectInputData(2,year, month, day, "ball",Double.valueOf(ballField.getText()));
		    	  					playerdata.collectInputData(2,year, month, day, "fly",Double.valueOf(flyField.getText()));
		    	  					playerdata.collectInputData(2,year, month, day, "process",Double.valueOf(processField.getText()));
		    	  					playerdata.collectInputData(2,year, month, day, "correct",Double.valueOf(correctField.getText()));
		    	  					text.setText(year+"/"+month+"/"+day+'\n'+"外野守備接球率 = " + (int)playerdata.collectsingleData(2, "fly",year, month, day) + " / " + (int)playerdata.collectsingleData(2, "ball",year, month, day)
										+'\n'+'\n'+"外野守備傳球準確率 = " + (int)playerdata.collectsingleData(2, "correct",year, month, day)+ " / " + (int)playerdata.collectsingleData(2, "ball",year, month, day)
										+'\n'+'\n'+"外野守備處理球時間 = " + playerdata.collectsingleData(2, "process",year, month, day)
	    	  						);
	    	  					}
	    	  					catch(Exception ee) {
	    	  						JOptionPane.showMessageDialog(frame,"Invalid Input!!");
	    	  					    ee.printStackTrace();
	    	  					}
	    	  			  } 
	    	  			} );
	    	  			
	    	  			ballField = new JTextField(String.valueOf((int)playerdata.collectsingleData(2, "ball",year, month, day)));
		  		     	flyField = new JTextField(String.valueOf((int)playerdata.collectsingleData(2, "fly",year, month, day)));
		  		     	processField = new JTextField(String.valueOf(playerdata.collectsingleData(2, "process",year, month, day)));
		  		     	correctField = new JTextField(String.valueOf((int)playerdata.collectsingleData(2, "correct",year, month, day)));
		  		     	
	    	  			
	    	  			JPanel panel1,panel2,panel3,panel4;
		  	        	titleLabel = new JLabel(year+"/"+month+"/"+day+"外野"); 
		  		     	ballLabel = new JLabel("多少球 = "); 
		  		     	flyLabel = new JLabel("接到幾顆 = ");
		  		     	correctLabel = new JLabel("傳準幾顆 = ");
		  		     	processLabel = new JLabel("處理球時間 = ");
		  		     	
			  		  	panel1 = new JPanel(new GridLayout(1,2));
		  		     	panel2 = new JPanel(new GridLayout(1,2));
		  		     	panel3 = new JPanel(new GridLayout(1,2));
		  		     	panel4 = new JPanel(new GridLayout(1,2));
			  		     	
		  		     	panel1.add(ballLabel);
		  		     	panel1.add(ballField);
		  		     	panel2.add(flyLabel);
		  		     	panel2.add(flyField);
		  		     	panel3.add(correctLabel);
		  		     	panel3.add(correctField);
		  		     	panel4.add(processLabel);
		  		     	panel4.add(processField);
		  		     	Dopanel.removeAll();
		  		     	Dopanel.setLayout(new GridLayout(6, 1));
		  	     		Dopanel.add(titleLabel);
		  	     		Dopanel.add(panel1);
		  	     		Dopanel.add(panel2);
		  	     		Dopanel.add(panel3);
		  	     		Dopanel.add(panel4);
		  	     		Dopanel.add(button);
		  	        	frame.revalidate();
	    	  			
	    	  		} 
	    	  		
	    	  		else if(command.equals( "查詢" )){
	    	  			text.setText(year+"/"+month+"/"+day+'\n'+"外野守備接球率 = " + (int)playerdata.collectsingleData(2, "fly",year, month, day) + " / " + (int)playerdata.collectsingleData(2, "ball",year, month, day)
  									+'\n'+'\n'+"外野守備傳球準確率 = " + (int)playerdata.collectsingleData(2, "correct",year, month, day)+ " / " + (int)playerdata.collectsingleData(2, "ball",year, month, day)
  									+'\n'+'\n'+"外野守備處理球時間 = " + playerdata.collectsingleData(2, "process",year, month, day)
	    	  						);
	    	  			Dopanel.removeAll();
	    	  			Dopanel.setLayout(new GridLayout(2, 2));
	    	  			Dopanel.add(playerdata.collectPlayerData(new OutField(username),1));
	    	  			Dopanel.add(playerdata.collectPlayerData(new OutField(username),2));
	    	  			Dopanel.add(playerdata.collectPlayerData(new OutField(username),3));
	    	  			Dopanel.add(new JPanel());
	    	  			frame.revalidate();
	    	  		}  
	    	  	  break;
	    	  	  
	    		case "內野":
	    	  		if( command.equals( "新增" ))  {
	    	  			text.setText("");
	    	  			frame.revalidate();
	    	  			button = new JButton("更新");
	    	  			button.setActionCommand("更新");
	    	  			button.addActionListener(new ActionListener() { 
	    	  			  public void actionPerformed(ActionEvent e) { 
	    	  				if(e.getActionCommand() == "更新");
	    	  					try {
	    	  						playerdata.collectInputData(1,year, month, day, "ball",Double.valueOf(ballField.getText()));
	    	  						playerdata.collectInputData(1,year, month, day, "ground",Double.valueOf(groundField.getText()));
	    	  						playerdata.collectInputData(1,year, month, day, "process",Double.valueOf(processField.getText()));
	    	  						playerdata.collectInputData(1, year,month, day, "correct",Double.valueOf(correctField.getText()));
	    	  						text.setText(year+"/"+month+"/"+day+'\n'+"內野守備接球率 = " + (int)playerdata.collectsingleData(1, "ground",year, month, day) + " / " + (int)playerdata.collectsingleData(1, "ball",year, month, day)
	    	  									+'\n'+'\n'+"內野守備傳球準確率 = " + (int)playerdata.collectsingleData(1, "correct",year, month, day)+ " / " + (int)playerdata.collectsingleData(1, "ball",year, month, day)
	    	  									+'\n'+'\n'+"內野守備處理球時間 = " + playerdata.collectsingleData(1, "process",year, month, day)
	    	  									);
	    	  					}
	    	  					catch(Exception ee) {
	    	  						JOptionPane.showMessageDialog(frame,"Invalid Input!!");
	    	  					    ee.printStackTrace();
	    	  					}
	    	  					
	    	  			  } 
	    	  			} );
	    	  			
	    	  			
		    	  		ballField = new JTextField(String.valueOf((int)playerdata.collectsingleData(1, "ball",year, month, day)));
			  		    groundField = new JTextField(String.valueOf((int)playerdata.collectsingleData(1, "ground",year, month, day)));
			  		    processField = new JTextField(String.valueOf(playerdata.collectsingleData(1, "process",year, month, day)));
			  		    correctField = new JTextField(String.valueOf((int)playerdata.collectsingleData(1, "correct",year, month, day)));
		    	  		
	    	  			
	    	  			JPanel panel1,panel2,panel3,panel4;
		  	        	titleLabel = new JLabel(year+"/"+month+"/"+day+"內野"); 
		  		     	ballLabel = new JLabel("多少球 = "); 
		  		     	groundLabel = new JLabel("接到幾顆 = ");
		  		     	correctLabel = new JLabel("傳準幾顆 = ");
		  		     	processLabel = new JLabel("處理球時間 = ");
		  		     	
			  		  	panel1 = new JPanel(new GridLayout(1,2));
		  		     	panel2 = new JPanel(new GridLayout(1,2));
		  		     	panel3 = new JPanel(new GridLayout(1,2));
		  		     	panel4 = new JPanel(new GridLayout(1,2));
			  		     	
		  		     	panel1.add(ballLabel);
		  		     	panel1.add(ballField);
		  		     	panel2.add(groundLabel);
		  		     	panel2.add(groundField);
		  		     	panel3.add(correctLabel);
		  		     	panel3.add(correctField);
		  		     	panel4.add(processLabel);
		  		     	panel4.add(processField);
		  		     	Dopanel.removeAll();
		  		     	Dopanel.setLayout(new GridLayout(6, 1));
		  	     		Dopanel.add(titleLabel);
		  	     		Dopanel.add(panel1);
		  	     		Dopanel.add(panel2);
		  	     		Dopanel.add(panel3);
		  	     		Dopanel.add(panel4);
		  	     		Dopanel.add(button);
		  	        	frame.revalidate();
	    	  		} 
	    	  		
	    	  		else if(command.equals( "查詢" )){
	    	  			text.setText(year+"/"+month+"/"+day+'\n'+"內野守備接球率 = " + (int)playerdata.collectsingleData(1, "ground",year, month, day) + " / " + (int)playerdata.collectsingleData(1, "ball",year, month, day)
							+'\n'+'\n'+"內野守備傳球準確率 = " + (int)playerdata.collectsingleData(1, "correct",year, month, day)+ " / " + (int)playerdata.collectsingleData(1, "ball",year, month, day)
							+'\n'+'\n'+"內野守備處理球時間 = " + playerdata.collectsingleData(1, "process",year, month, day)
  						);
	    	  			Dopanel.removeAll();
	    	  			Dopanel.setLayout(new GridLayout(2, 2));
	    	  			Dopanel.add(playerdata.collectPlayerData(new InField(username),1));
	    	  			Dopanel.add(playerdata.collectPlayerData(new InField(username),2));
	    	  			Dopanel.add(playerdata.collectPlayerData(new InField(username),3));
	    	  			Dopanel.add(new JPanel());
	    	  			frame.revalidate();
	    	  		}  
	    	  	  break;
	    		case "跑壘":
	    	  		if( command.equals( "新增" ))  {
	    	  			text.setText("");
	    	  			frame.revalidate();
	    	  			button = new JButton("更新");
	    	  			button.setActionCommand("更新");
	    	  			button.addActionListener(new ActionListener() { 
	    	  			  public void actionPerformed(ActionEvent e) { 
	    	  				if(e.getActionCommand() == "更新");
	    	  					try {
		    	  					playerdata.collectInputData(3,year, month, day, "0to1",Double.valueOf(Field0to1.getText()));
		    	  					playerdata.collectInputData(3,year, month, day, "0to2",Double.valueOf(Field0to2.getText()));
		    	  					playerdata.collectInputData(3,year, month, day, "1to3",Double.valueOf(Field1to3.getText()));
		    	  					playerdata.collectInputData(3,year, month, day, "2to0",Double.valueOf(Field2to0.getText()));
		    	  					playerdata.collectInputData(3,year, month, day, "100m",Double.valueOf(Field100m.getText()));
		    	  					text.setText(year+"/"+month+"/"+day+'\n'+"本壘到一壘 = " + playerdata.collectsingleData(3, "0to1",year, month, day) 
		    						+'\n'+'\n'+"本壘到二壘 = " + playerdata.collectsingleData(3, "0to2",year, month, day)
		    						+'\n'+'\n'+"一壘到三壘 = " + playerdata.collectsingleData(3, "1to3",year, month, day)
		    						+'\n'+'\n'+"二壘到本壘 = " + playerdata.collectsingleData(3, "2to0",year, month, day)
		    						+'\n'+'\n'+"100公尺 時間 = " + playerdata.collectsingleData(3, "100m",year, month, day)
		    						);
	    	  					}
	    	  					catch(Exception ee) {
	    	  						JOptionPane.showMessageDialog(frame,"Invalid Input!!");
	    	  					    ee.printStackTrace();
	    	  					}
	    	  			  } 
	    	  			} );
	    	  			
	    	  			Field0to1 = new JTextField(String.valueOf(playerdata.collectsingleData(3, "0to1",year, month, day)));
	    	  			Field0to2 = new JTextField(String.valueOf(playerdata.collectsingleData(3, "0to2",year, month, day)));
	    	  			Field1to3 = new JTextField(String.valueOf(playerdata.collectsingleData(3, "1to3",year, month, day)));
	    	  			Field2to0 = new JTextField(String.valueOf(playerdata.collectsingleData(3, "2to0",year, month, day)));
	    	  			Field100m = new JTextField(String.valueOf(playerdata.collectsingleData(3, "100m",year, month, day)));
		  		     	
	    	  			
	    	  			JPanel panel1,panel2,panel3,panel4,panel5;
		  	        	titleLabel = new JLabel(year+"/"+month+"/"+day+"跑壘"); 
		  		     	Label0to1 = new JLabel("本壘到一壘幾秒 = "); 
		  		     	Label0to2 = new JLabel("本壘到二壘幾秒 = ");
		  		     	Label1to3 = new JLabel("一壘到三壘幾秒 = ");
		  		     	Label2to0 = new JLabel("二壘到本壘幾秒 = ");
		  		     	Label100m = new JLabel("100 Meter 時間 = ");
		  		     	
			  		  	panel1 = new JPanel(new GridLayout(1,2));
		  		     	panel2 = new JPanel(new GridLayout(1,2));
		  		     	panel3 = new JPanel(new GridLayout(1,2));
		  		     	panel4 = new JPanel(new GridLayout(1,2));
		  		     	panel5 = new JPanel(new GridLayout(1,2));
			  		     	
		  		     	panel1.add(Label0to1);
		  		     	panel1.add(Field0to1);
		  		     	panel2.add(Label0to2);
		  		     	panel2.add(Field0to2);
		  		     	panel3.add(Label1to3);
		  		     	panel3.add(Field1to3);
		  		     	panel4.add(Label2to0);
		  		     	panel4.add(Field2to0);
		  		     	panel5.add(Label100m);
		  		     	panel5.add(Field100m);
		  		     	Dopanel.removeAll();
		  		     	Dopanel.setLayout(new GridLayout(7, 1));
		  	     		Dopanel.add(titleLabel);
		  	     		Dopanel.add(panel1);
		  	     		Dopanel.add(panel2);
		  	     		Dopanel.add(panel3);
		  	     		Dopanel.add(panel4);
		  	     		Dopanel.add(panel5);
		  	     		Dopanel.add(button);
		  	        	frame.revalidate();
	    	  		} 
	    	  		else if(command.equals( "查詢" )){
	    	  			text.setText(year+"/"+month+"/"+day+'\n'+"本壘到一壘 = " + playerdata.collectsingleData(3, "0to1", year,month, day) 
						+'\n'+'\n'+"本壘到二壘 = " + playerdata.collectsingleData(3, "0to2",year, month, day)
						+'\n'+'\n'+"一壘到三壘 = " + playerdata.collectsingleData(3, "1to3",year, month, day)
						+'\n'+'\n'+"二壘到本壘 = " + playerdata.collectsingleData(3, "2to0",year, month, day)
						+'\n'+'\n'+"100公尺 時間 = " + playerdata.collectsingleData(3, "100m",year, month, day)
						);
	    	  			Dopanel.removeAll();
	    	  			Dopanel.setLayout(new GridLayout(3, 2));
	    	  			Dopanel.add(playerdata.collectPlayerData(new Run(username),1));
	    	  			Dopanel.add(playerdata.collectPlayerData(new Run(username),2));
	    	  			Dopanel.add(playerdata.collectPlayerData(new Run(username),3));
	    	  			Dopanel.add(playerdata.collectPlayerData(new Run(username),4));
	    	  			Dopanel.add(playerdata.collectPlayerData(new Run(username),5));
	    	  			Dopanel.add(new JPanel());
	    	  			frame.revalidate();
	    	  		}  
	    	  	  break;
	    	  }
			}
		}
	}
	public JFrame getFrame() {
        return this.frame;
    }
	public void getDate() {
		String place = null;
		String itemSel = (String) ItemjComboBox.getSelectedItem();//get the selected item
		switch(itemSel) {
			case "內野":
				place = "infield";
				break;
			case "外野":
				place = "outfield";
				break;
			case "跑壘":
				place = "run";
				break;
			case "側拋":
				place = "sidehit";
				break;
			case "正拋":
				place = "hit";
				break;
		}
		
		DatejComboBox.removeAllItems();
		Dopanel.removeAll();
		text.setText("");
		DatejcomboBox date = new DatejcomboBox(place,username);
		yearList = date.getYearList();
		monthList = date.getMonthList();
		dayList = date.getDayList();
		for(int i=0;i<monthList.size();i++) {
			DatejComboBox.addItem(String.valueOf(yearList.get(i))+"/" +String.valueOf(monthList.get(i))+"/" + String.valueOf(dayList.get(i)));
		}
	}
	
}
