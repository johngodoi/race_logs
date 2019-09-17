package com.johngodoi.gympass.race

object Starter extends App {
  private val records: List[LogRecord] = LogLoader.load(args(0))
  println("Rank")
  RaceMetrics.rank(records).foreach(println(_))
  println("Pilot best lap")
  RaceMetrics.bestLapsByPilot(records).foreach(println(_))
  println("Fast lap")
  println(RaceMetrics.bestLap(records))
  println("Average Speed by Pilot")
  RaceMetrics.averageSpeedByPilot(records).foreach(println(_))
  println("Rank with time difference")
  RaceMetrics.rankWithTimeDifferences(records).foreach(println(_))
}
