package com.johngodoi.gympass.race

import java.time.Duration

object RaceMetrics {

  private def groupByPilot(records: List[LogRecord]) = records.groupBy(_.name)

  def rankWithTimeDifferences(records: List[LogRecord]) = {
    val finishingTime = processByPilot(xs => xs.map(_.time).max)(records)
    val smallerFinishingTime = finishingTime.values.min
    finishingTime.mapValues(Duration.between(_,smallerFinishingTime)).toList.sortBy(_._2).reverse
  }

  def averageSpeedByPilot(records: List[LogRecord]) = processByPilot(xs => xs.map(_.lapAverageSpeed).sum/xs.size)(records).toList

  def bestLapsByPilot(records: List[LogRecord]) = processByPilot(xs => xs.map(_.lapTime).min)(records).toList

  def processByPilot[A](f: List[LogRecord] => A)(records: List[LogRecord]) = groupByPilot(records).mapValues(f(_))

  def bestLap(records: List[LogRecord]) = bestLapsByPilot(records).minBy(_._2)

  def rank(records: List[LogRecord]) = {
    val rank = groupByPilot(records)
      .map(pr => (
        pr._2.map(_.code).head,
        pr._1,
        pr._2.map(_.lapNumber).max,
        pr._2.map(_.lapTime).reduce(_ plus _)
      )).toList.sortBy(_._4)
    (for (p <- rank.indices) yield (p+1, rank(p)._1, rank(p)._2, rank(p)._3, rank(p)._4)).toList
  }

  def findWinner(records: List[LogRecord]) = rank(records).head

}
