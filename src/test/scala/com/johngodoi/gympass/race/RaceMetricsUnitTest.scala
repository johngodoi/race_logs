package com.johngodoi.gympass.race

import java.time.Duration

import org.scalatest.{FlatSpec, Matchers}

class RaceMetricsUnitTest extends FlatSpec with Matchers {

  private val records = LogLoader.load(".\\src\\test\\resources\\race_results.log")

  "From LogRecord list" should "find winner" in {
    RaceMetrics.findWinner(records) should be (1,"038","F.MASSA",4,Duration.parse("PT04M11.578S") )
  }

  "From LogRecord list" should "mount rank" in {
    RaceMetrics.rank(records) should contain theSameElementsInOrderAs 
      List((1,"038","F.MASSA",4,Duration.parse("PT4M11.578S")),
    (2,"002","K.RAIKKONEN",4,Duration.parse("PT4M15.153S")),
    (3,"033","R.BARRICHELLO",4,Duration.parse("PT4M16.08S")),
    (4,"023","M.WEBBER",4,Duration.parse("PT4M17.722S")),
    (5,"015","F.ALONSO",4,Duration.parse("PT4M54.221S")),
    (6,"011","S.VETTEL",3,Duration.parse("PT6M27.276S")))
  }

  "From LogRecord list" should "find best lap for each pilot" in{
    RaceMetrics.bestLapsByPilot(records) should contain theSameElementsAs
      List(("K.RAIKKONEN",Duration.parse("PT1M3.076S")),
        ("S.VETTEL",Duration.parse("PT1M18.097S")),
        ("F.MASSA",Duration.parse("PT1M2.769S")),
        ("F.ALONSO",Duration.parse("PT1M7.011S")),
        ("R.BARRICHELLO",Duration.parse("PT1M3.716S")),
        ("M.WEBBER",Duration.parse("PT1M4.216S"))
      )
  }

  "From LogRecord list" should "find race's best lap" in{
    RaceMetrics.bestLap(records) should be ("F.MASSA",Duration.parse("PT1M2.769S"))
  }

  "From LogRecord list" should "find average speed during the race for each pilot" in{
    RaceMetrics.averageSpeedByPilot(records) should contain theSameElementsAs
      List(
        ("K.RAIKKONEN",43.627250000000004),
        ("S.VETTEL",25.745666666666665),
        ("F.MASSA",44.24575),
        ("F.ALONSO",38.06625),
        ("R.BARRICHELLO",43.467999999999996),
        ("M.WEBBER",43.191250000000004)
      )
  }

  "From LogRecord list" should "mount rank with time difference" in {
    RaceMetrics.rankWithTimeDifferences(records) should contain theSameElementsInOrderAs
      List(
        ("F.MASSA",Duration.parse("PT0S")),
        ("K.RAIKKONEN",Duration.parse("PT-5.117S")),
        ("R.BARRICHELLO",Duration.parse("PT-5.583S")),
        ("M.WEBBER",Duration.parse("PT-8.972S")),
        ("F.ALONSO",Duration.parse("PT-49.738S")),
        ("S.VETTEL",Duration.parse("PT-2M-40.754S"))
      )
  }
}
