package advent_of_code.one.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Monkey {
	int id;
	List<Integer> items;
	int operation;
	int value;
	int test;
	int pass;
	int fail;
	int sum;
	
	HashMap<Integer, Integer> my_levels = new HashMap<>();

	public Monkey(int id, List<Integer> items, int operation, int value, int test, int pass, int fail, HashMap<Integer, Integer> levels) {
		super();
		this.items = items;
		this.operation = operation;
		this.value = value;
		this.test = test;
		this.pass = pass;
		this.fail = fail;
		this.my_levels = levels;
	}
	
	public void add_item(int item) {
		items.add(item);
	}
	
	public int getSum() {
		return sum;
	}
	
	public List<Integer> getItems() {
		return items;
	}
	
	public void update_level(int id, int value) {
		my_levels.put(id, value % test);
	}
	
	public int get_level(int id) {
		return my_levels.get(id);
	}
	 
	public void pass_items(List<Monkey> monkeys) {
		for (Integer item : items) {
			sum++;
			for(Monkey m : monkeys) {
				int old = m.get_level(item);
				int next = 0;
				if (operation == 1) {
					if (value == -1) {
						next = old + old;
					} else {
						next = old + value;
					}
				} else {
					if (value == -1) {
						next = old * old;
					} else {
						next = old * value;
					}
				}
				
				m.update_level(item, next);
				System.out.println(m.get_level(item));
			}

			//worry = worry / 3;
			if (this.get_level(item) % test == 0) {
				monkeys.get(pass).add_item(item);
			} else {
				monkeys.get(fail).add_item(item);
			}
		}
		
		items = new ArrayList<>();
	}
	
}
