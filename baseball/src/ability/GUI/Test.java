package ability.GUI;
import javax.swing.*;
public class Test {
	public static void main(String[] args) {
		/*PlayerDataGUI gui3 = new PlayerDataGUI("官廷洋");
		gui3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui3.setBounds(100,100,380,295);
		gui3.setVisible(true);
		*/
		PlayerDataGUI gui = new PlayerDataGUI("test");
		JFrame frame = gui.getFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100,100,800,500);
        frame.setVisible(true);
	}
}
