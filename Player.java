import java.awt.event.KeyEvent;

public class Player extends Sprite
{
    public Player(int x, int y, String filename)
    {
        super( x, y, filename );
    }
    
    
    @Override
    public void move( int i )
    {
        setX( getX() + getdX() );
        setY( getY() + getdY() );
    }
    
    
    public void keyTyped( KeyEvent e )
    {
        char key = e.getKeyChar();
        if( key == 'a' )
            setX( getX() - 48 );
        if( key == 'd' )
            setX( getX() + 48 );
        if( key == 'w' )
            setY( getY() - 48 );
        if( key == 's' )
        {            
            setY( getY() + 48 );
        }
    }
    
    
    public void keyReleased( KeyEvent e )
    {
        setdX( 0 );
        setdY( 0 );
    }
    
    
}