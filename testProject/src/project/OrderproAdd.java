package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OrderproAdd extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfTradename;
	private JTextField tfAmount;
	
	private OrderproDAO dao;
	private OrderproDTO dto;
	private Vector<String> col;
	private DefaultTableModel model;
	private OrderTableList frm;
	private JButton btnAdd;
	private JTextField tfTotal;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderproAdd frame = new OrderproAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderproAdd(OrderTableList frm) {
		this();
		this.frm = frm;
	}
	
	public OrderproAdd() {
		setTitle("남은재고");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 363, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 116, 323, 261);
		contentPane.add(scrollPane);
		
		dao = new OrderproDAO();
		col = new Vector<String>();
		col.add("제품명");
		col.add("전체 재고");
		col.add("주문 후 남은재고");
		list();
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = table.getSelectedRow();
				tfTradename.setEditable(false);
				tfTradename.setText(table.getValueAt(idx, 0)+"");
				tfAmount.setText(table.getValueAt(idx, 1)+"");
				tfTotal.setText(table.getValueAt(idx, 2)+"");
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("제품명");
		lblNewLabel_2.setBounds(12, 10, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		tfTradename = new JTextField();
		tfTradename.setBounds(81, 7, 116, 21);
		contentPane.add(tfTradename);
		tfTradename.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("전체 재고");
		lblNewLabel_5.setBounds(12, 41, 57, 15);
		contentPane.add(lblNewLabel_5);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(81, 38, 116, 21);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
		label = new JLabel("주문 후 남은수량");
		label.setBounds(12, 82, 100, 15);
		contentPane.add(label);
		
		tfTotal = new JTextField();
		tfTotal.setBounds(135, 79, 154, 21);
		contentPane.add(tfTotal);
		tfTotal.setColumns(10);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result = dao.insertOrderpro(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(OrderproAdd.this, "재고가 추가되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnAdd.setBounds(220, 6, 100, 50);
		contentPane.add(btnAdd);
	}
	
	
	public void input() {
		String tradename = tfTradename.getText();
		int amount = Integer.parseInt(tfAmount.getText());
		dto = new OrderproDTO(tradename, amount);
	}
	
	public void clear() {
		tfTradename.setText("");
		tfAmount.setText("");
		tfTotal.setText("");
		tfTradename.requestFocus();
		tfTradename.setEditable(true);	
	}
	
	public void list() {
		model = new DefaultTableModel(dao.listOrderpro(), col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
}
