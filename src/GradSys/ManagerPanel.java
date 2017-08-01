package GradSys;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

//管理人员的功能选择页面
public class ManagerPanel extends JPanel implements TreeSelectionListener {
	JSplitPane splitPanel = new JSplitPane();
	JPanel partLeft = new JPanel();
	JPanel partRight = new JPanel();
	JTree tree = null;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("功能选择");
	DefaultMutableTreeNode firstExam = new DefaultMutableTreeNode("初试管理");
	DefaultMutableTreeNode firOutScore = new DefaultMutableTreeNode("初试改卷");
	DefaultMutableTreeNode passStudent = new DefaultMutableTreeNode("通过学生");
	DefaultMutableTreeNode summary = new DefaultMutableTreeNode("初试统计");
	DefaultMutableTreeNode secExam = new DefaultMutableTreeNode("复试管理");
	DefaultMutableTreeNode secSetLine = new DefaultMutableTreeNode("设定分数线");
	DefaultMutableTreeNode secOutScore = new DefaultMutableTreeNode("复试改卷");
	DefaultMutableTreeNode count = new DefaultMutableTreeNode("录取情况");

	JTextArea welcome = new JTextArea();
	CardLayout card = new CardLayout();
	// 子面板
	summaryInfo summaryStudent = new summaryInfo();
	FirExamOutScore firOut = new FirExamOutScore();
	passStudent passStu = new passStudent();
	SecExamOutPanel secOut = new SecExamOutPanel();
	SetSecLine setLine = new SetSecLine();
	SummaryFirExam summaryFir = new SummaryFirExam();

	void creatTree() {
		root.add(firstExam);
		firstExam.add(firOutScore);
		firstExam.add(passStudent);
		firstExam.add(summary);
		root.add(secExam);
		secExam.add(secOutScore);
		secExam.add(secSetLine);
		root.add(count);
		tree = new JTree(root);
	}

	ManagerPanel() {

		creatTree();
		partLeft.setLayout(new BorderLayout());
		JScrollPane leftScroolPane = new JScrollPane();
		leftScroolPane.setViewportView(tree);
		partLeft.add(leftScroolPane, BorderLayout.CENTER);
		splitPanel.setDividerLocation(100);
		splitPanel.setLeftComponent(partLeft);

		// 注册监听器
		tree.addTreeSelectionListener(this);
		// 右侧
		partRight.setLayout(card);
		// 添加卡片

		partRight.add(welcome, "welcome");
		partRight.add(firOut, "firOut");
		partRight.add(passStu, "passStu");
		partRight.add(secOut, "secOut");
		partRight.add(setLine, "setLine");
		partRight.add(summaryFir, "summaryFir");
		partRight.add(summaryStudent, "summaryStudent");
		splitPanel.setRightComponent(partRight);

		this.setLayout(new BorderLayout());
		this.add(splitPanel);
	}

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.add(new ManagerPanel());
		f.setVisible(true);
	}

	// 监听事件
	public void valueChanged(TreeSelectionEvent e) {
		if (e.getSource() == tree) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (node.isLeaf()) {
				String str = node.toString();
				if (str.equals("初试改卷")) {
					firOut.refresh();
					card.show(partRight, "firOut");
				} else if (str.equals("通过学生")) {
					if (firOut.getState()) {
						card.show(partRight, "passStu");
					} else {
						JOptionPane.showMessageDialog(this, "初试改卷尚未开始");
					}
				} else if (str.equals("复试改卷")) {
					if (firOut.getState()) {
						card.show(partRight, "secOut");
					} else {
						JOptionPane.showMessageDialog(this, "初试改卷尚未开始");
					}

				} else if (str.equals("设定分数线")) {
					if (firOut.getState() && secOut.getState()) {
						setLine.refresh();
						card.show(partRight, "setLine");
					} else {
						if (!firOut.getState()){
							JOptionPane.showMessageDialog(this, "初试改卷尚未开始");
						}else{
							JOptionPane.showMessageDialog(this, "复试改卷尚未开始");
						}
					}

					
				} else if (str.equals("初试统计")) {
					if (firOut.getState()) {
						summaryFir.refersh();
						card.show(partRight, "summaryFir");
					} else {
						JOptionPane.showMessageDialog(this, "初试改卷尚未开始");
					}
				} else if (str.equals("录取情况")) {
					if (firOut.getState() && secOut.getState()) {
						summaryStudent.refresh();
						card.show(partRight, "summaryStudent");
					} else {
						if (!firOut.getState()){
							JOptionPane.showMessageDialog(this, "初试改卷尚未开始");
						}else{
							JOptionPane.showMessageDialog(this, "复试改卷尚未开始");
						}
					}

				}
			}
		}
	}
}
