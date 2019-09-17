package com.johngodoi.gympass.race

import java.time.{Duration, LocalTime}

import org.scalatest.{FlatSpec, Matchers}

class LogLoaderUnitTest extends FlatSpec with Matchers{

  "LogLoader receives a filepath" should "load a list of LogRecords" in {
    val records = LogLoader.load(".\\src\\test\\resources\\race_results.log")
    records should have size 23
    records.head should be (LogRecord(LocalTime.parse("23:49:08.277"),"038","F.MASSA",1,Duration.parse("PT1M02.852S"),44.275))
    records(2) should be (LogRecord(LocalTime.parse("23:49:11.075"),"002","K.RAIKKONEN",1,Duration.parse("PT1M04.108S"),43.408))
    records(4) should be (LogRecord(LocalTime.parse("23:49:30.976"),"015","F.ALONSO",1,Duration.parse("PT1M18.456S"),35.47))
    records(7) should be (LogRecord(LocalTime.parse("23:50:15.057"),"002","K.RAIKKONEN",2,Duration.parse("PT1M03.982S"),43.493))
    records(11) should be (LogRecord(LocalTime.parse("23:51:18.576"),"033","R.BARRICHELLO",3,Duration.parse("PT1M03.716S"),43.675))
    records(14) should be (LogRecord(LocalTime.parse("23:51:46.691"),"015","F.ALONSO",3,Duration.parse("PT1M08.704S"),40.504))
    records(17) should be (LogRecord(LocalTime.parse("23:52:22.586"),"033","R.BARRICHELLO",4,Duration.parse("PT1M04.010S"),43.474))
    records.last should be (LogRecord(LocalTime.parse("23:54:57.757"),"011","S.VETTEL",3,Duration.parse("PT1M18.097S"),35.633))
  }

}
