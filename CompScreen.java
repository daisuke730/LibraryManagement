import javax.swing.*;
import java.awt.*;

public class CompScreen extends JFrame{
	private Container cntnr;
	private JButton button;
	private JLabel label;
	CompScreen(){
		super("�������");
		super.setUndecorated(true);
		super.setResizable(false);
	}
	public void compPre(CompScreen cs){
		//FullScreen
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(cs);
		cntnr = getContentPane();
		cntnr.setLayout(new FlowLayout());
		label = new JLabel("�������������܂����B");
		label.setFont(new Font("Ariai",Font.PLAIN, 28)); 
        button = new JButton("TOP��ʂ�");
		//button.addActionListener(new CompEvent());
        cntnr.add(label);
        cntnr.add(button);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}