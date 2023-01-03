package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Tenth {
	static int cycle = 0;
	static int register = 1;
	static int sum = 0;
	static int[] checkpoints;
	
	public static void main(String[] args) throws IOException {
		Stream<String> lines = Files.lines(Paths.get("./src/advent_of_code/inputs/tenth"));
		
		checkpoints = new int[] {40,80,120,160,200,240};
		lines.forEach(Tenth::process);
		System.out.println(sum);
	}
	
	public static void process(String s) {
		String[] parts = s.split(" ");
		
		if (parts[0].startsWith("n")) {
			execute_cycle(0);
		} else {
			execute_cycle(0);
			execute_cycle(Integer.parseInt(parts[1]));
		}
	}

	private static void execute_cycle(int amount) {
		if (Math.abs(((cycle) % 40) - register) < 2) System.out.print("#"); 
		else System.out.print(".");
		
		for (int point : checkpoints) {
			if (cycle + 1 == point) {
				sum += point * register;
				System.out.println();
			}
		}

		register += amount;
		cycle++;
	}
	
}
