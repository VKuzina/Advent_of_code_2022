package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import advent_of_code.one.utils.Blueprint;
import advent_of_code.one.utils.Robot_state;

public class Nineteenth {
	
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/nineteenth")).toArray(String[]::new);
		
		int sum = 1;
		for (int j = 0; j < 3; j++) {
			List<Robot_state> states = new ArrayList<>();
			String line = lines[j];
			String[] parts = line.split(" ");
			Blueprint bp = new Blueprint(Integer.parseInt(parts[6]), Integer.parseInt(parts[12]), Integer.parseInt(parts[18]), Integer.parseInt(parts[21]), Integer.parseInt(parts[27]), Integer.parseInt(parts[30]));	
			states.add(new Robot_state(bp));
			
			
			for (int i = 0; i < 32; i++) {
				System.out.println(i + " " + states.size());
				List<Robot_state> next = new ArrayList<>();
				for (Robot_state state : states) {
					List<Robot_state> potential = state.legal_builds();
					
					int size = next.size();
					for (Robot_state pot : potential) {
						boolean check = true;
						for (int k = 0; k < size; k++) {
							Robot_state s = next.get(k);
							if (s.dominates(pot)) {
								check = false;
								break;
							}
							else if (pot.dominates(s)) {
								size--;
								next.remove(k);
								k--;
							}
						}
						
						if (check) next.add(pot);
					}
				}
				
				states = next;
			}
			
			int geode_max = 0;
			for (Robot_state state : states) {
				geode_max = Math.max(geode_max, state.geode);
			}
			
			sum *= geode_max;
			System.out.println(geode_max);
		}
		System.out.println(sum);
		
	}
}
