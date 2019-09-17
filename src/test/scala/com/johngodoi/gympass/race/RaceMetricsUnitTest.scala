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
}
