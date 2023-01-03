package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import advent_of_code.one.utils.Monkey;

public class Eleventh {
	
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/eleventh")).toArray(String[]::new);
		
		int monkey_id = 0;
		List<Integer> items = null;
		List<Monkey> monkeys = new ArrayList<>();
		int operation = 0;
		int value = 0;
		int test = 0;
		int pass = 0;
		int fail = 0;
		int index = 0;
		HashMap<Integer, Integer> levels = new HashMap<>();

		for (String line : lines) {
			if (line.equals("")) continue;
			String[] parts = line.trim().split(":");
			
			if (parts.length == 1) {
				monkey_id = Integer.parseInt(parts[0].split(" ")[1]);
				continue;
			}
			
			if(parts[0].startsWith("S")) {
				items = new ArrayList<>();
				levels = new HashMap<>();
				String[] parters = parts[1].split(", ");
				for (String s : parters) {
					int val = Integer.parseInt(s.trim());
					items.add(index);
					
					levels.put(index, val);
					index++;
				}
			} else if (parts[0].startsWith("O")) {
				String[] parters = parts[1].split("=")[1].trim().split(" ");
				
				if (parters[1].equals("+")) operation = 1;
				else operation = 2;
				
				if (parters[2].equals("old")) {
					value = -1; 
				} else {
					value = Integer.parseInt(parters[2]);
				}
			} else if (parts[0].startsWith("T")) {
				test = Integer.parseInt(parts[1].trim().split(" ")[2]);
			} else if (parts[0].endsWith("true")) {
				pass = Integer.parseInt(parts[1].trim().split(" ")[3]);
			} else if (parts[0].endsWith("false")) {
				fail = Integer.parseInt(parts[1].trim().split(" ")[3]);
				monkeys.add(new Monkey(monkey_id, items, operation, value, test, pass, fail, levels));
			}
		}
		
		for (int i = 0; i < monkeys.size(); i++) {
			for (int j = 0; j < monkeys.size(); j++) {
				if (i == j) continue;
				Monkey m = monkeys.get(i);
				Monkey m2 = monkeys.get(j);
				for (int item : m.getItems()) {
					m2.update_level(item, m.get_level(item));
				}
			}
		}
		
		for (int i = 0; i < 10000; i++) {
			
			for (Monkey m : monkeys) {
				System.out.println(m.getSum());
				System.out.println(m.getItems());
			}
			for (Monkey m : monkeys) {
				m.pass_items(monkeys);
			}
		}
		
		long max = 0;
		for (int i = 0; i < monkeys.size(); i++) {
			System.out.println(monkeys.get(i).getSum());
			for (int j = i + 1; j < monkeys.size(); j++) {
				max = Math.max(max, monkeys.get(i).getSum() * monkeys.get(j).getSum());
			}
		}
		
		System.out.println(max);
 	}
}
