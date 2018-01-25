package simplePseudonymity

import scroll.internal.Compartment
import java.util.concurrent._

class PseudonymityCompartment extends Compartment {
  
  case class PseudoRole(pseudonym: String) {
    def getMyName(blub : String) = pseudonym
  
  }
}