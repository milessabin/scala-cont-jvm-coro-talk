package scoroapi

import java.io.File
import javax.xml.parsers.{ SAXParser, SAXParserFactory }
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

import Generators._

object PullFromPush {
  def main(args : Array[String]) {
    val elems = generator[String] { implicit gen =>
      val parser = SAXParserFactory.newInstance.newSAXParser
      parser.parse(
        new File("content.xml"),
        new DefaultHandler() {
          override def startElement(uri : String, localName : String, qName : String, attributed : Attributes) {
            yld(qName)
          }
        })
    }
    
    elems foreach println
  }
}
