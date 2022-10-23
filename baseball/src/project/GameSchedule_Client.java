package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class GameSchedule_Client {
    private Connection con = null;
    private Connection con1 = null;
    private Connection con2 = null;
    private Statement stat = null;
    private Statement stat1 = null;
    private Statement stat2 = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    private ResultSet rs2 = null;
    private PreparedStatement pst = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private PreparedStatement pst1 = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private PreparedStatement pst2 = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private JFrame frame;
    private JLabel text,text2;
    private ActionListener listener1 = new MyEventListener();
    private ActionListener listener2 = new MyEventListener2();
    private JButton go,button2,button3,button_2;
    private JPanel panel1_2,panel2_2,panel3_2,panel4_2,smallpanel,bigpanel,panel4,panel1, panel2, panel3;
    private int week=0;
    private JRadioButton W1_1, W1_2, W2_1, W2_2, W3_1, W3_2, W4_1, W4_2, W5_1, W5_2;
    private int w1_1=0,w1_2=0,w2_1=0,w2_2=0,w3_1=0,w3_2=0,w4_1=0,w4_2=0,w5_1=0,w5_2=0;
    private JComboBox[] comboBoxes = new JComboBox[10];
    private String Myname;
    /*public static void main(String[] args) {
        GameSchedule_Client gui = new GameSchedule_Client("Test");
        JFrame frame = gui.getFrame();
    }*/
    public GameSchedule_Client(String myname) {
        Myname = myname;
        //---------抓week-------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/week?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/contest?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost/gameschedule?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
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
                text = new JLabel("第"+week+"週不能參與的時間 : ",0);
                text.setFont(new Font("標楷體", Font.BOLD, 20));
                panel1 = new JPanel(new GridLayout(1, 6));
                panel1.add(new JLabel(" "));
                panel1.add(new JLabel("星期一"));
                panel1.add(new JLabel("星期二"));
                panel1.add(new JLabel("星期三"));
                panel1.add(new JLabel("星期四"));
                panel1.add(new JLabel("星期五"));
                panel2 = new JPanel(new GridLayout(1, 6));
                panel2.add(new JLabel("早",0));
                panel2.add(W1_1 = new JRadioButton());
                panel2.add(W2_1 = new JRadioButton());
                panel2.add(W3_1 = new JRadioButton());
                panel2.add(W4_1 = new JRadioButton());
                panel2.add(W5_1 = new JRadioButton());
                panel3 = new JPanel(new GridLayout(1, 6));
                panel3.add(new JLabel("中",0));
                panel3.add(W1_2 = new JRadioButton());
                panel3.add(W2_2 = new JRadioButton());
                panel3.add(W3_2 = new JRadioButton());
                panel3.add(W4_2 = new JRadioButton());
                panel3.add(W5_2 = new JRadioButton());
                panel4 = new JPanel();
                go = new JButton("傳送");
                go.addActionListener(listener2);
                panel4.add(go);
                //----------------如果有填過，把資料放上去---------
                try {
                    stat2 = con2.createStatement();
                    rs2 = stat2.executeQuery("select * from week"+week);
                    while(rs2.next())
                    {
                        if(rs2.getString("name").equals(Myname))
                        {
                            if(rs2.getInt(2)==1){W1_1.setSelected(true);}if(rs2.getInt(3)==1){W1_2.setSelected(true);}
                            if(rs2.getInt(4)==1){W2_1.setSelected(true);}if(rs2.getInt(5)==1){W2_2.setSelected(true);}
                            if(rs2.getInt(6)==1){W3_1.setSelected(true);}if(rs2.getInt(7)==1){W3_2.setSelected(true);}
                            if(rs2.getInt(8)==1){W4_1.setSelected(true);}if(rs2.getInt(9)==1){W4_2.setSelected(true);}
                            if(rs2.getInt(10)==1){W5_1.setSelected(true);}if(rs2.getInt(11)==1){W5_2.setSelected(true);}
                            stat2.executeUpdate("DELETE FROM week"+week+" WHERE Name='"+Myname+"'");
                        }
                        else{
                            break;
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
                //--------------------------------------------------------
                bigpanel.add(text);
                bigpanel.add(panel1);
                bigpanel.add(panel2);
                bigpanel.add(panel3);
                bigpanel.add(panel4);
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
                frame.dispose();
            }
            else if(buttonName=="傳送"){
                try
                {
                    pst2 = con2.prepareStatement("INSERT INTO week"+week+" VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                    if(W1_1.isSelected()){w1_1=1;}if(W1_2.isSelected()){w1_2=1;}
                    if(W2_1.isSelected()){w2_1=1;}if(W2_2.isSelected()){w2_2=1;}
                    if(W3_1.isSelected()){w3_1=1;}if(W3_2.isSelected()){w3_2=1;}
                    if(W4_1.isSelected()){w4_1=1;}if(W4_2.isSelected()){w4_2=1;}
                    if(W5_1.isSelected()){w5_1=1;}if(W5_2.isSelected()){w5_2=1;}
                    pst2.setString(1, Myname);
                    pst2.setInt(2, w1_1);pst2.setInt(3, w1_2);
                    pst2.setInt(4, w2_1);pst2.setInt(5, w2_2);
                    pst2.setInt(6, w3_1);pst2.setInt(7, w3_2);
                    pst2.setInt(8, w4_1);pst2.setInt(9, w4_2);
                    pst2.setInt(10, w5_1);pst2.setInt(11, w5_2);
                    pst2.executeUpdate();
                }
                catch(SQLException e)
                {
                    System.out.println("InsertDB Exception :" + e.toString());
                }
                JOptionPane.showMessageDialog(frame,"傳送成功,隨時注意賽程表!");
                frame.dispose();
            }
        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
}
