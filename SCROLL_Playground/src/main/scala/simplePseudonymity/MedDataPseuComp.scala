package simplePseudonymity

import scroll.internal.Compartment
import scroll.internal.TextualPseudonym
import scroll.internal.util.Log.info
import scroll.internal.support.DispatchQuery

class MedDataPseuComp extends Compartment {
  class MedDataClient() {
    def sendMedData(medAnalServ : MedDataAnalysisServer, medData : String, pseudonym : String){
      implicit var textualPseudonym : TextualPseudonym = if(pseudonym != null) new TextualPseudonym(pseudonym) else null
      (+medAnalServ) receiveMedData medData
      
    }
  }
  
  
  class MedDataAnalysisServer() {
    var rcvedMedData : Map[String, List[String]] = Map()
    
    def receiveMedData(medData : String){
      info("-------------------------------------------------------------------------------")
      info("MedDataAnalysisServer: I was sent the following medical data: " + medData)
      var currentCallerPseudonym : Option[String] = rtmeta.textualPseudonym
      rtmeta.textualPseudonym match {
        case Some(txtpseu) => {
          info("MedDataAnalysisServer: This data was sent by client '" + txtpseu + "'")
          info("MedDataAnalysisServer: This client did also send the following data: " + (rcvedMedData getOrElse(txtpseu, List("No data has been sent yet"))))
          rcvedMedData = rcvedMedData + (txtpseu -> ((rcvedMedData getOrElse(txtpseu, List())) :+ medData))
        }
        case None => {
          info("MedDataAnalysisServer: This data point was sent by an anonymous client")
          info("MedDataAnalysisServer: I will not process data points of anonymous clients")
        }
      }
      info("-------------------------------------------------------------------------------")
      info(" ")
    }
  }
  
//  class MedDataAnalysisServer() {
//    def receiveMedData(medData : String){
//      rtmeta.textualPseudonym match { //caller pseudonym
//        case Some(txtpseu) => {}      //rtmeta: RuntimeMetaInformation...
//        case None => {}               //...provided by SCROLLs runtime environment
//      }
//    }
//  }
//  
  
  
}