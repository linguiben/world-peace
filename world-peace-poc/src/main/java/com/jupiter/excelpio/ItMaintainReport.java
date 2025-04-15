package com.jupiter.excelpio;

import com.WriteLog;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ItMaintainReport {
	

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	private String pwd = "";
	
	public String rptCreate() {

		final JFrame frmFrame = new JFrame();
		Panel pnlPanel = new Panel();
		final String usr = "";
		Label lblUsername = new Label("�û���");
		Label lblRptDate = new Label("��������");
		Label lblPassword = new Label("����");
		final TextField txtUsername = new TextField(9);
		final TextField txtPassword = new TextField(9);
		final DateInput dateInput = new DateInput(6);
		final ExcelPIO excelpio = new ExcelPIO();
		
		//frmFrame.setLayout(null);
		
		frmFrame.setResizable(false);
		txtUsername.setText(excelpio.getG_user());
		txtUsername.setEditable(true);
		txtPassword.setEchoChar('*');
		txtPassword.setText(excelpio.getG_pwd());
		Button btnButton1 = new Button("��ʼ");
		Button btnButton3 = new Button("�ر�");
		
		final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -1);
	
		dateInput.setText(df.format(calendar.getTime()));
			
		
		btnButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String dateinput = dateInput.getText();
				if(Integer.parseInt(dateInput.getText()) < 20120731){
					JOptionPane.showMessageDialog(frmFrame, "�޴˱�������");
					return;
				}
				if (!dateinput.equals(df.format(java.sql.Date.valueOf(dateinput
						.substring(0, 4)
						+ "-"
						+ dateinput.substring(4, 6)
						+ "-"
						+ dateinput.substring(6, 8))))) {
					JOptionPane.showMessageDialog(frmFrame, "������8λ��ȷ����");
					return;
				}
				if ((txtPassword.getText()).length() == 0) {
					JOptionPane.showMessageDialog(frmFrame, "����������");
					return;
				}
				if ((txtUsername.getText()).length() == 0) {
					JOptionPane.showMessageDialog(frmFrame, "�û���Ϊ��");
					return;
				}
				//txtPassword.setColumns(16);
				WriteLog.writeFile("�û�" + txtUsername.getText() + "�������ɱ���..,�������ڣ�" + dateInput.getText());
				pwd = txtPassword.getText();
				try {
					WriteLog.writeFile("������ʼ....");
					excelpio.setG_pwd(txtPassword.getText());
					excelpio.setG_user(txtUsername.getText());
					int curRptdate = excelpio.curRptdate();
					if(curRptdate == -1){
						JOptionPane.showMessageDialog(frmFrame, "��������ݿ����Ӵ���");
						WriteLog.writeFile("���ݿ����Ӵ���");
						return;
						
					}
					if(Integer.parseInt(dateInput.getText()) > curRptdate){
						JOptionPane.showMessageDialog(frmFrame, "�޷����ɱ���ԭ��:\n"+dateInput.getText()+ "������δ������ϵͳ��\n��ǰ���µ���������Ϊ:" + curRptdate);
						WriteLog.writeFile("��ǰ��������");
						//dateInput.setText(Integer.toString(curRptdate));
						return;
					}
					int excdate = Integer.parseInt(dateInput.getText());
					excelpio.setRptdtdate(excdate);
					String rptPath = excelpio.genReport(excelpio);
					JOptionPane.showMessageDialog(frmFrame, "OK.�뵽����Ŀ¼��ȡ����\n" + rptPath);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frmFrame, "��ȡ����ʧ��,�����Ի���ϵ����Ա");
					e1.printStackTrace();
					return;
				}
			};
			
		});
		btnButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int windowWidth = frmFrame.getWidth(); // ��ô��ڿ�
		int windowHeight = frmFrame.getHeight(); // ��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		frmFrame.setLocation(screenWidth / 3 - windowWidth / 2, screenHeight
				/ 3 - windowHeight / 2);

		pnlPanel.add(lblUsername);
		pnlPanel.add(txtUsername);
		pnlPanel.add(lblPassword);
		pnlPanel.add(txtPassword);
		pnlPanel.add(lblRptDate);
		//pnlPanel.add(txtRptDate);
		pnlPanel.add(dateInput);
		pnlPanel.add(btnButton1);
		pnlPanel.add(btnButton3);
		
		frmFrame.add(pnlPanel);
		frmFrame.setTitle("������ά����������v1.01");
		frmFrame.pack();
		// frmFrame.show();
		frmFrame.setVisible(true);
		
			
		return pwd;
	}
	public static void main(String args[]) throws Exception {
		ItMaintainReport rpt = new ItMaintainReport();
		rpt.rptCreate();
		
	}
	
}
