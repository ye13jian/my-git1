package com.jianye.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.jianye.dao.BookDao;
import com.jianye.dao.BookTypeDao;
import com.jianye.model.Book;
import com.jianye.model.BookType;
import com.jianye.utils.DbUtil;
import com.jianye.utils.StringUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InternalBookAddFrm extends JInternalFrame {
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField priceTxt;
	private JRadioButton manJrb;
	private JRadioButton femalJrb;
	private JTextArea bookDescTxt;
	private JComboBox bookTypeJcb;
	
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();
	private BookTypeDao bookTypeDao = new BookTypeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalBookAddFrm frame = new InternalBookAddFrm();
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
	public InternalBookAddFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书添加");
		setBounds(100, 100, 497, 487);
		
		JLabel label = new JLabel("图书名称：");
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("图书作者：");
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("作者性别：");
		
		manJrb = new JRadioButton("男");
		buttonGroup.add(manJrb);
		
		femalJrb = new JRadioButton("女");
		buttonGroup.add(femalJrb);
		
		JLabel label_3 = new JLabel("图书价格：");
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("图书描述：");
		
		bookDescTxt = new JTextArea();
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 保存当前图书
				bookAddActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(InternalBookAddFrm.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 重置值
				resetBookAddValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(InternalBookAddFrm.class.getResource("/images/reset.png")));
		
		JLabel label_5 = new JLabel("图书类别：");
		
		bookTypeJcb = new JComboBox();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(156, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(100)
					.addComponent(button_1)
					.addGap(111))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookDescTxt))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_2)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(manJrb)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(femalJrb)))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_1)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_3)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(priceTxt)))))
							.addContainerGap(29, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(manJrb)
						.addComponent(femalJrb)
						.addComponent(label_3)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(92)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button))
					.addGap(34))
		);
		getContentPane().setLayout(groupLayout);
		// 设置文本框域
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		// 设置初始化
		initBookTypeCommobox();
	}
	
	/**
	 * 重置值的事件
	 * @param event 事件
	 */
	private void resetBookAddValueActionPerformed(ActionEvent event) {
		resetValue();
	}

	/**
	 * 图书添加
	 * @param event 事件
	 */
	private void bookAddActionPerformed(ActionEvent event) {
		String bookName = bookNameTxt.getText();
		String author = authorTxt.getText();
		String price = priceTxt.getText();
		String bookDesc = bookDescTxt.getText();
		
		if (StringUtils.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(null, "图书名称不能为空");
			return;
		}
		if (StringUtils.isEmpty(price)) {
			JOptionPane.showMessageDialog(null, "价格不能为空");
			return;
		}
		if (StringUtils.isEmpty(author)) {
			JOptionPane.showMessageDialog(null, "作者不能为空");
			return;
		}
		
		// 获取性别
		String sex = "";
		if (manJrb.isSelected()) {
			sex = "男";
		} else {
			sex = "女";
		}
		// 获取图书类型编码
		BookType bookType = (BookType) bookTypeJcb.getSelectedItem();
		int bookTypeId = bookType.getId();
		
		// 封装参数
		Book book = new Book();
		book.setAuthor(author);
		book.setBookDesc(bookDesc);
		book.setBookName(bookName);
		book.setBookTypeId(bookTypeId);
		book.setSex(sex);
		book.setPrice(Float.parseFloat(price));
		
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			int result = bookDao.add(conn, book);
			if (result >= 1) {
				JOptionPane.showMessageDialog(null, "保存成功!");
				resetValue();
			} else {
				JOptionPane.showMessageDialog(null, "保存失败!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "保存失败!");
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
	 * 重置值
	 */
	private void resetValue() {
		bookNameTxt.setText("");
		authorTxt.setText("");
		priceTxt.setText("");
		manJrb.setSelected(true);
		bookDescTxt.setText("");
		if (bookTypeJcb.getItemCount() > 0) {
			bookTypeJcb.setSelectedIndex(0);
		}
	}

	/**
	 * 初始化Commobox
	 */
	private void initBookTypeCommobox() {
		Connection conn = null;
		BookType bookType = null;
		try {
			conn = dbUtil.getConn();
			ResultSet rs = bookTypeDao.list(conn, new BookType());
			while (rs.next()) {
				bookType = new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookTypeJcb.addItem(bookType);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
