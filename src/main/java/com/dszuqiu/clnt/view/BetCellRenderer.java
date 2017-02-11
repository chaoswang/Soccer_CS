package com.dszuqiu.clnt.view;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class BetCellRenderer implements TableCellRenderer {
	private JPanel panel;
	private JButton button;

	public BetCellRenderer() {
		this.initButton();
		this.initPanel();

		// ��Ӱ�ť��
		JTextField jf = new JTextField("Ͷע��");
		// ���ð�ť�Ĵ�С��λ�á�
		jf.setBounds(0, 0, 60, 25);
		// ���ð�ť�Ĵ�С��λ�á�
		this.button.setBounds(60, 0, 60, 25);
		this.panel.add(jf);
		this.panel.add(this.button);
	}

	private void initButton() {
		this.button = new JButton("��ע");
		// ���ð�ť�Ĵ�С��λ�á�
//		this.button.setBounds(0, 0, 100, 50);
	}

	private void initPanel() {
		this.panel = new JPanel();

		// panelʹ�þ��Զ�λ������button�Ͳ������������Ԫ��
		this.panel.setLayout(null);
//		this.panel.setLayout(new FlowLayout());
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// ֻΪ��ť��ֵ���ɡ�Ҳ������������������汳���ȡ�
//		this.button.setText(value == null ? "" : String.valueOf(value));

		return this.panel;
	}

}
