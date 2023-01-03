package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import advent_of_code.one.utils.Node_int;

public class Twentieth {
	
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/twentieth")).toArray(String[]::new);
		
		long dec_key = 811589153;
		int length = lines.length;
		Node_int head = new Node_int(Integer.parseInt(lines[0]), null);
		List<Node_int> nodes = new ArrayList<>();

		Node_int last = head;
		nodes.add(head);
		for (int i = 1; i < lines.length; i++) {
			Node_int next = new Node_int(Integer.parseInt(lines[i]), last);
			last.next = next;
			last = next;
			nodes.add(next);
		}
		
		last.next = head;
		head.previous = last;
		for (int r = 0; r < 10; r++) {
			for (int k = 0; k < length; k++) {
				Node_int current = nodes.get(k);
				
				long increment = (current.value * dec_key) % (length - 1);
				if (increment == 0) continue;
				if (increment < 0) increment += (length - 1);
	
				Node_int temp = current;
				current.previous.next = current.next;
				current.next.previous = current.previous;
				
				for (long i = 0; i < increment; i++) {
					current = current.next;
				}
				
				temp.previous = current;
				temp.next = current.next;
				current.next.previous = temp;
				current.next = temp;
			}
		}
			
		Node_int current = head;
		
		while(current.value != 0) {
			current = current.next;
		}
		
		long sum = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1000; j++) {
				current = current.next;
			}
			
			sum += current.value * dec_key;
		}
		
		System.out.println(sum);
	}
}
