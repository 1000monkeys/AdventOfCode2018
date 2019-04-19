package com.kjellvos.aletho;

import java.util.ArrayList;

public class Node {
	public ArrayList<Character> dependencies;
	public int workerId;
	public int timeValue;
	
	public Node() {
		dependencies = new ArrayList<Character>();
	}
	
	public Node(int timeValue) {
		dependencies = new ArrayList<Character>();
		this.timeValue = timeValue;
	}
	
	public void addToDependencies(char dependency) {
		dependencies.add(dependency);
	}

	public boolean blockedByDependencies() {
		if (dependencies.size() > 0) {
			return true;
		}
		return false;
	}
	
	public void removeDependencie(char dependency) {
		dependencies.remove(new Character(dependency));
	}
}
