package uk.co.devexe

import java.io.{InputStream, UnsupportedEncodingException}
import java.net.{URL, URLEncoder, HttpURLConnection}

/**
 * Created by walpolrx on 13/07/2015.
 */
object Endpoint {
  val DBPEDIA_BASE_URI = "http://dbpedia.org"
  val DBPEDIA_SPARQL_URI = DBPEDIA_BASE_URI + "/sparql"

  def apply(sparqlQuery: String, method: String, doOutput: Boolean): Endpoint = {
    new Endpoint(sparqlQuery, method, doOutput)
  }
}

class Endpoint(val sparqlQuery: String, val method: String, val doOutput: Boolean) {

  def openConnection: InputStream = {
    val url = new URL(getUrl)
    val connection = url.openConnection.asInstanceOf[HttpURLConnection]
    connection.setRequestMethod(method)
    connection.setDoOutput(doOutput)
    connection.getInputStream
  }

  def getUrl: String = {
    val urlBuilder = new StringBuilder(Endpoint.DBPEDIA_SPARQL_URI)
      .append("?default-graph-uri=" + Endpoint.DBPEDIA_BASE_URI)
      .append("&query=" + URLEncoder.encode(sparqlQuery, "UTF-8"))
      .append("&format=text/turtle")
    urlBuilder.toString
  }

}
