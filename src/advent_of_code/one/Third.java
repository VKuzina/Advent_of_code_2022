package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class Third {
	static int sum = 0;
	static String[] group = new String[3];
	static int check = 0;

	public static void main(String[] args) throws IOException {
		Stream<String> lines = Files.lines(Paths.get("./src/advent_of_code/inputs/third"));

		lines.forEach(Third::process);
		
		System.out.println(sum);
	}
	
	public static void process(String s) {
		group[check++] = s;
		if (check < 3) return;
		
		check = 0;
	
		HashMap<Character, Integer> seen = new HashMap<>();
		
		for(char c : group[0].toCharArray()) seen.put(c, 1);
		for(char c : group[1].toCharArray()) {
			if (seen.get(c) != null) seen.put(c, 2);
		}
		
		for(char c : group[2].toCharArray()) {
			if (seen.get(c) != null && seen.get(c) == 2) {
				if (c > 96) sum += c - 96;
				else sum += c - 38;
				return;
			}
		}
	}
}
