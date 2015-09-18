package uk.co.devexe

import com.hp.hpl.jena.rdf.model.{SimpleSelector, Model}

/**
 * Created by walpolrx on 13/07/2015.
 */
class QueryHandler(model: Model) {

  def runQuery(resourceName: String) = {
    val endpoint = Endpoint(getSparqlQuery(resourceName), "GET", true)
    val inputStream = endpoint.openConnection
    model.read(inputStream, null, "TURTLE")
    val town = extractValue("town", model);
    val country = extractValue("country", model);
    if(town.isEmpty) {
      "Sorry, no town of birth found.";
    } else if(country.isEmpty) {
      "Place of birth is " + town.get + " but country unknown.";
    } else{
      town.get + ", " + country.get;
    }
  }

  def getSparqlQuery(resourceName: String): String = {
    val sparqlBuilder = new StringBuilder("select (str(?townName) as ?town) (str(?countryName) as ?country)")
      .append("where {")
      .append("?place rdfs:label ?townName .")
      .append("filter(langMatches(lang(?townName), \"EN\"))")
      .append("?place dbpedia-owl:country ?country .")
      .append("?country rdfs:label ?countryName .")
      .append("filter(langMatches(lang(?countryName), \"EN\"))")
      .append("{select ?place ")
      .append("where {")
      .append("dbpedia:" + resourceName + " dbpedia-owl:birthPlace ?place .")
      .append("?place a dbpedia-owl:Settlement .")
      .append("}}}")
    sparqlBuilder.toString
  }

  def extractValue(varName: String, model: Model): Option[String] = {
    //import scala.collection.JavaConverters._
    val selector = new SimpleSelector(null, Vocabulary.VARIABLE_PROPERTY, varName)
    val statements = model.listStatements(selector).toList
    if(!statements.isEmpty) {
      val townResource = statements.get(0).getSubject
      val valueStatements = model.listStatements(townResource, Vocabulary.VALUE_PROPERTY, null).toList
      if(!valueStatements.isEmpty) {
        Some(valueStatements.get(0).getObject.asLiteral.toString)
      } else {
        Option.empty[String]
      }
    } else {
      Option.empty[String]
    }
  }

}