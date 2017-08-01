package GradSys;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

//�ľ�ҳ��
public class FirExamOutScore extends JPanel implements ActionListener{
	boolean state = false;//�ľ�״̬
	
	JButton jb = new JButton("��ʼ�ľ�");
	JTextArea jt = new JTextArea(8, 40);
	JPanel partHigh = new JPanel();
	JPanel partLow = new JPanel();
	
	Box b = Box.createVerticalBox();
	FirExamOutScore(){
		jt.setEditable(false);
		//���ü�����
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
			//���ô洢��������ó��Գɼ�
			try {
				if (state){
					throw new WorkFinshed("���Ըľ����Ѿ����");
				}
				CallableStatement c = Utility.createConnection().prepareCall("{call out_fir_score()}");
				c.execute();
				jt.setText("�ľ��Ѿ����!");
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
