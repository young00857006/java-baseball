package score;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class ScoreGUI {
    private JFrame frame;
    private JPanel panel,panel1,panel2,panel1_1,panel2_1,panel3_1,panel4_1,panel5_1,panel6_1,panel7_1,panel8_1,panel1_2,panel2_2,panel3_2,panel4_2,panel5_2,panel6_2,panel7_2;
    private JButton button1,button2,saveBtn,startBtn;
    private DefaultTableModel model = null;
    private ActionListener listener1 = new MyEventListener();
    private ActionListener listener2 = new MyEventListener2();
    private Connection con = null;
    private Statement stat = null;
    private ResultSet rs = null;
    private JTable table;
    private JLabel text,scoreSum[] = new JLabel[28];
    private int i,run=0;
    private String Myname;
    private PreparedStatement pst = null;
    private String insertdbSQL = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String sqlstr ="",dateIn="",
            player1In="",p11In="", p12In="", p13In="", p14In="", p15In="", p16In="", p17In="", p18In="", p19In="", p1rIn="", p1hIn="", p1eIn="",
            player2In="",p21In="", p22In="", p23In="", p24In="", p25In="", p26In="", p27In="", p28In="", p29In="", p2rIn="", p2hIn="", p2eIn="";
    JXDatePicker datepick = new JXDatePicker();
    String DefaultFormat = "yyyy-MM-dd";
    public ScoreGUI(String myname){
        Myname = myname;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/score?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
        }
        catch(ClassNotFoundException f) {
            System.out.println("DriverClassNotFound :"+f.toString());
        }//有可能會產生sqlexception
        catch(SQLException x) {
            System.out.println("Exception :"+x.toString());
        }
        frame = new JFrame("比賽內容");
        panel = new JPanel(new GridLayout(8, 1));
        panel1 = new JPanel();
        panel2 = new JPanel(new GridLayout(2,1));
        button1 = new JButton("設定");
        button2 = new JButton("查看");
        button1.addActionListener(listener1);
        button2.addActionListener(listener1);
        panel2.add(button1);
        panel2.add(button2);
        if(myname!="隊長"){
            button1.setEnabled(false);
        }
        frame.add(panel,BorderLayout.CENTER);
        frame.add(panel2,BorderLayout.WEST);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500,0,550,500);
        frame.setVisible(true);
    }
    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent f){
            String buttonName = f.getActionCommand();
            if(buttonName=="設定"){
                panel.removeAll();
                panel1_1 = new JPanel();//放日期
                Date date = new Date();
                datepick.setDate(date);
                datepick.setBounds(137, 83, 177, 24);
                panel1_1.add(datepick);
                panel2_1 = new JPanel();//分數表格
                JLabel label=new JLabel("輸入完記得按下'Enter'鍵使框框變藍色(尤其是最後一格)") ;
                panel2_1.add(label);
                //-------------------------------------------------
                panel3_1 = new JPanel();//分數表格
                String[] titles = { "隊名", "1", "2", "3", "4", "5", "6", "7", "8", "9", "R", "H", "E"};
                model = new DefaultTableModel(titles, 2);
                table = new JTable(model);
                panel3_1.add(new JScrollPane(table));
                panel6_1 = new JPanel();//按鈕
                saveBtn = new JButton("儲存");
                saveBtn.addActionListener(listener2);
                panel6_1.add(saveBtn);
                panel4_1 = new JPanel();
                panel5_1 = new JPanel();
                panel7_1 = new JPanel();
                panel8_1 = new JPanel();
                panel.add(panel1_1);
                panel.add(panel7_1);
                panel.add(panel2_1);
                panel.add(panel3_1);
                panel.add(panel4_1);
                panel.add(panel8_1);
                panel.add(panel5_1);
                panel.add(panel6_1);
                panel.repaint();
                panel.revalidate();
                frame.add(panel,BorderLayout.CENTER);
            }
            else {
                panel.removeAll();
                panel1_2 = new JPanel();//放日期
                Date date = new Date();
                datepick.setDate(date);
                datepick.setBounds(137, 83, 177, 24);
                text = new JLabel("請選擇欲搜尋日期",0);
                text.setFont(new Font("標楷體", Font.BOLD, 20));
                panel1_2.add(datepick);
                panel2_2 = new JPanel(new GridLayout(3, 13));//分數表格
                panel3_2 = new JPanel(new GridLayout(3, 13));//分數表格
                panel4_2 = new JPanel(new GridLayout(3, 13));//分數表格
                panel5_2 = new JPanel();
                startBtn = new JButton("開始查詢");
                startBtn.addActionListener(listener2);
                panel5_2.add(startBtn);
                panel6_2 = new JPanel();
                panel7_2 = new JPanel();
                panel.add(text);
                panel.add(panel1_2);
                panel.add(panel2_2);
                panel.add(panel6_2);
                panel.add(panel3_2);
                panel.add(panel7_2);
                panel.add(panel4_2);
                panel.add(panel5_2);
                panel.repaint();
                panel.revalidate();
                frame.add(panel,BorderLayout.CENTER);
            }
        }
    }
    public class MyEventListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String buttonName = e.getActionCommand();
            if (buttonName.equals("儲存")){
                Date d = datepick.getDate();
                Mysql use = new Mysql();
                use.start();
                dateIn = d.toString();
                player1In = (String) model.getValueAt(0, 0);
                p11In = (String) model.getValueAt(0, 1);
                p12In = (String) model.getValueAt(0, 2);
                p13In = (String) model.getValueAt(0, 3);
                p14In = (String) model.getValueAt(0, 4);
                p15In = (String) model.getValueAt(0, 5);
                p16In = (String) model.getValueAt(0, 6);
                p17In = (String) model.getValueAt(0, 7);
                p18In = (String) model.getValueAt(0, 8);
                p19In = (String) model.getValueAt(0, 9);
                p1rIn = (String) model.getValueAt(0, 10);
                p1hIn = (String) model.getValueAt(0, 11);
                p1eIn = (String) model.getValueAt(0, 12);
                player2In = (String) model.getValueAt(1, 0);
                p21In = (String) model.getValueAt(1, 1);
                p22In = (String) model.getValueAt(1, 2);
                p23In = (String) model.getValueAt(1, 3);
                p24In = (String) model.getValueAt(1, 4);
                p25In = (String) model.getValueAt(1, 5);
                p26In = (String) model.getValueAt(1, 6);
                p27In = (String) model.getValueAt(1, 7);
                p28In = (String) model.getValueAt(1, 8);
                p29In = (String) model.getValueAt(1, 9);
                p2rIn = (String) model.getValueAt(1, 10);
                p2hIn = (String) model.getValueAt(1, 11);
                p2eIn = (String) model.getValueAt(1, 12);
                if(dateIn==null||player1In==null||p11In==null||p12In==null||p13In==null||p14In==null||p15In==null||p16In==null||p17In==null||p18In==null||p19In==null||p1hIn==null||p1rIn==null||p1eIn==null ||
                        player2In==null||p21In==null||p22In==null||p23In==null||p24In==null||p25In==null||p26In==null||p27In==null||p28In==null||p29In==null||p2hIn==null||p2rIn==null||p2rIn==null){
                    JOptionPane.showMessageDialog(frame,"尚有空格未輸入完成");
                }
                else{
                    JOptionPane.showMessageDialog(frame,"註冊成功");
                    //----------計分表資料確認-----------
                /*
                for(int i=0;i<=12;i++) {
                    System.out.printf("%s ", model.getValueAt(0, i));
                }
                System.out.print("\n");
                for(int j=0;j<=12;j++) {
                    System.out.printf("%s ", model.getValueAt(0, j));
                }
                System.out.print("\n");
                */
                    try {
                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost/score?useUnicode=true&characterEncoding=utf-8",
                                "root","Warren0702");
                        if(!con.isClosed())
                            System.out.println("資料庫連線成功");

                        try {
                            pst = con.prepareStatement(insertdbSQL);
                            pst.setString(1, dateIn);
                            pst.setString(2, player1In);
                            pst.setString(3, p11In);
                            pst.setString(4, p12In);
                            pst.setString(5, p13In);
                            pst.setString(6, p14In);
                            pst.setString(7, p15In);
                            pst.setString(8, p16In);
                            pst.setString(9, p17In);
                            pst.setString(10, p18In);
                            pst.setString(11, p19In);
                            pst.setString(12, p1rIn);
                            pst.setString(13, p1hIn);
                            pst.setString(14, p1eIn);
                            pst.setString(15, player2In);
                            pst.setString(16, p21In);
                            pst.setString(17, p22In);
                            pst.setString(18, p23In);
                            pst.setString(19, p24In);
                            pst.setString(20, p25In);
                            pst.setString(21, p26In);
                            pst.setString(22, p27In);
                            pst.setString(23, p28In);
                            pst.setString(24, p29In);
                            pst.setString(25, p2rIn);
                            pst.setString(26, p2hIn);
                            pst.setString(27, p2eIn);
                            pst.executeUpdate();
                        }
                        catch(SQLException e2) {
                            System.out.println("InsertDB Exception :" + e2.toString());
                        }
                    }
                    catch(SQLException e1) {
                        System.out.println("資料庫連線失敗");
                    }
                }
            }
            else{
                run=0;
                panel2_2.removeAll();
                panel3_2.removeAll();
                panel4_2.removeAll();
                panel2_2.repaint();
                panel3_2.repaint();
                panel4_2.repaint();
                panel2_2.revalidate();
                panel3_2.revalidate();
                panel4_2.revalidate();
                //--------------------------------------------------------
                JOptionPane.showMessageDialog(frame, "查詢成功");

                try {
                    stat = con.createStatement();
                    Date d = datepick.getDate();
                    dateIn = d.toString();
                    rs = stat.executeQuery("select * from user where date = '"+dateIn+"'");
                    panel2_2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                    panel3_2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                    panel4_2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                    while(rs.next()) {
                        if(run==0){
                            panel2_2.add(new JLabel("隊名"));
                            panel2_2.add(new JLabel("1"));
                            panel2_2.add(new JLabel("2"));
                            panel2_2.add(new JLabel("3"));
                            panel2_2.add(new JLabel("4"));
                            panel2_2.add(new JLabel("5"));
                            panel2_2.add(new JLabel("6"));
                            panel2_2.add(new JLabel("7"));
                            panel2_2.add(new JLabel("8"));
                            panel2_2.add(new JLabel("9"));
                            panel2_2.add(new JLabel("R"));
                            panel2_2.add(new JLabel("H"));
                            panel2_2.add(new JLabel("E"));
                            for(i=2; i<28; i++){
                                panel2_2.add(scoreSum[i] = new JLabel(rs.getString(i)));
                            }
                            panel2_2.setBorder(BorderFactory.createTitledBorder(""));
                            panel3_2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                            panel4_2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                        }
                        if(run==1){
                            panel3_2.add(new JLabel("隊名"));
                            panel3_2.add(new JLabel("1"));
                            panel3_2.add(new JLabel("2"));
                            panel3_2.add(new JLabel("3"));
                            panel3_2.add(new JLabel("4"));
                            panel3_2.add(new JLabel("5"));
                            panel3_2.add(new JLabel("6"));
                            panel3_2.add(new JLabel("7"));
                            panel3_2.add(new JLabel("8"));
                            panel3_2.add(new JLabel("9"));
                            panel3_2.add(new JLabel("R"));
                            panel3_2.add(new JLabel("H"));
                            panel3_2.add(new JLabel("E"));
                            for(i=2; i<28; i++){
                                panel3_2.add(scoreSum[i] = new JLabel(rs.getString(i)));
                            }
                            panel3_2.setBorder(BorderFactory.createTitledBorder(""));
                            //panel4 = new JPanel(new GridLayout(3, 13));//分數表格
                            panel4_2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                        }
                        if(run==2){
                            panel4_2.add(new JLabel("隊名"));
                            panel4_2.add(new JLabel("1"));
                            panel4_2.add(new JLabel("2"));
                            panel4_2.add(new JLabel("3"));
                            panel4_2.add(new JLabel("4"));
                            panel4_2.add(new JLabel("5"));
                            panel4_2.add(new JLabel("6"));
                            panel4_2.add(new JLabel("7"));
                            panel4_2.add(new JLabel("8"));
                            panel4_2.add(new JLabel("9"));
                            panel4_2.add(new JLabel("R"));
                            panel4_2.add(new JLabel("H"));
                            panel4_2.add(new JLabel("E"));
                            for(i=2; i<28; i++){
                                panel4_2.add(scoreSum[i] = new JLabel(rs.getString(i)));
                            }
                            panel4_2.setBorder(BorderFactory.createTitledBorder(""));
                        }
                        run++;
                    }
                }
                catch(SQLException e2) {
                    System.out.println("DropDB Exception :" + e2.toString());
                }
                panel2_2.repaint();
                panel2_2.revalidate();

                panel3_2.repaint();
                panel3_2.revalidate();

                panel4_2.repaint();
                panel4_2.revalidate();

                frame.repaint();
            }
        }
    }
}
