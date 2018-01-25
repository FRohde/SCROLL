package simplePseudonymity

import scroll.internal.Compartment
import scroll.internal.TextualPseudonym
import scroll.internal.util.Log.info
import scroll.internal.support.DispatchQuery

class ImplParameterCompartment extends Compartment {
  //def beginColaboration... oder in jeder Rolle eine Funktion def beginColaboration
  //baue das mal um zum CalendarEntry-Beispiel. Dann kannst Du im Vortrag vielleicht Quellcode zeigen. Sage auf IFTTT-Seite: 
  // "Das Update stammt von diesem Kalendereintrag. Dieser Kalendereintrag hatte schon diese Updates gesendet:"
  // besser: Nutzer Frank hat Kalendereintrag erstellt: Losfahren zum RoSI-Workshop
  //         Nutzer Frank hat Kalendereintrag erstellt: RoSI-Vortrag halten
  //         Nutzer Bernd hat Kalendereintrag erstellt: Ghostwriter kontaktieren
  //         Nutzer Bernd hat Kalendereintrag erstellt: Bewerbung bei anderem Graduiertenkolleg abgeben
  class Student() {
    def askProfessor(prof : Professor, question : String, pseudonym : String){
      println("ImplParameterCompartment.Student.askProfessor")
      implicit var textualPseudonym : TextualPseudonym = if(pseudonym != null) new TextualPseudonym(pseudonym) else null
      (+prof) answerStudentQuestion question
      
//      textualPseudonym = new TextualPseudonym("Heinz")
//      (+prof) answerStudentQuestion question
//      
//      textualPseudonym = null
//      (+prof) answerStudentQuestion question
    }
  }
  class Professor() {
    var studQuestions : Map[String, List[String]] = Map()
    
    def answerStudentQuestion(question : String){
      println("ImplParameterCompartment.Professor.answerStudentQuestion")
      info("I was asked the following question: " + question)
      var currentCallerPseudonym : Option[String] = rtmeta.textualPseudonym
      rtmeta.textualPseudonym match {
        case Some(txtpseu) => {
          info("This question was asked by student '" + txtpseu + "'")
          info("This Student did also ask the following questions: " + (studQuestions getOrElse(txtpseu, List("No questions have been asked yet"))))
          studQuestions = studQuestions + (txtpseu -> ((studQuestions getOrElse(txtpseu, List())) :+ question))
        }
        case None => {
          info("The question was asked by an anonymous student")
          info("I will not answer questions of anonymous students")
        }
      }
    }
  }
}