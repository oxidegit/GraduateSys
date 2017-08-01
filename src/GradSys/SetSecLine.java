package GradSys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

//设定复试分数线
public class SetSecLine extends JPanel implements ActionListener {
	Box v1, v2, baseBox;
	String[] s = { "专业代号:", "复试分数线" };
	JTextField[] jt = new JTextField[2];
	JPanel part1 = new JPanel();
	JButton enter = new JButton("确认设置");
	JButton cancel = new JButton("取消设置");

	SetSecLine() {
		enter.addActionListener(this);

		v1 = Box.createVerticalBox();
		v2 = Box.createVerticalBox();
		baseBox = Box.createHorizontalBox();
		for (int i = 0; i < 2; i++) {
			jt[i] = new JTextField(6);
			v1.add(new JLabel(s[i]));
			v1.add(Box.createVerticalStrut(4));
			v2.add(jt[i]);
		}
		v1.add(enter);
		v2.add(cancel);
		baseBox.add(v1);
		baseBox.add(v2);
		part1.add(baseBox);
		this.add(part1);
	}

	void refresh() {
		jt[0].setText("");
		jt[1].setText("");
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new SetSecLine());
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enter) {
			try {
				String number = jt[0].getText();
				int Line = Integer.parseInt(jt[1].getText());

				String sql = "update school_major_info set second_exam_line = " + Line + " where major_number = '"
						+ number + "'";
				Utility.createConnection();
				Connection conn = Utility.getCon();

				Statement sta = conn.createStatement();
				int i = sta.executeUpdate(sql);
				if (i == 0) {
					JOptionPane.showMessageDialog(this, "更新失败,请检查输入是否正确！");
				} else {
					JOptionPane.showMessageDialog(this, "更新成功");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}catch (NumberFormatException a){
				JOptionPane.showMessageDialog(this, "含非法字符");
			}

			Utility.closeConnection();

		}
	}
}
