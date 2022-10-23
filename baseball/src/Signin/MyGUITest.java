package Signin;

import javax.swing.*;
public class MyGUITest {//登入介面
    public static void main(String[] args) {
        MyGUI gui = new MyGUI();
        JFrame frame = gui.getFrame();
        frame.setBounds(100,100,380,295);
        frame.setVisible(true);
    }
}
