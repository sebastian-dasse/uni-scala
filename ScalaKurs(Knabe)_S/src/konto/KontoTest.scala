package konto

import org.junit.Assert._
import org.junit.Test



/**JUnit4-Testtreiber für die zu erstellende Konto-Klassenhierarchie sowie das Konto-Begleitobjekt.
 * @author Christoph Knabe 2014-10-28*/
//Für Eclipse müssen Sie in der Datei .classpath hinter dem Scala- und Java-Container folgenden ergänzen:
//   <classpathentry kind="con" path="org.eclipse.jdt.junit.JUNIT_CONTAINER/4"/>
class KontoTest {    

  @Test def buchungen(){
    //Expliziter Aufruf der Fabrikmethode apply aus dem Begleitobjekt Konto: 
    val knabeKonto = Konto.apply(new Person("Knabe"))
    val schmidtKonto = Konto.apply(new Person("Schmidt"))    
    assertEquals(0, knabeKonto.saldoCt)
    assertEquals(0, schmidtKonto.saldoCt)
    
    knabeKonto.einzahlen(10000)
    assertEquals(10000, knabeKonto.saldoCt)
    
    knabeKonto.ueberweisen(schmidtKonto, 3333)
    assertEquals(6667, knabeKonto.saldoCt)
    assertEquals(3333, schmidtKonto.saldoCt)
    
    //Einzahlungen müssen positiv sein!
    def verboteneEinzahlungenPruefen(konto: Konto, betraege: Long*){
      for(betrag<-betraege) _exceptionExpected(konto.einzahlen(betrag))
    }
    verboteneEinzahlungenPruefen(knabeKonto, 0, -1, -999)
    verboteneEinzahlungenPruefen(schmidtKonto, 0, -1, -999)
    
    //Nur positive Beträge dürfen überwiesen werden!
    def verboteneUeberweisungenPruefen(betraege: Long*){
      for(betrag<-betraege) _exceptionExpected(knabeKonto.ueberweisen(schmidtKonto, betrag))
    }
    verboteneUeberweisungenPruefen(0, -1, -999)
    verboteneUeberweisungenPruefen(0, -1, -999)
  }
  
  @Test def sparKontoBuchungen(){
    //Impliziter Aufruf ohne Methodenbezeichner der Fabrikmethoden apply aus dem Begleitobjekt Konto: 
    val knabeKonto = Konto(new Person("Knabe"), 0.02f) //SparKonto mit Guthabenzinssatz
    val schmidtKonto = Konto(new Person("Schmidt")) //Einfaches Konto ohne Zinssatz und Kreditrahmen    
    assertEquals(0, knabeKonto.saldoCt)
    assertEquals(0, schmidtKonto.saldoCt)
    
    knabeKonto.einzahlen(10000)
    assertEquals(10000, knabeKonto.saldoCt)
    
    knabeKonto.ueberweisen(schmidtKonto, 3333)
    assertEquals(6667, knabeKonto.saldoCt)
    assertEquals(3333, schmidtKonto.saldoCt)
    
    knabeKonto.ueberweisen(schmidtKonto, 6667)
    assertEquals(0, knabeKonto.saldoCt)
    assertEquals(10000, schmidtKonto.saldoCt)
    
    //Das knabeKonto ist jetzt leer. Da es ein SparKonto ist, darf nicht weiter darüber verfügt werden!
    try{
      knabeKonto.ueberweisen(schmidtKonto, 1)
      fail("expected: IllegalArgumentException")
    }catch{
      case expected: IllegalArgumentException => 
    }
    //Dieser Überweisungsversuch darf nichts an den Salden geändert haben:
    assertEquals(0, knabeKonto.saldoCt)
    assertEquals(10000, schmidtKonto.saldoCt)
  }
  
  @Test def giroKontoBuchungen(){
    val kreditrahmen = 50000
    val knabeKonto = Konto(new Person("Knabe"), kreditrahmen) //GiroKonto mit Kreditrahmen
    val schmidtKonto = Konto(new Person("Schmidt"), 0.02f) //SparKonto mit Guthabenzinssatz    
    assertEquals(0, knabeKonto.saldoCt)
    assertEquals(0, schmidtKonto.saldoCt)
    
    knabeKonto.einzahlen(10000)
    assertEquals(10000, knabeKonto.saldoCt)
    
    knabeKonto.ueberweisen(schmidtKonto, 3333)
    assertEquals(6667, knabeKonto.saldoCt)
    assertEquals(3333, schmidtKonto.saldoCt)
    
    knabeKonto.ueberweisen(schmidtKonto, 6667)
    assertEquals(0, knabeKonto.saldoCt)
    assertEquals(10000, schmidtKonto.saldoCt)
    
    knabeKonto.ueberweisen(schmidtKonto, kreditrahmen)
    assertEquals(-kreditrahmen, knabeKonto.saldoCt)
    assertEquals(60000, schmidtKonto.saldoCt)
    
    //Das knabeKonto hat jetzt den Kreditrahmen erschöpft. Es darf nicht weiter darüber verfügt werden!
    try{
      knabeKonto.ueberweisen(schmidtKonto, 1)
      fail("expected: IllegalArgumentException")
    }catch{
      case expected: IllegalArgumentException => 
    }
    //Dieser Überweisungsversuch darf nichts an den Salden geändert haben:
    assertEquals(-50000, knabeKonto.saldoCt)
    assertEquals(60000, schmidtKonto.saldoCt)
  }
  
  @Test def sparzinsen(){
    val sparKonto = Konto(new Person("Knabe"), 0.03f) //SparKonto mit Guthabenzinssatz
    sparKonto.einzahlen(100000)
    assertEquals(100000, sparKonto.saldoCt)
    
    {
      val result = sparKonto.verzinsen(360)
	  assertEquals(3000, result)
      assertEquals(103000, sparKonto.saldoCt)
    }
    {
	  val result = sparKonto.verzinsen(10) //Kaufmännisch gelten 360 Tage als ein Jahr
      assertEquals(86, result)
      assertEquals(103086, sparKonto.saldoCt)
    }
  }
  
  @Test def kreditzinsen(){
    val giroKonto = Konto(new Person("Knabe"), 10000) //GiroKonto mit Kreditrahmen
    giroKonto.einzahlen(100000)
    assertEquals(100000, giroKonto.saldoCt)
    
    {
      //Guthaben werden auf einem Girokonto nicht verzinst:
      val result = giroKonto.verzinsen(360) //Kaufmännisch gelten 360 Tage als ein Jahr
	    assertEquals(0, result)
      assertEquals(100000, giroKonto.saldoCt)
    }
    
    val zielKonto = Konto(new Person("Knabe"))
    giroKonto.ueberweisen(zielKonto, 110000)
    assertEquals(-10000, giroKonto.saldoCt)
    Konto.kreditzinssatzPA = 0.10 // 10% per annum
    
    {
      val result = giroKonto.verzinsen(360) //Kaufmännisch gelten 360 Tage als ein Jahr
      assertEquals(-1000, result)
      assertEquals(-11000, giroKonto.saldoCt)
    }
    {
      val result = giroKonto.verzinsen(10) //Kaufmännisch gelten 360 Tage als ein Jahr
      assertEquals(-31, result)
      assertEquals(-11031, giroKonto.saldoCt)
    }
  }
  
  /**Wirft einen AssertionError, wenn bei der Ausführung von action
   *  keine IllegalArgumentException geworfen wird.*/
  private def _exceptionExpected(action: => Unit){
    try{
	  action
	  fail("expected: IllegalArgumentException")
	}catch{
	  case expected: IllegalArgumentException => 
	}
  }
    
    
}
