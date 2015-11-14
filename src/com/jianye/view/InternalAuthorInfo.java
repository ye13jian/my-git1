package com.jianye.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;

public class InternalAuthorInfo extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalAuthorInfo frame = new InternalAuthorInfo();
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
	public InternalAuthorInfo() {
		getContentPane().setBackground(new Color(51, 255, 204));
		
		JLabel label = new JLabel("叶健");
		label.setFont(new Font("方正舒体", Font.BOLD, 74));
		label.setIcon(new ImageIcon(InternalAuthorInfo.class.getResource("/images/userName.png")));
		getContentPane().add(label, BorderLayout.CENTER);
		setIconifiable(true);
		setClosable(true);
		setTitle("关于作者");
		setBounds(100, 100, 450, 300);

	}

}
