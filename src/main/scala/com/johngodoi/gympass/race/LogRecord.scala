package com.johngodoi.gympass.race

import java.time.{Duration, LocalTime}

case class LogRecord(time: LocalTime, codigoPiloto: String, piloto: String, nroVolta: Int, tempoVolta: Duration, velocidadeMediaVolta: Double)
