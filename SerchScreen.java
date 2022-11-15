import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SerchScreen extends JFrame{
	private Container cntnr;
	private JButton topBtn, serchBtn;
	private JLabel modeLbl, nameLbl, categoryLbl, stockLbl;
	private JTextField serchText;
	private JList nameList, categoryList, stockList;
	private JPanel p1, p2, p3, p4;
	
	SerchScreen(){
		super("�������");
		super.setUndecorated(true);
		super.setResizable(false);
	}
	public void serchPre(SerchScreen ss){
		String[] s1 = {"aaaaaaaaaaa","aaaaaaaaaaaaaa","aaaaaaaa","aa","a","aaaaaa","aaa"};
		
		//FullScreen
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(ss);
		//Container
		cntnr = getContentPane();
		cntnr.setLayout(new GridLayout(2, 1));
		//Panel
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p3 = new JPanel(new GridLayout(2, 3));
		p4 = new JPanel(new GridLayout(2, 1));
		//Button
		topBtn = new JButton("Top");
		serchBtn = new JButton("����");
		//Label
		modeLbl = new JLabel("�������[�h");
		nameLbl = new JLabel("�^�C�g��");
		categoryLbl = new JLabel("�J�e�S��");
		stockLbl = new JLabel("�݌�");
		//TextField
		serchText = new JTextField(20);
		//List
		nameList = new JList<String>(s1);
		categoryList = new JList<String>(s1);
		stockList = new JList<String>(s1);
		/*
		topBtn.addActionListener(new serchEvent());
		serchBtn.addActionListener(new serchEvent());
		*/
		p1.add(topBtn);
		p1.add(modeLbl);
		p2.add(serchText);
		p2.add(serchBtn);
		p3.add(nameLbl);
		p3.add(categoryLbl);
		p3.add(stockLbl);
		p3.add(nameList);
		p3.add(categoryList);
		p3.add(stockList);
		p4.add(p1);
		p4.add(p2);
		cntnr.add(p4);
		cntnr.add(p3);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}