package GradSys;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

//������Ա�Ĺ���ѡ��ҳ��
public class ManagerPanel extends JPanel implements TreeSelectionListener {
	JSplitPane splitPanel = new JSplitPane();
	JPanel partLeft = new JPanel();
	JPanel partRight = new JPanel();
	JTree tree = null;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("����ѡ��");
	DefaultMutableTreeNode firstExam = new DefaultMutableTreeNode("���Թ���");
	DefaultMutableTreeNode firOutScore = new DefaultMutableTreeNode("���Ըľ�");
	DefaultMutableTreeNode passStudent = new DefaultMutableTreeNode("ͨ��ѧ��");
	DefaultMutableTreeNode summary = new DefaultMutableTreeNode("����ͳ��");
	DefaultMutableTreeNode secExam = new DefaultMutableTreeNode("���Թ���");
	DefaultMutableTreeNode secSetLine = new DefaultMutableTreeNode("�趨������");
	DefaultMutableTreeNode secOutScore = new DefaultMutableTreeNode("���Ըľ�");
	DefaultMutableTreeNode count = new DefaultMutableTreeNode("¼ȡ���");

	JTextArea welcome = new JTextArea();
	CardLayout card = new CardLayout();
	// �����
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

		// ע�������
		tree.addTreeSelectionListener(this);
		// �Ҳ�
		partRight.setLayout(card);
		// ��ӿ�Ƭ

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

	// �����¼�
	public void valueChanged(TreeSelectionEvent e) {
		if (e.getSource() == tree) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (node.isLeaf()) {
				String str = node.toString();
				if (str.equals("���Ըľ�")) {
					firOut.refresh();
					card.show(partRight, "firOut");
				} else if (str.equals("ͨ��ѧ��")) {
					if (firOut.getState()) {
						card.show(partRight, "passStu");
					} else {
						JOptionPane.showMessageDialog(this, "���Ըľ���δ��ʼ");
					}
				} else if (str.equals("���Ըľ�")) {
					if (firOut.getState()) {
						card.show(partRight, "secOut");
					} else {
						JOptionPane.showMessageDialog(this, "���Ըľ���δ��ʼ");
					}

				} else if (str.equals("�趨������")) {
					if (firOut.getState() && secOut.getState()) {
						setLine.refresh();
						card.show(partRight, "setLine");
					} else {
						if (!firOut.getState()){
							JOptionPane.showMessageDialog(this, "���Ըľ���δ��ʼ");
						}else{
							JOptionPane.showMessageDialog(this, "���Ըľ���δ��ʼ");
						}
					}

					
				} else if (str.equals("����ͳ��")) {
					if (firOut.getState()) {
						summaryFir.refersh();
						card.show(partRight, "summaryFir");
					} else {
						JOptionPane.showMessageDialog(this, "���Ըľ���δ��ʼ");
					}
				} else if (str.equals("¼ȡ���")) {
					if (firOut.getState() && secOut.getState()) {
						summaryStudent.refresh();
						card.show(partRight, "summaryStudent");
					} else {
						if (!firOut.getState()){
							JOptionPane.showMessageDialog(this, "���Ըľ���δ��ʼ");
						}else{
							JOptionPane.showMessageDialog(this, "���Ըľ���δ��ʼ");
						}
					}

				}
			}
		}
	}
}
