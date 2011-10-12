package coroapi

import java.dyn.AsymCoroutine
import java.io.File

import javax.xml.parsers.{ SAXParser, SAXParserFactory }

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class PullFromPush extends AsymCoroutine[Nothing, Option[String]] {
  override def run(n : Nothing) : Option[String] = {
    val parser = SAXParserFactory.newInstance.newSAXParser
    parser.parse(
      new File("content.xml"),
      new DefaultHandler() {
        override def startElement(uri : String, localName : String, qName : String, attributed : Attributes) {
          ret(Some(qName))
        }
      })
    return None
  }
}

object PullFromPush {
  def main(args : Array[String]) {
    val parser = new PullFromPush
    
    while (!parser.isFinished) {
      val element = parser.call
      element map println
    }
  }
}
