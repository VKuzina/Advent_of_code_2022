package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Sixth {
	static int constant = 14;

	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/sixth")).toArray(String[]::new);
		
		char[] chars = lines[0].toCharArray();
		
		int counter = 0;
		
		for (int i = 0; i < chars.length; i++) {
			if (i == 2747) {
				System.out.println("bla");
			}
			for (int j = 1; j < constant; j++) {
				if (i - j < 0) break;
				System.out.println(chars[i]);
				System.out.println(chars[i - j]);
				if (chars[i] == chars[i-j])  {
					counter = Math.min(counter, j - 1);
					break;
				}
			}
			
			counter ++;
			
			if (counter == constant) {
				System.out.println(i + 1);
				return;
			}
		}
 	}
	
}
