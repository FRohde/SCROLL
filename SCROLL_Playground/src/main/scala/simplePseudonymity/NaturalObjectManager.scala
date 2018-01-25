package simplePseudonymity

import scala.collection.mutable.ListBuffer

class NaturalObjectManager {
  
  var objects : ListBuffer[Any] = new ListBuffer[Any]
  
  def registerObject(obj : Any) = {
    objects += obj
    
  }
  
  def getObjects() : List[Any] = {
    objects.toList
  }
  
  
}