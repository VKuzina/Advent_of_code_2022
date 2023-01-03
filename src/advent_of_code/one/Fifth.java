package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Fifth {

	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/fifth")).toArray(String[]::new);
		
		int len = lines[0].length();
		
		int input_number = (len + 1) / 4;
		
		List<Stack<Character>> stacks = new ArrayList<Stack<Character>>();
		
		for (int i = 0; i < input_number; i++) {
			stacks.add(new Stack<>());
		}
		
		int i = 0;
		
		while(!lines[i].equals("")) i++;
		
		int checkpoint = i;
		i -= 2;
		
		while( i >= 0 && lines[i].toCharArray()[0] == '[') {
			char[] chars = lines[i].toCharArray();
			for (int j = 0; j < lines[i].length(); j++) {
				if (chars[j] == '[') {
					stacks.get(j / 4).push(chars[++j]); 
				}
			}
			i--;;
		}
		
		i = checkpoint + 1;
		
		for(; i < lines.length; i++) {
			String[] instructions = lines[i].split(" ");
			int amount = Integer.parseInt(instructions[1]);
			int from = Integer.parseInt(instructions[3]) - 1;
			int to = Integer.parseInt(instructions[5]) - 1;
			
			Stack<Character> tempstStack = new Stack<>();
			for (int j = 0; j < amount; j++) {
				if (!stacks.get(from).empty()) tempstStack.push(stacks.get(from).pop());
			}
			
			while (!tempstStack.empty()) {
				stacks.get(to).push(tempstStack.pop());
			}
		}
		

		String out = "";
		
		for (Stack<Character> s : stacks) {
			if (s.empty()) out += " ";
			else out += s.peek();
		}
		
		System.out.println(out);
	}
	
}
