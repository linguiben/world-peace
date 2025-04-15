package com.jupiter.excelpio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String d = df.format(new Date());
		System.out.println("jintian:" + d);
		int ddd = 20120840;
		
		int[][] num1 = new int [5][8];
		int [][] num;
		num = new int[3][];
		num[0] = new int[5];
		num[1] = new int[3];
		num[2] = new int[2];
		for(int j=0;j<3;j++){
			for(int i=0;i<num[j].length;i++){
				System.out.print(num[j][i]);
			}
			System.out.println();
			System.out.println(num[j][num[j].length-1]);
		}
		
		try{
			Date date; 
			date = df.parse(ddd + "");
			System.out.println(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
					System.out.println(df.format(calendar.getTime()) + "1111111111");
		
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(".....");
			e.printStackTrace();
		}
		

	}

}
