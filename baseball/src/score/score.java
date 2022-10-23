package score;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
public class score{
    private final JFrame frame;
    private final JPanel panel1 ,panel2, panel3, panel4, panel5, panel6;
    private DefaultTableModel model = null;
    private final JTable table;
    private final JButton saveBtn;
    String DefaultFormat = "yyyy-MM-dd";
    JXDatePicker datepick = new JXDatePicker();
    public score() {
        frame = new JFrame("計分板-設置");
        frame.setLayout(new GridLayout(6, 1));
        panel1 = new JPanel();//放日期
        Date date = new Date();
        //----------資料庫----------------------------------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/score?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
        }
        catch(ClassNotFoundException e) {
            System.out.println("DriverClassNotFound :"+e.toString());
        }//有可能會產生sqlexception
        catch(SQLException x) {
            System.out.println("Exception :"+x.toString());
        }
        // 設定 date日期
        datepick.setDate(date);
        datepick.setBounds(137, 83, 177, 24);
        panel1.add(datepick);
        //-------------------------------------------------
        panel2 = new JPanel();//分數表格
        JLabel label=new JLabel("輸入完記得按下'Enter'鍵使框框變藍色(尤其是最後一格)") ;
        panel2.add(label);
        //-------------------------------------------------
        panel3 = new JPanel();//分數表格
        String[] titles = { "隊名", "1", "2", "3", "4", "5", "6", "7", "8", "9", "R", "H", "E"};
        model = new DefaultTableModel(titles, 2);
        table = new JTable(model);
        panel3.add(new JScrollPane(table));
        //-------------------------------------------------
        ActionListener listener1 = new MyEventListener();
        ActionListener listener2 = new MyEventListener();
        panel6 = new JPanel();//按鈕
        saveBtn = new JButton("儲存");
        saveBtn.addActionListener(listener1);
        panel6.add(saveBtn);
        //--------------------------------------------------
        panel4 = new JPanel();
        panel5 = new JPanel();

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.add(panel6);
    }
    private PreparedStatement pst = null;
    private String insertdbSQL = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String sqlstr ="",dateIn="",
            player1In="",p11In="", p12In="", p13In="", p14In="", p15In="", p16In="", p17In="", p18In="", p19In="", p1rIn="", p1hIn="", p1eIn="",
            player2In="",p21In="", p22In="", p23In="", p24In="", p25In="", p26In="", p27In="", p28In="", p29In="", p2rIn="", p2hIn="", p2eIn="";
    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String buttonName = e.getActionCommand();
            Date d = datepick.getDate();
            Mysql use = new Mysql();
            use.start();
            if (buttonName.equals("儲存")){
                //存入資料庫 TODO//
                //System.out.print("in sql!");
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
        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
}
