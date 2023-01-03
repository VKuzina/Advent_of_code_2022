package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import advent_of_code.one.utils.Math_monkey;

public class Twentyfirst {
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/twentyfirst")).toArray(String[]::new);
		
		LinkedHashMap<String, Math_monkey> monkeys = new LinkedHashMap<>();
		for (String line : lines) {
			String[] parts = line.split(":");
			
			String[] sec = parts[1].trim().split(" ");
			
			if (sec.length == 1) {
				monkeys.put(parts[0], new Math_monkey(parts[0], Long.parseLong(sec[0])));
				continue;
			}

			int operation = -1;

			switch(sec[1]) {
			case "+":
				operation = 0;
				break;
			case "-":
				operation = 1;
				break;
			case "*":
				operation = 2;
				break;
			case "/":
				operation = 3;
				break;
			}
			
			monkeys.put(parts[0], new Math_monkey(parts[0], operation, sec[0], sec[2]));
		
		}
		System.out.println(monkeys.get("root").find_human_value(monkeys));
	}
}
