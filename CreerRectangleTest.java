import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
  * Test permettant de vérifier le bon fonctionnement et la robustesse de la 
  * méthode de classe 'creerRectangle'.
  * 
  * @author Iffrig-Bourfa Dylan
  * @version version 1.0
  *
  */
public class CreerRectangleTest {
    
    // précision pour les comparaisons entre nombres réels
    public final static double EPSILON = 0.001;

    // Les points du sujet
    private Point A, B, C, D;

    @Before public void setUp() {
        A = new Point(5,2);
        B = new Point(10,2);
        C = new Point(10,5);
        D = new Point(5,5);


    }

/*    @Test
    public void testerOptionEnableAssertion() {
        try {
            assert false;
        } catch (AssertionError e) {
            // normal
            return;
        } catch (Throwable e) {
            // pas normal
        }
        fail("Il faut exécuter avec l'option -ea de java !");
    }

    @Test(expected=AssertionError.class)
    public void RobustesseCreerRectangle0() {
        Polygone.creerRectangle(null, A);
    }

    @Test(expected=AssertionError.class)
    public void RobustesseCreerRectangle1() {
        Polygone.creerRectangle(A, null);
    }

    @Test(expected=AssertionError.class)
    public void RobustesseCreerRectangle2() {
        Polygone.creerRectangle(null, null);
    }

    @Test(expected=AssertionError.class)
    public void RobustesseMemePoint() {
        Polygone.creerRectangle(A,A);
    }
*/

    @Test public void testPositionPoints() {
    	Point[] testPts = {A, B, C, D};
    
    	Polygone A_C = Polygone.creerRectangle(A,C);
    	Polygone B_D = Polygone.creerRectangle(B,D);
    	Polygone C_A = Polygone.creerRectangle(C,A);
    	Polygone D_B = Polygone.creerRectangle(D,B);
    
    	for(int i=0; i<4; i++){
    		memesCoordonnees("erreur de position", A_C.getSommet(i), testPts[i]);
    		memesCoordonnees("erreur de position", B_D.getSommet(i), testPts[i]);
    		memesCoordonnees("erreur de position", C_A.getSommet(i), testPts[i]);
    		memesCoordonnees("erreur de position", D_B.getSommet(i), testPts[i]);
    	}
    }

    @Test public void testEncapsulation() {   
    	Polygone C_A = Polygone.creerRectangle(A,C);
	    A.translater(2,5);
	    C.translater(2,5);
    	memesCoordonnees("erreur encapsulation des points", new Point(5,2), C_A.getSommet(0));
    	memesCoordonnees("erreur encapsulation des points", new Point(10,5), C_A.getSommet(2));
    }
	
    static void memesCoordonnees(String message, Point p1, Point p2) {
        assertEquals(message + " abscisse", p1.getX(), p2.getX(), EPSILON);
        assertEquals(message + " ordonnee", p1.getY(), p2.getY(), EPSILON);
    }

    public static void main(String[] args){
	    org.junit.runner.JUnitCore.main(CreerRectangleTest.class.getName());
    }
}
