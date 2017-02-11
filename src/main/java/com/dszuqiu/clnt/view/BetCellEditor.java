package com.dszuqiu.clnt.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * �Զ���һ�����������Ӱ�ť�ĵ�Ԫ��༭������ü̳�DefaultCellEditor����ȻҪʵ�ֵķ�����̫���ˡ�
 * 
 */
public class BetCellEditor extends DefaultCellEditor {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6546334664166791132L;

	private JPanel panel;

	private JButton button;

	public BetCellEditor() {
		// DefautlCellEditor�д˹���������Ҫ����һ�������������ʹ�õ���ֱ��newһ�����ɡ�
		super(new JTextField());

		// ���õ�����μ���༭��
		this.setClickCountToStart(1);

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

		// Ϊ��ť����¼�������ֻ�����ActionListner�¼���Mouse�¼���Ч��
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����ȡ���༭���¼����������tableModel��setValue������
				BetCellEditor.this.fireEditingCanceled();

				// �������������������
				// ���Խ�table���룬ͨ��getSelectedRow,getSelectColumn������ȡ����ǰѡ����к��м����������ȡ�
			}
		});

	}

	private void initPanel() {
		this.panel = new JPanel();

		// panelʹ�þ��Զ�λ������button�Ͳ������������Ԫ��
		this.panel.setLayout(null);
//		this.panel.setLayout(new FlowLayout());
	}

	/**
	 * ������д����ı༭����������һ��JPanel���󼴿ɣ�Ҳ����ֱ�ӷ���һ��Button���󣬵��������������������Ԫ��
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// ֻΪ��ť��ֵ���ɡ�Ҳ����������������
//		this.button.setText(value == null ? "" : String.valueOf(value));

		return this.panel;
	}

	/**
	 * ��д�༭��Ԫ��ʱ��ȡ��ֵ���������д��������ܻ�Ϊ��ť���ô����ֵ��
	 */
	@Override
	public Object getCellEditorValue() {
		return this.button.getText();
	}

}
