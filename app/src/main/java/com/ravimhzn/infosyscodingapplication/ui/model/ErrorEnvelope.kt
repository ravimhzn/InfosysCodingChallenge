package com.ravimhzn.infosyscodingapplication.ui.model

data class ErrorEnvelope(
  val status_code: Int,
  val status_message: String,
  val success: Boolean
)
