package GradSys;

import java.awt.*;
import javax.swing.*;

//ϵͳ��ҳ
public class mainPage extends JPanel{

	mainPage(){
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("��ӭʹ���о�������ϵͳ!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(label);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new mainPage());
		f.setVisible(true);

	}

}
