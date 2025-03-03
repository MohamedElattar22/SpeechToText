package org.elattar.km

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform