package GradSys;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
//查询初试成绩时弹出的窗口界面
public class FisrtExamDialog extends JDialog implements ActionListener{
	JTextField[] jt = new JTextField[1];
	JButton query = new JButton("确定");
	JButton cancel = new JButton("取消");
	
	FirstExamPanel firstExamPanel = new FirstExamPanel();
	JPanel contPanel = new JPanel();
	
	FisrtExamDialog(JFrame owner,boolean modal){
		super(owner,"查询系统",modal);
		query.addActionListener(this);
	}
	
	void refersh(){
		contPanel.removeAll();
		JLabel label1 = new JLabel("输入学号：");
		jt[0] = new JTextField(10);
		
		setListener(this);
		this.setContentPane(contPanel);
		JPanel jp = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		jp.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();
		
		jp.add(label1, Utility.createConstrain(constraints, 0, 0, 1, 1));
		jp.add(jt[0], Utility.createConstrain(constraints, 1, 0, 1, 1));
		jp.add(query, Utility.createConstrainAnchor(constraints, 0, 2, 1, 1, GridBagConstraints.WEST));
		jp.add(cancel, Utility.createConstrainAnchor(constraints, 1, 2, 1, 1, GridBagConstraints.EAST));
		
		this.setSize(800, 400);
		this.setLocation(400, 400);
		contPanel.add(jp);
		firstExamPanel.refresh();
		contPanel.add(firstExamPanel);	
	}
	

	void setListener(final FisrtExamDialog theFrame){
		//取消按钮的事件响应
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				theFrame.setVisible(false);
			}
		});
	}
	public void actionPerformed(ActionEvent e){
				try{
					int a = Integer.parseInt(jt[0].getText());
					//清空原来记录
					int count = this.firstExamPanel.defaultMode.getRowCount();
					if (count > 0) {
						this.firstExamPanel.defaultMode.removeRow(--count);
					}
					this.firstExamPanel.defaultMode.setRowCount(count);
					String sno = this.jt[0].getText();
					Vector v = this.firstExamPanel.getRowData(sno);
					for (int i=0; i<v.size(); i++){
						this.firstExamPanel.defaultMode.addRow((Vector) v.get(i));
					}
					this.firstExamPanel.jt.revalidate();
					this.firstExamPanel.setVisible(true);	
				}catch (NumberFormatException a){
					JOptionPane.showMessageDialog(this, "学号格式错误");
				}
				catch(ResultSetNullException b){
					JOptionPane.showMessageDialog(this, b.getMessage());
				}
				
			}
	public static void main(String[] args) {
	
	}
}
