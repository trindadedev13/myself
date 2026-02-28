package dev.trindadedev.myself

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform