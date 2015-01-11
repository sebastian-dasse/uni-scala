package konto

class Konto(val inhaber: Person) {
  
  protected var _saldoCt: Long = 0
  
  def saldoCt(): Long = _saldoCt
  
  def einzahlen(betragCt: Long): Unit = {
    require(betragCt > 0)
    _saldoCt += betragCt
  }
  
  def ueberweisen(zielKonto: Konto, betragCt: Long): Unit = {
    require(betragCt > 0)
    _saldoCt -= betragCt
    zielKonto.einzahlen(betragCt)
  }
  
  def verzinsen(anzahlTage: Int): Long = 
    Math.round(saldoCt() * zinssatz * anzahlTage / 360)
  
  protected def zinssatz(): Double = 0.0
}

object Konto {
  def apply(inhaber: Person) = new Konto(inhaber)
  def apply(inhaber: Person, zinssatzPA: Double) = new SparKonto(inhaber, zinssatzPA)
  def apply(inhaber: Person, kreditrahmenCt: Long) = new GiroKonto(inhaber, kreditrahmenCt)
  
  var kreditzinssatzPA: Double = 0.0
}
