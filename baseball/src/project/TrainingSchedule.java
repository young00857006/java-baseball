package project;
import ability.createDB.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class TrainingSchedule {
    private Connection con = null;
    private Connection con1 = null;
    private Statement stat = null;
    private Statement stat1 = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    private PreparedStatement pst1 = null;
    private JFrame frame;
    private static final String[] COLUME_NAMES = { "年份","日期", "時間", "地點","內容" };
    private JPanel panel,panel0,panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,p[] = new JPanel[8],p_y[] = new JPanel[8],p_d[] = new JPanel[8],p_t[] = new JPanel[8],bt[] = new JPanel[8];
    private JTable playersTable;
    private JButton button,button1,button2,button3,b[] = new JButton[8];
    private JTextField year[] = new JTextField[8],month[] = new JTextField[8],day[] = new JTextField[8],hour[] = new JTextField[8],minute[] = new JTextField[8],location[] = new JTextField[8];
    private JScrollPane tableWithScrollBar;
    private JComboBox train[] = new JComboBox[8];
    private ArrayList<Integer> Year;
    private ArrayList<Integer> Month;
    private ArrayList<Integer> Day;
    private ArrayList<Integer> Hour;
    private ArrayList<String> Minute;
    private ArrayList<String> Location;
    private ArrayList<String> Context;
    private ActionListener listener1 = new MyEventListener();
    private ActionListener listener2 = new MyEventListener2();
    public ArrayList<Train> list = new ArrayList<Train>();
    private int week;
    private int index=0;
    private String Myname;
    /*public static void main(String[] args) {
        TrainingSchedule gui = new TrainingSchedule("隊長");
    }*/
    public TrainingSchedule(String myname){
        Myname = myname;
        //-------------------抓week-----------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/training?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/week?useUnicode=true&characterEncoding=utf-8", "root","Warren0702"); //00857006為密碼
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("DriverClassNotFound :"+e.toString());
        }//有可能會產生sqlexception
        catch(SQLException x) {
            System.out.println("Exception :"+x.toString());
        }
        try {
            stat1 = con1.createStatement();
            rs1 = stat1.executeQuery("select * from week");
            rs1.next();
            week=rs1.getInt("Week");
        }
        catch(SQLException e) { week=1; }
        finally { try {
            if(rs1!=null) {
                rs1.close();
                rs1 = null; }
            if(stat1!=null) {
                stat1.close();
                stat1 = null; }
            if(pst1!=null) {
                pst1.close();
                pst1 = null; } }
        catch(SQLException e) { System.out.println("Close Exception :" + e.toString()); } }
        //------------------------------------------
        frame = new JFrame("訓練規劃");
        panel = new JPanel(new BorderLayout());
        panel1 = new JPanel();
        panel2 = new JPanel(new GridLayout(2,1));
        button1 = new JButton("設定");
        button2 = new JButton("查看");
        button1.addActionListener(listener1);
        button2.addActionListener(listener1);
        panel2.add(button1);
        panel2.add(button2);
        if(Myname!="隊長"){
            button1.setEnabled(false);
        }
        frame.add(panel,BorderLayout.CENTER);
        frame.add(panel2,BorderLayout.WEST);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(800,0,500,400);
        frame.setVisible(true);
    }
    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent f){
            String buttonName = f.getActionCommand();
            if(buttonName=="設定"){
                button1.setEnabled(false);
                button2.setEnabled(false);
                panel.removeAll();
                index = 0;
                panel3 = new JPanel();
                panel3.add(new JLabel("第"+week+"週訓練"));
                panel4 = new JPanel();
                panel4.add(button = new JButton("新增訓練"));
                panel0 = new JPanel();
                panel0.add(button3 = new JButton("確認"));
                button.addActionListener(listener2);
                button3.addActionListener(listener2);
                panel5 = new JPanel();
                panel5.add(panel3);
                panel5.add(panel4);
                panel6 = new JPanel(new GridLayout(1,6));
                panel6.add(new JLabel("年份",0));
                panel6.add(new JLabel("日期",0));
                panel6.add(new JLabel("時間",0));
                panel6.add(new JLabel("地點",0));
                panel6.add(new JLabel("內容",0));
                panel6.add(new JLabel("   ",0));
                panel7 = new JPanel(new GridLayout(2,1));
                panel7.add(panel5);
                panel7.add(panel6);
                panel8 = new JPanel();
                panel8.setLayout(new GridLayout(7,1));
                //----------------如果有填過，把資料放上去----------------------
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/training?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
                }
                catch(ClassNotFoundException e) { System.out.println("DriverClassNotFound :"+e.toString()); }//有可能會產生sqlexception
                catch(SQLException x) { System.out.println("Exception :"+x.toString()); }
                try {
                    stat1 = con1.createStatement();
                    rs1 = stat1.executeQuery("select * from week"+week);
                    while(rs1.next())
                    {
                        index++;
                        p[index] = new JPanel(new GridLayout(1,6));
                        p_y[index] = new JPanel();
                        p_y[index].add(year[index] = new JTextField(4));
                        year[index].setText(Integer.toString(rs1.getInt(1)));//1
                        p[index].add(p_y[index]);//p1
                        p_d[index] = new JPanel();
                        p_d[index].add(month[index] = new JTextField(2));
                        month[index].setText(Integer.toString(rs1.getInt(2)));//2
                        p_d[index].add(new JLabel("/"));
                        p_d[index].add(day[index] = new JTextField(2));
                        day[index].setText(Integer.toString(rs1.getInt(3)));//3
                        p[index].add(p_d[index]);//p2
                        p_t[index] = new JPanel();
                        p_t[index].add(hour[index] = new JTextField(2));
                        hour[index].setText(Integer.toString(rs1.getInt(4)));//4
                        p_t[index].add(new JLabel(":"));
                        p_t[index].add(minute[index] = new JTextField(2));
                        minute[index].setText(rs1.getString(5));//5
                        p[index].add(p_t[index]);//p3
                        p[index].add(location[index] = new JTextField(3));//p4
                        location[index].setText(rs1.getString(6));//6
                        train[index] = new JComboBox();
                        train[index].addItem("內野");
                        train[index].addItem("外野");
                        train[index].addItem("跑壘");
                        train[index].addItem("側拋");
                        train[index].addItem("正拋");
                        if(rs1.getString(7).equals("內野")){train[index].setSelectedIndex(0);}
                        else if(rs1.getString(7).equals("外野")){train[index].setSelectedIndex(1);}
                        else if(rs1.getString(7).equals("跑壘")){train[index].setSelectedIndex(2);}
                        else if(rs1.getString(7).equals("側拋")){train[index].setSelectedIndex(3);}
                        else if(rs1.getString(7).equals("正拋")){train[index].setSelectedIndex(4);}
                        p[index].add(train[index]);//p5
                        b[index] = new JButton("刪除");
                        b[index].addActionListener(listener2);
                        bt[index] = new JPanel();
                        bt[index].add(b[index]);
                        p[index].add(bt[index]);//p6
                        panel8.add(p[index]);
                        panel8.revalidate();
                    }
                    stat1.executeUpdate("DELETE FROM week"+week);
                }
                catch(SQLException e) { System.out.println("No 重複"); }
                finally { try {
                    if(rs1!=null) {
                        rs1.close();
                        rs1 = null; }
                    if(stat1!=null) {
                        stat1.close();
                        stat1 = null; }
                    if(pst1!=null) {
                        pst1.close();
                        pst1 = null; } }
                catch(SQLException e) { System.out.println("No 重複"); } }
                //------------------------------------------------------------
                panel.add(panel7,BorderLayout.NORTH);
                panel.add(panel8,BorderLayout.CENTER);
                panel.add(panel0,BorderLayout.SOUTH);
                panel.repaint();
                panel.revalidate();
                frame.add(panel,BorderLayout.CENTER);
            }
            else{
                panel.removeAll();
                String data[][];
                data = getData();
                panel1 = new JPanel();
                panel1.add(new JLabel("第"+week+"週訓練"));
                playersTable = new JTable(data, COLUME_NAMES);
                tableWithScrollBar = new JScrollPane(playersTable);
                panel.add(panel1,BorderLayout.NORTH);
                panel.add(tableWithScrollBar, BorderLayout.CENTER);
                panel.repaint();
                panel.revalidate();
                frame.add(panel,BorderLayout.CENTER);
                for(int i=1;i<index+1;i++) {
                    Train tmp = new Train(Integer.parseInt(year[i].getText()),Integer.parseInt(month[i].getText()),Integer.parseInt(day[i].getText()),Integer.parseInt(hour[i].getText()),minute[i].getText(),location[i].getText(), (String) train[i].getSelectedItem());
                    list.add(tmp);
                }
                inputData();
            }
        }
    }
    public class MyEventListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String buttonName = e.getActionCommand();//辨識哪個button被觸發
            if(buttonName=="確認"){
                for(int i=1;i<index+1;i++) {
                    Train tmp = new Train(Integer.parseInt(year[i].getText()),Integer.parseInt(month[i].getText()),Integer.parseInt(day[i].getText()),Integer.parseInt(hour[i].getText()),minute[i].getText(),location[i].getText(), (String) train[i].getSelectedItem());
                    list.add(tmp);
                }
                inputData();
                JOptionPane.showMessageDialog(frame,"傳送成功");
                frame.dispose();
            }
            else if(buttonName=="新增訓練"){
                index++;
                if(index>7) {
                    JOptionPane.showMessageDialog(frame,"練太多了!你是想操死人啊!");
                    index=7;
                }
                else{
                    p[index] = new JPanel(new GridLayout(1,6));
                    p_y[index] = new JPanel();
                    p_y[index].add(year[index] = new JTextField(4));
                    p[index].add(p_y[index]);//p1
                    p_d[index] = new JPanel();
                    p_d[index].add(month[index] = new JTextField(2));
                    p_d[index].add(new JLabel("/"));
                    p_d[index].add(day[index] = new JTextField(2));
                    p[index].add(p_d[index]);
                    p_t[index] = new JPanel();
                    p_t[index].add(hour[index] = new JTextField(2));
                    p_t[index].add(new JLabel(":"));
                    p_t[index].add(minute[index] = new JTextField(2));
                    p[index].add(p_t[index]);
                    p[index].add(location[index] = new JTextField(3));
                    train[index] = new JComboBox();
                    train[index].addItem("內野");
                    train[index].addItem("外野");
                    train[index].addItem("跑壘");
                    train[index].addItem("側拋");
                    train[index].addItem("正拋");
                    p[index].add(train[index]);
                    b[index] = new JButton("刪除");
                    b[index].addActionListener(listener2);
                    bt[index] = new JPanel();
                    bt[index].add(b[index]);
                    p[index].add(bt[index]);
                    panel8.add(p[index]);
                    panel8.revalidate();
                }

            }
            else if(buttonName=="刪除"){
                int confirm = 0;
                /*Kuan*/
                for(int i=1;i<index+1;i++){
                    if(e.getSource()==b[i]){
                        String context = null;
                        switch(String.valueOf(train[i].getSelectedItem())) {
                            case "內野":
                                context = "infield";
                                break;
                            case "外野":
                                context = "outfield";
                                break;
                            case "跑壘":
                                context = "run";
                                break;
                            case "正拋":
                                context = "hit";
                                break;
                            case "側拋":
                                context = "sidehit";
                                break;
                        }
                        try {
                            CreateTraing l = new CreateTraing(context,Integer.valueOf(year[i].getText()),Integer.valueOf(month[i].getText()),Integer.valueOf(day[i].getText()),0);
                            l.delcol();
                        } catch (NumberFormatException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        break;
                    }
                }
                /*kuan*/
                for(int i=1;i<index+1;i++){
                    if(e.getSource()==b[i]){
                        confirm=1;
                        p[i].removeAll();
                    }
                    if(confirm==1){
                        p[i].removeAll();
                        if(i+1<=index){//往前移一格的工程
                            p_y[i] = new JPanel();
                            year[i].setText(year[i+1].getText());
                            p_y[i].add(year[i]);
                            p[i].add(p_y[i]);//p1
                            //-------
                            p_d[i] = new JPanel();
                            month[i].setText(month[i+1].getText());
                            p_d[i].add(month[i]);
                            p_d[i].add(new JLabel("/"));
                            day[i].setText(day[i+1].getText());
                            p_d[i].add(day[i]);
                            p[i].add(p_d[i]);//p2
                            //-------
                            p_t[i] = new JPanel();
                            hour[i].setText(hour[i+1].getText());
                            p_t[i].add(hour[i]);
                            p_t[i].add(new JLabel(":"));
                            minute[i].setText(minute[i+1].getText());
                            p_t[i].add(minute[i]);
                            p[i].add(p_t[i]);//p3
                            //-------
                            location[i] = new JTextField(3);
                            location[i].setText(location[i+1].getText());
                            p[i].add(location[i]);//p4
                            //-------
                            train[i] = new JComboBox();
                            train[i] = train[i+1];
                            p[i].add(train[i]);//p5
                            //-------
                            b[i] = new JButton("刪除");
                            b[i].addActionListener(listener2);
                            bt[i] = new JPanel();
                            bt[i].add(b[i]);
                            p[i].add(bt[i]);//p6
                            //-------
                            p[i].revalidate();
                        }
                    }
                }
                p[index].removeAll();
                panel8.removeAll();
                for(int i=1;i<index;i++){
                    panel8.add(p[i]);
                }
                panel8.revalidate();
                panel8.repaint();
                panel.add(panel8,BorderLayout.CENTER);
                panel.revalidate();
                index--;
            }
        }
    }
    public String[][] getData(){
        try
        {
            stat = con.createStatement();
            rs = stat.executeQuery("select * from week"+week);
            Year = new ArrayList<Integer>();
            Month = new ArrayList<Integer>();
            Day = new ArrayList<Integer>();
            Hour = new ArrayList<Integer>();
            Minute = new ArrayList<String>();
            Location = new ArrayList<String>();
            Context = new ArrayList<String>();
            //System.out.println("Date\t\tTimes\t\tSuccess");
            while(rs.next())
            {
                Year.add(rs.getInt("Year"));
                Month.add(rs.getInt("Month"));
                Day.add(rs.getInt("Day"));
                Hour.add(rs.getInt("Hour"));
                Minute.add(rs.getString("Minute"));
                Location.add(rs.getString("Location"));
                Context.add(rs.getString("Context"));
                //System.out.println(rs.getInt("Date")+"\t\t"+rs.getInt("Times")+"\t\t"+rs.getInt("Success")+"\t\t");
            }
        }
        catch(SQLException e)
        {
            System.out.println("DropDB Exception :" + e.toString());
        }
        int size = Month.size();
        String[][] listArray = new String[size][];
        for(int i=0;i<size;i++)
        {
            String[] record = new String[5];
            String s0 = String.valueOf(Year.get(i));
            String s1 = Month.get(i)+"/"+Day.get(i);
            System.out.printf("%s%n",s1);
            String s2 = Hour.get(i)+":"+Minute.get(i);
            String s3 = Location.get(i);
            String s4 = Context.get(i);
            record[0] = s0;
            record[1] = s1;
            record[2] = s2;
            record[3] = s3;
            record[4] = s4;
            listArray[i] = record;
        }
        return listArray;
    }
    public void inputData(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/training?useUnicode=true&characterEncoding=utf-8", "root","Warren0702"); //00857006為密碼
        }
        catch(ClassNotFoundException e) {
            System.out.println("DriverClassNotFound :"+e.toString());
        }//有可能會產生sqlexception
        catch(SQLException x) {
            System.out.println("Exception :"+x.toString());
        }
        try
        {
            String u = "week"+week;
            pst1 = con1.prepareStatement("INSERT INTO "+ u +" VALUES(?,?,?,?,?,?,?)");
            for(int k=1;k<index+1;k++) {
                pst1.setInt(1, list.get(k-1).getYear());
                pst1.setInt(2, list.get(k-1).getMonth());
                pst1.setInt(3, list.get(k-1).getDay());
                pst1.setInt(4, list.get(k-1).getHour());
                pst1.setString(5, list.get(k-1).getMinute());
                pst1.setString(6, list.get(k-1).getLocation());
                pst1.setString(7, list.get(k-1).getContext());
                pst1.executeUpdate();
            }
            /*官廷洋*/
            for(int k=1;k<index+1;k++) {
                String context="";
                switch(list.get(k-1).getContext()) {
                    case "內野":
                        context = "infield";
                        break;
                    case "外野":
                        context = "outfield";
                        break;
                    case "跑壘":
                        context = "run";
                        break;
                    case "正拋":
                        context = "hit";
                        break;
                    case "側拋":
                        context = "sidehit";
                        break;
                }
                CreateTraing t = new CreateTraing(context,list.get(k-1).getYear(),list.get(k-1).getMonth(),list.get(k-1).getDay());
            }
            /*官廷洋*/
        }
        catch(SQLException e)
        {
            System.out.println("InsertDB Exception :" + e.toString());
        }
        finally
        {
            try
            {
                if(rs1!=null)
                {
                    rs1.close();
                    rs1 = null;
                }
                if(stat1!=null)
                {
                    stat1.close();
                    stat1 = null;
                }
                if(pst1!=null)
                {
                    pst1.close();
                    pst1 = null;
                }
            }
            catch(SQLException e)
            {
                System.out.println("Close Exception :" + e.toString());
            }
        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
}