import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Move extends JPanel implements KeyListener, Runnable{

	private ImageIcon image;
	private static final int SIZE = 50; 
	private static final int VELOCITY = 5; 
	private int x, y;
	private boolean up, down, left, right, quit;
	
	public Move()
	{
		super();
		
		image = new ImageIcon("Bandage.jpg");   
		x = 20;
		y = 20;
		up = down = left = right = quit = false;
		
		this.setFocusable(true);   // without it for JPanel, it will not pick up any keylistener. 
		this.addKeyListener(this);      
		
		Thread t1 = new Thread(this);  
	    t1.start(); 
	}

	public void run()
	{
		while (true)
		{
			if (up)
			{
				move(0, -1);
			}
			if (down)
			{
				move(0, 1);
			}
			if (left)
			{
				move(-1, 0);
			}
			if (right)
			{
				move(1, 0);
			}
			if (quit)
			{
				quit = false; 
				break; 
			}
			
			repaint();
			
			try
			{
				Thread.sleep(10);
			} catch(InterruptedException e){
	  			e.printStackTrace();
			}	
		}
	}
	
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W)
		{
			up = true;
		}
		if (code == KeyEvent.VK_S)
		{
			down = true;
		}
		if (code == KeyEvent.VK_A)
		{
			left = true;
		}
		if (code == KeyEvent.VK_D)
		{
			right = true;
		}
		if (code == KeyEvent.VK_ESCAPE)
		{
			quit = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W)
		{
			up = false;
		}
		if (code == KeyEvent.VK_S)
		{
			down = false;
		}
		if (code == KeyEvent.VK_A)
		{
			left = false;
		}
		if (code == KeyEvent.VK_D)
		{
			right = false;
		}
		if (code == KeyEvent.VK_ESCAPE)
		{
			quit = true;
		}
	}

	public void move (int x, int y)
	{
		this.x += x*VELOCITY;
		this.y += y*VELOCITY;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.drawImage(image.getImage(), x, y, SIZE, SIZE, this);
	}
	
	public static void main(String[] args) 
	{
		JFrame jf = new JFrame("Move");
		Move m = new Move();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1280, 800);
		jf.setVisible(true);
		jf.add(m); 
	}
}
