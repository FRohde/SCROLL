package scroll.internal

import scroll.internal.errors.SCROLLErrors.SCROLLError
import scroll.internal.support.DispatchQuery

/**
  * This Trait enables dynamic invocation of role methods that are not natively available on the player object.
  */
trait SCROLLDynamic extends Dynamic {
  /**
    * Allows to call a function with arguments.
    *
    * @param name          the function name
    * @param args          the arguments handed over to the given function
    * @param dispatchQuery the dispatch rules that should be applied
    * @param callerTextualPseudonym a text version of the caller objects pseudonym
    * @tparam E return type
    * @tparam A argument type
    * @return the result of the function call or an appropriate error
    */
  def applyDynamic[E, A](name: String)(args: A*)(implicit dispatchQuery: DispatchQuery = DispatchQuery.empty, callerTextualPseudonym : TextualPseudonym = null): Either[SCROLLError, E]

  /**
    * Allows to call a function with named arguments.
    *
    * @param name          the function name
    * @param args          tuple with the the name and argument handed over to the given function
    * @param dispatchQuery the dispatch rules that should be applied
    * @tparam E return type
    * @return the result of the function call or an appropriate error
    */
  def applyDynamicNamed[E](name: String)(args: (String, Any)*)(implicit dispatchQuery: DispatchQuery = DispatchQuery.empty): Either[SCROLLError, E]

  /**
    * Allows to read a field.
    *
    * @param name          of the field
    * @param dispatchQuery the dispatch rules that should be applied
    * @tparam E return type
    * @return the result of the field access or an appropriate error
    */
  def selectDynamic[E](name: String)(implicit dispatchQuery: DispatchQuery = DispatchQuery.empty): Either[SCROLLError, E]

  /**
    * Allows to write field updates.
    *
    * @param name          of the field
    * @param value         the new value to write
    * @param dispatchQuery the dispatch rules that should be applied
    */
  def updateDynamic(name: String)(value: Any)(implicit dispatchQuery: DispatchQuery = DispatchQuery.empty): Unit
}