package ueb03;


public interface Folgengenerator {

    /**Liefert die Folge aller Ganzzahlen von 1 bis n als Zeichenkette, jeweils mit einem nachstehenden Zwischenraum.*/
    public String folge(final int n);
    
    /**Liefert die Folge aller Ganzzahlen von 1 bis n als Zeichenkette, jeweils mit einem nachstehenden Zwischenraum.
     * Das daraus ermittelte Ergebnis wird noch mit accu konkateniert.*/
    public String folge(final int n, final String accu);

}
