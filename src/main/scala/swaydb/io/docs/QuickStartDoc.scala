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

package swaydb.io.docs

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.Main
import swaydb.io.common.ScalaCode

object QuickStartDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(^.id := "quick-start", "Quick start")
      ),
      SetupDoc.body,
      <.p(
        "Quick start demo app.",
        <.a(
          ^.href := "https://github.com/simerplaha/SwayDB.examples/blob/master/src/test/scala/quickstart/QuickStartPersistentSpec.scala",
          ^.role := "button",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - QuickStartSpec")),
          ^.className := "btn btn-xs btn-info pull-right",
          ^.target := "blank",
          "View test"
        )
      ),
      ScalaCode(
        """
          |object QuickStart extends App {
          |
          |  import swaydb._
          |  import swaydb.serializers.Default._ //import default serializers
          |
          |  val db = memory.Map[Int, String]().get //Create a memory database
          |
          |  db.put(1, "one").get
          |  db.get(1).get
          |  db.remove(1).get
          |
          |  //write 100 key-values atomically
          |  (1 to 100) map {
          |    key =>
          |      (key, key.toString)
          |  } andThen {
          |    keyValues =>
          |      db.put(keyValues)
          |  }
          |
          |  //Iteration: fetch all key-values withing range 10 to 90, update values and atomically write updated key-values
          |  db
          |    .from(10)
          |    .tillKey(_ <= 90)
          |    .map {
          |      case (key, value) =>
          |        (key, value + "_updated")
          |    } andThen {
          |      updatedKeyValues =>
          |        db.put(updatedKeyValues)
          |  }
          |}
        """.stripMargin
      ),

      <.div(
        ^.className := "alert alert-info",
        <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
        " Clone ",
        <.a(
          ^.href := "https://github.com/simerplaha/SwayDB.examples",
          ^.target := "blank",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - SwayDB.examples")),
          "SwayDB.examples"
        ),
        " to view all examples."
      )
    )
  }
}
