package uk.co.devexe

import java.io.PrintStream
import java.util.Scanner

/**
 * @author ${user.name}
 */
object App {

  def main(args : Array[String]) {
    execute(new Scanner(System.in), System.out, System.err)
  }

  def execute(in: Scanner, out: PrintStream, err: PrintStream) = {
    val model = Vocabulary.createModel
    val queryHandler = new QueryHandler(model)
    val queryService = new QueryService(queryHandler)
    out.println("Enter a question, e.g. 'Where was David Cameron born?' or type quit to exit..")
    var question = in.nextLine()
    while(!question.equals("quit")) {
      out.println(queryService.ask(question))
      question = in.nextLine()
    }
  }

}
