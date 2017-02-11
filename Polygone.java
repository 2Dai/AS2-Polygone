import java.awt.Color;

/** Polygone modélise un polygone dans un plan munie d'un repère cartésien.
 * Un polygone contient un <i>un ensemble de points</i> et <i>une couleur</i>.
 * et il peut être affiché et translaté.
 * On a peut obtenir :
 * <ul>
 * <li>son odre</li>
 * <li>un sommet</li>
 * <li>le perimetre du polygone.</li>
 * </ul>
 * @author Dylan Iffrig-Bourfa
 * @version 1.0
 */

public class Polygone {

    /** E9: Ensemble des sommets qui composent le Polygone. */
    private Point[] sommets;
    /** E9: Couleur du polygone (indépendante de la couleur des points. */
    private Color couleur;

    // Constructeurs

	/** E7: Construire un polygnone à partir de sa couleur et d'une série de points.
	 * @param couleur : couleur du polygone.
     * @param arg : ensemble des sommets du polygone.
	 */
	public Polygone(Color couleur, Point...arg) {
        assert (couleur != null);
        this.setPolygone(arg);
        this.setCouleur(couleur);
    }

	/** E8: Construire un polygnone à partir d'une série de points.
	 * @param arg : ensemble des sommets du polygone.
	 */
	public Polygone(Point...arg) {
        this.setPolygone(arg);
        this.couleur = Color.blue;
    }

    // Getters et Setters

    /** Get polygone.
     * @return le polygone
     * @see Polygone#setPolygone
     */
    public Point[] getPolygone() {
        return sommets;
    }

    /** Set polygone.
     * @param arg tableau de points
     * @see Polygone#getPolygone
     */
    public void setPolygone(Point[] arg) {
		this.sommets = new Point[arg.length];
		for (int i = 0; i < arg.length; i++) {
			assert (arg[i] != null);
			Point pointx = new Point(arg[i].getX(), arg[i].getY());
            pointx.setCouleur(arg[i].getCouleur());
			this.sommets[i] = pointx;
    	}
	}

    /** E5: Changer la couleur du polygone.
     * @param nouvelleCouleur la nouvelle couleur.
     * @see Polygone#getCouleur
     */
    public void setCouleur(Color nouvelleCouleur) {
        assert (nouvelleCouleur != null);
	    this.couleur = nouvelleCouleur;
    }

    /** E5: Obtenir la couleur du polygone.
     * @return la couleur du polygone.
     * @see Polygone#setCouleur
     */
    public Color getCouleur() {
        return this.couleur;
    }

    //Actions

    /** E1: Translater le polygone.
     * @param dx : déplacement suivant l'axe X
     * @param dy : déplacement suivant l'axe Y
     */
    public void translater(double dx, double dy) {
        for (Point i : this.sommets) {
            i.translater(dx, dy);
        }
    }

    /** E2: Obtenir l'ordre du polygone.
     * @return l'ordre du polygone.
     */
    public int getOrdre() {
        return this.sommets.length;
    }

    /** E3: Obtenir un sommet du polygone.
     * @param index : l'index du sommet.
     * @return le sommet correspondant à l'index donnée.
     */
    public Point getSommet(int index) {
		assert (index < this.sommets.length && index > -1);
		if (index == -1) {
			index = this.sommets.length; //valeur -1 associé au dernier sommet
		}
        Point som = new Point(this.sommets[index].getX(), this.sommets[index].getY());
        som.setCouleur(this.sommets[index].getCouleur());
		return som;
    }

    /** E4: Obtenir le périmètre du polygone.
     * @return le périmètre du polygone.
     */
    public double perimetre() {
        double perim = 0;
        int index = 0;
        for (Point i : this.sommets) {
            //Calcul des distances entre les points du polygone.
            perim += i.distance(this.sommets[(index + 1) % this.getOrdre()]);
            index++;
        }
        return perim;
    }

    /** E6: Methode toString.
     * @return une chaine de caractère formaté pour l'affichage.
     */
    public String toString() {
        String result = "<< ";
        for (Point i : this.sommets) {
            result += i.toString() + " ";
        }
        return result + ">>";
    }

    /** E6: Afficher le polygone.
     * Il serra affiché de cette forme.
     *
     *          << (2.0, -1.0) (5.0, -1.0) (4.0, 3.0) >>
     *
     */
    public void afficher() {
        System.out.print(this);
    }

    /** E11: Ajouter un sommet au polygone
     * (le sommet est placé au niveau de l'index donné en argument).
     * @param index : l'endroit ou placer le sommet.
     * @param sommet : la valeur du point à ajouter.
     */
    public void ajouter(Point sommet, int index) {
   		assert (sommet != null && index <= this.getOrdre() && index >= 0);
		int i = 0;
		Point[] poly = new Point[this.getOrdre() + 1]; //redimention polygone.
		while (i < index) {
			poly[i] = this.sommets[i]; //ajout des premiers points
			i++;
		}
		poly[i] = new Point(sommet.getX(), sommet.getY()); //ajout nouveau point
        poly[i].setCouleur(sommet.getCouleur());
		while (i < this.getOrdre()) {
			poly[i + 1] = this.sommets[i]; //ajout du reste des points
			i++;
    	}
		this.sommets = poly;
	}

    /** E11 : Ajouter un sommet au polygone
     * (le sommet est placé en bout de polygone).
     * @param sommet : la valeur du point à ajouter.
     */
    public void ajouter(Point sommet) {
		assert (sommet != null);
		Point[] poly = new Point[this.getOrdre() + 1];
		for (int i = 0; i < this.getOrdre(); i++) {
			poly[i] = this.sommets[i];
		}
		poly[this.getOrdre()] = new Point(sommet.getX(), sommet.getY());
        poly[this.getOrdre()].setCouleur(sommet.getCouleur());
		this.sommets = poly;
	}

    /** E10 : Methode de classe qui génére un rectangle à partir de 2 points
     * non adjacents, le résultat est retourné dans le sens direct en commencant
     * par le point de plus petite abscisse.
     * @param pA : point initiale.
     * @param pB : second point non adjacent au premier.
     * @return le rectangle d'ordre 4.
     */
    public static Polygone creerRectangle(Point pA, Point pB) {
        assert (pA != null && pB != null);

        Point p1, p2, p3, p4;
	    double x0, y0, x2, y2;
	    Polygone rectangle;
	    x2 = pB.getX(); // On initialise avec une valeur par défaut.
	    y2 = pB.getY();	//
	    x0 = pA.getX(); //
	    y0 = pA.getY(); //

	    if (pA.getX() > pB.getX()) { //On cherche le plus petit abscisse
		    x0 = pB.getX();
		    x2 = pA.getX();
    	}
	    if (pA.getY() > pB.getY()) { //On cherche la plus petite ordonée
		    y0 = pB.getY();
		    y2 = pA.getY();
	    }

	    p1 = new Point(x0, y0); // le point initiale,(plus petite abs, ord)
	    p2 = new Point(x2, y0); // on tourne dans le sens direct
	    p3 = new Point(x2, y2);
    	p4 = new Point(x0, y2);
	    rectangle = new Polygone(p1, p2, p3, p4);
        return rectangle;
    }
}
