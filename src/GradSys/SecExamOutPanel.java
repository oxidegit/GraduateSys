package GradSys;

import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

//复试判卷

public class SecExamOutPanel extends FirExamOutScore{
	JButton button = new JButton("开始批卷");
	boolean state = false;
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	SecExamOutPanel(){
		
		//设置监听器
		button.addActionListener(this);
		
		this.jb.setVisible(false);
		partHigh.add(button);
		partLow.add(jt);
		b.add(partHigh);
		b.add(partLow);
		this.add(b);
	}
	public void actionPerformed(ActionEvent e) {
		System.out.print("dfas");
		if (e.getSource() == button){
			//调用存储过程来获得初试成绩
			try {
				if (state){
					throw new WorkFinshed("复试改卷工作已经完成");
				}
				CallableStatement c = Utility.createConnection().prepareCall("{call out_sec_score()}");
				c.execute();
				jt.setText("改卷完成!");
				state = true;
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (WorkFinshed e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			Utility.closeConnection();		
		}
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new SecExamOutPanel());
		f.setVisible(true);
	}
}
