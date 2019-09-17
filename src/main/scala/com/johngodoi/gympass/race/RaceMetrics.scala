package com.johngodoi.gympass.race

import java.time.Duration

object RaceMetrics {
  def rank(records: List[LogRecord]):List[(Int, String, String, Int, Duration)] = {
    val rank = records
      .groupBy(r => r.piloto)
      .map(pr => (
        pr._2.map(r => r.codigoPiloto).head,
        pr._1,
        pr._2.map(r => r.nroVolta).max,
        pr._2.map(r => r.tempoVolta).reduce((d1, d2) => d1.plus(d2))
      )).toList.sortWith((pr1, pr2) => pr1._4.compareTo(pr2._4) < 0)
    (for (p <- rank.indices) yield (p+1, rank(p)._1, rank(p)._2, rank(p)._3, rank(p)._4)).toList
  }

  def findWinner(records: List[LogRecord]):(Int, String, String, Int, Duration) = rank(records).head

}