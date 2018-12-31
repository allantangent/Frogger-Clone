import javax.swing.JFrame;

public class Frogger extends JFrame
{
    
    
    public Frogger()
    {
        add( new Board() );
        
        pack();//found in javadocs
        
        setTitle( "GAME" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
    }
    
    
    public static void main( String [] args )
    {
        Frogger run = new Frogger();
        run.setVisible( true );
    }
    
}
