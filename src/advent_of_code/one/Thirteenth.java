package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import advent_of_code.one.utils.Element;

public class Thirteenth {
	
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/thirteenth")).toArray(String[]::new);
		
		int sum = 0;
		
		List<Element> all = new ArrayList<>();
		
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].equals("")) continue;
			
			String first = lines[i];
			String second = lines[i+1];
			
			all.add(turn_to_array(first.toCharArray()));
			all.add(turn_to_array(second.toCharArray()));
			
			i += 2;
		}
		Element div1 = turn_to_array("[[2]]".toCharArray());
		div1.set_index(1);
		all.add(div1);
		Element div2 = turn_to_array("[[6]]".toCharArray());
		div2.set_index(1);
		all.add(div2);
		Element[] array = all.toArray(new Element[all.size()]);
		
		Arrays.sort(array);
		
		int mul = 1;
		for (int i = 0; i < array.length; i++) {
			if (array[i].get_index() == 1) mul *= i + 1;
		}
		
		System.out.println(mul);
	}
	
	

	private static Element turn_to_array(char[] charArray) {
		Element start = new Element(null);
		Element current = start;
		String val = "";
		for(int i = 0; i < charArray.length; i++) {
			if (charArray[i] == '[') {
				current = current.add_list();
				continue;
			} else if (charArray[i] == ']') {
				if (!val.equals("")) {
					current.add_child(val);
					val = "";
				}
				current = current.get_parent();
				continue;
			} else if (charArray[i] == ',' || charArray[i] == ' ') {
				if (!val.equals("")) {
					current.add_child(val);
				}
				val = "";
			} else {
				val += charArray[i];
			}
		}
		
		return current.get_child(0);
	}
}
