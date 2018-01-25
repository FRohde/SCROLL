package simplePseudonymity

import java.util.Timer
import java.util.concurrent._
import scroll.internal.util.Log.info

class AppStatus(var timePassed : Integer){
  
  def incrTime = {
    timePassed = (timePassed + 1) % 10
  }
  
  var myPc : PseudonymityCompartment = null
  def setPc(pc : PseudonymityCompartment) = {
    myPc = pc
  }
  
  def getPc() : PseudonymityCompartment = {
    myPc
  }
}

object PseudoApp extends App {

  var myStatus = new AppStatus(0)
  var myTimeUpdater = new TimeUpdater(myStatus);
  myTimeUpdater.start();

  new PseudonymityCompartment {
  
    var nom = new NaturalObjectManager()
    
    var p1 = new Person("Ferdinand")
    var p2 = new Person("Harri")

    p1 play new RoleManager //+p1.isPlaying kann nicht aufgerufen werden wenn das Objekt noch gar keine Rolle spielt
    
    info("playing PseudoRole: " + (+p1).isPlaying[PseudoRole]) 
    
    var myRoleBinder = new RoleBinder(p1, p2, myStatus)
    myRoleBinder.start()
    var threadsPc : PseudonymityCompartment = null
    while(true){
      Thread.sleep(1000)
      threadsPc = myStatus.getPc()
      this partOf threadsPc
      info("natural: Hi, my name is " + p1.getMyName(null))
      var rolename : String = (+p1).getMyName("yoha")
      info("role: Hi, my name is " + rolename)
      println("current time: " + myStatus.timePassed)
    }
  }



}


//  var r1 = new PseudoRole("F1")
//  var r2 = new PseudoRole("H1")
//  
//  info("Hi, my name is " + p1.getMyName())
//  +p1 play r1
//  var rolename : String = (+p1).getMyName()
//  info("Hi, my name is " + rolename)
//  
//  
//  
//  nom.registerObject(p1)
//  nom.registerObject(p2)
//  

//println(rolename)
  
  //var contents : String = rolename.getOrElse("error")
  //println(contents)
  //  rolename match {
//    case Right(x) => println("yoa")
//    case Left(x)  => println(x)
//  }
  //info("Hiho, my name is " + (+p1).getMyName())

//    println("Started")
//  val executor = new ScheduledThreadPoolExecutor(1)
//  val task = new Runnable {
//    def run() = {
//      println("Current Time:" + System.currentTimeMillis().toString());
//      println("myStatus.timePassed.toString(): " + myStatus.timePassed.toString());  
//    }
//    
//  }
//  val future = executor.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS)
//  println("Scheduling done")
//  
  



