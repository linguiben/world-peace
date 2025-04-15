package com.jupiter.excelpio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import com.POReport;
import com.WriteLog;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelPIO {
	private String rData[][];
	//@SuppressWarnings("unchecked")
	private static List rlist = new ArrayList();


    private int rptdtdate = 0;
	public int getRptdtdate() {
		return rptdtdate;
	}

	public void setRptdtdate(int rptdtdate) {
		this.rptdtdate = rptdtdate;
	}

	private static String g_host;
	private static String g_dbname;
	private static String g_user;
	public String getG_user() {
		return g_user;
	}

	public void setG_user(String gUser) {
		g_user = gUser;
	}

	private static String g_pwd;
	private static String g_port;
	private static String g_dbtype;

	private static String driverName = "";
	private static String url = "";
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	/*
	 * �ж��Ƿ�Ϊ������������double��float
	 * 
	 * @param str ������ַ���
	 * 
	 * @return �Ǹ���������true,���򷵻�false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * ���Ƶ����ļ�
	 * 
	 * @param oldPath
	 *            String ԭ�ļ�·�� �磺c:/fqf.txt
	 * @param newPath
	 *            String ���ƺ�·�� �磺f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // �ļ�����ʱ
				InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // �ֽ��� �ļ���С
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			WriteLog.writeFile("ģ�治���ڻ򴴽�����ʱ����,�˳�����... ");
			e.printStackTrace();
			System.exit(0);
		}

	}

	/*
	 * ��ȡ��ǰ���µı�������
	 */
	public int curRptdate() {
		try {
			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(url, g_user, g_pwd);
		} catch (Exception e) {
			return -1;
		}
		try {
			String SQL = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rr = stmt.executeQuery("select sql from dbo.ui_report where flag = 9999");
			if( rr .next()){
					SQL = rr.getString("SQL");
			}
			CallableStatement stmt = conn.prepareCall(SQL);//"{call dbo.p_test}"
			rs = stmt.executeQuery();// �жϵ�ǰ��������
			WriteLog.writeFile("�ɹ����ã�" + SQL);
			if (rs.next())
				rptdtdate = rs.getInt("rptdate");
			conn.close();
			WriteLog.writeFile("��ǰ��������:" + rptdtdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

////		try {
////			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
////					ResultSet.CONCUR_UPDATABLE);
////			rs = stmt
////					.executeQuery("select busdate,rptdate from dbo.itsm_busdate");// �жϵ�ǰ��������
////			if (rs.next())
////				rptdtdate = rs.getInt("rptdate");
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}

		return rptdtdate;
	}
	
	
	//��ʼ������,ѭ�������ñ�dbo.ui_report�����ݷ���rlist
	public void initialRList(String dbtype, String host, String port,
			String dbname, String username, String pwd) throws Exception {
//			WriteLog.writeFile("��ʼ��ʼ������");
//		    Scanner sc = new Scanner(System.in);
//		    WriteLog.writeFile("����������(�س�Ĭ��������):");
//		    String pwdIn = sc.nextLine();
//		    if(pwdIn.length() == 0) pwdIn = pwd;
			try {
			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(url, username, pwd);
//			while(true){
//				try{
//					conn = DriverManager.getConnection(url, username, pwdIn);
//					break;
//				}catch(Exception e){
//					WriteLog.writeFile("��������ݿ����Ӵ���,��������������:");
//					pwdIn = sc.nextLine();
//				    if(pwdIn.length() == 0) pwdIn = pwd;
//				}
//			}
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//			ResultSet rr;// = stmt.executeQuery("select busdate,rptdate from dbo.itsm_busdate");//�жϵ�ǰ��������
		    //if (rr.next()) rptdtdate = rr.getInt("rptdate");
//			if (rr.next()) {
//				rptdtdate = rr.getInt("rptdate");
//				WriteLog.writeFile("�����뱨������(Ĭ��Ϊ):" + rptdtdate);
//				String rptdate = sc.nextLine();
//				int rptInputDate = 0;
//				while (rptdate.length() > 0) {
//					try {
//						rptInputDate = Integer.parseInt(rptdate);
//						// ��������ڷ�ΧΪ19000101~��ǰ���±�������
//						if (rptInputDate > 19000101 && rptInputDate < rptdtdate) {
//							rptdtdate = rptInputDate;
//							break;
//						}
//					} catch (Exception e) {
//					}
//					WriteLog.writeFile("�Ƿ����롣");
//					WriteLog.writeFile("�����뱨������(Ĭ��Ϊ):" + rptdtdate);
//					rptdate = sc.nextLine();
//				}
//			}

			// ִ��SQL,��������
//			rr = stmt
//					.executeQuery("select sql from dbo.ui_report where flag = 9999");
//			String SQL = null;
//			if (rr.next()) {
//				SQL = rr.getString("SQL");
//				try {
//					stmt.execute(SQL + "  " + rptdtdate);
//					WriteLog.writeFile("ִ�����ɹ�:  " + SQL + "  " + rptdtdate);
//				} catch (SQLException e) {
//					WriteLog.writeFile("ִ�����ʧ��:  " + SQL + "  " + rptdtdate);
//					WriteLog.writeFile("ִ�����ݴ����쳣,����ϵ����Ա!������룺"
//							+ e.getErrorCode() + "������Ϣ:" + e.getMessage());
//					rr.close();
//					stmt.close();
//					conn.close();
//					System.exit(1);
//				}
//			}else{
//				WriteLog.writeFile("����ִ�����ݴ���9999");
//			}
			try{
			rs = stmt.executeQuery("select * from dbo.ui_report where flag = 0");
			// ���������rlist
			while (rs.next()) {
				POReport po = new POReport();
				// po.setServerName(rs.getString("SERVERNAME"));
				// po.setDbName(rs.getString("DBNAME"));
				// po.setTName(rs.getString("TNAME"));
				po.setXstart(rs.getInt("XSTART"));
				po.setYstart(rs.getInt("YSTART"));
				po.setXend(rs.getInt("XEND"));
				po.setYend(rs.getInt("YEND"));
				po.setSheetIndex(rs.getInt("SHEETINDEX"));
				po.setXlsName(rs.getString("XLSNAME"));
				// po.setColName(rs.getString("COLNAME"));
				// po.setOrderName(rs.getString("ORDERNAME"));
				// po.setSchema(rs.getString("SCHEMA"));
				po.setTemplate(rs.getString("TEMPLATE"));
				po.setSql(rs.getString("SQL"));
				rlist.add(po);
			}
			}catch (Exception e){
				WriteLog.writeFile("��ȡ�����ļ�ʱ����:" + e.getMessage());
			}
		} catch (Exception e) {
			WriteLog.writeFile("δ֪����,�������ݿ����� �� ��ϵ����Ա...");
			WriteLog.writeFile(e.getMessage());
		}
	}

	/**
	 * ȡ�����ñ���SQL�ֶ����������ݣ����ڶ�ά������
	 * 
	 * @throws Exception
	 */
	public void initialData(String serverName, String dbName, String user,
			String password, String sql, String dbtype, String port)
			throws Exception {
		int rowCount = 0;
		int colCount = 0;
		WriteLog.writeFile("initialData...");
			try {
			//Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(url, user, password);
			// �õ���ά���������
			Statement stmtCount = conn.createStatement();
			String sqlCount = "select count(1) as ROW_COUNT  from (" + sql
					+ ") as a";
			WriteLog.writeFile("sqlCount=" + sqlCount);
			ResultSet rsCount = stmtCount.executeQuery(sqlCount);
			while (rsCount.next()) {
				rowCount = rsCount.getInt("ROW_COUNT");
			}
			if (rsCount != null) {
				rsCount.close();
				stmtCount.close();
			}

			// �õ���ά���������
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			colCount = rsmd.getColumnCount();
			WriteLog.writeFile("rowCount=" + rowCount + "; colCount="
					+ colCount);
			
			//��ά���鱣��׼��д��Excel������
			rData = new String[rowCount][colCount];
			rs.beforeFirst();
			rowCount = 0;
			while (rs.next()) {
				for (int j = 0; j <= colCount - 1; j++) {
					rData[rowCount][j] = rs.getString(j + 1);
				}
				rowCount++;
			}

		} catch (Exception sqle) {
			System.out.println(sqle);
		}
	}

	/**
	 * ��ȡExcel����滻���������
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String readAndUpdate(String xlsname, int sheetIndex, int xstart,
			int xend, int ystart, int yend, String template)
			throws FileNotFoundException, IOException {
		try {
			// �ж��Ƿ��ļ����ڣ����������½�
			File file = new File(xlsname);
			if (!file.exists()) {
				WriteLog.writeFile("Create a new file...to  " + template + "    using   " + xlsname);
				ExcelPIO.copyFile(template, xlsname);
			}
			FileInputStream fileInputStream = new FileInputStream(xlsname);
			POIFSFileSystem fileSystem = new POIFSFileSystem(fileInputStream);
			HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			HSSFRow row = null;
			HSSFCell cell = null;
			// �������У���Ϊ0��nullʱ��Ϊ���ݱ�Ĵ�С
			if (yend == 0) {
				yend = rData.length + ystart - 1;
			}
			if (xend == 0) {
				xend = rData[0].length + xstart - 1;
			}
			//System.out.println("xstart=" + xstart + ",ystart=" + ystart + ",xend=" + xend + ",yend=" + yend);
			// ѭ��дexcel�ļ�,ע�������0��ʼ
			for (int i = ystart; i <= yend; i++) {
				row = sheet.getRow(i);
				if (row == null) {
					row = sheet.createRow(i);
				}
				for (int j = xstart; j <= xend; j++) {
					cell = row.getCell(j);
					if (cell == null) {
						cell = row.createCell(j);
					}
					if (i - ystart <= rData.length - 1
							&& j - xstart <= rData[0].length - 1) {
						// cell.setCellValue(rData[i-ystart][j-xstart]);
						if (rData[i - ystart][j - xstart] != null
								&& ExcelPIO.isDouble(rData[i - ystart][j
										- xstart])) {
							// cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC );
							cell.setCellValue(Double.parseDouble(rData[i
									- ystart][j - xstart]));
						} else {
							cell.setCellValue(rData[i - ystart][j - xstart]);
						}
						// cell.setCellValue("bb");
						// System.out.println(rData[i-xstart][j-ystart]);
					}
				}
			}
			fileInputStream.close();
			FileOutputStream outputStream = new FileOutputStream(xlsname);
			workbook.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			WriteLog.writeFile("error:" + e.getMessage());
			e.printStackTrace();
			// �رճ���
			// Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
			WriteLog.writeFile("д�������쳣��Ϊ��");
			return xlsname;
		}
		// �رճ���
		// Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
		WriteLog.writeFile("write to report success.......");
		return xlsname;
	}

	/**
	 * ���췽������ʼ�������ļ�
	 * 
	 * @throws Exception
	 */
	public ExcelPIO() {
		try {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("report.properties");
		Properties p = new Properties();

			p.load(inputStream);
			g_host = p.getProperty("SERVERNAME");
			g_dbname = p.getProperty("DBNAME");
			g_user = p.getProperty("USER");
			setG_pwd(p.getProperty("PASSWORD"));
			g_port = p.getProperty("PORT");
			g_dbtype = p.getProperty("DBTYPE");
		} catch (IOException e1) {
			e1.printStackTrace();
			WriteLog.writeFile("��ȡ�����ļ�����,�˳�����");
			System.exit(0);
		}
		if (g_dbtype.toLowerCase().equals("db2")) {
			driverName = "com.ibm.db2.jcc.DB2Driver";
			url = "jdbc:" + g_dbtype + "://" + g_host + ":" + g_port + "/"
					+ g_dbname;
		} else if (g_dbtype.toLowerCase().equals("oracle")) {
			driverName = "oracle.jdbc.driver.OracleDriver";
			url = "jdbc:" + g_dbtype + "://" + g_host + ":" + g_port + "/"
					+ g_dbname;
		} else if (g_dbtype.toLowerCase().equals("sqlserver")) {
			driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			url = "jdbc:sqlserver://" + g_host + ":" + g_port
					+ ";DatabaseName=" + g_dbname;
		}else{
			WriteLog.writeFile("�����ļ����ݿ���Ϣ����!�޷�ʶ����������ݿ⣡");
			System.exit(0);
		}
	}

	public String genReport(ExcelPIO pio) throws Exception {
		WriteLog.writeFile("Started!!!");
		POReport po = null;
		try {
			//ȡ���ñ���Ϣ
			initialRList(g_dbtype, g_host, g_port, g_dbname, g_user, getG_pwd());
			// ѭ���������
			for (int i = 0; i < rlist.size(); i++) {
				po = (POReport) rlist.get(i);
				//���ݳ�ʼ��
				pio.initialData(g_host, g_dbname, g_user, getG_pwd(), po.getSql(),
						g_dbtype, g_port);
				//д��excel
				pio.readAndUpdate(po.getXlsName(), po.getSheetIndex(), po
						.getXstart(), po.getXend(), po.getYstart(), po
						.getYend(), po.getTemplate());
			}
			WriteLog.writeFile("Finished!!!");
		} catch (Exception e) {
			WriteLog.writeFile(e.getMessage());
			WriteLog.writeFile("Abort!!!");
		}
		return po.getXlsName();
	}

	public static void main(String args[]) throws Exception {
		WriteLog.writeFile("������ʼ....");
		ExcelPIO pio = new ExcelPIO();
		pio.genReport(pio);
	}

	public void setG_pwd(String g_pwd) {
		ExcelPIO.g_pwd = g_pwd;
	}

	public String getG_pwd() {
		return g_pwd;
	}

}
