package konto

class SparKonto(inhaber: Person, val zinssatzPA: Double) extends Konto(inhaber) {
  
  override def ueberweisen(zielKonto: Konto, betragCt: Long): Unit = {
    require(saldoCt >= betragCt)
    super.ueberweisen(zielKonto, betragCt)
  }
  
  override protected def zinssatz(): Double = zinssatzPA
  
  override def verzinsen(anzahlTage: Int): Long = {
    val zinsen: Long = super.verzinsen(anzahlTage)
    _saldoCt += zinsen
    zinsen
  }
}