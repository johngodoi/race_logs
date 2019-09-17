package com.johngodoi.gympass.race

import java.time.Duration

object RaceMetrics {

  private def groupByPilot(records: List[LogRecord]) = records.groupBy(_.name)

  def rankWithTimeDifferences(records: List[LogRecord]):List[(String,Duration)] = {
    val finishingTime = groupByPilot(records).map(pr => (pr._1, pr._2.map(_.time).max))
    val smallerFinishingTime = finishingTime.values.min
    finishingTime.map(ft =>(ft._1,Duration.between(ft._2,smallerFinishingTime)))
      .toList.sortWith((r1,r2) => r1._2.compareTo(r2._2)>0)
  }

  def averageSpeedByPilot(records: List[LogRecord]):List[(String,Double)] = groupByPilot(records)
    .map(pr => (
      pr._1,
      pr._2.map(_.lapAverageSpeed).sum/pr._2.size
    )).toList

  def bestLap(records: List[LogRecord]):(String,Duration) = groupByPilot(records)
    .map(r => (
      r._1,
      r._2.map(_.lapTime).min
    )).minBy(_._2)

  def bestLapsByPilot(records: List[LogRecord]):List[(String, Duration)] = groupByPilot(records)
    .map(pr => (
      pr._1,
      pr._2.map(_.lapTime).min
    )).toList

  def rank(records: List[LogRecord]):List[(Int, String, String, Int, Duration)] = {
    val rank = groupByPilot(records)
      .map(pr => (
        pr._2.map(_.code).head,
        pr._1,
        pr._2.map(_.lapNumber).max,
        pr._2.map(_.lapTime).reduce(_ plus _)
      )).toList.sortWith((pr1, pr2) => pr1._4.compareTo(pr2._4) < 0)
    (for (p <- rank.indices) yield (p+1, rank(p)._1, rank(p)._2, rank(p)._3, rank(p)._4)).toList
  }

  def findWinner(records: List[LogRecord]):(Int, String, String, Int, Duration) = rank(records).head

}
