/**
 * 
 */
package com.jupiter.excelpio;

import com.Sport;

/**
 * @author Jupiter Lin 
 * 2012-8-17 ����10:47:25
 */
class Athlete implements Sport {

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
