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
 *
 * @param <T> Der Datentyp der in der Schlange gespeicherten Elemente.
 * 
 * @author Hendrik Bodenstein (basierend auf Originalcode)
 * @author Gemini (�berarbeitungen und Verbesserungen)
 * @author ChatGPT (�berarbeitungen und Verbesserungen)
 * @version 1.1
 */
public class Queue<T> {
	/**
	 * Zur Verwaltung des ersten Elements der Schlange.
	 */
	Item head;
	/**
	 * Zur Verwaltung des letzten Elements der Schlange.
	 */
	private Item back;

	private int size;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private GUI gui = new GUI(this);

	/**
	 * Eine leere Schlange wird angelegt.
	 */
	public Queue() {
		head = null;
		back = null;
		size = 0;
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
	 * Gibt den Inhalt des ersten Elements der Schlange zur�ck, ohne es zu
	 * entfernen.
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
		size--;
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
		size++;
		back = i;
	}

	/**
	 * Gibt die L�nge der Schlange zur�ck. Wird f�r Visualisierung in Processing
	 * ben�tigt.
	 *
	 * @return Die L�nge der Schlange.
	 */
	int size() {
		return size;
	}

	/**
	 * Visualisierung einer Schlange in Processing.
	 *
	 * @param sketch Das PApplet-Objekt f�r die Darstellung.
	 */
	public void drawQueue(PApplet sketch) {
		gui.drawQueue(sketch);
	}

	/**
	 * Visualisierung einer Schlange in Processing an einer vorgegebenen H�he.
	 *
	 * @param sketch Das PApplet-Objekt f�r die Darstellung.
	 * @param y      Die vorgegebenen H�he.
	 */
	public void drawQueue(PApplet sketch, float y) {
		gui.drawQueue(sketch, y);
	}

	/**
	 * Klasse Item zur internen Verwaltung der einzelnen Elemente der dynamischen
	 * Reihung.
	 */
	class Item {
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