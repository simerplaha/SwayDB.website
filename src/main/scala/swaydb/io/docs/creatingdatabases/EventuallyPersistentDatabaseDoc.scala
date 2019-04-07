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

package swaydb.io.docs.creatingdatabases

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.common.{LinkIn, Snippet}
import swaydb.io.{Main, Page, RouterController}

object EventuallyPersistentDatabaseDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Create eventually persistent databases")
      ),

      <.p(
        "Configuration used: ",
        <.a(
          ^.href := "https://github.com/simerplaha/SwayDB/blob/master/configs/src/main/scala/swaydb/configs/level/DefaultEventuallyPersistentConfig.scala",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - DefaultEventuallyPersistentConfig.scala")),
          ^.target := "blank",
          "Eventually persistent"
        )
      ),

      <.p("A 3 leveled database that writes 10 Segments to disk when memory level size reaches 100.mb."),

      <.h3("Key-value database"),
      <.pre(
        <.code(^.className := "scala")(
          """
            |import swaydb._
            |import swaydb.serializers.Default._
            |
            |//map database
            |val map = eventually.persistent.Map[Long, String](dir = "myMap")
            |
            |//set database
            |val set = eventually.persistent.Set[String](dir = "mySet")
            |
            |""".stripMargin
        )
      )
    )
}
