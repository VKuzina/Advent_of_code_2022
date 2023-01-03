package advent_of_code.one.utils;

import java.util.LinkedHashMap;

public class Math_monkey {
	int operation;
	String first_operand;
	String second_operand;
	long number;
	boolean operation_monkey;
	String name;
	
	public Math_monkey(String name, long number) {
		this.name = name;
		this.number = number;
		
		this.operation_monkey = false;
	}

	public Math_monkey(String name, int operation, String first_operand, String second_operand) {
		this.name = name;
		this.operation = operation;
		this.first_operand = first_operand;
		this.second_operand = second_operand;
		
		this.operation_monkey = true;
	}
	
	public long calculate(LinkedHashMap<String, Math_monkey> monkeys) {
		if (operation_monkey) {
			long first_value = monkeys.get(first_operand).calculate(monkeys);
			long second_value = monkeys.get(second_operand).calculate(monkeys);
			
			switch (operation) {
			case 0:
				return first_value + second_value;
			case 1:
				return first_value - second_value;
			case 2 :
				return first_value * second_value;
			case 3 :
				return first_value / second_value;
			}
			
			return -1;
		} else {
			return number;
		}
	}
	
	public boolean check_for_humn(LinkedHashMap<String, Math_monkey> monkeys) {
		if (operation_monkey) {
			return monkeys.get(first_operand).check_for_humn(monkeys) || monkeys.get(second_operand).check_for_humn(monkeys);
		} else {
			if (name.equals("humn")) return true;
		}
		
		return false;
	}
	
	public long find_human_value(LinkedHashMap<String, Math_monkey> monkeys) {
		long eq_val = 0;
		if (monkeys.get(first_operand).check_for_humn(monkeys)) {
			eq_val = monkeys.get(second_operand).calculate(monkeys);
			return monkeys.get(first_operand).send_down(monkeys, eq_val);
		} else {
			eq_val = monkeys.get(first_operand).calculate(monkeys);
			return monkeys.get(second_operand).send_down(monkeys, eq_val);
		}
	}

	private long send_down(LinkedHashMap<String, Math_monkey> monkeys, long eq_val) {
		if (name.equals("humn")) {
			return eq_val;
		} else {
			long next_val = 0;
			if (monkeys.get(first_operand).check_for_humn(monkeys)) {
				next_val = monkeys.get(second_operand).calculate(monkeys);
				long final_val = 0;
				switch (operation) {
				case 0:
					final_val = eq_val - next_val;
					break;
				case 1:
					final_val = eq_val + next_val;
					break;
				case 2 :
					final_val = eq_val / next_val;
					break;
				case 3 : 
					final_val = eq_val * next_val;
					break;
				}
				
				return monkeys.get(first_operand).send_down(monkeys, final_val);
			} else {
				next_val = monkeys.get(first_operand).calculate(monkeys);
				long final_val = 0;
				switch (operation) {
				case 0:
					final_val = eq_val - next_val;
					break;
				case 1:
					final_val = next_val - eq_val;
					break;
				case 2 :
					final_val = eq_val / next_val;
					break;
				case 3 : 
					final_val = next_val / eq_val;
					break;
				}
				return monkeys.get(second_operand).send_down(monkeys, final_val);
			}
		}
		
	}
}
