package com.johngodoi.gympass.race

import java.time.Duration

object RaceMetrics {

  def rankWithTimeDifferences(records: List[LogRecord]):List[(String,Duration)] = {
    val finishingTime = records
      .groupBy(_.piloto).map(pr => (pr._1, pr._2.map(_.time).max))
    val smallerFinishingTime = finishingTime.values.min
    finishingTime.map(ft =>(ft._1,Duration.between(ft._2,smallerFinishingTime)))
      .toList.sortWith((r1,r2) => r1._2.compareTo(r2._2)>0)
  }

  def averageSpeedByPilot(records: List[LogRecord]):List[(String,Double)] = records
    .groupBy(_.piloto)
    .map(pr => (
      pr._1,
      pr._2.map(_.velocidadeMediaVolta).sum/pr._2.size
    )).toList

  def bestLap(records: List[LogRecord]):(String,Duration) = records
    .groupBy(_.piloto)
    .map(r => (r._1,r._2.map(_.tempoVolta).min))
    .minBy(_._2)

  def bestLapsByPilot(records: List[LogRecord]):List[(String, Duration)] = records
    .groupBy(_.piloto)
    .map(pr => (
      pr._1,
      pr._2.map(_.tempoVolta).min
    )).toList

  def rank(records: List[LogRecord]):List[(Int, String, String, Int, Duration)] = {
    val rank = records
      .groupBy(_.piloto)
      .map(pr => (
        pr._2.map(_.codigoPiloto).head,
        pr._1,
        pr._2.map(_.nroVolta).max,
        pr._2.map(_.tempoVolta).reduce(_ plus _)
      )).toList.sortWith((pr1, pr2) => pr1._4.compareTo(pr2._4) < 0)
    (for (p <- rank.indices) yield (p+1, rank(p)._1, rank(p)._2, rank(p)._3, rank(p)._4)).toList
  }

  def findWinner(records: List[LogRecord]):(Int, String, String, Int, Duration) = rank(records).head

}
