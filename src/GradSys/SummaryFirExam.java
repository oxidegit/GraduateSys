package GradSys;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

//ͳ�Ƴ��Կ�Ŀ�����Ϣ
public class SummaryFirExam extends JPanel implements ItemListener, ActionListener {

	String name = "math";
	JPanel partOne = new JPanel();
	Vector subName = new Vector();
	Box v1 = Box.createVerticalBox();
	Box v2 = Box.createVerticalBox();
	Box h1 = Box.createHorizontalBox();
	JTextField majorNum = new JTextField(10);
	JComboBox subject;
	JButton enter = new JButton("ȷ��");
	JButton cancle = new JButton("ȡ��");

	JPanel partTwo = new JPanel();
	JTextArea output = new JTextArea(6, 8);

	Box baseBox = Box.createVerticalBox();

	SummaryFirExam() {
		output.setEditable(false);

		subName.add("math");
		subName.add("english");
		subName.add("politics");
		subName.add("major_score");
		subject = new JComboBox(subName);
		subject.addItemListener(this);
		enter.addActionListener(this);

		v1.add(new JLabel("רҵ��:"));
		v1.add(Box.createHorizontalStrut(5));
		v1.add(new JLabel("ѧ������:"));
		v1.add(Box.createHorizontalStrut(7));
		v1.add(enter);
		v2.add(majorNum);
		v2.add(subject);
		v2.add(cancle);
		h1.add(v1);
		h1.add(v2);
		partOne.add(h1);

		partTwo.setLayout(new BorderLayout());
		partTwo.add(output);

		baseBox.add(partOne);
		baseBox.add(partTwo);
		this.setLayout(new BorderLayout());
		this.add(baseBox);

	}

	void refersh() {
		majorNum.setText("");
		output.setText("");
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new SummaryFirExam());
		f.setVisible(true);

	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			System.out.print(name = e.getItem().toString());
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enter) {
			output.setText("");
			show(output, majorNum.getText(), name);
		}
	}

	// ��ָ������Ϣ���ı�������ʾ����
	void show(JTextArea t, String number, String nameRea) {
		double lowline = 0, highline = 0;
		int pass = 0, count = 0;
		int countHigh = 0, countCenter = 0, countLow = 0;
		double sum = 0, average = 0, score = 0;
		int name = 0;

		try {
			System.out.print(number);
			int a = Integer.parseInt(number);
			if (nameRea.equals("politics")) {
				name = 1;
			} else if (nameRea.equals("english")) {
				name = 2;
			} else if (nameRea.equals("math")) {
				name = 3;
			} else if (nameRea.equals("major_score")) {
				name = 4;
			}

			Connection con = Utility.createConnection();

			CallableStatement c;
			c = con.prepareCall("{call getMajorLine(?, ?, ?)}");
			c.setString(1, number);
			c.registerOutParameter(2, Types.DOUBLE);
			c.registerOutParameter(3, Types.DOUBLE);
			c.execute();
			lowline = c.getDouble(2);
			highline = c.getDouble(3);
			System.out.print(lowline);
			System.out.print(highline);

			Statement sta = Utility.getSta();

			ResultSet rs = sta.executeQuery(
					"select a.politics, a.english,a.math,  a.major_score from first_exam_score a, enroll_student b where a.sno=b.sno and b.major =  '"
							+ number + "'");
			rs.last();
			count = rs.getRow();
			rs.beforeFirst();
			while (rs.next()) {
				score = rs.getDouble(name);
				if (name == 1 || name == 2) {
					if (score >= 80) {
						countHigh++;
					} else if (score >= 60 && score < 80) {
						countCenter++;
					} else {
						countLow++;
					}
					if (score >= lowline) {
						pass += 1;
					}
				} else {
					if (score >= 120) {
						countHigh++;
					} else if (score >= 90 && score < 120) {
						countCenter++;
					} else {
						countLow++;
					}
					if (score >= highline) {
						pass += 1;
					}
				}

				sum += score;
			}
			double passPossible = pass / count;
			t.append("������רҵ��������" + count + "\n");
			t.append("���ж���" + nameRea + "ѧ��\n");
			t.append("ͨ���ʣ�" + passPossible + "\n");
			t.append("ƽ���֣�" + sum / count + "\n");
			t.append("�߷ֶΣ�" + countHigh + "��\n");
			t.append("�еȷֶΣ�" + countCenter + "��\n");
			t.append("�ͷֶΣ�" + countLow + "��\n");

			Utility.closeConnection();
		} catch (NumberFormatException a) {
			JOptionPane.showMessageDialog(this, "����ȷ����רҵ��");
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ArithmeticException a){
			JOptionPane.showMessageDialog(this, "�޴�רҵ,��˶�רҵ��");
		}
	}

}
