package GradSys;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;

//这是学生网上报名窗口
public class EnrollPane extends JPanel{
	JTextField[] jt = new JTextField[10]; 
	String[] s = {"考号:", "姓名:", "性别:", "年龄:", "政治面貌:", "是否应届:", "学历:", "来源:", "报考专业:", "报考类别:"};;
	JButton enter = new JButton("提交");
	JButton cancel = new JButton("取消");
	
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
	
	//为面板里的组件设置监听器
	void setListener(final JPanel p){
		//为确定按钮注册监听器
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Vector v = getEnrollInfo();
				StudentInfo s = new StudentInfo();
				try {
					s.insertTable(v);
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(p, "错误：某些选项是否含输入非法字符！");
				}catch(SQLException e1){
					JOptionPane.showMessageDialog(p, "错误：该学号已经报名!");
				} catch (InputNullException e1) {
					JOptionPane.showMessageDialog(p, e1.getMessage());
				}		
			}	
		});
		
	}
	
	
	//返回已经注册学生的信息
	Vector getEnrollInfo(){
		Vector v = new Vector();
		
		for (int i=0; i<10; i++){
			v.add(jt[i].getText());
		}
		return v;	
	}
	
	
}
