package GradSys;

import java.awt.*;
import javax.swing.*;

//系统主页
public class mainPage extends JPanel{

	mainPage(){
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("欢迎使用研究生管理系统!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(label);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new mainPage());
		f.setVisible(true);

	}

}
