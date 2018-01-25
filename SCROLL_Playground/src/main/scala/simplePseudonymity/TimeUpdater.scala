package simplePseudonymity

import java.lang.Thread

class TimeUpdater(var currStatus : AppStatus) extends Thread {
  
  override def run() : Unit = {
    var startOfTimeIntv : Long = System.currentTimeMillis()
    var currTime : Long = startOfTimeIntv
    while(true){
      currTime = System.currentTimeMillis();
      if(currTime - startOfTimeIntv > 1000){
        startOfTimeIntv = currTime;
        currStatus.incrTime
      }
    }

  }
  
}