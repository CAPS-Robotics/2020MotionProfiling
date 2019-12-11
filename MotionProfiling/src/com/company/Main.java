package com.company;

public class Main {
	private static PathFinder pathFinder;
	
    public static void main(String[] args) throws InterruptedException {
	    pathFinder = new PathFinder();
	    
	    pathFinder.setPath(0, 50);

	    do {
	    	System.out.println("Time: " + pathFinder.getElapsedTime());
	    	System.out.println("Speed: " + pathFinder.getCurrentSpeed());
	    	System.out.println();
	    	Thread.sleep(125);
	    } while (pathFinder.getCurrentSpeed() >= 0);
    }
}
