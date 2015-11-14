package com.jianye.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.jianye.dao.BookTypeDao;
import com.jianye.model.BookType;
import com.jianye.utils.DbUtil;
import com.jianye.utils.StringUtils;

public class InternalBookTypeAddFrm extends JInternalFrame {
	private JTextField bookTypeNameTxt;
	
	private JTextArea bookTypeDescTxt;
	
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private DbUtil dbUtil = new DbUtil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalBookTypeAddFrm frame = new InternalBookTypeAddFrm();
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
	public InternalBookTypeAddFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书类别添加");
		setBounds(100, 100, 450, 300);
		
		JLabel label = new JLabel("图书类别名称：");
		
		JLabel label_1 = new JLabel("图书类别描述：");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		bookTypeDescTxt = new JTextArea();
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBookTypePerformAction(e);
			}
		});
		button.setIcon(new ImageIcon(InternalBookTypeAddFrm.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 重置图书类型
				resetBookTypeAddValue(e);
			}
		});
		button_1.setIcon(new ImageIcon(InternalBookTypeAddFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bookTypeDescTxt))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(82, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(72)
					.addComponent(button)
					.addGap(80)
					.addComponent(button_1)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		// 设置文本框内容
		bookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
	}

	/**
	 * 添加图书类型
	 * @param event 事件类型
	 */
	private void addBookTypePerformAction(ActionEvent event) {
		String bookTypeName = this.bookTypeNameTxt.getText();
		String bookTypeDesc = this.bookTypeDescTxt.getText();
		if (StringUtils.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空！");
			return;
		}
		BookType bookType = new BookType();
		bookType.setBookTypeName(bookTypeName);
		bookType.setBookTypeDesc(bookTypeDesc);
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			
			int n_bookNum = bookTypeDao.add(conn, bookType);
			if (n_bookNum == 1) {
				JOptionPane.showMessageDialog(null, "图书类别添加成功！");
				resetValue();
			} else {
				JOptionPane.showMessageDialog(null, "图书类别添加失败！");
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "图书类别添加失败！");
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
	 * 重置添加内容
	 * @param event
	 */
	private void resetBookTypeAddValue(ActionEvent event) {
		resetValue();
	}

	private void resetValue() {
		this.bookTypeDescTxt.setText("");
		this.bookTypeNameTxt.setText("");
	}
}
