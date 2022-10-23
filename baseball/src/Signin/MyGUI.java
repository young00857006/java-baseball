package Signin;
import project.*;
import project.PlayerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// 資料庫
public class MyGUI {
    private Connection con = null;
    private Statement stat = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;//week
    private final JFrame frame;
    private final JPanel panel1,panel2,panel3;
    private final JTextField account, password;
    private final JButton login, signin;
    private final JLabel text,label1,label2;
    private final ImageIcon img1,img2;
    public String name;
    public MyGUI(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8", "root", "Warren0702");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("DriverClassNotFound :"+e.toString());
        }//有可能會產生sqlexception
        catch(SQLException x) {
            System.out.println("Exception :"+x.toString());
        }
        ActionListener listener1 = new MyEventListener();
        ActionListener listener2 = new MyEventListener();
        frame = new JFrame("登入介面");
        frame.setLayout(new GridLayout(4, 1));
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        text = new JLabel("球隊管理系統",0);
        text.setFont(new Font("標楷體", Font.BOLD, 16));
        img1 = new ImageIcon();
        img2 = new ImageIcon();
        //-----------------------------------------------------------
        account = new JTextField("輸入您的帳號",20);
        Image image = new ImageIcon("D:\\intellij\\src\\Signin\\登入icon.png").getImage();
        image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        img1.setImage(image);
        label1 = new JLabel(img1);
        panel1.add(label1);
        panel1.add(account);
        //-----------------------------------------------------------
        password = new JTextField("輸入您的密碼",20);
        Image image2 = new ImageIcon("D:\\intellij\\src\\Signin\\密碼icon.png").getImage();
        image2 = image2.getScaledInstance(40,40,Image.SCALE_DEFAULT);
        img2.setImage(image2);
        label2 = new JLabel(img2);
        panel2.add(label2);
        panel2.add(password);
        //-----------------------------------------------------------
        login = new JButton("登入");
        login.addActionListener(listener1);
        signin = new JButton("註冊");
        signin.addActionListener(listener2);
        panel3.add(login);
        panel3.add(signin);
        //-----------------------------------------------------------
        frame.add(text);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
    }
    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String buttonName = e.getActionCommand();
            if (buttonName.equals("登入")){
                //確認是否為合法帳號
                MysqlJdbc t = new MysqlJdbc();
                try {
                    if(t.checkAccount(account.getText(), password.getText())!=null){
                        JOptionPane.showConfirmDialog(null,"登入成功"," " , JOptionPane.PLAIN_MESSAGE);
                        if(account.getText().equals("super")){
                            new CaptainGUI("隊長");
                            frame.dispose();
                        }
                        else{
                            System.out.printf("%s",account.getText());
                            try {
                                stat = con.createStatement();
                                rs = stat.executeQuery("select * from user WHERE account='"+account.getText()+"'");
                                while(rs.next())
                                {
                                    name = rs.getString(3);
                                }
                            }
                            catch(SQLException f) { System.out.println("No 重複"); }
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
                            catch(SQLException f) { System.out.println("No 重複"); } }
                            new PlayerGUI(name);
                            frame.dispose();
                        }
                    }
                    else {
                        JOptionPane.showConfirmDialog(null,"帳號或密碼錯誤"," " , JOptionPane.PLAIN_MESSAGE);
                    }
                }
                catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
            else if(buttonName.equals("註冊")){
                MysqlJdbc use = new MysqlJdbc();
                use.start();
            }
        }
    }
    public JFrame getFrame() {
        return this.frame;
    }
}
