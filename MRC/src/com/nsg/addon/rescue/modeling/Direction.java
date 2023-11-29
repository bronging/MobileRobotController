package com.nsg.addon.rescue.modeling;

public enum Direction {
	EAST,  SOUTH, WEST, NORTH;
	
	public Direction next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
