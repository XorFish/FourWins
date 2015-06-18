import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class FieldCanvas extends Canvas implements MouseListener{

	private char[][] field;
	private boolean gameOver=false;
	private  int _r;
	private String _winner;
	private String _player1;
	private String _player2;
	private Color _player1Color;
	private Color _player2Color;
	
	private String currentPlayer="blue";
	int mousePressed=0;
	
	public void drawCilcle (Graphics g, int x, int y) {
	      g.fillArc(x, y, 2*_r, 2*_r, 0, 360);
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
		_player1="blue";
		_player2="yellow";
		setPlayer1Color(Color.blue);
		setPlayer2Color(Color.yellow);
		
	}
	void setPlayer1(String player1){
		if(currentPlayer==_player1){
			currentPlayer=player1;
		}
		_player1=player1;
	}
	String getPlayer1(){
		return _player1;
	}
	void setPlayer1Color(Color player1Color){
		_player1Color=player1Color;
	}
	Color getPlayer1Color(){
		return _player1Color;
	}
	Color getPlayer2Color(){
		return _player2Color;
	}
	void setPlayer2Color(Color player2Color){
		_player2Color=player2Color;
	}
	
	String getPlayer2(){
		return _player2;
	}
	void setPlayer2(String player2){
		if(currentPlayer==_player2){
			currentPlayer=player2;
		}
		_player2=player2;
	}
	
	void setField(char[][] playfield){
		field=playfield;
	}
	void setWinner(String Winner){
		_winner=Winner;
	}
	void setRadius(int radius){
		_r=radius;
	}
	void setCurrentPlayer(int player){
		if(player==1){
			currentPlayer=_player1;
		} else if(player==2){
			currentPlayer=_player2;
		}
	}

	char togglePlayer(){
		if(currentPlayer==_player1){
			currentPlayer=_player2;
			return '1';
		} else {
			currentPlayer=_player1;
			return '2';
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
			setRadius(getWidth()/14);
		} else {
			setRadius((int)((getHeight()-30)/14.5));
		}
		Image image=createImage(getWidth(),getHeight());
		Graphics g=image.getGraphics();
		g.setColor(Color.lightGray);
		
		g.fillRoundRect(_r*10, _r*14+2, 4*_r,_r,_r/4, _r/4);
		g.setColor(Color.BLACK);
		g.drawString("Restart", _r*10+_r/2, _r*14+2+_r/2+4);
	   	  /* Display the vertical lines */
		
	   	  for (int i = 1; i < 8; i++) {
	   	     g.drawLine(2*_r*i, 0, 2*_r*i, 14*_r);
	   	  }
	   	  
	   	  /* Display the horizontal lines */
	   	  for (int i = 1; i < 8; i++) {
	  	     g.drawLine(0, 2*_r*i, 14*_r, 2*_r*i);
	  	  }
	   	
	   	  /* Display the characters  */
	   	  for (int i = 0; i < 7; i++) {
		     for (int j = 0; j < 7; j++) {
	            if (field[i][j] == '1') {
	            	g.setColor(_player1Color);
					drawCilcle (g, 2*i*_r, 2*j*_r);
					
				}
	            if (field[i][j] == '2') {
	            	g.setColor(_player2Color);
	            	drawCilcle (g, 2*i*_r, 2*j*_r);
				}
	         }
	      }
	   	  
	   	  /* Display the stat of the game */
	   	  if (gameOver == true) {
		     if(_winner==_player1) {
	   			  g.setColor(_player1Color);
	   			  g.drawString("Game Over", 5,_r*14+15);
	   			  g.drawString("Player " +_player1+" wins", 5, _r*14+30);
	   			  
	   		} else if(_winner==_player2) {
	   			g.setColor(_player2Color);
	   			g.drawString("Game Over", 5,_r*14+15);
	   			g.drawString("Player " +_player2+" wins", 5, _r*14+30);
	   		}
	   	  } else {
	   		 if(currentPlayer==_player1){
	   			 g.setColor(_player1Color);
	   		 } else {
	   			 g.setColor(_player2Color);
	   		 }
	   	     g.drawString(currentPlayer + "'s turn", 5, _r*14+15);
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
	   	  int x = event.getX() / (2*_r);

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
	      	    	field [x][nextfree] = togglePlayer();
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
	  	  	if((event.getX() >= _r * 10) && (event.getX() <= 14 * _r) && (event.getY() >= _r * 14 + 2) && (event.getY() <= _r * 15 + 2)){
	  	  		field=new char [7][7];
	  	  		gameOver=false;
	  	  		mousePressed=0;
	  	  		togglePlayer();
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
