/*
 * Copyright 2018 Simer Plaha (@simerplaha)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package swaydb.io.docs.apis.write

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Main, Page}
import swaydb.io.common.{LinkIn, LinkOut, ScalaCode, Snippet}

object RegisterFunctionDoc {

  def apply(showNote: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.RegisterFunction.name)
      ),
      <.p(
        "Updates & removes can be performed registering any Scala or Java function. No query language (",
        LinkIn(Page.Goals, "one of the goals"),
        ")."
      ),

      <.p(
        "Since these functions are basic ",
        <.strong("JVM functions"),
        " they can implement ",
        " complex logic and are not restricted by a query language. ",
        "Any external JVM library can be used within these functions."
      ),

      <.h3("Demo"),
      <.p(^.className := "header")(
        "For the following examples lets assume there exists a ",
        Snippet("Map[String, Long]"),
        " database that stores ",
        Snippet("userId: String"),
        " as key and ",
        Snippet("totalLikes: Long"),
        " as value for each user."
      ),

      <.h3("Example functions"),
      <.a(
        ^.href := "https://github.com/simerplaha/SwayDB.examples/blob/master/src/test/scala/functions/LikesSpec.scala",
        ^.role := "button",
        ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - LikesSpec")),
        ^.className := "btn btn-xs btn-info pull-right",
        ^.target := "blank",
        "View example test"
      ),
      <.p(
        "Increment the key's total likes count by 1."
      ),
      ScalaCode(
        """
          |likesMap.registerFunction(
          |  functionID = "increment likes counts",
          |  function =
          |    (likes: Long) =>
          |      Apply.Update(value = likes + 1)
          |)
          |
          |""".stripMargin
      ),
      <.p(
        """Resets likes for a user and increments the deadline (if exists) by 10.seconds."""
      ),
      ScalaCode(
        """
          |likesMap.registerFunction(
          |  functionID = "reset likes and deadline",
          |  function =
          |    (user: String, deadline: Option[Deadline]) =>
          |      if (user == "someUser")
          |        Apply.Update(value = 0, deadline = deadline.map(_ + 10.second))
          |      else
          |        Apply.Nothing //do not perform any update
          |)
          |
          |""".stripMargin
      ),

      <.p(
        """If the user has no likes set expiration."""
      ),
      ScalaCode(
        """
          |likesMap.registerFunction(
          |  functionID = "set deadline if empty",
          |  function =
          |    (user: String, likes: Long, deadline: Option[Deadline]) =>
          |      if (likes == 0 && deadline.isEmpty)
          |        Apply.Expire(1.hour.fromNow)
          |      else
          |        Apply.Nothing //do not perform any update
          |)
          |
          |""".stripMargin
      ),

      <.p(
        Snippet("functionID"),
        " can then be used to execute these functions with ",
        LinkIn(Page.ApplyFunction),
        "."
      ),

      <.h3("Memory"),
      <.p(
        Snippet("registerFunction"),
        " stores functions in memory and are required to be in memory until the ",
        LinkIn(Page.Compaction),
        " completes for the applied functions. "
      ),

      <.p(
        LinkOut("https://github.com/simerplaha/SwayDB/issues/51", "#51"),
        " will implement API to automatically remove applied functions from memory."
      ),

      <.p(
        LinkOut("https://github.com/simerplaha/SwayDB/issues/52", "#52"),
        " will implement API to serialise registered functions and persist them in the database itself."
      ),

    )
  }
}