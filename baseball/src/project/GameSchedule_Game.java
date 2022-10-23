package project;

import email.SendMail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class GameSchedule_Game {
    private Connection con = null;
    private Connection con1 = null;
    private Statement stat = null;
    private Statement stat1 = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    private PreparedStatement pst = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private PreparedStatement pst1 = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private JFrame frame;
    private JLabel text,text2;
    private ActionListener listener1 = new MyEventListener();
    private ActionListener listener2 = new MyEventListener2();
    private JButton button,button1,button2,button3,button_2;
    private JPanel panel1_2,panel2_2,panel3_2,panel4_2,smallpanel,bigpanel,panel,panel0,panel1, panel2, panel3,pan1,pan2,pan3,pan4,pan5,pan6,pan7,pan8,pan9,pan10,pan0,pan00;
    private int week=0;
    private JComboBox[] comboBoxes = new JComboBox[10];
    /*public static void main(String[] args) {
        GameSchedule_Game gui = new GameSchedule_Game();
        JFrame frame = gui.getFrame();
    }*/
    public GameSchedule_Game() {
        //---------抓week-------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/week?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/contest?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
        }
        catch(ClassNotFoundException e) { System.out.println("DriverClassNotFound :"+e.toString()); }//有可能會產生sqlexception
        catch(SQLException x) { System.out.println("Exception :"+x.toString()); }
        try {
            stat = con.createStatement();
            rs = stat.executeQuery("select * from week");
            rs.next();
            week=rs.getInt("Week");
        }
        catch(SQLException e) { System.out.println("DropDB Exception :" + e.toString()); }
        finally { try {
            if(rs!=null) {
                rs.close();
                rs = null; }
            if(stat!=null) {
                stat.close();
                stat = null; }
            if(pst!=null) {
                pst.close();
                pst = null; } }
        catch(SQLException e) { System.out.println("Close Exception :" + e.toString()); } }
        //--------GUI設定---------------------
        frame = new JFrame("球賽規劃");
        bigpanel = new JPanel(new GridLayout(5, 1));
        smallpanel = new JPanel(new GridLayout(2,1));
        button2 = new JButton("設定");
        button3 = new JButton("查看");
        button2.addActionListener(listener1);
        button3.addActionListener(listener1);
        smallpanel.add(button2);
        smallpanel.add(button3);
        frame.add(bigpanel,BorderLayout.CENTER);
        frame.add(smallpanel,BorderLayout.WEST);
        frame.setBounds(0,0,400,300);
        frame.setVisible(true);
    }
    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent f){
            String buttonName = f.getActionCommand();//辨識哪個button被觸發
            if(buttonName=="設定"){
                button2.setEnabled(false);
                button3.setEnabled(false);
                System.out.printf("in");
                bigpanel.removeAll();
                panel = new JPanel();
                text = new JLabel("第" + week + "週賽程 : ", 0);
                text.setFont(new Font("標楷體", Font.BOLD, 20));
                panel.add(text);
                button1 = new JButton("球員狀況");
                button1.addActionListener(listener2);
                panel.add(button1);
                panel1 = new JPanel(new GridLayout(1, 6));
                panel1.add(new JLabel("時程", 0));
                panel1.add(new JLabel("星期一", 0));
                panel1.add(new JLabel("星期二", 0));
                panel1.add(new JLabel("星期三", 0));
                panel1.add(new JLabel("星期四", 0));
                panel1.add(new JLabel("星期五", 0));
                for(int i=0;i<10;i++){
                    comboBoxes[i] = new JComboBox();
                    comboBoxes[i].addItem("X");
                    comboBoxes[i].addItem("航管");comboBoxes[i].addItem("機械");
                    comboBoxes[i].addItem("河工");comboBoxes[i].addItem("海洋");comboBoxes[i].addItem("環漁");
                    comboBoxes[i].addItem("電機");comboBoxes[i].addItem("運輸");comboBoxes[i].addItem("輪機");
                    comboBoxes[i].addItem("養殖");comboBoxes[i].addItem("造船");
                }
                panel2 = new JPanel(new GridLayout(1, 6));
                pan0 = new JPanel();
                pan0.add(new JLabel("早", 0));
                panel2.add(pan0);
                pan1 = new JPanel();
                pan1.add(comboBoxes[0]);
                panel2.add(pan1);
                pan2 = new JPanel();
                pan2.add(comboBoxes[2]);
                panel2.add(pan2);
                pan3 = new JPanel();
                pan3.add(comboBoxes[4]);
                panel2.add(pan3);
                pan4 = new JPanel();
                pan4.add(comboBoxes[6]);
                panel2.add(pan4);
                pan5 = new JPanel();
                pan5.add(comboBoxes[8]);
                panel2.add(pan5);
                panel3 = new JPanel(new GridLayout(1, 6));
                pan00 = new JPanel();
                pan00.add(new JLabel("中", 0));
                panel3.add(pan00);
                pan6 = new JPanel();
                pan6.add(comboBoxes[1]);
                panel3.add(pan6);
                pan7 = new JPanel();
                pan7.add(comboBoxes[3]);
                panel3.add(pan7);
                pan8 = new JPanel();
                pan8.add(comboBoxes[5]);
                panel3.add(pan8);
                pan9 = new JPanel();
                pan9.add(comboBoxes[7]);
                panel3.add(pan9);
                pan10 = new JPanel();
                pan10.add(comboBoxes[9]);
                panel3.add(pan10);
                button = new JButton("傳送");
                button.addActionListener(listener2);
                panel0 = new JPanel();
                panel0.add(button);
                //----------------如果有填過，把資料放上去---------
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/contest?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
                }
                catch(ClassNotFoundException e) { System.out.println("DriverClassNotFound :"+e.toString()); }//有可能會產生sqlexception
                catch(SQLException x) { System.out.println("Exception :"+x.toString()); }
                try {
                    stat1 = con1.createStatement();
                    rs1 = stat1.executeQuery("select * from week"+week);
                    while(rs1.next())
                    {
                        for(int i=1;i<11;i++){
                            if(rs1.getString(i).equals("X")){comboBoxes[i-1].setSelectedIndex(0);}
                            else if(rs1.getString(i).equals("航管")){comboBoxes[i-1].setSelectedIndex(1);}
                            else if(rs1.getString(i).equals("機械")){comboBoxes[i-1].setSelectedIndex(2);}
                            else if(rs1.getString(i).equals("河工")){comboBoxes[i-1].setSelectedIndex(3);}
                            else if(rs1.getString(i).equals("海洋")){comboBoxes[i-1].setSelectedIndex(4);}
                            else if(rs1.getString(i).equals("環漁")){comboBoxes[i-1].setSelectedIndex(5);}
                            else if(rs1.getString(i).equals("電機")){comboBoxes[i-1].setSelectedIndex(6);}
                            else if(rs1.getString(i).equals("運輸")){comboBoxes[i-1].setSelectedIndex(7);}
                            else if(rs1.getString(i).equals("輪機")){comboBoxes[i-1].setSelectedIndex(8);}
                            else if(rs1.getString(i).equals("養殖")){comboBoxes[i-1].setSelectedIndex(9);}
                            else if(rs1.getString(i).equals("造船")){comboBoxes[i-1].setSelectedIndex(10);}
                        }
                        panel2.revalidate();
                        panel3.revalidate();
                    }
                    stat1.executeUpdate("DELETE FROM week"+week);
                }
                catch(SQLException e) { System.out.println("No 重複"); }
                finally { try {
                    if(rs!=null) {
                        rs.close();
                        rs = null; }
                    if(stat!=null) {
                        stat.close();
                        stat = null; }
                    if(pst!=null) {
                        pst.close();
                        pst = null; } }
                catch(SQLException e) { System.out.println("No 重複"); } }
                //--------------------------------------------------------*/
                bigpanel.add(panel);
                bigpanel.add(panel1);
                bigpanel.add(panel2);
                bigpanel.add(panel3);
                bigpanel.add(panel0);
                bigpanel.repaint();
                bigpanel.revalidate();
                frame.add(bigpanel,BorderLayout.CENTER);
            }
            else if (buttonName=="查看"){
                bigpanel.removeAll();
                //-----------------------------------------
                text2 = new JLabel("第"+week+"週賽程 : ",0);
                text2.setFont(new Font("標楷體", Font.BOLD, 20));
                //-----------------------------------------
                panel1_2 = new JPanel(new GridLayout(1, 6));
                panel1_2.add(new JLabel("時程",0));
                panel1_2.add(new JLabel("星期一",0));
                panel1_2.add(new JLabel("星期二",0));
                panel1_2.add(new JLabel("星期三",0));
                panel1_2.add(new JLabel("星期四",0));
                panel1_2.add(new JLabel("星期五",0));
                //-----------------------------------------
                panel2_2 = new JPanel(new GridLayout(1, 6));
                panel2_2.add(new JLabel("早",0));
                panel3_2 = new JPanel(new GridLayout(1, 6));
                panel3_2.add(new JLabel("中",0));
                try {
                    stat1 = con1.createStatement();
                    rs1 = stat1.executeQuery("select * from week"+week);
                    while(rs1.next()){
                        panel2_2.add(new JLabel(rs1.getString("W1_1"),0));
                        panel2_2.add(new JLabel(rs1.getString("W2_1"),0));
                        panel2_2.add(new JLabel(rs1.getString("W3_1"),0));
                        panel2_2.add(new JLabel(rs1.getString("W4_1"),0));
                        panel2_2.add(new JLabel(rs1.getString("W5_1"),0));
                        panel3_2.add(new JLabel(rs1.getString("W1_2"),0));
                        panel3_2.add(new JLabel(rs1.getString("W2_2"),0));
                        panel3_2.add(new JLabel(rs1.getString("W3_2"),0));
                        panel3_2.add(new JLabel(rs1.getString("W4_2"),0));
                        panel3_2.add(new JLabel(rs1.getString("W5_2"),0));
                    }
                }
                catch(SQLException e) { System.out.println("No 重複"); }
                finally { try {
                    if(rs!=null) {
                        rs.close();
                        rs = null; }
                    if(stat!=null) {
                        stat.close();
                        stat = null; }
                    if(pst!=null) {
                        pst.close();
                        pst = null; } }
                catch(SQLException e) { System.out.println("No 重複"); } }
                //--------------------------------------------------------
                panel4_2 = new JPanel();
                button_2 = new JButton("確定");
                button_2.addActionListener(listener2);
                panel4_2.add(button_2);
                bigpanel.add(text2);
                bigpanel.add(panel1_2);
                bigpanel.add(panel2_2);
                bigpanel.add(panel3_2);
                bigpanel.add(panel4_2);
                bigpanel.repaint();
                bigpanel.revalidate();
                frame.add(bigpanel,BorderLayout.CENTER);
            }
        }
    }
    public class MyEventListener2 implements ActionListener {
        public void actionPerformed(ActionEvent f){
            String buttonName = f.getActionCommand();//辨識哪個button被觸發
            if(buttonName=="球員狀況"){
                new GameSchedule_Data();
            }
            else if(buttonName=="確定"){
                /*官廷洋*/
                SendMail sendmail = new SendMail("比賽時間",week);
                /*官廷洋*/
                frame.dispose();
            }
            else if(buttonName=="傳送"){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/contest?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
                }
                catch(ClassNotFoundException e) { System.out.println("DriverClassNotFound :"+e.toString()); }//有可能會產生sqlexception
                catch(SQLException x) { System.out.println("Exception :"+x.toString()); }
                //--------insert data-------
                try
                {
                    stat1 = con1.createStatement();
                    stat1.executeUpdate("DELETE FROM week"+week);
                    pst1 = con1.prepareStatement("INSERT INTO week"+week+" VALUES(?,?,?,?,?,?,?,?,?,?)");
                    for(int i=0;i<10;i++){
                        String temp=(String) comboBoxes[i].getSelectedItem();
                        pst1.setString((i+1),temp);
                    }
                    pst1.executeUpdate();
                }
                catch(SQLException e)
                {
                    System.out.println("InsertDB Exception :" + e.toString());
                }
                JOptionPane.showMessageDialog(frame,"傳送成功,叫隊員注意賽程表!");
                frame.dispose();
            }
        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
}
