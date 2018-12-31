
import java.awt.Rectangle;

public class Logs extends Sprite
{
    private boolean visible, onLog;
    
    public Logs(int x, int y, String filename, int speed)
    {
        super( x, y, filename );
        visible = true;
        onLog = false;
        setdX(speed);
    }
    
    public void move( int i )
    {
        
        if( i == 1 )
        {
            setX( getX() - getdX() );
            if( getX() <= -100  )
                visible = false;
        }
        
        else
        {
            setX( getX() + getdX() );
            if( getX() >= 1300  )
                visible = false;
        }
        
    }
    //i is speed at which logs move
   
    public boolean getVis()
    {
        return visible;
    }
    
    public void setVis( int i)
    {
        if( i == 1 )
            visible = true;
        else visible = false;
    }
    
    public boolean onLog()
    {
        return onLog;
    }
    
    public void moveFrogger( Player s )
    {
        Rectangle r1 = this.getRectangle();
        int logXTR = s.getX();
        int logYTR = s.getY();
                
        int logXTL = s.getX() + s.getWidth();
        int logYTL = s.getY();
        
        int logXBR = s.getX();
        int logYBR = s.getY() + s.getHeight();
        
        int logXBL = s.getX() + s.getWidth();
        int logYBL = s.getY() + s.getHeight();
        
        if( r1.contains( logXBR, logYBR ) && r1.contains( logXBL, logYBL ) )
        {
            if(s.getY() >= 75)
                s.setX( s.getX() + getdX() );
            else
                s.setX( s.getX() - getdX() );
            onLog = true;
        }
        
        
        
        
    }
    
    
    
    
    
}