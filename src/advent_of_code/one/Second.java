package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Second {
	static int sum = 0;
	public static void main(String[] args) throws IOException {
		Stream<String> lines = Files.lines(Paths.get("./src/advent_of_code/inputs/second"));

		lines.forEach(Second::process);
		
		System.out.println(sum);
	}
	
	public static void process(String s) {
		String[] split = s.split(" ");
		
		sum += calculate_adjusted_result(split[0].toCharArray()[0], (char) (split[1].toCharArray()[0] - 23));
	}

	private static int calculate_result(char c1, char c2) {
		int temp_sum = 0;
		if (c1 == c2) temp_sum += 3;
		else if (c2 - c1 == 1 || c2 - c1 == -2) temp_sum += 6;
		
		return temp_sum + c2 - 64;
	}
	
	private static int calculate_adjusted_result(char c1, char c2) {
		char c3 = 0;
		
		if (c2 == 'A') {
			if (c1 == 'A') c3 = 'C';
			else c3 = (char) (c1 - 1);
		} else if (c2 == 'B') c3 = c1;
		else {
			if (c1 == 'C') c3 = 'A';
			else c3 = (char) (c1 + 1);
		}
		
		return calculate_result(c1, c3);
	}
	
}
