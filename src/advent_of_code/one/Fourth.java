package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class Fourth {
	static int sum = 0;

	public static void main(String[] args) throws IOException {
		Stream<String> lines = Files.lines(Paths.get("./src/advent_of_code/inputs/fourth"));

		lines.forEach(Fourth::process);
		
		System.out.println(sum);
	}
	
	public static void process(String s) {
		String[] split = s.split(",");
		String[] first = split[0].split("-");
		String[] second = split[1].split("-");
		
		int fs= Integer.parseInt(first[0]);
		int fe= Integer.parseInt(first[1]);
		int ss= Integer.parseInt(second[0]);
		int se= Integer.parseInt(second[1]);
		
		if (fs > se) return;
		else if (fe < ss) return;
		
		sum++;
	}
}
