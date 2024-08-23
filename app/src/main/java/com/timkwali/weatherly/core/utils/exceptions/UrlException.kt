package com.timkwali.weatherly.core.utils.exceptions

class UrlException(baseUrl: String): Throwable("Error connecting to $baseUrl. Could not resolve hostname.")