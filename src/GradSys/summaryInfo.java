package GradSys;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

//统计录取学生信息
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class summaryInfo extends JPanel implements ActionListener {

	String major_num;
	JTextArea jarea;

	JPanel partOne;
	JTextField jt;
	JButton enter = new JButton("确认");
	JButton cancel = new JButton("取消");
	Box v1;
	Box v2;
	Box h1;
	Box baseBox;

	JPanel partTwo = new JPanel();
	String[] s = { "考号", "姓名", "性别", "来源", "初试总分", "复试总分" };
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jtable = null;
	DefaultTableModel defaultMode;// 创建表格
	Statement sta = null;

	summaryInfo() {
		// 构造列容器
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// 输入列名
		}
		enter.addActionListener(this);

	}

	void refresh() {
		this.removeAll();
		partOne = new JPanel();
		partTwo = new JPanel();
		v1 = Box.createVerticalBox();
		v2 = Box.createVerticalBox();
		h1 = Box.createHorizontalBox();
		baseBox = Box.createVerticalBox();

		jt = new JTextField(10);
		jarea = new JTextArea(6, 8);
		jarea.setEditable(false);

		v1.add(new JLabel("专业号:"));
		v1.add(Box.createHorizontalStrut(5));
		v1.add(enter);
		v2.add(jt);
		v2.add(cancel);
		h1.add(v1);
		h1.add(v2);
		partOne.add(h1);

		// 创建表格
		defaultMode = new DefaultTableModel(getRowData(), columnName);
		jtable = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jtable);
		partTwo.add(js);
		partTwo.setBorder(new TitledBorder("录取名单："));
		partTwo.revalidate();

		baseBox.add(partOne);
		baseBox.add(partTwo);
		baseBox.add(jarea);
		this.setLayout(new BorderLayout());
		this.add(baseBox);

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new summaryInfo());
		f.setVisible(true);
	}

	// 从表中获取数据， 并从返回结果容器
	Vector getRowData() {
		Vector row = new Vector();
		Vector rowChild = null;
		Utility.createConnection();
		sta = Utility.getSta();
		// row.clear();

		String sql = "select sno, name, sex, source, sum_fir_score, sum_sec_score from succeed_student where major = '"
				+ jt.getText() + "'";
		try {
			CallableStatement c = Utility.getCon().prepareCall("{call confirm_succeed_student()}");
			c.execute();
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				// 将一个元组的数据放到容器里面
				rowChild = new Vector();
				rowChild.add(rs.getString(1));
				rowChild.add(rs.getString(2));
				rowChild.add(rs.getString(3));
				rowChild.add(rs.getString(4));
				for (int i = 5; i <= 6; i++) {
					rowChild.add(rs.getDouble(i));
				}
				System.out.print("hahah");
				row.add(rowChild);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return row;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enter) {
			major_num = jt.getText();
			System.out.print(major_num);
			// 创建表格
			partTwo.removeAll();
			defaultMode = new DefaultTableModel(getRowData(), columnName);
			jtable = new JTable(defaultMode);
			JScrollPane js = new JScrollPane(jtable);
			partTwo.add(js);
			this.validate();
			Utility.createConnection();
			try {
				rs = sta.executeQuery(
						"select major_name, reality_count, plan_count from school_major_info b where b.major_number='"
								+ major_num + "'");
				while (rs.next()) {
					jarea.append("专业名称：" + rs.getString(1) + "\n");
					jarea.append("计划录取人数：" + rs.getInt(3) + "\n");
					jarea.append("实际录取人数：" + rs.getInt(2) + "\n");
				}
			} catch (SQLException a) {
				a.printStackTrace();
			}
			Utility.closeConnection();// 结束连接
		}
	}
}
