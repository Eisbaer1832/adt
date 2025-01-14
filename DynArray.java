package adt;

import processing.core.PApplet;

/**
 * Implementierung der Klasse DynArray (Dynamische Reihung):
 * 
 * Die Implementierung nutzt zur Verwaltung der Inhaltsklasse der dynamischen
 * Reihung generische Datentypen. Das bedeutet, dass bei der Deklaration einer
 * dynamischen Reihung die zu verwendende Inhaltsklasse mit angegeben werden
 * muss. Die interne Verwaltung des Inhalts der dynamischen Reihung erfolgt �ber
 * eine interne Klasse Element.
 * 
 * Die Funktionalit�t und die Bezeichnungen der dynamischen Reihung entsprechen
 * den Vorgaben der Thematischen Schwerpunkte f�r die schriftliche Abiturpr�fung
 * 2018 in Informatik in Niedersachsen. Das bedeutet auch, dass in der
 * Implementierung keine "Absicherungen" enthalten sind, die z. B. das Entnehmen
 * bei einer leeren dynamischen Reihung verhindern.
 *
 * Alternativ lie�e sich auch eine spezielle Klasse Dynamische Reihung
 * implementieren, die die konkrete Inhaltsklasse der bearbeiteten
 * Aufgabenstellung verwendet. Dazu m�sste aber f�r jede Aufgabe ggf. eine neue
 * Klasse Dynamische Reihung erzeugt werden. Nutzt man als Inhaltsklasse die
 * sehr allgemeine Java-Klasse Object, so ist diese zwar universell nutzbar, man
 * ben�tigt beim Zugriff auf die dynamische Reihung dann aber h�ufig
 * Typecasting.
 * 
 * Update: Hendrik Bodenstein, 16.12.2024
 *
 * @param <Variablentyp> Der Typ der Elemente, die in der dynamischen Reihung gespeichert werden.
 */
public class DynArray<Variablentyp> {
    // Anfang Attribute
    private int laenge;
    private Element kopf;
    // Ende Attribute

    /**
     * Konstruktor f�r ein leeres DynArray.
     */
    public DynArray() {
        laenge = 0;
        kopf = null;
    }

    // Anfang Methoden

    /**
     * �berpr�ft, ob das DynArray leer ist.
     *
     * @return true, wenn das DynArray leer ist, sonst false.
     */
    public boolean isEmpty() {
        if (laenge == 0)
            return true;
        // else
        return false;
    }

    /**
     * Gibt das Element an einem bestimmten Index zur�ck.
     *
     * @param index Der Index des Elements.
     * @return Das Element am angegebenen Index oder null, wenn der Index ung�ltig ist.
     */
    public Variablentyp getItem(int index) {
        if (index >= 0 && index < laenge) {
            Element aktuell = kopf;
            for (int i = 0; i < index; i++) {
                aktuell = aktuell.naechstes;
            }
            return aktuell.inhalt;
        }
        return null;
    }

    /**
     * Gibt die Anzahl der Elemente im DynArray zur�ck.
     *
     * @return Die Anzahl der Elemente.
     */
    public int getLength() {
        return laenge;
    }

    /**
     * F�gt ein Element am Ende des DynArrays hinzu.
     *
     * @param inhalt Der Inhalt des hinzuzuf�genden Elements.
     */
    public void append1(Variablentyp inhalt) {
        if (!isEmpty()) {
            getElement(laenge).naechstes = new Element(inhalt);
        } else {
            kopf = new Element(inhalt);
        }
        laenge++;
    }

    /**
     * F�gt ein Element an einem bestimmten Index ein.
     *
     * @param index Der Index, an dem das Element eingef�gt werden soll.
     * @param inhalt Der Inhalt des einzuf�genden Elements.
     */
    public void insertAt(int index, Variablentyp inhalt) {
        if (index > 0 && index < laenge) { // Es wird "mittig" eingef�gt
            Element neu = new Element(inhalt);
            Element temp = getElement(index);
            getElement(index - 1).naechstes = neu;
            neu.naechstes = temp;
            laenge++;
        } else if (index == 0) { // Es wird vorne eingef�gt
            Element temp = kopf;
            kopf = new Element(inhalt);
            kopf.naechstes = temp;
            laenge++;
        } else if (index == laenge) { // Es wird am Ende hinzugef�gt
            append1(inhalt);
        }
    }

    /**
     * Setzt den Inhalt des Elements an einem bestimmten Index.
     *
     * @param index Der Index des zu setzenden Elements.
     * @param inhalt Der neue Inhalt des Elements.
     */
    public void setItem(int index, Variablentyp inhalt) {
        if (index > 0 && index <= laenge) {
            Element temp = getElement(index);
            temp.inhalt = inhalt;
        }
    }

    /**
     * L�scht das Element an einem bestimmten Index.
     *
     * @param index Der Index des zu l�schenden Elements.
     */
    public void delete(int index) {
        if (index > 0 && index < laenge) { // Es wird "mittig" gel�scht
            getElement(index - 1).naechstes = getElement(index - 1).naechstes.naechstes;
            laenge--;
        } else if (index == laenge - 1) { // Es wird das letzte Element gel�scht
            getElement(index - 1).naechstes = null;
            laenge--;
        } else if (index == 0) { // Es wird das erste Element gel�scht
            kopf = kopf.naechstes;
            laenge--;
        }
    }

    /**
     * Interne Hilfsoperation zum vereinfachten Zugriff auf die Elemente der Reihung
     * durch die �ffentlichen Operationen.
     *
     * @param index Der Index des gew�nschten Elements (beginnend bei 1).
     * @return Das Element am angegebenen Index oder null, wenn der Index ung�ltig ist.
     */
    private Element getElement(int index) {
        if (laenge >= index) {
            Element aktuell = kopf;
            for (int i = 1; i < index; i++) {
                aktuell = aktuell.naechstes;
            }
            return aktuell;
        }
        return null;
    }

    /**
     * Visualisierung eines DynArray in Processing.
     * @param sketch Der PApplet, auf dem gezeichnet wird.
     */
    public void drawDynArray(PApplet sketch) {
        drawDynArray(sketch, sketch.height/2);
    }

    /**
     * Visualisierung eines DynArray in Processing.
     * @param sketch Der PApplet, auf dem gezeichnet wird.
     * @param y Die y-Position der Darstellung.
     */
    @SuppressWarnings({ "static-access" })
    public void drawDynArray(PApplet sketch, float y) {
        if (laenge != 0) {

            float textSizeFactor = 0.4f;
            float itemWidth = (sketch.width - sketch.width / 50) / laenge;
            sketch.textSize(itemWidth * textSizeFactor);
            for (int i = 0; i < laenge; i++) {
                sketch.fill(255);
                sketch.rect(i * itemWidth + sketch.width / 100, y - itemWidth / 2, itemWidth, itemWidth);
                // Text in der Mitte der Zelle
                sketch.fill(0);
                sketch.textAlign(sketch.CENTER, sketch.CENTER); // Text zentrieren
                sketch.text(getItem(i).toString(), i * itemWidth + sketch.width / 100 + itemWidth / 2, y);
            }
        } else {
            System.out.println("Das DynArray ist leer!");
        }
    }

    // Ende Methoden

    /**
     * Klasse Element zur internen Verwaltung der einzelnen Elemente der
     * dynamischen Reihung.
     */
    private class Element {
        /**
         * Der Inhalt des Elements.
         */
        public Variablentyp inhalt;
        /**
         * Das n�chste Element in der Liste.
         */
        public Element naechstes;

        /**
         * Konstruktor f�r ein Element.
         *
         * @param inh Der Inhalt des Elements.
         */
        public Element(Variablentyp inh) {
            inhalt = inh;
            naechstes = null;
        }
    } // Ende der Klasse Element
}