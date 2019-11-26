package project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class OrderTableList extends JFrame {

	private JPanel contentPane;
	private JTextField tfNum;
	private JTextField tfOffice;
	private JTextField tfTradename;
	private JTextField tfCompany;
	private JTextField tfSal;
	private JTextField tfAmount;
	private JTextField tfSearch;
	private JTable table;
	private JButton btnDelete;
	private JButton btnChange;
	private JButton btnSearch;
	
	private OrderTableDAO dao;
	private OrderTableDTO dto;
	private Vector<String> col;
	private DefaultTableModel model;
	private JLabel lblNewLabel;
	private JButton btnProadd;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderTableList frame = new OrderTableList();
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
	public OrderTableList() {
		setTitle("주문 목록");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 217, 532, 335);
		contentPane.add(scrollPane);
		
		dao = new OrderTableDAO();
		col = new Vector<String>();
		col.add("제품번호");
		col.add("판매점");
		col.add("제품명");
		col.add("제품회사");
		col.add("판매가격");
		col.add("구입수량");
		col.add("총 판매가격");
		list();
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = table.getSelectedRow();
				tfNum.setEditable(false);
				tfNum.setText(table.getValueAt(idx, 0)+"");
				tfOffice.setText(table.getValueAt(idx, 1)+"");
				tfTradename.setText(table.getValueAt(idx, 2)+"");
				tfCompany.setText(table.getValueAt(idx, 3)+"");
				tfSal.setText(table.getValueAt(idx, 4)+"");
				tfAmount.setText(table.getValueAt(idx, 5)+"");			
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNum = new JLabel("제품번호");
		lblNum.setBounds(12, 10, 57, 15);
		contentPane.add(lblNum);
		
		tfNum = new JTextField();
		tfNum.setBounds(81, 7, 199, 21);
		contentPane.add(tfNum);
		tfNum.setColumns(10);
		
		JLabel lblOffice = new JLabel("판매점");
		lblOffice.setBounds(12, 41, 57, 15);
		contentPane.add(lblOffice);
		
		tfOffice = new JTextField();
		tfOffice.setBounds(81, 38, 199, 21);
		contentPane.add(tfOffice);
		tfOffice.setColumns(10);
		
		JLabel lblTradename = new JLabel("제품명");
		lblTradename.setBounds(12, 72, 57, 15);
		contentPane.add(lblTradename);
		
		tfTradename = new JTextField();
		tfTradename.setBounds(81, 69, 199, 21);
		contentPane.add(tfTradename);
		tfTradename.setColumns(10);
		
		JLabel lblCompany = new JLabel("제품회사");
		lblCompany.setBounds(12, 103, 57, 15);
		contentPane.add(lblCompany);
		
		tfCompany = new JTextField();
		tfCompany.setBounds(81, 100, 199, 21);
		contentPane.add(tfCompany);
		tfCompany.setColumns(10);
		
		JLabel lblSal = new JLabel("판매가격");
		lblSal.setBounds(12, 134, 57, 15);
		contentPane.add(lblSal);
		
		tfSal = new JTextField();
		tfSal.setBounds(81, 131, 199, 21);
		contentPane.add(tfSal);
		tfSal.setColumns(10);
		
		JLabel lblAmount = new JLabel("구입수량");
		lblAmount.setBounds(12, 165, 57, 15);
		contentPane.add(lblAmount);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(81, 162, 199, 21);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
		btnDelete = new JButton("주문 취소");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = tfNum.getText();
				int result = 0;
				int response = JOptionPane.showConfirmDialog(
						OrderTableList.this, "주문을 취소 하시겠습니까?.");
				if(response==JOptionPane.YES_OPTION) {
					result = dao.deleteOrderTable(num);
				}
				if(result==1) {
					JOptionPane.showMessageDialog(
							OrderTableList.this, "주문이 취소 되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		
		JButton btnAdd = new JButton("주문");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result = dao.insertOrderTable(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(OrderTableList.this, "주문이 완료 되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnAdd.setBounds(368, 6, 114, 37);
		contentPane.add(btnAdd);
		btnDelete.setBounds(567, 217, 97, 59);
		contentPane.add(btnDelete);
		
		btnChange = new JButton("주문 변경");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result = dao.updateOrderTable(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(
							OrderTableList.this, "주문 내용이 변경 되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnChange.setBounds(368, 53, 114, 37);
		contentPane.add(btnChange);
		
		JLabel lblSearch = new JLabel("제품 번호를 입력하세요 :");
		lblSearch.setBounds(311, 117, 148, 15);
		contentPane.add(lblSearch);
		
		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		tfSearch.setColumns(10);
		tfSearch.setBounds(477, 114, 199, 21);
		contentPane.add(tfSearch);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(399, 143, 133, 37);
		contentPane.add(btnSearch);
		
		lblNewLabel = new JLabel("(가격단위 : 만)");
		lblNewLabel.setBounds(455, 192, 89, 15);
		contentPane.add(lblNewLabel);
		
		btnProadd = new JButton("재고확인");
		btnProadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderproAdd frm = new OrderproAdd(OrderTableList.this);
				frm.setVisible(true);//프레임을 화면에 표시
				frm.setLocation(200, 200); //프레임을 표시할 좌표 지정
			}
		});
		btnProadd.setBounds(494, 6, 114, 37);
		contentPane.add(btnProadd);
	}
	
	public void search() {
		String num = tfSearch.getText();
		model = new DefaultTableModel(dao.searchOrderTable(num), col) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table.setModel(model);
	}
	
	public void input() {
		String num = tfNum.getText();
		String office = tfOffice.getText();
		String tradename = tfTradename.getText();
		String company = tfCompany.getText();
		int sal = Integer.parseInt(tfSal.getText());
		int amount = Integer.parseInt(tfAmount.getText());
		dto = new OrderTableDTO(num, office, tradename, company, sal, amount);
	}
	
	public void clear() {
		tfNum.setText("");
		tfOffice.setText("");
		tfTradename.setText("");
		tfCompany.setText("");
		tfSal.setText("");
		tfAmount.setText("");
		tfNum.requestFocus();
		tfNum.setEditable(true);	
	}
	
	public void list() {
		model = new DefaultTableModel(dao.listOrderTable(), col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
}
