package simplePseudonymity


import scroll.internal.util.Log.info
import scala.reflect._

object MedDataPseu extends App {
  new MedDataPseuComp {
    var frank = new Person("Frank")
    var client_frank = new MedDataClient()
    +frank play client_frank
    
    var cloudService = new Service("CloudService")
    var medDataAnalysisServer = new MedDataAnalysisServer()
    cloudService play medDataAnalysisServer
    
    info("-------------------------------------------------------------------------------")
    info("Frank is about to send some biomedical data to a medical data analysis server.")
    info("-------------------------------------------------------------------------------")
    info(" ")
    
    (+frank) sendMedData(medDataAnalysisServer, "time: 9:00 ; Glucose: 100 mg/dl", "Klaus")
    (+frank) sendMedData(medDataAnalysisServer, "time: 9:30 ; Glucose: 90 mg/dl", "Klaus")
    (+frank) sendMedData(medDataAnalysisServer, "time: 10:00 ; Glucose: 110 mg/dl", "Klaus")
    
    (+frank) sendMedData(medDataAnalysisServer, "time: 10:30 ; Glucose: 105 mg/dl", "Heinz")
    (+frank) sendMedData(medDataAnalysisServer, "time: 11:00 ; Glucose: 95 mg/dl", "Heinz")
    (+frank) sendMedData(medDataAnalysisServer, "time: 11:30 ; Glucose: 90 mg/dl", "Heinz")
    
  }
}
