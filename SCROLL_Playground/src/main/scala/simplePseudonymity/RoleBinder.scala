package simplePseudonymity

import java.lang.Thread
import scroll.internal.util.Log.info

class RoleBinder(var p1 : Person, p2 : Person, appStatus : AppStatus) extends Thread {
  
  override def run() : Unit = {
    
    new PseudonymityCompartment {
      var r1 = new PseudoRole("F1")
      var r2 = new PseudoRole("H1")
      appStatus.setPc(this)
      p1 play new RoleManager() //+p1.isPlaying kann nicht aufgerufen werden wenn das Objekt noch gar keine Rolle spielt
      while(true){
        if (appStatus.timePassed <= 4 && !((+p1).isPlaying[PseudoRole]) ){
          +p1 play r1
        }
        Thread.sleep(500)
        
        if (appStatus.timePassed > 4 && (+p1).isPlaying[PseudoRole] ){
          +p1 drop r1
        }
        Thread.sleep(500)
      }
      
      
      //info("Hi, my name is " + p1.getMyName())
      //info("Hi, my name is " + rolename)
      //println(rolename)
      //var rolename : String = (+p1).getMyName()
      
      //var contents : String = rolename.getOrElse("error")
      //println(contents)
      //  rolename match {
    //    case Right(x) => println("yoa")
    //    case Left(x)  => println(x)
    //  }
      //info("Hiho, my name is " + (+p1).getMyName())
  }
    
  }
  
}