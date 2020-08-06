import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable, KeyListener {

    Graphics gfx;
    Image img;
    Thread thread;
    Snake snake;
    boolean gameOver=false;
    Token token;


    public void init()
    {
      this.resize(400,400);
      img =createImage(400,400);
      gfx=img.getGraphics();
      this.addKeyListener(this);
      snake=new Snake();
      token=new Token(snake);

      thread=new Thread(this);
      thread.start();

    }
    public void paint(Graphics g)
    {
     gfx.setColor(Color.BLACK);
     gfx.fillRect(0,0,400,400);
     if(!gameOver)
     {
         snake.draw(gfx);
         token.draw(gfx);
     }
     else{
         gfx.setColor(Color.RED);
         gfx.drawString("GAME OVER",180,150);
         gfx.drawString("Score :  "+token.getScore(),180,170);
     }



     g.drawImage(img,0,0,null);
    }
    public void repaint(Graphics g)
    {
        paint(g);

    }
    public void update(Graphics g)
    {
        paint(g);

    }


    public void run() {
        for(;;)
        {
            if(!gameOver) {
                snake.move();
                checkGameOver();
                token.snakeCollision();
            }

            this.repaint();


            try {
                thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void checkGameOver()
    {
        if(snake.getX()<0 || snake.getX()>396)
        {
            gameOver=true;
        }
        if(snake.getY()<0 || snake.getY()>396)
        {
            gameOver=true;
        }
        if(snake.snakeCollision()){
            gameOver=true;
        }


    }



    public void keyTyped(KeyEvent keyEvent) {

    }


    public void keyPressed(KeyEvent e) {
        if(!snake.isMoving()) {
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_DOWN) {
                snake.setIsMoving(true);

            }
        }


      if(e.getKeyCode()==KeyEvent.VK_UP)
      {
           if(snake.getyDir() != 1)
           {
               snake.setyDir(-1);
               snake.setxDir(0);
           }
      }
      if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {

            if(snake.getyDir() != -1)
            {
                snake.setyDir(1);
                snake.setxDir(0);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if(snake.getxDir() != 1)
            {
                snake.setxDir(-1);
                snake.setyDir(0);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            if(snake.getxDir() != -1)
            {
                snake.setxDir(1);
                snake.setyDir(0);
            }
        }
    }


    public void keyReleased(KeyEvent keyEvent) {

    }
}
