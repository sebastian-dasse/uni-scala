package konto

class GiroKonto(inhaber: Person, val kreditrahmenCt: Long) extends Konto(inhaber) {
  require(kreditrahmenCt > 0)
  
  override def ueberweisen(zielKonto: Konto, betragCt: Long): Unit = {
    require(saldoCt + kreditrahmenCt >= betragCt)
    super.ueberweisen(zielKonto, betragCt)
  }
  
  override protected def zinssatz(): Double = Konto.kreditzinssatzPA
  
  override def verzinsen(anzahlTage: Int): Long = {
    if (saldoCt > 0) 0
    else {
      val zinsen: Long = super.verzinsen(anzahlTage)
      _saldoCt += zinsen
      zinsen
    }
  }
}