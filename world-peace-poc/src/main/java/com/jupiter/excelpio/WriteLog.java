package com.jupiter.excelpio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;




public class WriteLog
{
	
	static FileOutputStream fis = null;

	static String filename = "rpt.log";

	static boolean isFirst = true;

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss", Locale.CHINESE);
	
	static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	
	static String fileDate="";

	/**
	 * Ĭ����һ���ļ�
	 * 
	 * @param Str
	 * @param flag
	 * @throws IOException
	 */
	public static void writeFile(String Str) {

		System.out.println((sdf.format(new Date()) + " --- " + Str + "\r\n"));
		String fileDatetemp=sdf1.format(new Date());
		boolean flag=fileDatetemp.equalsIgnoreCase(fileDate);
		if(!flag){
			
			try {
				fileDate=fileDatetemp;
				fis = new FileOutputStream(filename,true);
				// fw=new java.io.FileWriter(filename, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
			// pw.println(new String(Str.getBytes("utf-8"),"gbk"));
			try {
				fis.write((sdf.format(new Date()) + " --- " + Str + "\r\n").getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

public static void main(String args[]) throws IOException{	
	writeFile("program starting....");
}
}