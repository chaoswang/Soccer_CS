package com.dszuqiu.clnt.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * �Զ���һ�����������Ӱ�ť�ĵ�Ԫ��༭������ü̳�DefaultCellEditor����ȻҪʵ�ֵķ�����̫���ˡ�
 * 
 */
public class OddsCellEditor extends DefaultCellEditor {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6546334664166791132L;

	private JPanel panel;

	private JButton button;
	
	private JLabel orderOdds;

	public OddsCellEditor(JLabel orderOdds) {
		// DefautlCellEditor�д˹���������Ҫ����һ�������������ʹ�õ���ֱ��newһ�����ɡ�
		super(new JTextField());

		this.orderOdds = orderOdds;
		// ���õ�����μ���༭��
		this.setClickCountToStart(1);

		this.initButton();

		this.initPanel();

		// ��Ӱ�ť��
		this.panel.add(this.button);
	}

	private void initButton() {
		this.button = new JButton();

		// ���ð�ť�Ĵ�С��λ�á�
		this.button.setBounds(0, 0, 60, 25);

		// Ϊ��ť����¼�������ֻ�����ActionListner�¼���Mouse�¼���Ч��
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����ȡ���༭���¼����������tableModel��setValue������
				OddsCellEditor.this.fireEditingCanceled();

				// �������������������
				// ���Խ�table���룬ͨ��getSelectedRow,getSelectColumn������ȡ����ǰѡ����к��м����������ȡ�
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						orderOdds.setText("@" + button.getText());
						orderOdds.updateUI();
					}
				});
			}
		});

	}

	private void initPanel() {
		this.panel = new JPanel();

		// panelʹ�þ��Զ�λ������button�Ͳ������������Ԫ��
		this.panel.setLayout(null);
	}

	/**
	 * ������д����ı༭����������һ��JPanel���󼴿ɣ�Ҳ����ֱ�ӷ���һ��Button���󣬵��������������������Ԫ��
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// ֻΪ��ť��ֵ���ɡ�Ҳ����������������
		this.button.setText(value == null ? "" : String.valueOf(value));

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
