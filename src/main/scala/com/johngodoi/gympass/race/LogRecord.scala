package com.johngodoi.gympass.race

import java.time.{Duration, LocalTime}

case class LogRecord(time: LocalTime, code: String, name: String, lapNumber: Int, lapTime: Duration, lapAverageSpeed: Double)
