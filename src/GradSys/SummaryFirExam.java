package GradSys;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

//统计初试科目相关信息
public class SummaryFirExam extends JPanel implements ItemListener, ActionListener {

	String name = "math";
	JPanel partOne = new JPanel();
	Vector subName = new Vector();
	Box v1 = Box.createVerticalBox();
	Box v2 = Box.createVerticalBox();
	Box h1 = Box.createHorizontalBox();
	JTextField majorNum = new JTextField(10);
	JComboBox subject;
	JButton enter = new JButton("确认");
	JButton cancle = new JButton("取消");

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

		v1.add(new JLabel("专业号:"));
		v1.add(Box.createHorizontalStrut(5));
		v1.add(new JLabel("学科名称:"));
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

	// 将指定的信息在文本区中显示出来
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
			t.append("报考该专业总人数：" + count + "\n");
			t.append("其中对于" + nameRea + "学科\n");
			t.append("通过率：" + passPossible + "\n");
			t.append("平均分：" + sum / count + "\n");
			t.append("高分段：" + countHigh + "人\n");
			t.append("中等分段：" + countCenter + "人\n");
			t.append("低分段：" + countLow + "人\n");

			Utility.closeConnection();
		} catch (NumberFormatException a) {
			JOptionPane.showMessageDialog(this, "请正确输入专业号");
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ArithmeticException a){
			JOptionPane.showMessageDialog(this, "无此专业,请核对专业号");
		}
	}

}
