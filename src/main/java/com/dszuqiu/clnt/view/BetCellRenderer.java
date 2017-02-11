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

		// 添加按钮。
		JTextField jf = new JTextField("投注额");
		// 设置按钮的大小及位置。
		jf.setBounds(0, 0, 60, 25);
		// 设置按钮的大小及位置。
		this.button.setBounds(60, 0, 60, 25);
		this.panel.add(jf);
		this.panel.add(this.button);
	}

	private void initButton() {
		this.button = new JButton("下注");
		// 设置按钮的大小及位置。
//		this.button.setBounds(0, 0, 100, 50);
	}

	private void initPanel() {
		this.panel = new JPanel();

		// panel使用绝对定位，这样button就不会充满整个单元格。
		this.panel.setLayout(null);
//		this.panel.setLayout(new FlowLayout());
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// 只为按钮赋值即可。也可以作其它操作，如绘背景等。
//		this.button.setText(value == null ? "" : String.valueOf(value));

		return this.panel;
	}

}
