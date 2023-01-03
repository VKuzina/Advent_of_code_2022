package advent_of_code.one.utils;

import java.util.ArrayList;
import java.util.List;

public class Robot_state {
	int ore_robots = 1;
	int clay_robots;
	int bsidian_robots;
	int geode_robots;
	
	Blueprint blueprint;
	
	int ore;
	int clay;
	int bsidian;
	public int geode;
	
	public Robot_state(Blueprint blueprint) {
		this.blueprint = blueprint;
	}
	
	
	
	public Robot_state(int ore_robots, int clay_robots, int bsidian_robots, int geode_robots, Blueprint blueprint,
			int ore, int clay, int bsidian, int geode) {
		super();
		this.ore_robots = ore_robots;
		this.clay_robots = clay_robots;
		this.bsidian_robots = bsidian_robots;
		this.geode_robots = geode_robots;
		this.blueprint = blueprint;
		this.ore = ore;
		this.clay = clay;
		this.bsidian = bsidian;
		this.geode = geode;
	}



	public void calculate_next() {
		ore += ore_robots;
		clay += clay_robots;
		bsidian += bsidian_robots;
		geode += geode_robots;
	}
	
	public List<Robot_state> legal_builds() {
		List<Robot_state> legal = new ArrayList<>();
		if (bsidian >= blueprint.gr_b && ore >= blueprint.gr_o) {
			Robot_state rs = this.copy();
			rs.calculate_next();
			rs.geode_robots++;
			rs.bsidian -= blueprint.gr_b;
			rs.ore -= blueprint.gr_o;
			
			legal.add(rs);
		}
		
		if (clay >= blueprint.br_c && ore >= blueprint.br_o) {
			Robot_state rs = this.copy();
			rs.calculate_next();
			rs.bsidian_robots++;
			rs.clay -= blueprint.br_c;
			rs.ore -= blueprint.br_o;
			
			legal.add(rs);
		}
		
		if (ore >= blueprint.cr) {
			Robot_state rs = this.copy();
			rs.calculate_next();
			rs.clay_robots++;
			rs.ore -= blueprint.cr;
			
			legal.add(rs);
		}
		
		if (ore >= blueprint.or) {
			Robot_state rs = this.copy();
			rs.calculate_next();
			rs.ore_robots++;
			rs.ore -= blueprint.or;
			
			legal.add(rs);
		}
		
		this.calculate_next();
		legal.add(this);
		
		return legal;
	}
	
	public Robot_state copy() {
		return new Robot_state(ore_robots, clay_robots, bsidian_robots, geode_robots, blueprint, ore, clay, bsidian, geode);
	}
	
	public boolean dominates(Robot_state o) {
//		if (this.geode_robots > o.geode_robots) {
//			return true;
//		} else if (this.geode_robots >= o.geode_robots && this.bsidian_robots > o.bsidian_robots + 1) {
//			return true;
//		}
		
		if (this.geode_robots > o.geode_robots + 1) {
			return true;
		}

		if (this.ore_robots > o.ore_robots && this.clay_robots > o.clay_robots &&
			this.bsidian_robots > o.bsidian_robots && this.geode_robots > o.geode_robots) {
			return true;
		}
		
		if (this.ore_robots >= o.ore_robots && this.clay_robots >= o.clay_robots &&
			this.bsidian_robots >= o.bsidian_robots && this.geode_robots >= o.geode_robots &&
			this.ore >= o.ore && this.clay >= o.clay && this.bsidian >= o.bsidian && this.geode >= o.geode) {
			return true;
		}
		
		return false;
	}
}
