package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import advent_of_code.one.utils.Node;

public class Seventh {
	static Node root;
	static Node current;
	static int maxsize = 70000000;
	static int required_size = 30000000;
	static int free_space;
	
	public static void main(String[] args) throws IOException {
		Stream<String> lines = Files.lines(Paths.get("./src/advent_of_code/inputs/seventh"));
		
		root = new Node(0, null, "/");
		current = root;
		
		lines.forEach(Seventh::process);
		
		root.child_value_update();
		free_space = maxsize - root.value;
		
		System.out.println(root.find_smalles_cutoff(required_size - free_space));
	}
	
	public static void process(String s) {
		String[] parts = s.split(" ");
		if (parts[0].equals("$")) {
			if (parts[1].equals("cd")) {
				if (parts[2].equals("/")) {
					current = root;
				} else if (parts[2].equals("..")) {
					current = current.parent;
				} else {
					Node next = new Node(0, current, parts[2]);
					current.add_child(next);
					current = next;
				}
			}
			
		} else if (parts[0].equals("dir")) {
			return;
		} else {
			current.add_value(Integer.parseInt(parts[0]));
		}
	}
}
