package dev.trindadedev.myself.platform

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform