import javax.swing.*;
import java.awt.*;

public class TopScreen extends JFrame{
	private Container cntnr;
	public JButton lendBtn, returnBtn, serchBtn, stopBtn;
	TopScreen(){
		super("Top���");
		super.setUndecorated(true);
		super.setResizable(false);
	}
	public void topPre(TopScreen ts){
		//�t���X�N���[��
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(ts);
		//�z�u
		cntnr = getContentPane();
		cntnr.setLayout(new GridLayout(2, 2));
		lendBtn = new JButton("�{���؂��");
		lendBtn.setMargin(new Insets(10, 10, 10, 10));
		returnBtn = new JButton("�{��ԋp����");
		serchBtn = new JButton("�{����������");
		stopBtn = new JButton("�I������");
		cntnr.add(lendBtn);
		cntnr.add(returnBtn);
		cntnr.add(serchBtn);
		cntnr.add(stopBtn);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lendBtn.addActionListener(new TopEvent());
		returnBtn.addActionListener(new TopEvent());
		serchBtn.addActionListener(new TopEvent());
		stopBtn.addActionListener(new TopEvent());
	}
}