import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
  * Test complémentaires à SujetPolygoneTest (ajout de cas limite)
  *
  * @author Iffrig-Bourfa Dylan
  * @version 1.0
  */

public class PolygoneTest {

	// précision pour les comparaisons entre nombres réels
	public final static double EPSILON = 0.1;

	// Les points du sujet
	private Point A, B, C, D, E, F;
	private Point R, S, T;
	private Point I, J, K, L;

	// Les polygones du sujet
	private Polygone AJ;
	private Polygone RST;
	private Polygone IJKL;
    private Polygone AA;
    private Polygone pA;


	@Before public void setUp() {
		// Construire les points
		A = new Point(9, 1);
		B = new Point(9, 1);

		R = new Point(2, -1);
		S = new Point(5, -1);
		T = new Point(4, 3);

		I = new Point(-3, 4);
		J = new Point(1, 1);
		K = new Point(1, 4);
		L = new Point(-3, 1);

		// Construire les polygones
		AA = new Polygone(A, B); // Polygone meme points
        AJ = new Polygone(A, J); // Polygone segments
        pA = new Polygone (A); // Polygone point
		RST = new Polygone(R, S, T);
		IJKL = new Polygone(I, J, K, L);
	}

    @Test public void testerInitialiserpA() {
        memesCoordonnees("erreur sommet pA", A, pA.getSommet(0));
    }

    @Test public void testerInitialiserAA() {
        String message = "Erreur initialisation d'un polygone de memes points";
        memesCoordonnees(message, A, AA.getSommet(0));
        memesCoordonnees(message, A, AA.getSommet(1));
    }

    @Test public void testerOrdre() {
        assertEquals("Ordre incorrect", 2, AA.getOrdre());
        assertEquals("Ordre incorrect", 1, pA.getOrdre());
    }

    @Test public void testerToString() {
        assertEquals("E6: toString() redéfinie ? Correctement ?",
                "<< (9.0, 1.0) (9.0, 1.0) >>", AA.toString());
        assertEquals("E6: toString() redéfinie ? Correctement ?",
                "<< (9.0, 1.0) >>", pA.toString());           
    }

    @Test public void testerPerimetre() {
        assertEquals("Perimetre Incorrect.", 16, IJKL.perimetre(), EPSILON);
        assertEquals("Perimetre Incorrect.", 11.6, RST.perimetre(), EPSILON);
        assertEquals("Perimetre Incorrect.", 0, AA.perimetre(), EPSILON);
        assertEquals("Perimetre Incorrect.", 0, pA.perimetre(), EPSILON);
        assertEquals("Perimetre Incorrect.", 16, AJ.perimetre(), EPSILON);
    }

    static void memesCoordonnees(String message, Point p1, Point p2) {
        assertEquals(message + " (abscisses différentes)", p1.getX(),
                                                p2.getX(), EPSILON);
        assertEquals(message + " (ordonnées différentes)", p1.getY(),
                                                p2.getY(), EPSILON);
    }


	public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(PolygoneTest.class.getName());
	}

}
