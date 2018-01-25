package simplePseudonymity


import scroll.internal.util.Log.info
import scala.reflect._

object ImplParameter_Test extends App {
  new ImplParameterCompartment {
    var person_hans = new Person("Hans")
    var student_hans = new Student()
    +person_hans play student_hans
    
//    val classTagStudent = classTag[Student]
//    val hansIsPlaying = +person_hans isPlaying classTagStudent
//    println("person_hans isPlaying student_hans: " +  hansIsPlaying )
    
    var person_ulrich = new Person("Ulrich")
    var prof_ulrich = new Professor()
    person_ulrich play prof_ulrich
    
    info("Hans is about to ask Prof.Ulrich some questions using different pseudonyms")
    
    (+person_hans) askProfessor(prof_ulrich, "What is the exam-contents?", "Peter")
    (+person_hans) askProfessor(prof_ulrich, "What is the pass mark of the exam?", "Peter")
    (+person_hans) askProfessor(prof_ulrich, "Are cheat sheets allowed?", "Peter")
    
    (+person_hans) askProfessor(prof_ulrich, "How can I legally challenge the exam result?", "Heinz")
    (+person_hans) askProfessor(prof_ulrich, "Is your assessor corruptible?", "Heinz")
    (+person_hans) askProfessor(prof_ulrich, "Can the exam be torn easily?", "Heinz")
    
    (+person_hans) askProfessor(prof_ulrich, "What else can be studied here?", null)
  }
}
