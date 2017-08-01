package GradSys;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.*;

class Login extends JDialog implements ActionListener{
	JTextField[] jt;
	String[] s = { "�˺�:", "����:" };
	boolean state = false;

	JButton enter = new JButton("ȷ��");
	JButton cancel = new JButton("ȡ��");
	JPanel loginPanel = new JPanel();
	
	Login(JFrame owner, String title, boolean modal){
		
		super(owner, title, modal);
		jt = new JTextField[2];
		
		
		//������
		enter.addActionListener(this);
		
		this.setLocation(400, 200);
		this.setSize(400, 400);
		this.setVisible(false);
	}
	
	void refersh(){
		loginPanel.removeAll();
		for (int i=0; i<2; i++){
			jt[i] = new JTextField(10);
		}
		GridBagLayout layout = new GridBagLayout();
		loginPanel.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();
		
		loginPanel.add(new JLabel(s[0]), Utility.createConstrain(constraints, 0, 0, 1, 1));
		loginPanel.add(jt[0], Utility.createConstrain(constraints, 1, 0, 1, 1));
		loginPanel.add(new JLabel(s[1]), Utility.createConstrain(constraints, 0, 1, 1, 1));
		loginPanel.add(jt[1], Utility.createConstrain(constraints, 1, 1, 1, 1));
		loginPanel.add(enter, Utility.createConstrainAnchor(constraints, 0, 2, 1, 1, GridBagConstraints.WEST));
		loginPanel.add(cancel, Utility.createConstrainAnchor(constraints, 1, 2, 1, 1, GridBagConstraints.EAST));
		
		this.setContentPane(loginPanel);
	}
	public boolean getState() {
		
		return state;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enter) {
			if (jt[0].getText().equals("zoucheng") && jt[1].getText().equals("123")) {
				state = true;
				this.setVisible(false);
				System.out.print("benshen");
			} else {
				JOptionPane.showMessageDialog(this, "�˺Ż��������,����������!");
				jt[0].setText("");
				jt[1].setText("");
			}
		}
	}
	public static void main(String[] args) {
	}
}


