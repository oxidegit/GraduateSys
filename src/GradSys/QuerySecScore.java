package GradSys;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//查询复试成绩
public class QuerySecScore extends JPanel {
	JTextField jtext;
	JButton query = new JButton("复试查询");
	// 两部分面板
	JPanel partOne = new JPanel();
	JPanel partTwo = new JPanel();
	// 表格相关
	String[] s = { "学号", "姓名", "专业课", "上机", "面试" };
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode;// 创建表格
	Statement sta = null;
	// 盒式布局

	QuerySecScore() {
		// 构造列容器
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// 输入列名
		}
	}

	// 从表中获取数据， 并从返回结果容器
	Vector getRowData(String number) throws ResultSetNullException {
		Vector row = new Vector();
		Vector rowChild = null;
		Utility.createConnection();
		sta = Utility.getSta();
		// row.clear();
		if (number != null) {
			String sql = "select * from second_exam_score where sno = '" + number + "'";
			try {
				rs = sta.executeQuery(sql);
				if (rs.next()) {
					// 将一个元组的数据放到容器里面
					rowChild = new Vector();
					rowChild.add(rs.getString(1));
					rowChild.add(rs.getString(2));
					for (int i = 3; i <= 5; i++) {
						rowChild.add(rs.getDouble(i));
					}
					row.add(rowChild);
				}
				else{
					throw new ResultSetNullException("未查到相关信息！");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Utility.closeConnection();// 结束连接
		return row;
	}

	void setListener(final QuerySecScore p) {
		query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 清空原来记录
				int count = p.defaultMode.getRowCount();
				if (count > 0) {
					p.defaultMode.removeRow(--count);
				}
				p.defaultMode.setRowCount(count);
				String sno = p.jtext.getText();
				Vector v;
				try {
					v = p.getRowData(sno);
					for (int i = 0; i < v.size(); i++) {
					p.defaultMode.addRow((Vector) v.get(i));
				}
				} catch (ResultSetNullException e1) {
					JOptionPane.showMessageDialog(p, e1.getMessage());
					
				}
				
				p.jtext.revalidate();
				p.setVisible(true);
			}
		});
	}

	public void refresh() {
		partOne.removeAll();
		partTwo.removeAll();
		this.removeAll();
		jtext = new JTextField(10);
		JLabel label1 = new JLabel("输入学号：");
		GridBagLayout layout = new GridBagLayout();
		partOne.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();

		partOne.add(label1, Utility.createConstrain(constraints, 0, 0, 1, 1));
		partOne.add(jtext, Utility.createConstrain(constraints, 1, 0, 1, 1));
		partOne.add(query, Utility.createConstrainAnchor(constraints, 2, 0, 1, 1, GridBagConstraints.CENTER));

		// 创建表格
		defaultMode = new DefaultTableModel(columnName, 0);
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		partTwo.add(js);

		Box boxV = Box.createVerticalBox();
		boxV.add(partOne);
		boxV.add(partTwo);
		// 设置监听器
		setListener(this);
		this.add(boxV);
	}
}
