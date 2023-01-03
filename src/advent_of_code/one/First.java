package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class First {
	static int[] top3 = new int[3];
	static int temp = 0;
	static boolean check = false;
	static int smallest = 0;
	
	public static void main(String[] args) throws IOException {
		Stream<String> lines = Files.lines(Paths.get("./src/advent_of_code/inputs/one_first"));
		
		lines.forEach(First::process);
		
		if (temp > top3[smallest]) top3[smallest] = temp;
		
		System.out.println(top3[0] + top3[1] + top3[2]);
	}
	
	public static void process(String s) {
		  if(s.equals("")) {
			  check = true;
			  return;
		  }
		  
		  if (check) {
			  if (temp > top3[smallest]) {
				  top3[smallest] = temp;
				  smallest = smallest();
			  }
			  temp = 0;
			  check = false;
		  }
		  
		  temp += Integer.parseInt(s);
	}
	
	public static int smallest() {
		if (top3[0] < top3[1] && top3[0]< top3[2]) return 0;
		else if (top3[1] < top3[2]) return 1;
		else return 2; 
	}
}
