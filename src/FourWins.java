

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.*;

public class FourWins extends Frame implements WindowListener,ActionListener {
   char playField [][] = new char [7][7];
   private MenuItem m_red,m_green,m_yellow,m_blue; 
 
   int mousePressed = 0;
   static final int r = 20;
   FieldCanvas Field;

   FourWins()
   {
	   addWindowListener(this);
	   Dimension d=new Dimension(500, 500);
	   setSize(d);
	   Field=new FieldCanvas(playField, r);
	   Field.setCurrentPlayer(1);
	   add(Field);
	   initializeMenue();
	   setVisible(true);
	   //setResizable(false);
	   
   }
	public static void main (String[] args) {
		   new FourWins();
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//Here we have the Menu where you can choose between the colors of the Players
	public void initializeMenue()
	{
		MenuBar mMenuBar=new MenuBar();
		setMenuBar(mMenuBar);
		Menu mMenu;
		//1.Menu Punkt
		mMenuBar.add(mMenu=new Menu("Option"));
		Menu mSubMenuPlayer = new Menu("Player");
		mMenu.add(mSubMenuPlayer);
		
		Menu mSubMenuPlayer1 = new Menu("Player1");
		Menu mSubMenuPlayer2 = new Menu("Player2");
		mSubMenuPlayer.add(mSubMenuPlayer1);
		mSubMenuPlayer.add(mSubMenuPlayer2);
		mSubMenuPlayer1.add(m_red= new MenuItem("Red"));
		mSubMenuPlayer1.add(m_blue= new MenuItem("Blue"));
		
		mSubMenuPlayer2.add(m_green= new MenuItem("Green"));
		mSubMenuPlayer2.add(m_yellow= new MenuItem("Yellow"));
	
		
		//we need to add the ActionListener to the MenueItems
		mMenu.addActionListener(this);
		mSubMenuPlayer1.addActionListener(this);
		mSubMenuPlayer2.addActionListener(this);
		m_red.addActionListener(this);
		m_blue.addActionListener(this);
		m_yellow.addActionListener(this);
		m_green.addActionListener(this);

		
	}
	@Override
	//Here we have the most important part of the menu
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==m_red)
		{
			Field.setPlayer1("red");
			Field.setPlayer1Color(Color.red);
			System.out.println("Player1=Red");
			Field.repaint();
		}
		if(e.getSource()==m_blue)
		{
			Field.setPlayer1("blue");
			Field.setPlayer1Color(Color.blue);
			System.out.println("Player1=blue");
			Field.repaint();
		}
		if(e.getSource()==m_yellow)
		{
			Field.setPlayer2("yellow");
			Field.setPlayer2Color(Color.yellow);
			System.out.println("Player2=yellow");
			Field.repaint();
		}
		if(e.getSource()==m_green)
		{
			Field.setPlayer2("green");
			Field.setPlayer2Color(Color.green);
			System.out.println("Player2=green");
			Field.repaint();
		}	
	}
}