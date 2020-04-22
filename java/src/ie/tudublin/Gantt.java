package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> task = new ArrayList<Task>();
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table table = loadTable("tasks.csv","header");
		for(TableRow row : table.rows())
		{
			Task t = new Task(row);
			task.add(t);
		}
		
	}

	public void printTasks()
	{
		for(Task t:task)
		{
			System.out.println(t);
		}	
	}

	public void displayTasks()
	{
		float gap = 150;
		float border = (float) (height * 0.05);
		int days= 31;
		int p;
		int q;
		float col;
		stroke(255);
		for(int i=1;i<days;i++)
		{
			float x = map(i,1,30,gap,width-border);
			line(x, border, x, height - border);
			textAlign(CENTER);
			text(i, x, 15);
		}
		for(int i=0;i<task.size();i++)
		{
			Task t = task.get(i);
			p = t.getStart();
			q = t.getEnd();
			q = q - p;
			noStroke();
			col = map(i,0,11,0,330);
			float start = map(p,1,30,gap,width - border);
			float end = map(q,0,30,0,width - gap - border);
			float y = map(i,0,task.size(),border +20 , height - gap);
			fill(col,255,255);
			rect(start,y,end,30);
			fill(255);
			text(t.getTask(),75,y+15);
		}

		

	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
		loadTasks();
		printTasks();
		colorMode(HSB);
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
