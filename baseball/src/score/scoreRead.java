package score;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;
public class scoreRead{
    private final JFrame frame;
    private JPanel panel1 ,panel2, panel3, panel4, panel5, panel6, panel7;
    private DefaultTableModel model1 = null, model2=null, model3=null;
    private JTable table3;
    private final JButton startBtn;
    String DefaultFormat = "yyyy-MM-dd";
    JXDatePicker datepick = new JXDatePicker();
    private Connection con = null; //Database objects//連接object
    private Statement stat = null;//執行,傳入之sql為完整字串
    private ResultSet rs = null;//結果集
    private PreparedStatement pst = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
    private String dateIn = null;
    private JLabel text, scoreSum[] = new JLabel[28], p2[] = new JLabel[13];
    private String sum[] = new String[15];
    private int run=0;
    public scoreRead() {
        frame = new JFrame("計分板-查閱");
        GridLayout grid = new GridLayout(8,1);
        frame.setLayout(grid);
        panel1 = new JPanel();//放日期
        Date date = new Date();
        datepick.setDate(date);
        datepick.setBounds(137, 83, 177, 24);
        text = new JLabel("請選擇欲搜尋日期",0);
        text.setFont(new Font("標楷體", Font.BOLD, 20));

        panel1.add(datepick);
        //-------------------------------------------------
        panel2 = new JPanel(new GridLayout(3, 13));//分數表格

        //-------------------------------------------------
        panel3 = new JPanel(new GridLayout(3, 13));//分數表格

        //-------------------------------------------------
        panel4 = new JPanel(new GridLayout(3, 13));//分數表格
        //panel6.add(panel2);
        //panel6.add(panel3);
        //panel6.add(panel4);
        //--------------------------------------------------
        ActionListener listener1 = new MyEventListener();
        panel5 = new JPanel();
        startBtn = new JButton("開始查詢");
        startBtn.addActionListener(listener1);

        panel5.add(startBtn);
        //--------------------------------------------------
        panel6 = new JPanel();
        panel7 = new JPanel();
        frame.add(text);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel6);
        frame.add(panel3);
        frame.add(panel7);
        frame.add(panel4);
        frame.add(panel5);
        //frame.add(panel6);

    }
    int i;
    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String buttonName = e.getActionCommand();
            if (buttonName.equals("開始查詢")) {
                run=0;
                //panel6.removeAll();
                //panel6.setLayout(new GridLayout(3,1));
                //frame.revalidate();

                panel2.removeAll();
                panel3.removeAll();
                panel4.removeAll();
                panel2.repaint();
                panel3.repaint();
                panel4.repaint();
                panel2.revalidate();
                panel3.revalidate();
                panel4.revalidate();
                //--------------------------------------------------------
                JOptionPane.showMessageDialog(frame, "查詢成功");

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/score?useUnicode=true&characterEncoding=utf-8", "root", "Warren0702");
                } catch (ClassNotFoundException e1) {
                    System.out.println("DriverClassNotFound :" + e1.toString());
                }//有可能會產生sqlexception
                catch (SQLException x) {
                    System.out.println("Exception :" + x.toString());
                }
                try {
                    stat = con.createStatement();
                    Date d = datepick.getDate();
                    dateIn = d.toString();
                    rs = stat.executeQuery("select * from user where date = '"+dateIn+"'");
                    panel2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                    panel3.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                    panel4.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                    while(rs.next()) {
                        if(run==0){
                            panel2.add(new JLabel("隊名"));
                            panel2.add(new JLabel("1"));
                            panel2.add(new JLabel("2"));
                            panel2.add(new JLabel("3"));
                            panel2.add(new JLabel("4"));
                            panel2.add(new JLabel("5"));
                            panel2.add(new JLabel("6"));
                            panel2.add(new JLabel("7"));
                            panel2.add(new JLabel("8"));
                            panel2.add(new JLabel("9"));
                            panel2.add(new JLabel("R"));
                            panel2.add(new JLabel("H"));
                            panel2.add(new JLabel("E"));
                            for(i=2; i<28; i++){
                                panel2.add(scoreSum[i] = new JLabel(rs.getString(i)));
                            }
                            panel2.setBorder(BorderFactory.createTitledBorder(""));
                            panel3.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                            panel4.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                        }
                        if(run==1){
                            panel3.add(new JLabel("隊名"));
                            panel3.add(new JLabel("1"));
                            panel3.add(new JLabel("2"));
                            panel3.add(new JLabel("3"));
                            panel3.add(new JLabel("4"));
                            panel3.add(new JLabel("5"));
                            panel3.add(new JLabel("6"));
                            panel3.add(new JLabel("7"));
                            panel3.add(new JLabel("8"));
                            panel3.add(new JLabel("9"));
                            panel3.add(new JLabel("R"));
                            panel3.add(new JLabel("H"));
                            panel3.add(new JLabel("E"));
                            for(i=2; i<28; i++){
                                panel3.add(scoreSum[i] = new JLabel(rs.getString(i)));
                            }
                            panel3.setBorder(BorderFactory.createTitledBorder(""));
                            //panel4 = new JPanel(new GridLayout(3, 13));//分數表格
                            panel4.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0)));
                        }
                        if(run==2){
                            panel4.add(new JLabel("隊名"));
                            panel4.add(new JLabel("1"));
                            panel4.add(new JLabel("2"));
                            panel4.add(new JLabel("3"));
                            panel4.add(new JLabel("4"));
                            panel4.add(new JLabel("5"));
                            panel4.add(new JLabel("6"));
                            panel4.add(new JLabel("7"));
                            panel4.add(new JLabel("8"));
                            panel4.add(new JLabel("9"));
                            panel4.add(new JLabel("R"));
                            panel4.add(new JLabel("H"));
                            panel4.add(new JLabel("E"));
                            for(i=2; i<28; i++){
                                panel4.add(scoreSum[i] = new JLabel(rs.getString(i)));
                            }
                            panel4.setBorder(BorderFactory.createTitledBorder(""));
                        }
                        run++;
                    }
                }
                catch(SQLException e2) {
                    System.out.println("DropDB Exception :" + e2.toString());
                }
                panel2.repaint();
                panel2.revalidate();

                panel3.repaint();
                panel3.revalidate();

                panel4.repaint();
                panel4.revalidate();

                frame.repaint();
            }
        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
}