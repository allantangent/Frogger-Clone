import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Sprite
{
    private int speedx, speedy, x , y, width, height;
    private Image spritePic;
    
    
    public Sprite( int startx, int starty, String filename )
    {
        setUpImage( filename );
        x = startx;
        y = starty;
        width = spritePic.getWidth( null );
        height = spritePic.getHeight( null );
    }
    
    
    public void setUpImage( String filepath )
    {
        ImageIcon i = new ImageIcon( "src/images/" + filepath );
        spritePic = i.getImage();
    }
    
    
    public abstract void move( int i );
    
    
    public int getX()
    {
        return x;
    }
    
    
    public int getY()
    {
        return y;
    }
    
    
    public void setX( int num )
    {
        x = num;
    }
    
    
    public void setdX( int num )
    {
        speedx = num;
    }
    
    
    public void setY( int num )
    {
        y = num;
    }
    
    
    public void setdY( int num )
    {
        speedy = num;
    }
    
    
    public int getdY()
    {
        return speedy;
    }
    
    
    public int getdX()
    {
        return speedx;
    }
    
    
    public Image getSpritePic()
    {
        return spritePic;
    }
    
    
    public void setSpritePic( ImageIcon i)
    {
        spritePic = i.getImage();
    }
    
    
    public int getWidth()
    {
        return width;
    }
    
    
    public int getHeight()
    {
        return height;
    }
    
    
    public Rectangle getRectangle()
    {
        return new Rectangle( x, y, width, height );
    }
    
    
}