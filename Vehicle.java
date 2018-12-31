public class Vehicle extends Sprite
{
    private boolean visible;
    private int dx;
    
    
    public Vehicle( int x, int y, String filename )
    {
        super( x, y, filename );
        visible = true;
    }
    
    
    public Vehicle( int x, int y, String filename, int speed )
    {
        super( x, y, filename );
        visible = true;
        dx = speed;
    }
    
    
    public boolean getVis()
    {
        return visible;
    }
    
    
    //if i = 1, visible is true
    public void setVis( int i)
    {
        if( i == 1 )
            visible = true;
        else visible = false;
    }
    
    
    //if i = 1, then going left, else going right
    @Override
    public void move( int i )
    {
        if( i == 1 )
        {
            setX( getX() - dx );
            if( getX() <= -100  )
                visible = false;
        }
        
        else
        {
            setX( getX() + dx );
            if( getX() >= 1300  )
                visible = false;
        }
    }
    
    
}