package com.johngodoi.gympass.race

import java.time.{Duration, LocalTime}

object LogLoader {
  def load(filepath: String):List[LogRecord] = scala.io.Source.fromFile(filepath).getLines().toList.tail
    .map(line => parseLineIntoLogRecord(line))

  private def parseLineIntoLogRecord(line: String):LogRecord = {
    val arrayLine = line.split("[\\s][\\s]").filter(v => v.nonEmpty).map(v => v.trim)
    val pilot = arrayLine(1).split(" – ")
    LogRecord(
      LocalTime.parse(arrayLine(0)),
      pilot(0),
      pilot(1),
      arrayLine(2).toInt,
      Duration.parse("PT"+arrayLine(3).replace(":","M")+"S"),
      arrayLine(4).replace(",",".").toDouble
    )
  }
}
