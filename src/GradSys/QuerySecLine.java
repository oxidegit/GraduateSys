package GradSys;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//��ѯ���Է�����
public class QuerySecLine extends JPanel {
	String[] s = { "רҵ����", "רҵ����", "������" };
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode;// �������
	Statement sta = null;

	QuerySecLine() {
		// ����������
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// ��������
		}

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new QuerySecLine());
		f.setVisible(true);
	}

	// ˢ��
	void refresh() {
		// �������
		this.removeAll();
		defaultMode = new DefaultTableModel(getRowData(), columnName);
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		this.add(js);
	}

	// �ӱ��л�ȡ���ݣ� ���ӷ��ؽ������
	Vector getRowData() {
		Vector row = new Vector();
		Vector rowChild = null;
		Utility.createConnection();
		sta = Utility.getSta();
		// row.clear();

		String sql = "select * from school_major_info";
		try {
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				// ��һ��Ԫ������ݷŵ���������
				rowChild = new Vector();
				rowChild.add(rs.getString(1));
				rowChild.add(rs.getString(2));

				rowChild.add(rs.getDouble(6));

				row.add(rowChild);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Utility.closeConnection();// ��������
		return row;
	}
}
