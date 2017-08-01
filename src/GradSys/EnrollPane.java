package GradSys;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;

//����ѧ�����ϱ�������
public class EnrollPane extends JPanel{
	JTextField[] jt = new JTextField[10]; 
	String[] s = {"����:", "����:", "�Ա�:", "����:", "������ò:", "�Ƿ�Ӧ��:", "ѧ��:", "��Դ:", "����רҵ:", "�������:"};;
	JButton enter = new JButton("�ύ");
	JButton cancel = new JButton("ȡ��");
	
	EnrollPane(){
	}
	
	void refresh(){
		
		this.removeAll();
		GridBagLayout lay=new GridBagLayout();
		setLayout(lay);
		GridBagConstraints constraints = new GridBagConstraints();
		setListener(this);
		
		for (int i=0; i<10; i++){
			jt[i] = new JTextField(10);
			this.add(new JLabel(s[i]), Utility.createConstrainInset(constraints, 0, i, 1, 1,GridBagConstraints.WEST, new Insets(0, 0, 0, 8)));
			this.add(jt[i], Utility.createConstrainAnchor(constraints, 1, i, 1, 1, GridBagConstraints.EAST));
		}
		
		this.add(enter, Utility.createConstrainInset(constraints, 0, 10, 1, 1, GridBagConstraints.WEST, new Insets(13, 0, 0, 0)));
		this.add(cancel, Utility.createConstrainInset(constraints, 1, 10, 1, 1, GridBagConstraints.EAST, new Insets(13, 0, 0, 0)));
	}
	
	//Ϊ������������ü�����
	void setListener(final JPanel p){
		//Ϊȷ����ťע�������
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Vector v = getEnrollInfo();
				StudentInfo s = new StudentInfo();
				try {
					s.insertTable(v);
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(p, "����ĳЩѡ���Ƿ�����Ƿ��ַ���");
				}catch(SQLException e1){
					JOptionPane.showMessageDialog(p, "���󣺸�ѧ���Ѿ�����!");
				} catch (InputNullException e1) {
					JOptionPane.showMessageDialog(p, e1.getMessage());
				}		
			}	
		});
		
	}
	
	
	//�����Ѿ�ע��ѧ������Ϣ
	Vector getEnrollInfo(){
		Vector v = new Vector();
		
		for (int i=0; i<10; i++){
			v.add(jt[i].getText());
		}
		return v;	
	}
	
	
}
