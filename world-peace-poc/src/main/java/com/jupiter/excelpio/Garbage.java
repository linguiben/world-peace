package com.jupiter.excelpio;

/**
 * @author Jupiter Lin
 *
 */
public class Garbage {
	
	int index;
	static int count;
	Garbage(){
		count++;
		System.out.println("Object " + count + " construct!");
		setID(count);
	}
	void setID(int id){
		index = id;
	}
	@Override
	protected void finalize(){
		//overridefinalize��������Object�̳ж�����
		System.out.println("Object " + count + " is reclaimed");
	}
	public static void main(String[] args){
		new Garbage();
		new Garbage();
		new Garbage();
		new Garbage();
		System.gc();
	}

}
