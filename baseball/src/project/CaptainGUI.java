package project;
import ability.GUI.PlayerDataGUI;
import email.SendMail;
import score.ScoreGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
public class CaptainGUI {
    private Connection con = null;//week
    private Statement stat = null;//week
    private ResultSet rs = null;//week
    private PreparedStatement pst = null;//week
    private Connection con1 = null;//notice
    private Statement stat1 = null;//notice
    private ResultSet rs1 = null;//notice
    private PreparedStatement pst1 = null;//notice
    private Connection con2 = null;//delete
    private Statement stat2 = null;//delete
    private ResultSet rs2 = null;//delete
    private PreparedStatement pst2 = null;//delete
    private Connection con3 = null;//gameschedule
    private Statement stat3 = null;//gameschedule
    private ResultSet rs3 = null;//gameschedule
    private PreparedStatement pst3 = null;//gameschedule
    private Connection con4 = null;//week
    private Statement stat4 = null;//gameschedule
    private Connection con5 = null;//week
    private Connection con6=null,con7=null,con8=null,con9=null,con10=null;//官廷洋
    private Statement stat6 = null,stat7 = null,stat8 = null,stat9 = null,stat10 = null;//kuan
    private JFrame frame,frame2;
    public static JLabel title,title2;
    private JLabel name;
    private JPanel panel,panel0,panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,panel10,panel11,panel12,panel14,panel16,panel17,panel18,panel19;
    public JPanel panel15;
    private JButton button1,button2,button3,button4,button5,button6,button7,button8,button9,reset;
    public static JButton button;
    private JTextField textField,delete;
    private JScrollPane tableWithScrollBar;
    private ArrayList<String> totallist;
    private ActionListener listener1 = new MyEventListener();
    private JComboBox jComboBox;
    public static final CountDownLatch ctl = new CountDownLatch(1);
    public static int week=0;
    public String Myname;
    public static void main(String[] args) throws SQLException {
        CaptainGUI gui = new CaptainGUI("隊長");

    }
    public CaptainGUI(String myname) throws SQLException {
        Myname = myname;
        //---------抓week-------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/week?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/notice?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con3 = DriverManager.getConnection("jdbc:mysql://localhost/gameschedule?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con4 = DriverManager.getConnection("jdbc:mysql://localhost/training?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con5 = DriverManager.getConnection("jdbc:mysql://localhost/contest?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con6 = DriverManager.getConnection("jdbc:mysql://localhost/infield?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con7 = DriverManager.getConnection("jdbc:mysql://localhost/outfield?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con8 = DriverManager.getConnection("jdbc:mysql://localhost/hit?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con9 = DriverManager.getConnection("jdbc:mysql://localhost/sidehit?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con10 = DriverManager.getConnection("jdbc:mysql://localhost/run?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
        }
        catch(ClassNotFoundException e) { System.out.println("DriverClassNotFound :"+e.toString()); }//有可能會產生sqlexception
        catch(SQLException x) { System.out.println("Exception :"+x.toString()); }
        try {
            stat = con.createStatement();
            rs = stat.executeQuery("select * from week");
            rs.next();
            week=rs.getInt("Week");
        }
        catch(SQLException e) {
            stat.executeUpdate("CREATE TABLE week (   Week        INTEGER)");
            pst = con.prepareStatement("INSERT INTO Week VALUES(?)");
            pst.setInt(1,1);
            pst.executeUpdate();
            week=1;
            create();
        }
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
        //-----------------------------------
        frame = new JFrame("球員管理系統-隊長");
        panel0 = new JPanel(new BorderLayout());
        panel15 = new JPanel(new FlowLayout());
        title = new JLabel("第"+week+"週",0);
        title.setFont(new Font("標楷體", Font.BOLD, 30));
        button = new JButton("開放下一週");
        button.addActionListener(listener1);
        reset = new JButton("開啟下一學期");
        reset.addActionListener(listener1);
        panel15.add(title);
        panel15.add(button);
        panel15.add(reset);
        panel0.add(panel15,BorderLayout.CENTER);
        panel = new JPanel(new GridLayout(2,2));
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        button1 = new JButton("本週賽程");
        button2 = new JButton("本週訓練");
        button3 = new JButton("球員能力");
        button4 = new JButton("比賽內容");
        button1.addActionListener(listener1);
        button2.addActionListener(listener1);
        button3.addActionListener(listener1);
        button4.addActionListener(listener1);
        panel1.add(button1);
        panel2.add(button2);
        panel3.add(button3);
        panel4.add(button4);
        button1.setPreferredSize(new Dimension(120, 50));
        button2.setPreferredSize(new Dimension(120, 50));
        button3.setPreferredSize(new Dimension(120, 50));
        button4.setPreferredSize(new Dimension(120, 50));
        button1.setFont(new Font("標楷體",Font.BOLD , 18));
        button2.setFont(new Font("標楷體",Font.BOLD , 18));
        button3.setFont(new Font("標楷體",Font.BOLD , 18));
        button4.setFont(new Font("標楷體",Font.BOLD , 18));
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.setBorder(BorderFactory.createTitledBorder("功能"));
        panel5 = new JPanel(new BorderLayout());
        panel6 = new JPanel();
        panel7 = new JPanel();
        //-----------------右邊的欄位-------------
        panel8 = new JPanel(new BorderLayout());
        panel8.setBorder(BorderFactory.createTitledBorder("未填名單"));
        panel9 = new JPanel(new GridLayout(14,1));
        totallist = new ArrayList<String>();
        fillList();
        tableWithScrollBar = new JScrollPane(panel9);
        panel8.add(tableWithScrollBar,BorderLayout.CENTER);
        panel14 = new JPanel();
        button7 = new JButton("刷新");
        button7.addActionListener(listener1);
        button9 = new JButton("寄信");
        button9.addActionListener(listener1);
        panel14.add(button7);
        panel14.add(button9);
        panel8.add(panel14,BorderLayout.SOUTH);
        //-----------------左邊的欄位-------------
        panel10 = new JPanel(new GridLayout(1,1));
        panel10.setBorder(BorderFactory.createTitledBorder("其他功能"));
        panel11 = new JPanel(new GridLayout(2,1));
        panel11.add(new JLabel("刪除球員:"));
        panel12 = new JPanel();
        delete = new JTextField(6);
        panel12.add(delete);
        button5 = new JButton("刪除");
        button5.addActionListener(listener1);
        panel12.add(button5);
        panel11.add(panel12);
        panel10.add(panel11);
        //--------------------------------------
        panel6.add(new JLabel("提醒隊員:"));
        button6 = new JButton("公告");
        button6.addActionListener(listener1);
        textField = new JTextField(37);
        panel6.add(textField);
        panel6.add(button6);
        panel6.setBorder(BorderFactory.createTitledBorder("公告"));
        panel7.add(name = new JLabel(myname));
        name.setFont(new Font("標楷體",Font.BOLD , 14));
        panel7.setBorder(BorderFactory.createTitledBorder("姓名"));
        panel5.add(panel6,BorderLayout.WEST);
        panel5.add(panel7,BorderLayout.EAST);
        //------如果"公告"有填過，把資料放上去------
        try {
            stat1 = con1.createStatement();
            rs1 = stat1.executeQuery("select * from text");
            while(rs1.next())
            {
                textField.setText(rs1.getString(1));
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
        //---------------------------------
        frame.add(panel0,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.CENTER);
        frame.add(panel5,BorderLayout.SOUTH);
        frame.add(panel8,BorderLayout.EAST);
        frame.add(panel10,BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400,180,550,300);
        frame.setVisible(true);
    }
    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent f){
            String buttonName = f.getActionCommand();
            if(buttonName=="公告"){
                try
                {
                    stat1 = con1.createStatement();
                    stat1.executeUpdate("DELETE FROM text");
                    pst1 = con1.prepareStatement("INSERT INTO text VALUES(?)");
                    pst1.setString(1, textField.getText());
                    pst1.executeUpdate();
                    /*官廷洋 公告成功後寄信給每個人*/
                    SendMail sendmail = new SendMail("球隊公告",String.valueOf(textField.getText()));
                    /*官廷洋*/
                    JOptionPane.showMessageDialog(frame,"公告成功");
                }
                catch(SQLException e)
                {
                    System.out.println("InsertDB Exception :" + e.toString());
                }
            }
            else if(buttonName=="刪除"){
                try {
                    stat2 = con2.createStatement();
                    rs2 = stat2.executeQuery("select * from user");
                    /*官廷洋*/
                    stat6 = con6.createStatement();
                    stat6.executeUpdate("DROP TABLE "+delete.getText());
                    stat7 = con7.createStatement();
                    stat7.executeUpdate("DROP TABLE "+delete.getText());
                    stat8 = con8.createStatement();
                    stat8.executeUpdate("DROP TABLE "+delete.getText());
                    stat9 = con9.createStatement();
                    stat9.executeUpdate("DROP TABLE "+delete.getText());
                    stat10 = con10.createStatement();
                    stat10.executeUpdate("DROP TABLE "+delete.getText());
                    /*官廷洋*/
                    int confirm = 0;
                    while(rs2.next()){
                        if(delete.getText().equals(rs2.getString(3))){
                            confirm=1;
                            try {
                                stat2.executeUpdate("DELETE FROM user WHERE name='"+delete.getText()+"'"); //DELETE FROM 表的名字 WHERE 名稱='名稱的value';
                            }
                            catch(SQLException e) {
                                System.out.println("DropDB Exception :" + e.toString());
                            }
                            JOptionPane.showMessageDialog(frame,"刪除"+delete.getText()+"成功!");
                        }
                    }
                    if(confirm==0){
                        JOptionPane.showMessageDialog(frame,"名單無此人");
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
                delete.setText("");
                panel12.revalidate();
            }
            else if(buttonName=="刷新"){
                panel9.removeAll();
                try {
                    stat2 = con2.createStatement();
                    rs2 = stat2.executeQuery("select * from user");
                    stat3 = con3.createStatement();
                    rs3 = stat3.executeQuery("select * from week"+week);
                    ArrayList<String> namelist;
                    namelist = new ArrayList<String>();
                    while(rs3.next()){
                        namelist.add(rs3.getString(1));
                    }
                    /*陳威融*/totallist = new ArrayList<String>();/*陳威融*/
                    while(rs2.next()){
                        int confirm=0;
                        for(int i=0;i<namelist.size();i++){
                            if(rs2.getString(3).equals(namelist.get(i))){
                                confirm = 1;
                                break;
                            }
                        }
                        if(confirm==0){
                            /*陳威融*/totallist.add(rs2.getString(3));/*陳威融*/
                            panel9.add(new JLabel(rs2.getString(3)));
                        }
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
                panel9.revalidate();
            }
            else if(buttonName=="開放下一週"){
                //----------insert 下一週---------------------
                try {
                    stat = con.createStatement();
                    pst  = con.prepareStatement("INSERT INTO Week VALUES(?)");
                    stat.executeUpdate("DELETE FROM week");
                    pst.setInt(1,week+1);
                    pst.executeUpdate();
                }
                catch(SQLException e) {
                    System.out.println("InsertDB Exception :" + e.toString()); }
                finally {
                    try {
                        if(rs!=null) {
                            rs.close();
                            rs = null; }
                        if(stat!=null) {
                            stat.close();
                            stat = null; }
                        if(pst!=null) {
                            pst.close();
                            pst = null; }
                    }
                    catch(SQLException e) { System.out.println("Close Exception :" + e.toString()); } }
                //----------開放下一周賽程規劃-------------------
                try
                {
                    stat3 = con3.createStatement();
                    stat3.executeUpdate("CREATE TABLE week"+(week+1)+" (" +
                            "    name     VARCHAR(20) " +
                            "  , W1_1    INTEGER " +
                            "  , W1_2    INTEGER " +
                            "  , W2_1    INTEGER " +
                            "  , W2_2    INTEGER " +
                            "  , W3_1    INTEGER " +
                            "  , W3_2    INTEGER " +
                            "  , W4_1    INTEGER " +
                            "  , W4_2    INTEGER " +
                            "  , W5_1    INTEGER " +
                            "  , W5_2    INTEGER)");
                }
                catch(SQLException e)
                {
                    System.out.println("CreateDB Exception :" + e.toString());
                }
                //----------開放下一周比賽-------------------
                try
                {
                    stat2 = con5.createStatement();
                    stat2.executeUpdate("CREATE TABLE week"+(week+1)+" (" +
                            "    W1_1    VARCHAR(20) " +
                            "  , W1_2    VARCHAR(20) " +
                            "  , W2_1    VARCHAR(20) " +
                            "  , W2_2    VARCHAR(20) " +
                            "  , W3_1    VARCHAR(20) " +
                            "  , W3_2    VARCHAR(20) " +
                            "  , W4_1    VARCHAR(20) " +
                            "  , W4_2    VARCHAR(20) " +
                            "  , W5_1    VARCHAR(20) " +
                            "  , W5_2    VARCHAR(20))");
                }
                catch(SQLException e)
                {
                    System.out.println("CreateDB Exception :" + e.toString());
                }
                //----------開放下一週訓練-------------------
                try
                {
                    stat3 = con4.createStatement();
                    stat3.executeUpdate("CREATE TABLE week"+ (week+1)+"("  +
                            "    Year     INTEGER " +
                            "  , Month     INTEGER " +
                            "  , Day  INTEGER "+
                            "  , Hour  INTEGER" +
                            "  , Minute VARCHAR(20)" +
                            "  , Location VARCHAR(20)" +
                            "  , Context VARCHAR(20))");
                }
                catch(SQLException e)
                {
                    System.out.println("CreateDB Exception :" + e.toString());
                }
                //--------------
                JOptionPane.showMessageDialog(null,"將週數推至"+(week+1)+"週");
                panel15.removeAll();
                week++;
                title.setText("第"+week+"週");
                System.out.printf("%s%n",title.getText());
                panel15.add(title);
                panel15.add(button);
                panel15.add(reset);
                panel15.repaint();
                panel15.revalidate();
                panel9.removeAll();
                try {
                    stat2 = con2.createStatement();
                    rs2 = stat2.executeQuery("select * from user");
                    stat3 = con3.createStatement();
                    rs3 = stat3.executeQuery("select * from week"+week);
                    ArrayList<String> namelist;
                    namelist = new ArrayList<String>();
                    while(rs3.next()){
                        namelist.add(rs3.getString(1));
                    }
                    while(rs2.next()){
                        int confirm=0;
                        for(int i=0;i<namelist.size();i++){
                            if(rs2.getString(3).equals(namelist.get(i))){
                                confirm = 1;
                                break;
                            }
                        }
                        if(confirm==0){
                            panel9.add(new JLabel(rs2.getString(3)));
                        }
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
                panel9.revalidate();
            }
            else if(buttonName=="開啟下一學期"){
                new DeleteAllData();
                JOptionPane.showMessageDialog(frame,"新學期開始!請重新登入!");
                frame.dispose();
            }
            else if(buttonName=="本週訓練"){
                new TrainingSchedule(Myname);
            }
            else if(buttonName=="本週賽程"){
                new GameSchedule_Game();
            }
            else if(buttonName=="球員能力"){
                pickname();
            }
            else if(buttonName=="比賽內容"){
                new ScoreGUI(Myname);
            }
            else if(buttonName=="確認"){
                frame2.dispose();
                String tempname = (String) jComboBox.getSelectedItem();
                PlayerDataGUI gui = new PlayerDataGUI(tempname);
                JFrame frame = gui.getFrame();
                //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setBounds(100,100,800,500);
                frame.setVisible(true);
            }
            /*陳威融 寄信給還沒交表的*/
            else if(buttonName=="寄信"){
                new SendMail("未交時間表!",totallist,week);
            }
            /*陳威融*/
        }
    }
    public void fillList(){
        try {
            stat2 = con2.createStatement();
            rs2 = stat2.executeQuery("select * from user");
            stat3 = con3.createStatement();
            rs3 = stat3.executeQuery("select * from week"+week);
            ArrayList<String> namelist;
            namelist = new ArrayList<String>();
            while(rs3.next()){
                namelist.add(rs3.getString(1));
            }
            /*陳威融*/totallist = new ArrayList<String>();/*陳威融*/
            while(rs2.next()){
                int confirm=0;
                for(int i=0;i<namelist.size();i++){
                    if(rs2.getString(3).equals(namelist.get(i))){
                        confirm = 1;
                        break;
                    }
                }
                if(confirm==0){
                    /*陳威融*/totallist.add(rs2.getString(3));/*陳威融*/
                    panel9.add(new JLabel(rs2.getString(3)));
                }
            }
        }
        catch(SQLException e) { System.out.println("No 重複qqq"); }
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
        catch(SQLException e) { System.out.println("No 重複qqq"); } }
    }
    public void create(){
        try
        {
            stat3 = con3.createStatement();
            stat3.executeUpdate("CREATE TABLE week1 (" +
                    "    name     VARCHAR(20) " +
                    "  , W1_1    INTEGER " +
                    "  , W1_2    INTEGER " +
                    "  , W2_1    INTEGER " +
                    "  , W2_2    INTEGER " +
                    "  , W3_1    INTEGER " +
                    "  , W3_2    INTEGER " +
                    "  , W4_1    INTEGER " +
                    "  , W4_2    INTEGER " +
                    "  , W5_1    INTEGER " +
                    "  , W5_2    INTEGER)");
        }
        catch(SQLException e)
        {
            System.out.println("CreateDB Exception :" + e.toString());
        }
        //----------開放下一周比賽-------------------
        try
        {
            stat2 = con5.createStatement();
            stat2.executeUpdate("CREATE TABLE week1 (" +
                    "    W1_1    VARCHAR(20) " +
                    "  , W1_2    VARCHAR(20) " +
                    "  , W2_1    VARCHAR(20) " +
                    "  , W2_2    VARCHAR(20) " +
                    "  , W3_1    VARCHAR(20) " +
                    "  , W3_2    VARCHAR(20) " +
                    "  , W4_1    VARCHAR(20) " +
                    "  , W4_2    VARCHAR(20) " +
                    "  , W5_1    VARCHAR(20) " +
                    "  , W5_2    VARCHAR(20))");
        }
        catch(SQLException e)
        {
            System.out.println("CreateDB Exception :" + e.toString());
        }
        //----------開放下一週訓練-------------------
        try
        {
            stat3 = con4.createStatement();
            stat3.executeUpdate("CREATE TABLE week1 ("  +
                    "    Year     INTEGER " +
                    "  , Month     INTEGER " +
                    "  , Day  INTEGER "+
                    "  , Hour  INTEGER" +
                    "  , Minute VARCHAR(20)" +
                    "  , Location VARCHAR(20)" +
                    "  , Context VARCHAR(20))");
        }
        catch(SQLException e)
        {
            System.out.println("CreateDB Exception :" + e.toString());
        }
        //----------create notice-------------------
        try {
            stat4 = con1.createStatement();
            stat4.executeUpdate("CREATE TABLE text ("  +
                    "   text VARCHAR(30))");
        }
        catch(SQLException e)
        {
            System.out.println("CreateDB Exception :" + e.toString());
        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
    public void pickname(){
        frame2 = new JFrame("選人");
        frame2.setLayout(new GridLayout(3,1));
        title2 = new JLabel("請選擇要查看的隊員:");
        title2.setFont(new Font("標楷體", Font.BOLD, 25));
        panel16 = new JPanel();
        panel17 = new JPanel();
        panel18 = new JPanel();
        panel16.add(title2);//1
        jComboBox = new JComboBox();
        try {
            stat2 = con2.createStatement();
            rs2 = stat2.executeQuery("select * from user");
            while(rs2.next()){
                jComboBox.addItem(rs2.getString(3));
            }
        }
        catch(SQLException e) { System.out.println("No 重複qqq"); }
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
        catch(SQLException e) { System.out.println("No 重複qqq"); } }
        panel17.add(jComboBox);//2
        button8 = new JButton("確認");
        button8.addActionListener(listener1);
        panel18.add(button8);
        frame2.add(panel16);
        frame2.add(panel17);
        frame2.add(panel18);
        frame2.setBounds(100,100,300,150);
        frame2.setVisible(true);
    }
}