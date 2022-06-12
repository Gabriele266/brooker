package com.gabrielecavallo.brooker.services.book

enum class BookDownloadFormat(
    val code: String
) {
    HTML("html"),
    PDF("pdf"),
    IMG("img");

    companion object {
        /**
         * Construct book download format from the code
         */
        fun fromCode(code: String): BookDownloadFormat =
            values().first { it.code.equals(code) }

    }
}