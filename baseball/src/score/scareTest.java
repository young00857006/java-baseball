package score;

import javax.swing.*;
import java.util.Scanner;
public class scareTest {
    public static void main(String[] args)
    {
        System.out.print("請輸入模式(1設定,2查閱):");
        int mod = 0;
        Scanner scanner = new Scanner(System.in);
        mod = scanner.nextInt();
        if(mod==1) {
            score s = new score();
            JFrame frame = s.getFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(100, 100, 500, 400);
            frame.setVisible(true);
        }
        else {
            scoreRead a = new scoreRead();
            JFrame frame1 = a.getFrame();
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setBounds(100, 100, 700, 500);
            frame1.setVisible(true);
        }
    }
}
