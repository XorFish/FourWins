

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.*;


public class FourWins extends Frame implements WindowListener {
   char playField [][] = new char [7][7];
   char xo = 'O';
   boolean gameOver = false;
   int mousePressed = 0;
   static final int r = 20;
   FieldCanvas Field;

   FourWins(){
	   addWindowListener(this);
	   Dimension d=new Dimension(500, 500);
	   setSize(d);
	   Field=new FieldCanvas(playField, r);
	   Field.setCurrentPlayer("blue");
	   add(Field);
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

   
}