package com.johngodoi.gympass.race

import java.time.Duration

import org.scalatest.{FlatSpec, Matchers}

class RaceMetricsUnitTest extends FlatSpec with Matchers {

  "From LogRecord list" should "find winner" in {
    val records = LogLoader.load(".\\src\\test\\resources\\race_results.log")
    RaceMetrics.findWinner(records) should be (1,"038","F.MASSA",4,Duration.parse("PT04M11.578S") )
  }

  "From LogRecord list" should "mount rank" in {
    val records = LogLoader.load(".\\src\\test\\resources\\race_results.log")
    RaceMetrics.rank(records) should contain theSameElementsAs
      List((1,"038","F.MASSA",4,Duration.parse("PT4M11.578S")),
    (2,"002","K.RAIKKONEN",4,Duration.parse("PT4M15.153S")),
    (3,"033","R.BARRICHELLO",4,Duration.parse("PT4M16.08S")),
    (4,"023","M.WEBBER",4,Duration.parse("PT4M17.722S")),
    (5,"015","F.ALONSO",4,Duration.parse("PT4M54.221S")),
    (6,"011","S.VETTEL",3,Duration.parse("PT6M27.276S")))
  }

  "From LogRecord list" should "find best lap for each pilot" in{
    val records = LogLoader.load(".\\src\\test\\resources\\race_results.log")
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
    val records = LogLoader.load(".\\src\\test\\resources\\race_results.log")
    RaceMetrics.bestLap(records) should be ("F.MASSA",Duration.parse("PT1M2.769S"))
  }
}
