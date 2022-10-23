package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
public class GameSchedule_Data {
    private JFrame frame;
    private Connection con = null; //Database objects//連接object
    private Connection con1 = null; //Database objects//連接object
    private Statement stat = null;//執行,傳入之sql為完整字串
    private Statement stat1 = null;//執行,傳入之sql為完整字串
    private ResultSet rs = null;//結果集
    private ResultSet rs1 = null;//結果集
    private PreparedStatement pst = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private PreparedStatement pst1 = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private JLabel text,morning[] = new JLabel[5],noon[] = new JLabel[5],name;
    private JPanel panel1,panel2,panel3,panel4;
    private JTextArea textArea;
    private int week=0;
    private String sum[] = new String[11];
    private int count[] = new int[11];
    /*public static void main(String[] args) {
        GameSchedule_Data gui = new GameSchedule_Data();
        JFrame frame = gui.getFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0,400,300);
        frame.setVisible(true);
    }*/
    public GameSchedule_Data(){
        //---------建立資料庫----------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/week?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
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
        //-----------------------------------
        frame = new JFrame("球隊賽程規劃-統計數據");
        frame.setLayout(new GridLayout(5, 1));
        text = new JLabel("第"+week+"週不能參與的時間 : ",0);
        text.setFont(new Font("標楷體", Font.BOLD, 20));
        MouseListener mouseListener1 = new MyEventListener1();
        MouseListener mouseListener2 = new MyEventListener2();
        MouseListener mouseListener3 = new MyEventListener3();
        MouseListener mouseListener4 = new MyEventListener4();
        MouseListener mouseListener5 = new MyEventListener5();
        MouseListener mouseListener6 = new MyEventListener6();
        MouseListener mouseListener7 = new MyEventListener7();
        MouseListener mouseListener8 = new MyEventListener8();
        MouseListener mouseListener9 = new MyEventListener9();
        MouseListener mouseListener10 = new MyEventListener10();
        //-----------------------------------------
        panel1 = new JPanel(new GridLayout(1, 6));
        panel1.add(new JLabel(" "));
        panel1.add(new JLabel("星期一"));
        panel1.add(new JLabel("星期二"));
        panel1.add(new JLabel("星期三"));
        panel1.add(new JLabel("星期四"));
        panel1.add(new JLabel("星期五"));
        //-----------------------------------------
        panel2 = new JPanel(new GridLayout(1, 6));
        panel2.add(new JLabel("早",0));
        panel2.add(morning[0] = new JLabel(" "));
        morning[0].addMouseListener(mouseListener1);
        panel2.add(morning[1] = new JLabel(" "));
        morning[1].addMouseListener(mouseListener3);
        panel2.add(morning[2] = new JLabel(" "));
        morning[2].addMouseListener(mouseListener5);
        panel2.add(morning[3] = new JLabel(" "));
        morning[3].addMouseListener(mouseListener7);
        panel2.add(morning[4] = new JLabel(" "));
        morning[4].addMouseListener(mouseListener9);
        //-----------------------------------------
        panel3 = new JPanel(new GridLayout(1, 6));
        panel3.add(new JLabel("中",0));
        panel3.add(noon[0] = new JLabel(" "));
        noon[0].addMouseListener(mouseListener2);
        panel3.add(noon[1] = new JLabel(" "));
        noon[1].addMouseListener(mouseListener4);
        panel3.add(noon[2] = new JLabel(" "));
        noon[2].addMouseListener(mouseListener6);
        panel3.add(noon[3] = new JLabel(" "));
        noon[3].addMouseListener(mouseListener8);
        panel3.add(noon[4] = new JLabel(" "));
        noon[4].addMouseListener(mouseListener10);
        //-----------------------------------------
        panel4 = new JPanel();
        name = new JLabel(" ",0);
        panel4.add(name);
        panel4.setBorder(BorderFactory.createTitledBorder("此時段不能到的名單"));
        getData();
        frame.add(text);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.setBounds(800,0,400,300);
        frame.setVisible(true);
    }
    public class MyEventListener1 implements MouseListener {
        @Override public void mouseClicked(MouseEvent e) {
            name.setText(sum[1]);
        }@Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public class MyEventListener2 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            name.setText(sum[2]);
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public class MyEventListener3 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            name.setText(sum[3]);
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public class MyEventListener4 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            name.setText(sum[4]);
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public class MyEventListener5 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            name.setText(sum[5]);
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public class MyEventListener6 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            name.setText(sum[6]);
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public class MyEventListener7 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            name.setText(sum[7]);
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public class MyEventListener8 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            name.setText(sum[8]);
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public class MyEventListener9 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            name.setText(sum[9]);
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public class MyEventListener10 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            name.setText(sum[10]);
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public void getData(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/gameschedule?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
        }
        catch(ClassNotFoundException e) { System.out.println("DriverClassNotFound :"+e.toString()); }//有可能會產生sqlexception
        catch(SQLException x) { System.out.println("Exception :"+x.toString()); }
        try
        {
            for(int i=0;i<11;i++){//初始化
                sum[i] = "";
            }
            stat1 = con1.createStatement();
            rs1 = stat1.executeQuery("select * from week"+week);
            while(rs1.next())
            {
                String name = rs1.getString(1);//先拿他的名字
                for(int i=2;i<12;i++){
                    if(rs1.getInt(i)==1){
                        sum[i-1] = sum[i-1]+name+"    ";
                        count[i-1]++;
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("DropDB Exception :" + e.toString());
        }
        for(int i=1;i<11;i++){
            if(i%2==0){
                noon[(i/2)-1].setText(Integer.toString(count[i]));
            }
            else{
                morning[(i/2)].setText(Integer.toString(count[i]));
            }
        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
}
