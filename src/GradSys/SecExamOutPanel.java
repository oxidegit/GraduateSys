package GradSys;

import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

//�����о�

public class SecExamOutPanel extends FirExamOutScore{
	JButton button = new JButton("��ʼ����");
	boolean state = false;
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	SecExamOutPanel(){
		
		//���ü�����
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
			//���ô洢��������ó��Գɼ�
			try {
				if (state){
					throw new WorkFinshed("���Ըľ����Ѿ����");
				}
				CallableStatement c = Utility.createConnection().prepareCall("{call out_sec_score()}");
				c.execute();
				jt.setText("�ľ����!");
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
