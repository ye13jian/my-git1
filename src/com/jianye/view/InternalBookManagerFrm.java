package com.jianye.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jianye.dao.BookTypeDao;
import com.jianye.model.BookType;
import com.jianye.utils.DbUtil;
import com.jianye.utils.StringUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InternalBookManagerFrm extends JInternalFrame {
	
	private JTable bookTypeTable;
	
	private DbUtil dbUtil = new DbUtil();
	
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private JTextField bookTypeNameTxt;
	private JTextField idTxt;
	private JTextField uBookTypeNameTxt;
	private JTextArea uBookTypeDescTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalBookManagerFrm frame = new InternalBookManagerFrm();
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
	public InternalBookManagerFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书分类管理");
		setBounds(100, 100, 580, 517);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("图书类别名称：");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(InternalBookManagerFrm.class.getResource("/images/search.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(button)
							.addGap(83))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane, Alignment.LEADING)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("编号：");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("图书类别名称：");
		
		uBookTypeNameTxt = new JTextField();
		uBookTypeNameTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("描述：");
		
		uBookTypeDescTxt = new JTextArea();
		
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBookTypeActionPerformer(e);
			}
		});
		button_1.setIcon(new ImageIcon(InternalBookManagerFrm.class.getResource("/images/modify.png")));
		
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 删除图书种类
				deleteBookTypeActionPerformed(e);
			}
		});
		button_2.setIcon(new ImageIcon(InternalBookManagerFrm.class.getResource("/images/delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(uBookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(uBookTypeDescTxt)))
					.addContainerGap(32, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(80)
					.addComponent(button_1)
					.addGap(53)
					.addComponent(button_2)
					.addContainerGap(140, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(uBookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addComponent(uBookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1)
						.addComponent(button_2))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeSelectMousePressed(e);
			}
		});
		bookTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u7C7B\u522B\u540D\u79F0", "\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(91);
		bookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(143);
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);
		
		// 设置文本域线条
		uBookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		
		// 添加表格数据
		initBookTypeTable(new BookType());
	}
	
	/**
	 * 删除图书类型
	 * @param event
	 */
	private void deleteBookTypeActionPerformed(ActionEvent event) {
		String id = idTxt.getText();
		if (StringUtils.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择图书类型！");
			return;
		} 
		int result = JOptionPane.showConfirmDialog(null, "确认删除吗？");
		if (result == 0) {
			Connection conn = null;
			try {
				conn = dbUtil.getConn();
				boolean flag = bookTypeDao.existBookType(conn, id);
				if (flag) {
					JOptionPane.showMessageDialog(null, "此类别下拥有图书不能删除！");
					return;
				}
				int deleteResult = bookTypeDao.deleteBookType(conn, id);
				if (deleteResult >= 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					resetValue();
					initBookTypeTable(new BookType());
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
	 * 修改基本数据
	 * @param e
	 */
	private void updateBookTypeActionPerformer(ActionEvent e) {
		String id = idTxt.getText();
		String bookTypeName = uBookTypeNameTxt.getText();
		String bookTypeDesc = uBookTypeDescTxt.getText();
		if (StringUtils.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的图书类型！");
			return;
		}
		
		if (StringUtils.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "请填写图书类型名称！");
			return;
		}
		
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			BookType bookType = new BookType();
			bookType.setId(Integer.parseInt(id));
			bookType.setBookTypeDesc(bookTypeDesc);
			bookType.setBookTypeName(bookTypeName);
			int result = bookTypeDao.updateBookType(conn, bookType);
			if (result >= 1) {
				JOptionPane.showMessageDialog(null, "更新图书类型成功！");
				resetValue();
				initBookTypeTable(new BookType());
			} else {
				JOptionPane.showMessageDialog(null, "更新失败！");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "更新失败！");
		} finally {
			try {
				dbUtil.closeCon(conn);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	/**
	 * 鼠标选中操作
	 * @param event
	 */
	private void bookTypeSelectMousePressed(MouseEvent event) {
		int row = bookTypeTable.getSelectedRow();
		idTxt.setText((String)bookTypeTable.getValueAt(row, 0));
		uBookTypeNameTxt.setText((String)bookTypeTable.getValueAt(row, 1));
		uBookTypeDescTxt.setText((String)bookTypeTable.getValueAt(row, 2));
	}

	/**
	 * 查询图书类型
	 * @param event 事件类型
	 */
	private void bookTypeSearchActionPerformed(ActionEvent event) {
		String bookTypeName = bookTypeNameTxt.getText();
		BookType bookType = new BookType();
		bookType.setBookTypeName(bookTypeName);
		initBookTypeTable(bookType);
	}

	/**
	 * 初始化table表格
	 */
	private void initBookTypeTable(BookType bookType) {
		DefaultTableModel dtm = (DefaultTableModel) bookTypeTable.getModel();
		dtm.setRowCount(0);
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			ResultSet rs = bookTypeDao.list(conn, bookType);
			while (rs.next()) {
				Vector v = new Vector<>();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
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
	
	/**
	 * 重置表单信息
	 */
	private void resetValue() {
		this.bookTypeNameTxt.setText("");
		this.idTxt.setText("");
		this.uBookTypeDescTxt.setText("");
		this.uBookTypeNameTxt.setText("");
	}
}
