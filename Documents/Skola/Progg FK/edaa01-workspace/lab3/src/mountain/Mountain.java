package mountain;

import fractal.*;

public class Mountain extends Fractal{
	private Point a;
	private Point b;
	private Point c;
	
	public Mountain(){
		super();
		a = new Point(20, 20);
		b = new Point(a.getX()+30,a.getY()+150);
		c = new Point(a.getX()+190,a.getY()-10);
	}
	
	public (TurtleGraphics turtle){
		
	}

	@Override
	public String getTitle() {
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics g) {
		if(order == 0){
			turtle.moveTo( a.getX(),  a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
			order++;
				
		} else{
//			turtle.moveTo( a.getX(),  a.getY());
			turtle.penDown();
			while (turtle.getLineCount() < (int)Math.pow(order, order)){
				turtle.moveTo(b.getX()/(order*4), b.getY()/(order*4));				
				turtle.moveTo(c.getX()/(order*4), c.getY()/(order*4));
				turtle.moveTo(a.getX()/(order*4), a.getY()/(order*4));	
			}
			
		}
		
		
		
	}
	private void fractalLine(TurtleGraphics turtle, int order, double length, int alpha){
		if(order == 0){
			turtle.moveTo( a.getX(),  a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
			order++;
		
	}

}
