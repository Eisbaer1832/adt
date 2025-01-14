package adt;

import processing.core.PApplet;

/**
 * Implementierung der Klasse Queue (Schlange):
 * <p>
 * Die Implementierung nutzt zur Verwaltung der Inhaltsklasse der Schlange
 * generische Datentypen. Das bedeutet, dass bei der Deklaration einer Schlange
 * die zu verwendende Inhaltsklasse mit angegeben werden muss. Die interne
 * Verwaltung des Inhalts der Schlange erfolgt �ber eine interne Klasse Item.
 * <p>
 * Die Funktionalit�t und die Bezeichnungen der Schlange entsprechen den
 * Vorgaben der Thematischen Schwerpunkte f�r die schriftliche Abiturpr�fung
 * 2018 in Informatik in Niedersachsen. Das bedeutet auch, dass in der
 * Implementierung keine "Absicherungen" enthalten sind, die z. B. das Entnehmen
 * bei einer leeren Schlange verhindern.
 * <p>
 * Alternativ lie�e sich auch eine spezielle Klasse Schlange implementieren, die
 * die konkrete Inhaltsklasse der bearbeiteten Aufgabenstellung verwendet. Dazu
 * m�sste aber f�r jede Aufgabe ggf. eine neue Klasse Schlange erzeugt werden.
 * Nutzt man als Inhaltsklasse die sehr allgemeine Java-Klasse Object, so ist
 * diese zwar universell nutzbar, man ben�tigt beim Zugriff auf die Schlange
 * dann aber h�ufig Typecasting.
 * <p>
 * Update: Hendrik Bodenstein, 17.12.2024
 *
 * @param <T> Der Datentyp der in der Schlange gespeicherten Elemente.
 */
public class Queue<T> {
    /**
     * Zur Verwaltung des ersten Elements der Schlange.
     */
    private Item head;
    /**
     * Zur Verwaltung des letzten Elements der Schlange.
     */
    private Item back;

    /**
     * Eine leere Schlange wird angelegt.
     */
    public Queue() {
        head = null;
        back = null;
    }

    /**
     * �berpr�ft, ob die Schlange leer ist.
     *
     * @return {@code true}, wenn die Schlange leer ist, {@code false} sonst.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Gibt den Inhalt des ersten Elements der Schlange zur�ck, ohne es zu entfernen.
     *
     * @return Der Inhalt des ersten Elements.
     */
    public T head() {
        return head.data;
    }

    /**
     * Gibt den Inhalt des ersten Elements der Schlange zur�ck und entfernt es.
     *
     * @return Der Inhalt des entfernten Elements.
     */
    public T dequeue() {
        T temp = head.data;
        head = head.next;
        return temp;
    }

    /**
     * F�gt ein neues Element mit dem �bergebenen Inhalt am Ende der Schlange hinzu.
     *
     * @param d Der Inhalt des neuen Elements.
     */
    public void enqueue(T d) {
        Item i = new Item(d);
        if (!isEmpty()) {
            back.next = i;
        } else {
            head = i;
        }
        back = i;
    }

    /**
     * Visualisierung einer Schlange in Processing.
     *
     * @param sketch Das PApplet-Objekt f�r die Darstellung.
     */
    public void drawQueue(PApplet sketch) {
        drawQueue(sketch, sketch.height / 2);
    }

    /**
     * Visualisierung einer Schlange in Processing mit angegebenen y-Wert.
     *
     * @param sketch Das PApplet-Objekt f�r die Darstellung.
     * @param y      Der y-Wert f�r die vertikale Position der Schlange.
     */
    @SuppressWarnings({"static-access"})
    public void drawQueue(PApplet sketch, float y) {
        if (!isEmpty()) {
            int groesse = 0;

            // L�nge der Schlange bestimmen
            Item aktuell = head;
            while (aktuell != null) {
                groesse++;
                aktuell = aktuell.next;
            }

            // Zeichnen der Schlange
            float textSizeFactor = 0.4f;
            float itemWidth = (sketch.width - sketch.width / 50) / groesse;
            float offsetX = sketch.width / 100;
            sketch.textSize(itemWidth * textSizeFactor);
            aktuell = head.next;

            for (int i = 1; i < groesse; i++) {
                sketch.strokeWeight(1);
                sketch.fill(255); // Wei�e F�llung f�r die Elemente
                sketch.stroke(0); // Schwarzer Rahmen

                // Rechteck f�r das aktuelle Element
                sketch.rect(i * itemWidth + offsetX, y - itemWidth / 2, itemWidth, itemWidth);

                // Text in der Mitte des Rechtecks
                sketch.fill(0); // Schwarzer Text
                sketch.textAlign(sketch.CENTER, sketch.CENTER); // Zentrierung
                sketch.text("" + aktuell.data, i * itemWidth + offsetX + itemWidth / 2, y);

                aktuell = aktuell.next;
            }

            // Zeichnen des head
            sketch.strokeWeight(3);
            sketch.fill(255);
            sketch.stroke(255, 255, 0); // Gelber Rahmen f�r head
            sketch.rect(offsetX, y - itemWidth / 2, itemWidth, itemWidth);

            // Text in der Mitte des Rechtecks
            sketch.fill(0); // Schwarzer Text
            sketch.textAlign(sketch.CENTER, sketch.CENTER); // Zentrierung
            sketch.text("" + head.data, offsetX + itemWidth / 2, y);

        } else {
            System.out.println("Die Queue ist leer!");
        }

    }

    /**
     * Klasse Item zur internen Verwaltung der einzelnen Elemente der dynamischen
     * Reihung.
     */
    private class Item {
        /**
         * Der Inhalt des Elements.
         */
        public T data;
        /**
         * Verweis auf das nachfolgende Element.
         */
        public Item next;

        /**
         * Erzeugt ein neues Item mit dem gegebenen Inhalt.
         *
         * @param d Der Inhalt des Items.
         */
        public Item(T d) {
            data = d;
            next = null;
        }
    }
}