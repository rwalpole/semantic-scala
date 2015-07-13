package uk.co.devexe

/**
 * Created by walpolrx on 13/07/2015.
 */
class QueryService(queryHandler: QueryHandler) {

  def ask(question: String) = {
    val lcQuestion = question.toLowerCase
    if(lcQuestion.contains("birth place of")) {
      val name = extractName(question, "birth place of", "?", 14)
      queryHandler.runQuery(name.replace(" ", "_"))
    }
    else if(lcQuestion.contains("where was") && lcQuestion.contains("born?")) {
      val name = extractName(question, "where was", "born?", 9);
      queryHandler.runQuery(name.replace(" ", "_"))
    }
    else {
      "Sorry, I do not understand your question"
    }
  }

  def extractName(question: String, startPhrase: String, endPhrase: String, startPhraseLength: Int): String = {
    val start = question.toLowerCase.indexOf(startPhrase) + startPhraseLength
    val end = question.toLowerCase.indexOf(endPhrase)
    question.substring(start, end).trim
  }

}
