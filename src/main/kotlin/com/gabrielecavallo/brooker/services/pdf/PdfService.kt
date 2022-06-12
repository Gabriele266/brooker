package com.gabrielecavallo.brooker.services.pdf

interface PdfService {
    fun createFromHtml(html: String): String
}