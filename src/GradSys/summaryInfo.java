package GradSys;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

//ͳ��¼ȡѧ����Ϣ
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class summaryInfo extends JPanel implements ActionListener {

	String major_num;
	JTextArea jarea;

	JPanel partOne;
	JTextField jt;
	JButton enter = new JButton("ȷ��");
	JButton cancel = new JButton("ȡ��");
	Box v1;
	Box v2;
	Box h1;
	Box baseBox;

	JPanel partTwo = new JPanel();
	String[] s = { "����", "����", "�Ա�", "��Դ", "�����ܷ�", "�����ܷ�" };
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jtable = null;
	DefaultTableModel defaultMode;// �������
	Statement sta = null;

	summaryInfo() {
		// ����������
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// ��������
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

		v1.add(new JLabel("רҵ��:"));
		v1.add(Box.createHorizontalStrut(5));
		v1.add(enter);
		v2.add(jt);
		v2.add(cancel);
		h1.add(v1);
		h1.add(v2);
		partOne.add(h1);

		// �������
		defaultMode = new DefaultTableModel(getRowData(), columnName);
		jtable = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jtable);
		partTwo.add(js);
		partTwo.setBorder(new TitledBorder("¼ȡ������"));
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

	// �ӱ��л�ȡ���ݣ� ���ӷ��ؽ������
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
				// ��һ��Ԫ������ݷŵ���������
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
			// �������
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
					jarea.append("רҵ���ƣ�" + rs.getString(1) + "\n");
					jarea.append("�ƻ�¼ȡ������" + rs.getInt(3) + "\n");
					jarea.append("ʵ��¼ȡ������" + rs.getInt(2) + "\n");
				}
			} catch (SQLException a) {
				a.printStackTrace();
			}
			Utility.closeConnection();// ��������
		}
	}
}
