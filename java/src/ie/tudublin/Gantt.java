package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> task = new ArrayList<Task>();
	float col;
	float gap = 150;
	float border = 50;
	int days= 31;
	float start=0;
	float end=0;
	float y=0;
	int currentTask;
	
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
		stroke(255);
		for(int i=1;i<days;i++)
		{
			float x = map(i,1,30,gap,width-border);
			line(x, border, x, height - border);
			textAlign(CENTER);
			text(i, x, 30);
		}

		for(int i=0;i<task.size();i++)
		{
			Task t = task.get(i);
			noStroke();
			col = map(i,0,11,0,330);
			start = map(t.getStart(),1,30,gap,width - border);
			end = map(t.getEnd()-t.getStart(),0,30,0,width - gap - border);
			y = map(i,0,task.size(),border +20 , height - gap);
			fill(col,255,255);
			rect(start,y,end,30);
			fill(255);
			text(t.getTask(),75,y+15);
		}

	}
	
	public void mousePressed()
	{
		for(int i = 0; i < task.size(); i ++)
		{
			Task t = task.get(i);
			start = map(t.getStart(),1,30,gap,width - border);
			end = map(t.getEnd(),1,30,gap,width - border);
			y = map(i,0,task.size(),border +20 , height - gap);
			if(mouseY > y && mouseY < y + 30 && mouseX > start && mouseX < end)
			{
				currentTask = i;
			}
		}	
	}

	public void mouseDragged()
	{
		Task t = task.get(currentTask);
		start = map(t.getStart(),1,30,gap,width - border);
		end = map(t.getEnd(),1,30,gap,width - border);
		if(mouseX > gap && mouseX < start + 20 && t.getStart() > 1 && (pmouseX - mouseX ) > 0)
		{
			println("start");
			t.setStart(t.getStart()-1);
			
		}
		if(mouseX < end && mouseX > end - 20 && t.getEnd() < 30 && (pmouseX - mouseX) < 0 )
		{
			println("end");
			t.setEnd(t.getEnd()+1);
		}
		
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
