package com.bright.updchat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuiChat extends JFrame {
	
	public GuiChat() throws HeadlessException {
		super();
		setUpUI();
		initSocket();
		setListener();
	}

	private static final int DEFAULT_PORT = 8899;
	// 把主窗体分为north,center,south三部分
	DatagramSocket datagramSoket;// 用于后面功能的实现
	private JLabel stateLB;
	private JTextArea centerTextArea;
	private JPanel southPanel;
	private JTextArea inputTextArea;
	private JPanel bottomPanel;
	private JTextField ipTextField;
	private JTextField remotePortTF;
	private JButton sendBT;
	private JButton clearBT;

	private void setUpUI() {
		setTitle("GUI聊天");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		// 窗口的north部分
		stateLB = new JLabel("当前还未启动监听");
		stateLB.setHorizontalAlignment(JLabel.RIGHT);
		// 窗口的center部分
		centerTextArea = new JTextArea();// 聊天记录显示区域
		centerTextArea.setEditable(false);
		centerTextArea.setBackground(new Color(211, 211, 211));
		// 窗口的south部分
		southPanel = new JPanel(new BorderLayout());
		inputTextArea = new JTextArea(5, 20);// 内容输入区域
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		ipTextField = new JTextField("127.0.0.1", 8);
		remotePortTF = new JTextField(String.valueOf(DEFAULT_PORT), 3);
		sendBT = new JButton("发送");
		clearBT = new JButton("清屏");
		bottomPanel.add(ipTextField);
		bottomPanel.add(remotePortTF);
		bottomPanel.add(sendBT);
		bottomPanel.add(clearBT);
		southPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
		southPanel.add(bottomPanel, BorderLayout.SOUTH);
		// 添加north,center,south部分的组件
		add(stateLB, BorderLayout.NORTH);
		add(new JScrollPane(centerTextArea), BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	private void setListener() {
		// 为sendBT按钮添加监听事件
		sendBT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取发送的目标ip和端口号
				String ipAddress = ipTextField.getText();
				String remotePort = remotePortTF.getText();
				// 判断ip地址和端口号是否为空
				if (ipAddress == null || ipAddress.trim().equals("")
						|| remotePort == null || remotePort.trim().equals("")) {
					JOptionPane.showMessageDialog(GuiChat.this, "监听不成功");
					return;
				}
				// 获得需要发送的内容
				String sendContent = inputTextArea.getText();
				byte[] buf = sendContent.getBytes();
				centerTextArea.append("我对" + ipAddress + ":" + remotePort
						+ "说\n" + inputTextArea.getText() + "\n\n");
				// 添加滚动条后,滚动条自动浮动到底部
				centerTextArea.setCaretPosition(centerTextArea.getText()
						.length());
				// 发送数据
				try {
					datagramSoket.send(new DatagramPacket(buf, buf.length,
							InetAddress.getByName(ipAddress), Integer
									.parseInt(remotePort)));
					inputTextArea.setText("");

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(GuiChat.this, "出错了,发送不成功");
					e1.printStackTrace();
				}
			}
		});

		// 为clearBT添加监听
		clearBT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 清空聊天记录的内容
				centerTextArea.setText("");
			}
		});
	}

	private void initSocket() {
		int port = DEFAULT_PORT;
		while (true) {
			if (datagramSoket != null && datagramSoket.isClosed()) {
				datagramSoket.close();
			}
			port = Integer.parseInt(JOptionPane.showInputDialog(this, "请输入端口号",
					"端口号", JOptionPane.QUESTION_MESSAGE));
			if (port < 1 || port > 65535) {
				throw new RuntimeException("端口号超出范围..");
			}
			// 启动datagramSoket
			try {
				datagramSoket = new DatagramSocket(port);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			startListen();
			// 在startLB中显示程序监听的端口号
			stateLB.setText("已在" + port + "端口监听");
			break;
		}
	}

	private void startListen() {
		// TODO Auto-generated method stub
		new Thread() {
			DatagramPacket p;
			public void run() {
				byte[] buf = new byte[1024];
				p = new DatagramPacket(buf,buf.length);
				while (!datagramSoket.isClosed()) {
					try {
						datagramSoket.receive(p);//接收聊天消息
						//添加到聊天记录框
						centerTextArea.append(p.getAddress().getHostAddress()+":"
								+((InetSocketAddress)p.getSocketAddress()).getPort()
								+"对我说:\n"+
								new String(p.getData(),0,p.getLength())+"\n\n");
						//使 滚动条滚动到底部
						centerTextArea.setCaretPosition(centerTextArea.getText().length());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
}
