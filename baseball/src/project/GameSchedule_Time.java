package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GameSchedule_Time {
    private Connection con = null;
    private Connection con1 = null;
    private Statement stat = null;
    private Statement stat1 = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    private PreparedStatement pst = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private PreparedStatement pst1 = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private final JFrame frame;
    private final JLabel text;
    private JPanel panel1, panel2, panel3, panel4;
    private int week=0;
    private JButton button;
    private JLabel W1_1, W1_2, W2_1, W2_2, W3_1, W3_2, W4_1, W4_2, W5_1, W5_2;
    /*public static void main(String[] args) {
        GameSchedule_Time gui = new GameSchedule_Time();
        JFrame frame = gui.getFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0,400,300);
        frame.setVisible(true);
    }*/
    public GameSchedule_Time(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/week?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/contest?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
        }
        catch(ClassNotFoundException e) { System.out.println("DriverClassNotFound :"+e.toString()); }//有可能會產生sqlexception
        catch(SQLException x) { System.out.println("Exception :"+x.toString()); }
        //---------抓week-------------------
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
        //---------抓Data--------------------

        frame = new JFrame("球隊賽程規劃-不能比賽時間");
        frame.setLayout(new GridLayout(5, 1));
        //-----------------------------------------
        text = new JLabel("第"+week+"週賽程 : ",0);
        text.setFont(new Font("標楷體", Font.BOLD, 20));
        //-----------------------------------------
        panel1 = new JPanel(new GridLayout(1, 6));
        panel1.add(new JLabel("時程",0));
        panel1.add(new JLabel("星期一",0));
        panel1.add(new JLabel("星期二",0));
        panel1.add(new JLabel("星期三",0));
        panel1.add(new JLabel("星期四",0));
        panel1.add(new JLabel("星期五",0));
        //-----------------------------------------
        panel2 = new JPanel(new GridLayout(1, 6));
        panel2.add(new JLabel("早",0));
        panel3 = new JPanel(new GridLayout(1, 6));
        panel3.add(new JLabel("中",0));
        try {
            stat1 = con1.createStatement();
            rs1 = stat1.executeQuery("select * from week"+week);
            while(rs1.next()){
                panel2.add(W1_1 = new JLabel(rs1.getString("W1_1"),0));
                panel2.add(W2_1 = new JLabel(rs1.getString("W2_1"),0));
                panel2.add(W3_1 = new JLabel(rs1.getString("W3_1"),0));
                panel2.add(W4_1 = new JLabel(rs1.getString("W4_1"),0));
                panel2.add(W5_1 = new JLabel(rs1.getString("W5_1"),0));
                panel3.add(W1_2 = new JLabel(rs1.getString("W1_2"),0));
                panel3.add(W2_2 = new JLabel(rs1.getString("W2_2"),0));
                panel3.add(W3_2 = new JLabel(rs1.getString("W3_2"),0));
                panel3.add(W4_2 = new JLabel(rs1.getString("W4_2"),0));
                panel3.add(W5_2 = new JLabel(rs1.getString("W5_2"),0));
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
        panel4 = new JPanel();
        button = new JButton("確定");
        panel4.add(button);
        frame.add(text);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
    }
    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e){


        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
}
