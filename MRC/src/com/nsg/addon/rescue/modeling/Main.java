package com.nsg.addon.rescue.modeling;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] e = {1, 1, 0, 1};
		int rst = 0; 
		
		for(int elem: e) {
			if(elem == 1)
				rst = (rst<<1) | 1; 
			else 
				rst = rst<<1; 
		}
		
		System.out.println(rst);
	}

}
