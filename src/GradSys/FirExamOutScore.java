package GradSys;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

//改卷页面
public class FirExamOutScore extends JPanel implements ActionListener{
	boolean state = false;//改卷状态
	
	JButton jb = new JButton("开始改卷");
	JTextArea jt = new JTextArea(8, 40);
	JPanel partHigh = new JPanel();
	JPanel partLow = new JPanel();
	
	Box b = Box.createVerticalBox();
	FirExamOutScore(){
		jt.setEditable(false);
		//设置监听器
		jb.addActionListener(this);
		
		partHigh.add(jb);
		partLow.add(jt);
		b.add(partHigh);
		b.add(partLow);
		this.setLayout(new BorderLayout());
		this.add(b);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new FirExamOutScore());
		f.setVisible(true);
	}
	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	void refresh(){
		jt.setText("");
	}
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == jb){
			//调用存储过程来获得初试成绩
			try {
				if (state){
					throw new WorkFinshed("初试改卷工作已经完成");
				}
				CallableStatement c = Utility.createConnection().prepareCall("{call out_fir_score()}");
				c.execute();
				jt.setText("改卷已经完成!");
				state = true;
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (WorkFinshed e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage());
			}
			Utility.closeConnection();		
		}
	}

}
