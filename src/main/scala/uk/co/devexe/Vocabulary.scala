package uk.co.devexe

import com.hp.hpl.jena.rdf.model.{Model, ModelFactory}

/**
 * Created by walpolrx on 13/07/2015.
 */
object Vocabulary {

  val SPARQL_RESULT_URI = "http://www.w3.org/2005/sparql-results#"
  val MODEL = ModelFactory.createDefaultModel;
  val VARIABLE_PROPERTY = MODEL.createProperty(SPARQL_RESULT_URI, "variable")
  val VALUE_PROPERTY = MODEL.createProperty(SPARQL_RESULT_URI, "value")

  def createModel: Model = {
    ModelFactory.createDefaultModel
  }

}
