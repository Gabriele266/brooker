package com.gabrielecavallo.brooker.services.pdf

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service
import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.ByteArrayOutputStream

@Service
class PdfServiceImpl : PdfService {
    override fun createFromHtml(html: String): String {
        // Create document
        val doc = Jsoup.parse(html, "UTF-8")
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml)
        val renderer = ITextRenderer()
        val context = renderer.sharedContext
        context.apply {
            isPrint = true
            isInteractive = false
        }

        renderer.setDocumentFromString(doc.html())
        renderer.layout()
        val stream = ByteArrayOutputStream()
        renderer.createPDF(stream)

        return stream.toString("UTF-8")
    }
}