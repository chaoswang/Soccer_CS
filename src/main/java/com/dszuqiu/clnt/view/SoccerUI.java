package com.dszuqiu.clnt.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import com.dszuqiu.clnt.netty.NettyClientBootstrap;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.common.entity.ServerMatchBean;
import com.dszuqiu.common.utils.DebugLogger;

public class SoccerUI {
	private static final Logger logger = DebugLogger.getLogger(SoccerUI.class);
	private JFrame frame = new JFrame("����168");

	private JTable table = null;
	private SoccerTableModel model = null;

	private JMenuBar menubar = new JMenuBar();
	private JMenu loginMenu = new JMenu("ϵͳ��¼");
	private JMenu ruleManageMenu = new JMenu("�������");
	private JMenu orderManageMenu = new JMenu("��������");
	private JMenu helpMenu = new JMenu("����");
	
	private JToolBar myJToolBar;
	private JButton refreshButton;
	private JLabel orderOdds;
	private JTextField orderMoney;
	private JButton betButton;

	public SoccerUI() {
		frame.setLayout(new GridBagLayout());
		initMenu();
		
		initToolBar();
		
		initMatchTable();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(1280, 600);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void initMenu() {
		JMenuItem userLoginMenu = new JMenuItem("�û���¼");
		userLoginMenu.addActionListener(new LoginActionListener());
		JMenuItem exitLoginMenu = new JMenuItem("�˳�");
		loginMenu.add(userLoginMenu);
		loginMenu.add(exitLoginMenu);
		menubar.add(loginMenu);
		
		JMenuItem ruleMenu = new JMenuItem("��������");
		ruleMenu.addActionListener(new RuleActionListener());
		ruleManageMenu.add(ruleMenu);
		menubar.add(ruleManageMenu);
		
		menubar.add(orderManageMenu);
		menubar.add(helpMenu);
		frame.getContentPane().add(menubar, new GBC(0,0).  
                setFill(GBC.HORIZONTAL).setWeight(0, 0));
	}

	class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame01 = new JFrame();
			frame01.setTitle("�û���¼");
			frame01.setBounds(450, 200, 400, 150);
			Container content01 = frame01.getContentPane();
			JPanel panel = new JPanel();
			JPanel panel01 = new JPanel();
			JPanel panel02 = new JPanel();
			JLabel label01 = new JLabel("�������û���: ");
			JLabel label02 = new JLabel("����������:      ");
			JTextField text01 = new JTextField(20);
			JPasswordField text02 = new JPasswordField(20);
			panel01.add(label01);
			panel01.add(text01);
			panel02.add(label02);
			panel02.add(text02);
			panel.add(panel01);
			panel.add(panel02);
			content01.add(panel, BorderLayout.CENTER);
			// frame01.pack();
			frame01.setVisible(true);
		}
	}
	
	class RuleActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame01 = new JFrame();
			frame01.setTitle("��������");
			Container content01 = frame01.getContentPane();

			JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP); 
                 
            JPanel pane  = new JPanel(); 
            pane.setLayout(new GridBagLayout());
            int i = 0,j = 0;
            for(Rule rule : Rule.values())
            {
            	 pane.add(new JCheckBox(rule.getName()), new GBC(i, j).  
                         setFill(GBC.HORIZONTAL).setWeight(0, 0)); 
            	 i++;
            	 if(i%2 == 0){
            		 i = 0;
            		 j++;
            	 }
            }
                         
            tab.addTab("��С�����", pane); 
			
			content01.add(tab, BorderLayout.CENTER);
			frame01.pack();
			frame01.setVisible(true);
		}
	}
	
	private void initToolBar() {
		myJToolBar = new JToolBar();
		myJToolBar.setFloatable(false);
		{
			refreshButton = new JButton("�ֶ�ˢ��");
			myJToolBar.setLayout(new FlowLayout());
			myJToolBar.add(refreshButton);
//			refreshButton.setPreferredSize(new Dimension(80, 34));
			refreshButton.setIcon(new ImageIcon("res//refresh.png"));
			refreshButton.setFont(new java.awt.Font("����", 0, 14));
			refreshButton.setToolTipText("���ˢ�±����б�");
			refreshButton.addActionListener(new RefreshActionListener(this));
			
			orderOdds = new JLabel(" ���� ");
			myJToolBar.add(orderOdds);
			orderMoney = new JTextField("Ͷע��");
			myJToolBar.add(orderMoney);
			betButton = new JButton("��ע");
			myJToolBar.add(betButton);
			betButton.setIcon(new ImageIcon("res//dollar.png"));
			betButton.setFont(new java.awt.Font("����", 0, 14));
			betButton.setToolTipText("���ģ����ע");
		}
		
		frame.getContentPane().add(myJToolBar,  new GBC(0,1).  
                setFill(GBC.HORIZONTAL).setWeight(0, 0));
	}
	
	class RefreshActionListener implements ActionListener {
		private SoccerUI ui;
		RefreshActionListener(SoccerUI ui){
			this.ui = ui;
		}
		
		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						NettyClientBootstrap client = new NettyClientBootstrap(9999,
								"localhost", ui);
					} catch (InterruptedException ex) {
						logger.error("���ӷ���˳���", ex);
					}
				}
				
			}).start();
		}
	}
	
	private void initMatchTable() {
		model = new SoccerTableModel();
		table = new JTable(model);
		table.getColumnModel().getColumn(7).setCellRenderer(new OddsCellRenderer());
		table.getColumnModel().getColumn(7).setCellEditor(new OddsCellEditor(orderOdds));
		table.getColumnModel().getColumn(9).setCellRenderer(new OddsCellRenderer());
		table.getColumnModel().getColumn(9).setCellEditor(new OddsCellEditor(orderOdds));
		table.getColumnModel().getColumn(11).setCellRenderer(new OddsCellRenderer());
		table.getColumnModel().getColumn(11).setCellEditor(new OddsCellEditor(orderOdds));
		table.getColumnModel().getColumn(13).setCellRenderer(new OddsCellRenderer());
		table.getColumnModel().getColumn(13).setCellEditor(new OddsCellEditor(orderOdds));
		table.getColumnModel().getColumn(15).setCellRenderer(new BetCellRenderer());
		table.getColumnModel().getColumn(15).setCellEditor(new BetCellEditor());
//		table.setSelectionForeground(Color.BLUE);
//		table.setBackground(Color.white);
		table.setFont(new Font("Menu.font", Font.BOLD, 15));
		table.setRowHeight(50);
		// ��ֹ����ѡ���ܡ���Ȼ�ڵ����ťʱ�������ж��ᱻѡ�С�Ҳ����ͨ��������ʽ��ʵ�֡�
		this.table.setRowSelectionAllowed(false);

		TableRowSorter<SoccerTableModel> tableRowSorter = new TableRowSorter<SoccerTableModel>(
				model);
		table.setRowSorter(tableRowSorter);
		TableColumnModel tcm = table.getColumnModel();
		//���ص�һ��
		tcm.getColumn(0).setMinWidth(0);
		tcm.getColumn(0).setMaxWidth(0);
		tcm.getColumn(0).setPreferredWidth(0);
		tcm.getColumn(1).setPreferredWidth(100);
		tcm.getColumn(2).setPreferredWidth(30);
		tcm.getColumn(3).setPreferredWidth(300);
		tcm.getColumn(4).setPreferredWidth(60);
		tcm.getColumn(5).setPreferredWidth(300);
		tcm.getColumn(6).setPreferredWidth(50);
		tcm.getColumn(7).setPreferredWidth(70);
		tcm.getColumn(8).setPreferredWidth(50);
		tcm.getColumn(9).setPreferredWidth(70);
		tcm.getColumn(10).setPreferredWidth(50);
		tcm.getColumn(11).setPreferredWidth(70);
		tcm.getColumn(12).setPreferredWidth(50);
		tcm.getColumn(13).setPreferredWidth(70);
		tcm.getColumn(14).setPreferredWidth(300);
		tcm.getColumn(15).setMinWidth(0);
		tcm.getColumn(15).setMaxWidth(0);
		tcm.getColumn(15).setPreferredWidth(0);
		
		frame.getContentPane().add(new JScrollPane(table), new GBC(0,2).  
                setFill(GBC.BOTH).setWeight(10, 10));
	}

	public void updateData(final List<ServerMatchBean> match_invests) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				model.updateMatches(match_invests);
				table.updateUI();
			}
		});

	}

}
