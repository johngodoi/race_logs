package com.johngodoi.gympass.race

import java.time.Duration

object RaceMetrics {
  def bestLap(records: List[LogRecord]):(String,Duration) = records
    .map(r => (r.piloto,r.tempoVolta))
    .groupBy(r => r._1)
    .map(r => (r._1,r._2.map(pr=>pr._2).min))
    .minBy(_._2)

  def bestLapsByPilot(records: List[LogRecord]):List[(String, Duration)] = records.groupBy(r => r.piloto)
    .map(pr => (pr._1, pr._2.map(r=>r.tempoVolta).min)).toList

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
