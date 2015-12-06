

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //justin sucks at clash
    	//blah
    }
    
    private int upperBound = 100;
    
    public Line[] genLines(int n) {
    	System.out.println("PRINTING");
    	Line[] lines = new Line[2*n];
    	Random randomGenerator = new Random();
    	//generate n horizontal lines
    	for(int i = 0; i < n; i++) {
    		int startX = randomGenerator.nextInt(upperBound);
    		int maxLen = Math.min(upperBound-startX, 25);
    		int endX = startX + randomGenerator.nextInt(maxLen);
    		int y = randomGenerator.nextInt(upperBound);
    		lines[i] = new Line(new EndPoint(startX, y), new EndPoint(endX, y), true);
    		System.out.println(lines[i]);
    	}
    	
    	//generate n vertical lines
    	for(int j = n; j < 2*n; j++) {
    		int startY = randomGenerator.nextInt(upperBound);
    		int maxLen = Math.min(startY, 25);
    		int endY = startY - randomGenerator.nextInt(maxLen);
    		int x = randomGenerator.nextInt(upperBound);
    		lines[j] = new Line(new EndPoint(x, startY), new EndPoint(x, endY), false);
    		System.out.println(lines[j]);
    	}
    	
    	return lines;
    }
    
    public void bruteForce(Line[] lines) {
    	for (int i = 0; i < lines.length; i++) {
    		for (int j = i; j < lines.length; j++) {
    			if (lines[i].isHoriz() ^ lines[j].isHoriz()) {
    				if (lines[i].isHoriz()) {
    					if(lines[i].start().x() <= lines[j].start().x() 
    							&& lines[i].end().x() >= lines[j].start().x()) {
    						System.out.println(lines[i] + " intersects with " + lines [j]);
    					}
    				}
    				else if (lines[j].start().x() <= lines[i].start().x() 
    							&& lines[j].end().x() >= lines[i].start().x()) {
    					System.out.println(lines[j] + " intersects with " + lines [i]);
    				}
    			}
    		}
    	}
    }
}
