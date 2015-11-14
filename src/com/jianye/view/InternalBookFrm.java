package com.jianye.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jianye.dao.BookDao;
import com.jianye.dao.BookTypeDao;
import com.jianye.model.Book;
import com.jianye.model.BookType;
import com.jianye.utils.DbUtil;
import com.jianye.utils.StringUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InternalBookFrm extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_bookNameTxt;
	private JTextField s_authorTxt;
	private JComboBox s_bookTypeJcb;

	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	
	private JTextField idTxt;
	private JTextField bookNameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton manJrb;
	private JRadioButton femalJrb;
	private JTextField priceTxt;
	private JTextField authorTxt;
	private JComboBox bookTypeJcb;
	private JTextArea bookDescTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalBookFrm frame = new InternalBookFrm();
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
	public InternalBookFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书管理");
		setBounds(100, 100, 800, 606);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		JLabel label_2 = new JLabel("编号：");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("图书名称：");
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("作者性别：");
		
		manJrb = new JRadioButton("男");
		buttonGroup.add(manJrb);
		manJrb.setSelected(true);
		
		femalJrb = new JRadioButton("女");
		buttonGroup.add(femalJrb);
		
		JLabel label_5 = new JLabel("价格：");
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		JLabel label_6 = new JLabel("图书作者：");
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		
		JLabel label_7 = new JLabel("图书类别：");
		
		bookTypeJcb = new JComboBox();
		
		JLabel label_8 = new JLabel("图书描述：");
		
		bookDescTxt = new JTextArea();
		
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookUpdateActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(InternalBookFrm.class.getResource("/images/modify.png")));
		
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookDeleteActionPerformed(e);
			}
		});
		button_2.setIcon(new ImageIcon(InternalBookFrm.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(button_1)
							.addGap(32)
							.addComponent(button_2)
							.addGap(495))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(label_8)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(label_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(idTxt))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(label_5)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(priceTxt, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
								.addGap(18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(label_3)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(label_6)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(authorTxt)))
								.addGap(22)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(label_4)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(manJrb)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(femalJrb))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(label_7)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
								.addGap(74)))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(manJrb)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(femalJrb)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label_5)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_6)
							.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_7)
							.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_8)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button_2))
					.addGap(25))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("图书名称：");
		
		s_bookNameTxt = new JTextField();
		s_bookNameTxt.setColumns(10);
		
		JLabel label = new JLabel("图书作者：");
		
		s_authorTxt = new JTextField();
		s_authorTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("图书类别：");
		
		s_bookTypeJcb = new JComboBox();
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 查询按钮的触发事件
				bookSearchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(InternalBookFrm.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(25))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				bookTableMousePressed(me);
			}
		});
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u4F5C\u8005\u6027\u522B", "\u56FE\u4E66\u4EF7\u683C", "\u56FE\u4E66\u63CF\u8FF0", "\u56FE\u4E66\u7C7B\u522B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(39);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(110);
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);
		// 设置文本框的线条
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));

		// 初始化
		initBookTypeCommbox("search");
		initBookDataTable(new Book());
		initBookTypeCommbox("modify");
	}
	
	/**
	 * 图书删除事件处理
	 * @param event
	 */
	private void bookDeleteActionPerformed(ActionEvent event) {
		String id = idTxt.getText();
		if (StringUtils.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择图书！");
			return;
		} 
		int result = JOptionPane.showConfirmDialog(null, "确认删除吗？");
		if (result == 0) {
			Connection conn = null;
			try {
				conn = dbUtil.getConn();
				int deleteResult = bookDao.deleteBook(conn, id);
				if (deleteResult >= 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					resetValue();
					initBookDataTable(new Book());
				} else {
					JOptionPane.showMessageDialog(null, "删除失败！");
				}
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null, "删除失败！");
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 更新图书信息
	 * @param event 更新图书信息
	 */
	private void bookUpdateActionPerformed(ActionEvent event) {
		String id = idTxt.getText();
		if (StringUtils.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择图书");
			return;
		}
		String bookName = bookNameTxt.getText();
		String author = authorTxt.getText();
		String price = priceTxt.getText();
		String bookDesc = bookDescTxt.getText();
		
		if (StringUtils.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(null, "图书名不能为空！");
			return;
		}
		if (StringUtils.isEmpty(author)) {
			JOptionPane.showMessageDialog(null, "作者不能为空！");
			return;
		}
		if (StringUtils.isEmpty(price)) {
			JOptionPane.showMessageDialog(null, "图书价格不能为空！");
			return;
		}
		String sex = "";
		if (manJrb.isSelected()) {
			sex = "男";
		} else {
			sex = "女";
		}
		BookType bookType = (BookType) bookTypeJcb.getSelectedItem();
		int bookTypeId = bookType.getId();
		
		Book book = new Book();
		book.setAuthor(author);
		book.setBookDesc(bookDesc);
		book.setBookName(bookName);
		book.setBookTypeId(bookTypeId);
		book.setId(Integer.parseInt(id));
		book.setPrice(Float.parseFloat(price));
		book.setSex(sex);
		
		// 保存数据
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			int result = bookDao.updateBook(conn, book);
			if (result >= 1) {
				JOptionPane.showMessageDialog(null, "更新图书信息成功");
				resetValue();
				initBookDataTable(new Book());
			} else {
				JOptionPane.showMessageDialog(null, "更新图书信息失败");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "更新图书信息失败");
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重置所有的value
	 */
	private void resetValue() {
		idTxt.setText("");
		bookNameTxt.setText("");
		authorTxt.setText("");
		priceTxt.setText("");
		priceTxt.setText("");
		manJrb.setSelected(true);
		bookDescTxt.setText("");
		if (bookTypeJcb.getItemCount() > 0) {
			bookTypeJcb.setSelectedIndex(0);
		}
	}

	/**
	 * 表格行点击事件处理
	 * @param me
	 */
	private void bookTableMousePressed(MouseEvent me) {
		int row = bookTable.getSelectedRow();
		// 获取当前行的所有值
		idTxt.setText((String)bookTable.getValueAt(row, 0));
		bookNameTxt.setText((String)bookTable.getValueAt(row, 1));
		authorTxt.setText((String)bookTable.getValueAt(row, 2));
		String sex = (String) bookTable.getValueAt(row, 3);
		if ("男".equals(sex)) {
			manJrb.setSelected(true);
		} else if ("女".equals(sex)) {
			femalJrb.setSelected(true);
		} 
		priceTxt.setText((String) bookTable.getValueAt(row, 4));
		bookDescTxt.setText((String) bookTable.getValueAt(row, 5));
		String bookTypeName = (String) bookTable.getValueAt(row, 6);
		int selectNum = bookTypeJcb.getItemCount();
		for (int i = 0; i < selectNum; ++i) {
			BookType bookType = (BookType) bookTypeJcb.getItemAt(i);
			if (bookType.getBookTypeName().equals(bookTypeName)) {
				bookTypeJcb.setSelectedIndex(i);
				break;
			}
		}
	}

	/**
	 * 图书查询事件处理
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent e) {
		String bookName = s_bookNameTxt.getText();
		String author = s_authorTxt.getText();
		BookType bookType = (BookType) s_bookTypeJcb.getSelectedItem();
		Book book = new Book();
		book.setAuthor(author);
		book.setBookName(bookName);
		book.setBookTypeId(bookType.getId());
		initBookDataTable(book);
	}

	/**
	 * 初始化下拉框
	 * @param type 下拉类型
	 */
	private void initBookTypeCommbox(String type) {
		Connection conn = null;
		BookType bookType = null;
		try {
			conn = dbUtil.getConn();
			ResultSet rs = bookTypeDao.list(conn, new BookType());
			// 查询
			if ("search".equals(type)) {
				bookType = new BookType();
				bookType.setBookTypeName("请选择");
				bookType.setId(-1);
				s_bookTypeJcb.addItem(bookType);
			}
			while (rs.next()) {
				bookType = new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));
				if ("search".equals(type)) {
					s_bookTypeJcb.addItem(bookType);
				} else if("modify".equals(type)) {
					bookTypeJcb.addItem(bookType);
				}
			}
		} catch (Exception e) {
			
		} finally {
			try {
				dbUtil.closeCon(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 初始化表格数据
	 */
	private void initBookDataTable(Book book) {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0);
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			ResultSet rs = bookDao.list(conn, book);
			while (rs.next()) {
				Vector v = new Vector<>();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("sex"));
				v.add(rs.getString("price"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeName"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
