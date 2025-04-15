package com.jupiter.excelpio;

/**
 * @author Jupiter Lin 2012-8-17 ����10:25:50
 */
interface Sport {

	void run();

	void jump();

}

class Athlete2 implements Sport {

	public void run() {
		System.out.println("i run");
	}

	public void jump() {
		System.out.println("i jump");
	}

	public static void main(String[] args) {

		Athlete athlete = new Athlete();
		athlete.run();
		athlete.jump();
	}
}
