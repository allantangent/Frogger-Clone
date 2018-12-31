import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class Board extends JPanel implements ActionListener
{
    private Image background;
    private Player player;
    private Timer timer;
    private boolean isGameOver;
    private ArrayList<Vehicle> lambo, truck, car, car2, truck2, lambo2, boat, boatRev, boat2; //vehicles and boats for each row
    private ArrayList<road> road;
    
    
    public Board()
    {
        //set up image
        ImageIcon image = new ImageIcon( "src/images/frogger background3.png" );
        background = image.getImage();
        
        //set up road 
        road = new ArrayList<road>();
        for( int i = 0; i < 20; i ++ )
        {
            int x = i * 200;
            int y = 47;
            road.add( new road( x, y, "road.png" ) );
            road.add( new road( x, y + 48, "road.png" ) );
            road.add( new road( x + 48, y + 48, "road.png" ) );
            road.add( new road( x + 48, y + 95, "road.png" ) );
        }
        
        //set up board size and stuff
        setPreferredSize( new Dimension( 1200, 565 ) );
        addKeyListener( new keyUpdater() );
      	setFocusable( true );
        
        //creates player
      	player = new Player( 590, 515, "frog walk.gif" ); //check y value bc it's weird
        isGameOver = false;
        
        //timer for refreshing game
        timer = new Timer( 10, this );
        timer.start();
        
        //create vehicle arrayLists
        lambo = new ArrayList<Vehicle>();
        truck = new ArrayList<Vehicle>();
        car = new ArrayList<Vehicle>();
        car2 = new ArrayList<Vehicle>();
        truck2  = new ArrayList<Vehicle>();
        lambo2 = new ArrayList<Vehicle>();
        
        //create boat arrayLists
        boat = new ArrayList<Vehicle>();
        boatRev = new ArrayList<Vehicle>();
        boat2 = new ArrayList<Vehicle>();
        
        //makes one row of logs
        //right
        for( int i = 0; i < 4; i++ ) 
        {
            boatRev.add(new Vehicle( 200 - ( i * 300 ), 48 , "boat.png", 1 ) );
        }
        
        //left
        for( int i = 0; i < 4; i++ ) 
        {
            boat.add(new Vehicle( 900 + ( i * 300 ), 94, "boat.png", 1 ) );
        }
        
        //right
        for( int i = 0; i < 4; i++ ) 
        {
            boat2.add(new Vehicle( 200 - ( i * 300 ), 146, "boat.png", 2 ) );
        }
        
        
        //filling arraylist with vehicles
        //going left
        for( int i = 0; i < 5; i++ ) 
        {
            truck.add( new Vehicle( 900 + ( i * 350 ), 476, "truck to left.png", 1 ) );
        }
            
        for( int i = 0; i < 4; i++ ) 
        {
            lambo.add( new Vehicle( 800 + ( i * 350 ), 428, "red car to left.png", 2 ) );
        }
        for( int i = 0; i < 3; i++ )
        {
            car.add( new Vehicle( 1000 + ( i * 350 ), 380, "blue car to left.png", 4 ) );
        }
        
        //going right
        for( int i = 0; i < 4; i++ )
        {
            truck2.add( new Vehicle( 200 - ( i * 350 ), 332, "truck to right.png", 1 ) );
        }
            
        for( int i = 0; i < 4; i++ )
        {
            lambo2.add( new Vehicle( 400 - ( i * 350 ), 284, "blue car to right.png", 2 ) );
        }
        
        for( int i = 0; i < 4; i++ )
        {
            car2.add( new Vehicle( 200 - ( i * 300 ), 236, "red car to right.png", 4 ) );
        }
    }
    
    
    @Override
    //learned about paintComponent from tutor and the Oracle provided class descriptions
    public void paintComponent( Graphics g )
    {   
        super.paintComponent( g );
        g.drawImage( background, 0, 0, null );
        Graphics2D g2d = ( Graphics2D )g;
        
        //draws road
        for( road x: road)
            g2d.drawImage( x.getSpritePic(), x.getX(), x.getY(), this );
        
        //draws boats
        for(Vehicle x : boatRev)
            if( x.getVis() )
                g2d.drawImage(x.getSpritePic(), x.getX(), x.getY(), this);
        
        for(Vehicle x : boat)
            if( x.getVis() )
                g2d.drawImage(x.getSpritePic(), x.getX(), x.getY(), this);
        
        for(Vehicle x : boat2)
            if( x.getVis() )
                g2d.drawImage(x.getSpritePic(), x.getX(), x.getY(), this);
        
        //draws vehicles
        for( Vehicle x: lambo )
            if( x.getVis() )
                g2d.drawImage( x.getSpritePic(), x.getX(), x.getY(), this );
        
        for( Vehicle x: lambo2 )
            if( x.getVis() )
                g2d.drawImage( x.getSpritePic(), x.getX(), x.getY(), this );
        
        for( Vehicle x: truck ) 
            if( x.getVis() )
                g2d.drawImage( x.getSpritePic(), x.getX(), x.getY(), this );
        
        for( Vehicle x: car )
            if( x.getVis() )
                g2d.drawImage( x.getSpritePic(), x.getX(), x.getY(), this );
        
        for( Vehicle x: car2 ) 
            if( x.getVis() )
                g2d.drawImage( x.getSpritePic(), x.getX(), x.getY(), this );
        
        for( Vehicle x: truck2 ) 
            if( x.getVis() )
                g2d.drawImage( x.getSpritePic(), x.getX(), x.getY(), this );
        
        
        g2d.drawImage( player.getSpritePic(), player.getX(), player.getY(), this );
    }

    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        //instructions for after game is over
        if( player.getY() + player.getHeight() <= 50)
        {
            timer.stop();
            int answer = JOptionPane.showConfirmDialog(null, "You've Won! New Game?"); //learned about JOptionPane from tutor
            
            if(answer == JOptionPane.YES_OPTION)
            {
                JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor( this );
                topFrame.getContentPane().removeAll();
                topFrame.getContentPane().add( new Board() );
                topFrame.setVisible( true );
            }
            else //if(answer == JOptionPane.CANCEL_OPTION)
            {
                System.exit(0); 
            }
        }
                
        if( isGameOver )
        {
            timer.stop();
            int answer = JOptionPane.showConfirmDialog(null, "Game Over. Try again?");
            
            if(answer == JOptionPane.YES_OPTION)
            {
                JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor( this );
                topFrame.getContentPane().removeAll();
                topFrame.getContentPane().add( new Board() );
                topFrame.setVisible( true );
            }
            else //if(answer == JOptionPane.CANCEL_OPTION)
            {
                
                System.exit(0); 
            }
        }
        
        
        if( player.getX() < - 6 ) 
        {
            player.setX( -6 );
            player.setdX( 0 );
        }
        
        if( player.getY() < -15 )
        {
            player.setY( -15 );
            player.setdY( 0 );
        }
        
        if( player.getX() > background.getWidth( this ) - 45 )
        {
            player.setX( background.getWidth( this ) - 45 );
            player.setdX( 0 );
        }
        
        if( player.getY() > background.getHeight( this ) - 50 )
        {
            player.setY( background.getHeight( this ) - 50 );
            player.setdY( 0 );
        }
        
        //new vehicle arraylist stuff. checks if still on screen
        for( int i = 0; i < lambo.size(); i++ )
        {
            Vehicle v = lambo.get( i );
            if( v.getVis() )
                v.move( 1 );
            //resets the car to the back again
            else
            {
                lambo.get(i).setX( 1200 );
                lambo.get(i).setVis(1);
            }
        }
        
        for( int i = 0; i < lambo2.size(); i++ )
        {
            Vehicle v = lambo2.get( i );
            if( v.getVis() )
                v.move( 0 );
            //resets the car to the back again
            else
            {
                lambo2.get(i).setX( -100 );
                lambo2.get(i).setVis(1);
            }
        }
        
        for( int i = 0; i < truck.size(); i++ )
        {
            Vehicle v = truck.get( i );
            if( v.getVis() )
                v.move( 1 );
            //resets the car to the back again
            else
            {
                truck.get(i).setX( 1200 );
                truck.get(i).setVis(1);
            }
           
        }
        
        for( int i = 0; i < car.size(); i++ )
        {
            Vehicle v = car.get( i );
            if( v.getVis() )
                v.move( 1 );
            //resets the car to the back again
            else
            {
                car.get(i).setX( 1200 );
                car.get(i).setVis(1);
            }
        }
        
        for( int i = 0; i < car2.size(); i++ )
        {
            Vehicle popo = car2.get( i );
            if( popo.getVis() )
                popo.move( 0 );
            //resets the car to the back again
            else
            {
                car2.get(i).setX( -100 );
                car2.get(i).setVis(1);
            }
        }
        
        for( int i = 0; i < truck2.size(); i++ )
        {
            Vehicle popo = truck2.get( i );
            if( popo.getVis() )
                popo.move( 0 );
            //resets the car to the back again
            else
            {
                truck2.get(i).setX( -100 );
                truck2.get(i).setVis(1);
            }
        }
        
        for( int i = 0; i < boat.size(); i++ )
        {
            Vehicle popo = boat.get( i );
            if( popo.getVis() )
                popo.move( 1 );
            //resets the boat to the back again
            else
            {
                boat.get(i).setX( 1300 );
                boat.get(i).setVis(1);
            }
        }
        
        for( int i = 0; i < boatRev.size(); i++ )
        {
            Vehicle popo = boatRev.get( i );
            if( popo.getVis() )
                popo.move( 0 );
            //resets the boat to the back again
            else
            {
                boatRev.get(i).setX( -100 );
                boatRev.get(i).setVis(1);
            }
        }
        
        for( int i = 0; i < boat2.size(); i++ )
        {
            Vehicle popo = boat2.get( i );
            if( popo.getVis() )
                popo.move( 0 );
            //resets the boat to the back again
            else
            {
                boat2.get(i).setX( -100 );
                boat2.get(i).setVis(1);
            }
        }
        
        collisionDetector(); //checks for collisions (got rid of collisionDetector class)
        repaint();
    }
    
    
    private class keyUpdater extends KeyAdapter
    {
        public void keyTyped( KeyEvent e )
        {
            player.keyTyped( e );
        }
    }
    
    
    public void collisionDetector()
    {
        //rectangle of player- used for checking collision of vehicles
        Rectangle r1 = player.getRectangle();
        
        for( Vehicle x: lambo )
        {
            Rectangle r2 = x.getRectangle();
            if( r1.intersects( r2 ) )
                isGameOver = true;
        }
        
        for( Vehicle x: lambo2 )
        {
            Rectangle r2 = x.getRectangle();
            
            if( r1.intersects( r2 ) )
                isGameOver = true;
        }
        
        for( Vehicle x: truck )
        {
            Rectangle r2 = x.getRectangle();
            if( r1.intersects( r2 ) )
                isGameOver = true;
        }
        
        for( Vehicle x: car )
        {
            Rectangle r2 = x.getRectangle();
            if( r1.intersects( r2 ) )
                isGameOver = true;
        }
        
        for( Vehicle x: car2 )
        {
            Rectangle r2 = x.getRectangle();
            if( r1.intersects( r2 ) )
                isGameOver = true;
        }
        
        for( Vehicle x: truck2 )
        {
            Rectangle r2 = x.getRectangle();
            if( r1.intersects( r2 ) )
                isGameOver = true;
        }
        
        //for when frog is on the road
        if( player.getY() + player.getHeight() < 200 &&  player.getY() + player.getHeight() > 50)
        {
            for( Vehicle x: boat )
            {
                Rectangle r2 = x.getRectangle();
                if( r1.intersects( r2 ) )
                    isGameOver = true;
            }

            for( Vehicle x: boatRev )
            {
                Rectangle r2 = x.getRectangle();
                if( r1.intersects( r2 ) )
                    isGameOver = true;
            }

            for( Vehicle x: boat2 )
            {
                Rectangle r2 = x.getRectangle();
                if( r1.intersects( r2 ) )
                    isGameOver = true;
            }
            
            //flag for if touching road
            boolean flag = false;
            
            for( road x : road )
            {
                //checks for coordinates of feet
                Rectangle r2 = x.getRectangle();
                int logXBR = player.getX();
                int logYBR = player.getY() + player.getHeight();

                int logXBL = player.getX() + player.getWidth();
                int logYBL = player.getY() + player.getHeight();


                if( r2.contains( logXBR, logYBR ) || r2.contains( logXBL, logYBL ) )
                {
                    flag = true;
                }
            }
            
            //if feet not on the road at all, lose
            if( !flag )
                isGameOver = true;
            }
    }   
}