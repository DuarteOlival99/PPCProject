package com.company;

public class 	Solution implements Cloneable {
	private boolean[] objects;
	private int[] values = new int[NemhauserUllmanSequential.NDIM];
	
	public Solution(boolean[] obj) {
		this.objects = obj;
	}
	public Solution(boolean[] obj, int[] values) {
		this.objects = obj.clone();
		this.values = values.clone();
	}
	
	public Solution clone() {
		return new Solution(objects, values);
	}
	
	public int getValue(int dim) {
		return values[dim];
	}
	
	public void enable(int position, int[] object) {
		this.objects[position] = true;
		for (int i=0; i<NemhauserUllmanSequential.NDIM;i++) {
			this.values[i] += object[i];
		}
	}
	
	public boolean isDominatedBy(Solution other) {
		if (this.values[0] < other.values[0]) {
			return false; // First goal is minimized
		}
		for (int i=1; i<NemhauserUllmanSequential.NDIM;i++) {
			if (this.values[i] >= other.values[i]) {
				return false;
			}
		}
		return true;
	}
}
