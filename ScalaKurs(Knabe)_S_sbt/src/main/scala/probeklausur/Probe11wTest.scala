package probeklausur

import org.junit.Assert._
import org.junit.Test
import scala.annotation.tailrec

/**JUnit4-Testtreiber für die Probeklausur.*/
//Für Eclipse müssen Sie in der Datei .classpath hinter dem Scala- und Java-Container folgenden Eintrag ergänzen:
//   <classpathentry kind="con" path="org.eclipse.jdt.junit.JUNIT_CONTAINER/4"/>
class Probe11wTest {    
  
    //Aufgaben für 2012-01-23

    @Test def sumUpAufgabe {
      /*Wandeln Sie die folgende Scala-Funktion so um, 
       * dass sie keine Variablen verwendet, aber das gleiche Ergebnis berechnet.*/
//      def sumUp(f: Int => Int, n: Int): Int = {
//        var result = 0
//        var i = n
//        while(i>=1){
//          result += f(i)
//          i -= 1
//        }
//        result
//      }
      def sumUp(f: Int => Int, n: Int): Int = {
        if (n < 1) 0
        else f(n) + sumUp(f, n-1)
      }
      def sumUpTest(sumUp: (Int=>Int, Int) => Int){
        assertEquals(6, sumUp(j=>j, 3))
        assertEquals(45, sumUp(j=>j, 9))
        assertEquals(30, sumUp(j=>j*j, 4))
        assertEquals(55, sumUp(j=>j*j, 5))        
      }
      sumUpTest(sumUp) //Testet die Vorgabe.
      sumUpTest(sumUpLoesung) //Testet auf gleiche Weise die Lösung.
    }
    
    @Test def potenzAufgabe {
      /*Wandeln Sie die folgende rekursive Scala-Funktion in eine gleichbedeutende, 
       * endrekursive Formulierung um.*/
//      def potenz(basis: Int, exponent: Int): Int = {
//        if(exponent<=0) 1
//        else basis * potenz(basis, exponent-1)
//      }
      def potenz(basis: Int, exponent: Int): Int = {
        @tailrec
        def potenzTailRec(basis: Int, exponent: Int, accu: Int): Int = {
          if (exponent <= 0) accu
          else potenzTailRec(basis, exponent-1, basis*accu)
        }
        potenzTailRec(basis, exponent, 1)
      }
      def potenzTest(potenz: (Int, Int) => Int){
        assertEquals(1, potenz(2, 0))
        assertEquals(2, potenz(2, 1))
        assertEquals(4, potenz(2, 2))
        assertEquals(8, potenz(2, 3))
        assertEquals(1024, potenz(2, 10))    
        assertEquals(81, potenz(3, 4))     
      }
      potenzTest(potenz) //Testet die Vorgabe.
      potenzTest(potenzLoesung) //Testet auf gleiche Weise die Lösung. 
    }
    
    @Test def charCount {
      //Implementieren Sie die folgende Funktion mittels der 
      //Collection-Funktionen filter, map, foldLeft oder length:
      /**Liefert die Anzahl Zeichen in allen Elementen von list.*/
      def charCount(list: List[String]): Int =
//      /* Versteckte Implementierung: */ = _charCount(list)
//        println( list.map(s => s.length).foldLeft(0)((accu, elem) => accu + elem) )
        list.map(_.length).foldLeft(0)((accu, elem) => accu + elem)
      
      assertEquals(0, charCount(List()))
      assertEquals(0, charCount(List("", "")))
      assertEquals(6, charCount(List("a", "ab", "abc")))
      assertEquals(17, charCount(List("alpha", "beta", "gamma", "...")))
    }
    
    @Test def countNames {
      //Implementieren Sie die folgende Funktion mittels der 
      //Collection-Funktionen filter, map, foldLeft oder length:
      /**Liefert die Anzahl von mit Großbuchstaben beginnenden Elementen von list.*/
      def countNames(list: List[String]): Int =
//      /* Versteckte Implementierung: */ = _countNames(list)
        list.filter(s => s.length > 0 && s.charAt(0).isUpper).length

      assertEquals(0, countNames(List("", "")))
      assertEquals(0, countNames(List("a", "ab", "abc")))
      assertEquals(3, countNames(List("Adolf", "alpha", "Bernhard", "beta", "Caesar", "gamma", "...")))
    }
    
    @Test def specialCharCount {
      //Implementieren Sie die folgende Funktion mittels der 
      //Collection-Funktionen filter, map, foldLeft oder length:
      /**Liefert die Anzahl aller Zeichen in allen Elementen von list, 
       * die das Prädikat accept erfüllen.*/
      def specialCharCount(list: List[String], accept: Char=>Boolean): Int =
//      /* Versteckte Implementierung: */ = _specialCharCount(list, accept)
        list.foldLeft(0)((accu, elem) => accu + elem.filter(accept).length)
        
      assertEquals(0, specialCharCount(List(), _.isUpper))
      assertEquals(6, specialCharCount(List("Alpha", "beta", "GAMMA"), _.isUpper))
      assertEquals(7, specialCharCount(List("Aa", "AaBb", "abcdABC"), _.isLower))
    }
    
    //------- Immutable Lists 30.01.12: -----------
    
    @Test def reverseTest {
      //Implementieren Sie die folgende Funktion mittels der 
      //Collection-Funktionen filter, map, foldLeft oder length:
      /**Liefert eine unveränderbare Liste mit allen Elementen 
       * der übergebenen Liste in umgekehrter Reihenfolge.*/
      def reverse(list: List[String]): List[String] =
//      /* Versteckte Implementierung: */ = _reverse(list)
        list.foldLeft(List[String]())((accu, elem) => elem :: accu)
        
      assertEquals(List(), reverse(List()))
      assertEquals(List("gamma", "beta", "alpha"), reverse(List("alpha", "beta", "gamma")))
      assertEquals(List("g", "f", "e", "d", "c", "b", "a"), reverse(List("a", "b", "c", "d", "e", "f", "g")))
    }
    
    @Test def filterTest {
      //Implementieren Sie die folgende Funktion mittels der 
      //List-Funktionen ::, foldLeft und reverse:
      /**Liefert eine unveränderbare Liste mit allen Elementen von list, 
       * die dem Prädikat accept genügen. 
       * Die Reihenfolge der Elemente untereinander darf dabei nicht 
       * verändert werden.*/
      def filter(list: List[String], accept: String=>Boolean): List[String] =
//      /* Versteckte Implementierung: */ = _filter(list, accept)
        list.foldLeft(List[String]())(
          (accu, elem) => if (accept(elem)) elem :: accu else accu
        ).reverse
        
      assertEquals(List(), filter(List(), x=>true))
      assertEquals(List("alpha", "beta", "gamma"), filter(List("alpha", "beta", "gamma"), x=>true))
      assertEquals(List(), filter(List("alpha", "beta", "gamma"), x=>false))
      assertEquals(List("Alpha", "GAMMA"), filter(List("Alpha", "beta", "GAMMA"), x => x.charAt(0).isUpper))
    }
    
    @Test def concatTest {
      //Implementieren Sie die folgende Funktion mittels der 
      //Collection-Funktionen ::, foldLeft und reverse:
      /**Liefert eine unveränderbare Liste, die erst alle Elemente von a, 
       * dann alle Elemente von b in deren Eingangsreihenfolge enthält.
       * Enstpricht der Scala-Bibliotheksfunktion ::: für Listen.*/
      def concat(a: List[Int], b: List[Int]): List[Int] =
//	  /* Versteckte Implementierung: */ = _concat(a, b)
	    a.reverse.foldLeft(b)((accu, elem) => elem :: accu)
        
      assertEquals(List(), concat(List(), List()))
      assertEquals(List(99), concat(List(99), List()))
      assertEquals(List(11), concat(List(), List(11)))
      assertEquals(List(1,2,3,4,5,6), concat(List(1,2,3), List(4,5,6)))
    }
    
    @Test def mergeTest {
      //Implementieren Sie die folgende Funktion mittels der 
      //List-Funktionen ::, isEmpty, foldLeft und reverse:
      /**Liefert eine unveränderbare Liste, die alle Elemente von a
       * und alle Elemente von b enthält. 
       * Dabei sollen diese abwechselnd enthalten sein, also in der
       * Reihenfolge a1,b1,a2,b2,....
       * Wenn eine der beiden Listen kürzer ist als die andere,
       * sollen noch alle übrigen Elemente der anderen folgen.*/
      def merge(aList: List[Int], bList: List[Int]): List[Int] = {
        //-- version 1 --
//        def doMerge(aList: List[Int], bList: List[Int], accu: List[Int]): List[Int] = (aList, bList) match {
//          case (Nil, Nil) => accu
//          case (Nil, b :: bs) => doMerge(Nil, bs, b :: accu)
//          case (a :: as, Nil) => doMerge(as, Nil, a :: accu)
//          case (a :: as, b :: bs) => doMerge(as, bs, b :: a :: accu)
//        }
//        doMerge(aList, bList, Nil).reverse
        
        //-- version 2 --
        var merged = List[Int]();
        var as = aList;
        var bs = bList;
        while (!as.isEmpty && !bs.isEmpty) {
          merged = bs.head :: as.head :: merged
          as = as.tail
          bs = bs.tail
        }
        var rest = if (as.isEmpty) bs else as
        merged.foldLeft(rest)((accu, elem) => elem :: accu)        
      }
//      /* Versteckte Implementierung: */ = _merge(aList, bList)
        
      assertEquals(List(), merge(List(), List()))
      assertEquals(List(99), merge(List(99), List()))
      assertEquals(List(11), merge(List(), List(11)))
      assertEquals(List(1,2,3,4,5,6), merge(List(1,3,5), List(2,4,6)))
      assertEquals(List(1,2,3,4,5,6,7,9), merge(List(1,3,5,7,9), List(2,4,6)))
      assertEquals(List(1,2,3,4,5,6,8,10), merge(List(1,3,5), List(2,4,6,8,10)))
    }
    
    @Test def partitionTest {
      //Implementieren Sie die folgende Funktion mittels der 
      //List-Funktionen ::, isEmpty, foreach und reverse:
      /**Liefert ein Paar von unveränderbaren Listen mit den Elementen aus list.
       * In _1 sollen alle Elemente sein, die das Prädikat intoFirst erfüllen, 
       * in _2 alle anderen.
       * Die Aufteilung soll reihenfolgebewahrend erfolgen.*/
      def partition(list: List[Int], intoFirst: Int=>Boolean): 
      (List[Int], List[Int]) = {
        //-- version 1 --
//        list.reverse.foldLeft((List[Int](), List[Int]()))(
//          (accu, elem) =>
//            if (intoFirst(elem)) (elem :: accu._1, accu._2)
//            else (accu._1, elem :: accu._2)
//        )
        
        //-- version 2 --
        var first = List[Int]()
        var second = List[Int]()
        list.reverse.foreach(x => if (intoFirst(x)) first = x:: first else second = x :: second)
        (first, second)
      }
//      /* Versteckte Implementierung: */ = _partition(list, intoFirst)
      
      val pair = partition(List(1,2,3,4,5,6), x=>x<4)
      assertEquals(List(1,2,3), pair._1)
      assertEquals(List(4,5,6), pair._2)
      assertEquals((List(1,2,3), List(4,5,6)), pair)
      
      assertEquals((List(2,4,6), List(1,3,5)), partition(List(1,2,3,4,5,6), x=>x%2==0))
      assertEquals((Nil,Nil), partition(List(), x=>true))
      assertEquals((List(1,2,3,4,5,6),Nil), partition(List(1,2,3,4,5,6), x=>true))
      assertEquals((Nil,List(1,2,3,4,5,6)), partition(List(1,2,3,4,5,6), x=>false))
    }
    
    //Call-By-Name-Parameter: Steuerstrukturen definieren: -------------------------
    
    @Test def repeatTest {
      //Implementieren Sie eine REPEAT-UNTIL-Schleife, 
      //wie es sie in Pascal gibt.  
      //Der durch repeat gesteuerte Block soll wiederholt, 
      //aber mindestens einmal ausgeführt werden.
      //Wenn die until-Bedingung zum ersten Mal erfüllt ist, 
      //soll abgebrochen werden.
      //Sie können die Implementierung mit Hilfe der Scala-while-Schleife vornehmen.
      //Syntax:    repeat{
      //             anweisungen
      //           }(until = boolescherAusdruck)
      
      def repeat(body: => Unit)(until: => Boolean) = {
        body
        while (!until) {
          body
        }
      }
      
      //Testfall:
      var sum = 0
      var i = 0
      repeat{
        i += 1
        sum += i
      }(until = i>=9)
      assertEquals(45, sum)
    }    
    
    //Case-Klassen, Pattern Matching--------------------------------------------:
    //Gegeben sind die folgenden Klassendeklarationen zur Darstellung 
    //von arithmetischen Ausdrücken.
    //Val steht für einen Int-Wert, Add für die Addition, 
    //Mul für die Multiplikation.
   
    abstract class Expr
    case class Val(v: Int) extends Expr
    case class Add(a: Expr, b: Expr) extends Expr
    case class Mul(a: Expr, b: Expr) extends Expr
    
    //Implementieren Sie die folgenden beiden Funktionen 
    //mittels Pattern Matching.
    
    /**Liefert den Wert, der durch Berechnung des Ausdrucks entsteht.*/
    def calc(expr: Expr): Int = expr match {
      case Val(v) => v
      case Add(a, b) => calc(a) + calc(b)
      case Mul(a, b) => calc(a) * calc(b)
    }
//    /* Versteckte Implementierung: */ _calc(expr)  
    
    /**Liefert eine klammersparende String-Darstellung des Ausdrucks. */
    def stringize(expr: Expr): String = expr match {
      case Val(v) => v.toString
      case Add(a, b) => stringize(a) + "+" + stringize(b)
      case Mul(a, b) => {
        def paren(e: Expr) = e match {
          case Add(_, _) => "(" + stringize(e) + ")"
          case _ => stringize(e)
        }
        paren(a) + "*" + paren(b)
      }
    }
//    /* Versteckte Implementierung: */ _stringize(expr)
    
    @Test def exprTest {     
      {
        val expr = Add(Add(Val(2),Val(3)), Add(Val(4),Val(5)))
        assertEquals(14, calc(expr))
        assertEquals("2+3+4+5", stringize(expr))
      }  
      {
        val expr = Mul(Add(Val(2),Val(3)), Add(Val(4),Val(5)))
        assertEquals(45, calc(expr))
        assertEquals("(2+3)*(4+5)", stringize(expr))
      }  
      {
        val expr = Add(Add(Val(2), Mul(Val(3),Val(4))), Mul(Val(5),Val(6)))
        assertEquals(44, calc(expr))
        assertEquals("2+3*4+5*6", stringize(expr))
      }
    }
    
    ////////////////////////////////////// LÖSUNGEN //////////////////////////////////////////////////
    
    private def sumUpLoesung(f: Int => Int, n: Int): Int = {
      if(n<1) 0
      else f(n) + sumUpLoesung(f, n-1)
    }
    
    private def potenzLoesung(basis: Int, exponent: Int): Int = {
      tailRecPotenz(1, basis, exponent)
    }
    @tailrec private 
    def tailRecPotenz(accu: Int, basis: Int, exponent: Int): Int = {
      if(exponent<=0) accu
      else tailRecPotenz(basis*accu, basis, exponent-1)          
    }
    
    def _charCount(list: List[String]): Int
    = list.map(_.length).foldLeft(0)((accu,elem) => accu+elem)
    
    def _countNames(list: List[String]): Int
    = list.filter(s => s.length>0 && s.charAt(0).isUpper).length
    
    def _specialCharCount(list: List[String], accept: Char=>Boolean): Int
    = list.foldLeft(0)((accu,elem) => accu+elem.filter(accept(_)).length)
    
    def _reverse(list: List[String]): List[String] = {
      list.foldLeft(List[String]())((accu,elem)=>elem::accu)
    }
    
    def _filter(list: List[String], accept: String=>Boolean): List[String] = {
      val result = list.foldLeft(List[String]()){
        (accu,elem) => if(accept(elem)) elem::accu else accu
      }
      result.reverse
    }
    
    def _concat(a: List[Int], b: List[Int]): List[Int] = {
      a.reverse.foldLeft(b){
        (accu,elem) => elem :: accu
      }
    }

    def _merge(aList: List[Int], bList: List[Int]): List[Int] = {
      var mergedHead = List[Int]()
      var a = aList;   var b = bList
      while(!a.isEmpty && !b.isEmpty){
        mergedHead = b.head :: a.head :: mergedHead
        a = a.tail;   b = b.tail
      }
      val rest = if(a.isEmpty) b else a
      mergedHead.foldLeft(rest){
        (accu,elem) => 
          elem :: accu
      }
    }
    
    def _partition(list: List[Int], intoFirst: Int=>Boolean): (List[Int], List[Int]) = {
      var a = List[Int]()
      var b = List[Int]()
      list.reverse.foreach{
        x =>
        if(intoFirst(x)) a = x :: a else b = x :: b
      }
      (a, b)
    }
    
    def repeat(body: => Unit)(until: => Boolean) = {
      body
      while(!until){
        body
      }
    }
    
    def _calc(expr: Expr): Int = {
      expr match {
        case Val(x) => x
        case Add(x,y) => calc(x) + calc(y)
        case Mul(x,y) => calc(x) * calc(y)
      }
    }
    
    def _stringize(expr: Expr): String = {
      expr match {
        case Val(x) => x toString
        case Add(x,y) => stringize(x) + "+" + stringize(y)
        case Mul(x,y) => {
          def paren(a: Expr): String = a match {
            case Add(_,_) => "(" + stringize(a) + ")" 
            case _ => stringize(a)
          }
          paren(x) + "*" + paren(y)
        }
      }
    }    
    
}
