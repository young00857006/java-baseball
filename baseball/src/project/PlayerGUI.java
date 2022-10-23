package project;
import ability.GUI.PlayerDataGUI;
import score.ScoreGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class PlayerGUI {
    private Connection con = null;
    private Connection con1 = null;
    private Statement stat = null;
    private Statement stat1 = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    private PreparedStatement pst = null;
    private JFrame frame;
    private JLabel title,name;
    private JPanel panel,panel1, panel2, panel3, panel4, panel5, panel6, panel7;
    private JButton button1,button2,button3,button4;
    private JTextField textField;
    private ActionListener listener1 = new MyEventListener();
    private int week=0;
    private String Myname ;
    public static void main(String[] args) {
        PlayerGUI gui = new PlayerGUI("Test");
        JFrame frame = gui.getFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0,380,300);
        frame.setVisible(true);
    }
    public PlayerGUI(String myname){
        Myname = myname;
        //---------抓week-------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/week?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/notice?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
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
        //-----------------------------------
        frame = new JFrame("球員管理系統-隊員");
        title = new JLabel("第"+week+"週",0);
        title.setFont(new Font("標楷體", Font.BOLD, 30));
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
        panel6.add(textField = new JTextField(32));
        try {
            stat1 = con1.createStatement();
            rs1 = stat1.executeQuery("select * from text");
            while(rs1.next())
            {
                textField.setText(rs1.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        panel6.setBorder(BorderFactory.createTitledBorder("注意事項"));
        panel7.add(name = new JLabel(myname));
        name.setFont(new Font("標楷體",Font.BOLD , 14));
        panel7.setBorder(BorderFactory.createTitledBorder("姓名"));
        panel5.add(panel6,BorderLayout.WEST);
        panel5.add(panel7,BorderLayout.EAST);
        frame.add(title,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.CENTER);
        frame.add(panel5,BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0,380,300);
        frame.setVisible(true);
    }
    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent f){
            String buttonName = f.getActionCommand();
            if(buttonName=="本週賽程"){
                new GameSchedule_Client(Myname);
            }
            else if(buttonName=="球員能力"){
                PlayerDataGUI gui = new PlayerDataGUI(Myname);
                JFrame frame = gui.getFrame();
                //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setBounds(100,100,800,500);
                frame.setVisible(true);
            }
            else if(buttonName=="本週訓練"){
                new TrainingSchedule(Myname);
            }
            else if(buttonName=="比賽內容"){
                new ScoreGUI(Myname);
            }
        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
}
