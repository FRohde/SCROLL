package internal.dispatch

import scala.collection.mutable

object DispatchDescription
{
  def When(when: () => Boolean) = new
    {
      def Dispatch(rules: DispatchRule*) = DispatchDescription(when, rules.toList)
    }
}

case class DispatchDescription(
  when: () => Boolean,
  rules: List[DispatchRule]
  )
{

  case class Plays(
    player: String,
    role: String
    )

  case class Score(
    name: String,
    value: Int
    )
  {
    override def hashCode(): Int = name.hashCode

    override def equals(obj: scala.Any): Boolean = obj match {
      case e: Score => name equals e.name
      case _ => super.equals(obj)
    }
  }

  val priorities = mutable.Map[Plays, Set[Score]]()

  calculateScores()

  def calculateScores()
  {
    rules.foreach(r => {
      val key = Plays(r.in, r.role)
      val value = mutable.HashSet[Score]()

      r.precs.foreach {
        case Before(left, right) =>
          val cur = value.find(_.name equals left).getOrElse(Score(left, 0))
          value.remove(cur)
          value.add(Score(left, cur.value + 1))
        case After(left, right) =>
          val cur = value.find(_.name equals right).getOrElse(Score(right, 0))
          value.remove(cur)
          value.add(Score(right, cur.value + 1))
        case Replace(left, right) => Score(right, 0)
      }
      priorities(key) = value.toSet
    })
  }
}

object DispatchRule
{
  def In(i: String) = new
    {
      def With(w: String)
        (st: Statement*) = DispatchRule(i, w, st.toList)
    }
}

case class DispatchRule(
  in: String,
  role: String,
  precs: List[Statement]
  )

object Statement
{
  def invoke(s: => String): Statement =
  {
    if (s.contains(Before.repr)) {
      val (l, r) = s.splitAt(s.indexOf(Before.repr))
      return Before(l, r.replace(Before.repr, ""))
    }
    if (s.contains(Replace.repr)) {
      val (l, r) = s.splitAt(s.indexOf(Replace.repr))
      return Replace(l, r.replace(Replace.repr, ""))
    }
    if (s.contains(After.repr)) {
      val (l, r) = s.splitAt(s.indexOf(After.repr))
      return After(l, r.replace(After.repr, ""))
    }
    throw new IllegalArgumentException("'" + s + "' is no valid Statement.")
  }
}

abstract class Statement(
  left: String,
  right: String
  )
{
}

object Before
{
  def repr = " before "
}

case class Before(
  left: String,
  right: String
  ) extends Statement(left, right)

object Replace
{
  def repr = " replace "
}

case class Replace(
  left: String,
  right: String
  ) extends Statement(left, right)

object After
{
  def repr = " after "
}

case class After(
  left: String,
  right: String
  ) extends Statement(left, right)