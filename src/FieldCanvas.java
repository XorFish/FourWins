import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class FieldCanvas extends Canvas implements MouseListener{

	private char[][] field;
	private boolean gameOver=false;
	private  int r;
	private String winner;
	private String currentPlayer="blue";
	int mousePressed=0;
	 public void drawCilcle (Graphics g, int x, int y) {
	      g.fillArc(x, y, 2*r, 2*r, 0, 360);
	   }
	public FieldCanvas() {
		// TODO Auto-generated constructor stub
	}
	public FieldCanvas(char[][] playfield, int radius) {
		// TODO Auto-generated constructor stub
		setField(playfield);
		setRadius(radius);
		setGameOver(false);
		addMouseListener(this);
		
	}
	void setField(char[][] playfield){
		field=playfield;
	}
	void setWinner(String Winner){
		winner=Winner;
	}
	void setRadius(int radius){
		r=radius;
	}
	void setCurrentPlayer(String Player){
		currentPlayer=Player;
	}
	void togglePlayer(){
		if(currentPlayer=="blue"){
			currentPlayer="red";
		} else {
			currentPlayer="blue";
		}
	}
	String getCurrentPlayer(){
		return currentPlayer;
	}
	void setGameOver(boolean go){
		gameOver=go;
	}
	boolean getGameOver(){
		return gameOver;
	}
	public void paint (Graphics graphics) {
		if(getWidth()<getHeight()-30){
			setRadius(getWidth()/16);
		} else {
			setRadius((getHeight()-30)/16);
		}
		Image image=createImage(getWidth(),getHeight());
		Graphics g=image.getGraphics();
	   	  /* Display the vertical lines */
	   	  for (int i = 1; i < 8; i++) {
	   	     g.drawLine(2*r*i, 0, 2*r*i, 14*r);
	   	  }
	   	  
	   	  /* Display the horizontal lines */
	   	  for (int i = 1; i < 8; i++) {
	  	     g.drawLine(0, 2*r*i, 14*r, 2*r*i);
	  	  }
	   	
	   	  /* Display the characters  */
	   	  for (int i = 0; i < 7; i++) {
		     for (int j = 0; j < 7; j++) {
	            if (field[i][j] == 'r') {
	            	g.setColor(Color.red);
					drawCilcle (g, 2*i*r, 2*j*r);
					
				}
	            if (field[i][j] == 'b') {
	            	g.setColor(Color.blue);
	            	drawCilcle (g, 2*i*r, 2*j*r);
				}
	         }
	      }
	   	  
	   	  /* Display the stat of the game */
	   	  if (gameOver == true) {
		     if(winner=="blue") {
	   			  g.setColor(Color.blue);
	   			  g.drawString("Das Spiel ist zu ende", 5,r*14+15);
	   			  g.drawString("blau hat gewonnen", 5, r*14+30);
	   			  
	   		} else if(winner=="red") {
	   			g.setColor(Color.red);
	   			g.drawString("Das Spiel ist zu ende", 5,r*14+15);
	   			g.drawString("rot hat gewonnen", 5, r*14+30);
	   		}
	   	  } else {
	   		 if(currentPlayer=="blue"){
	   			 g.setColor(Color.blue);
	   		 } else {
	   			 g.setColor(Color.red);
	   		 }
	   	     g.drawString(currentPlayer + " ist an der Reihe", 5, r*14+15);
	   	  }
	   	  graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	   	  
	   }
	
	
	public void mouseClicked(MouseEvent event) {
	   }

	   public void mouseEntered(MouseEvent event) {
	   }

	   public void mouseExited(MouseEvent event) {
	   }

	   public void mousePressed(MouseEvent event) {
	   	  int x = event.getX() / (2*r);

	      /* Do the player choose a valid play field ? */
	      if ((x < 7)) {
	      	 /* Is the game over ? */ 
	      	 if (!getGameOver()) {
	      	    /* Is the selected play field empty ? */ 
	      		 int nextfree=100;
	      	    for (int i=6;i>=0;--i) {
	      	    	if(field[x][i]==0){
	      	    		if(nextfree==100){
	      	    			nextfree=i;
	      	    		}
	      	    	}
	      	    }
	                
				/* Put the character in to the play field  */
	      	    if(nextfree<7){
	      	    	field [x][nextfree] = getCurrentPlayer().charAt(0);	
	                togglePlayer();
	         	    /* Increment the number of played characters */
	         	    mousePressed++;
	      	     }
	      	    
	      	     /* Is the game over ? */
	      	     if (mousePressed == 49) {
	      	    	setGameOver(true);
	      	     }
	  	 	}
	  	  	if(checkWinner()!=0&&(getGameOver()==false))
	  	  	{
	  	  		setGameOver(true);
	  	  		togglePlayer();
	  	  		setWinner(getCurrentPlayer());
	  	  	}
	  	  	repaint();
	      }
	   }
	   public char checkWinner () {
		     /* Check the columns */  
			 char old='n';
			 int same=1;
			 for (int i = 0; i < 7; ++i) {
				 for (int j = 0; j < 7; ++j) {
					 if(old==field[i][j]&&old!=0){
						 ++same;
					 } else{
						same=1;
						old=field[i][j];
					 }
					 if(same==4){
						 return old;
					 }
					 
				 }
				 old='n';      
		      }
			 for (int i = 0; i < 7; ++i) {
				 for (int j = 0; j < 7; ++j) {
					 if(old==field[j][i]&&old!=0){
						 ++same;
					 } else{
						same=1;
						old=field[j][i];
					 }
					 if(same==4){
						 return old;
					 }
					 
				 }
				 old='n';      
		      }
		      
		     
		      /* Check the diagonals */  
			 for(int i=0;i<4;++i){
		   	  for(int j=0;j<4;++j){
		   		  old=field[j][i];
		   		  char next=field[j+1][i+1];
		   		  same=1;
		   		  while(old==next&&old!=0){
		   			  same++;
		   			  if(j+same<7&&i+same<7){
		   			  next=field[j+same][i+same];
		   			  }
		   			  else{
		   				  next='n';
		   			  }
		   		  }
		   		  if(same>=4){
		   			  return old;
		   		  }
		   	  }
		     }
			 for(int i=0;i<4;++i){
		   	  for(int j=3;j<7;++j){
		   		  old=field[i][j];
		   		  char next=field[i+1][j-1];
		   		  same=1;
		   		  while(old==next&&old!=0){
		   			  same++;
		   			  if(i+same<7&&j-same>0){
		   			  next=field[i+same][j-same];
		   			  }
		   			  else{
		   				  next='n';
		   			  }
		   		  }
		   		  if(same>=4){
		   			  return old;
		   		  }
		   	  }
		     }
		      
		      return 0;
		   }
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
