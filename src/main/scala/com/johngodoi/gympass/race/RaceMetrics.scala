package com.johngodoi.gympass.race

import java.time.Duration

object RaceMetrics {

  private def groupByPilot(records: List[LogRecord]) = records.groupBy(_.name)

  def rankWithTimeDifferences(records: List[LogRecord]) = {
    val finishingTime = processByPilot(
      records,
      xs => xs.map(_.time).max
    )
    val smallerFinishingTime = finishingTime.values.min
    finishingTime.mapValues(Duration.between(_,smallerFinishingTime))
      .toList.sortWith((r1,r2) => r1._2.compareTo(r2._2)>0)
  }

  def averageSpeedByPilot(records: List[LogRecord]) = processByPilot(
    records,
    xs => xs.map(_.lapAverageSpeed).sum/xs.size
  ).toList

  def bestLapsByPilot(records: List[LogRecord]) = processByPilot(
    records,
    xs => xs.map(_.lapTime).min
  ).toList

  def processByPilot[A](records: List[LogRecord], f: List[LogRecord] => A) = groupByPilot(records)
    .mapValues(f(_))

  def bestLap(records: List[LogRecord]) = bestLapsByPilot(records).minBy(_._2)

  def rank(records: List[LogRecord]) = {
    val rank = groupByPilot(records)
      .map(pr => (
        pr._2.map(_.code).head,
        pr._1,
        pr._2.map(_.lapNumber).max,
        pr._2.map(_.lapTime).reduce(_ plus _)
      )).toList.sortWith((pr1, pr2) => pr1._4.compareTo(pr2._4) < 0)
    (for (p <- rank.indices) yield (p+1, rank(p)._1, rank(p)._2, rank(p)._3, rank(p)._4)).toList
  }

  def findWinner(records: List[LogRecord]) = rank(records).head

}
