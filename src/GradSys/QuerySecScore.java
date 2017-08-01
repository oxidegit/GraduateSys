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

//��ѯ���Գɼ�
public class QuerySecScore extends JPanel {
	JTextField jtext;
	JButton query = new JButton("���Բ�ѯ");
	// ���������
	JPanel partOne = new JPanel();
	JPanel partTwo = new JPanel();
	// ������
	String[] s = { "ѧ��", "����", "רҵ��", "�ϻ�", "����" };
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode;// �������
	Statement sta = null;
	// ��ʽ����

	QuerySecScore() {
		// ����������
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// ��������
		}
	}

	// �ӱ��л�ȡ���ݣ� ���ӷ��ؽ������
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
					// ��һ��Ԫ������ݷŵ���������
					rowChild = new Vector();
					rowChild.add(rs.getString(1));
					rowChild.add(rs.getString(2));
					for (int i = 3; i <= 5; i++) {
						rowChild.add(rs.getDouble(i));
					}
					row.add(rowChild);
				}
				else{
					throw new ResultSetNullException("δ�鵽�����Ϣ��");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Utility.closeConnection();// ��������
		return row;
	}

	void setListener(final QuerySecScore p) {
		query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ԭ����¼
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
		JLabel label1 = new JLabel("����ѧ�ţ�");
		GridBagLayout layout = new GridBagLayout();
		partOne.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();

		partOne.add(label1, Utility.createConstrain(constraints, 0, 0, 1, 1));
		partOne.add(jtext, Utility.createConstrain(constraints, 1, 0, 1, 1));
		partOne.add(query, Utility.createConstrainAnchor(constraints, 2, 0, 1, 1, GridBagConstraints.CENTER));

		// �������
		defaultMode = new DefaultTableModel(columnName, 0);
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		partTwo.add(js);

		Box boxV = Box.createVerticalBox();
		boxV.add(partOne);
		boxV.add(partTwo);
		// ���ü�����
		setListener(this);
		this.add(boxV);
	}
}
