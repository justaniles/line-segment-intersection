package com.datalgo;

public class EndPoint {
	private int xCoord;
	private int yCoord;
    private Line parentLine;
	
	public EndPoint(int x, int y, Line pl) {
		xCoord = x;
		yCoord = y;
		parentLine = pl;
	}
	
	public int x() {
		return xCoord;
	}
	
	public int y() {
		return yCoord;
	}

    public Line getParentLine() {
        return parentLine;
    }

    public boolean equals(EndPoint ep) {
        if (this.xCoord == ep.xCoord &&
                this.yCoord == ep.yCoord &&
                this.parentLine == ep.getParentLine()) {
            return true;
        }
        return false;
    }
}