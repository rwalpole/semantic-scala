package uk.co.devexe

import java.io.UnsupportedEncodingException
import java.net.{URL, URLEncoder, HttpURLConnection}

/**
 * Created by walpolrx on 13/07/2015.
 */
object Endpoint {
  val DBPEDIA_BASE_URI = "http://dbpedia.org"
  val DBPEDIA_SPARQL_URI = DBPEDIA_BASE_URI + "/sparql"
}

class Endpoint(val sparqlQuery: String, val connection: HttpURLConnection) {



}
