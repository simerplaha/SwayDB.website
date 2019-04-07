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

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.common.LinkIn
import swaydb.io.{Page, RouterController}

object CustomDatabaseDoc {


  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Create custom databases")
      ),

      <.p("Databases can also be initialised on custom configurations. Refer to ",
        LinkIn(Page.ConfiguringLevels),
        " documentation to see how to use ",
        <.span(^.className := "snippet", "ConfigWizard"),
        " to build custom ",
        LinkIn(Page.Level),
        " hierarchies."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |object CreateDB extends App {
            |
            |  import swaydb._
            |  import swaydb.serializers.Default._
            |
            |  implicit val ec = swaydb.SwayDB.defaultExecutionContext
            |  implicit val keyOrder = KeyOrder.default
            |
            |  //custom config
            |  val myConfig =
            |    ConfigWizard
            |      .addPersistentLevel0(???, ???, ???, ???, ???)
            |      .addMemoryLevel1(???, ???, ???, ???, ???, ???, ???)
            |
            |  //initialise map with custom config
            |  val map =
            |    SwayDB[Long, String](config = myConfig, ???, ???, ???, ???)
            |
            |  //or a set
            |  val set =
            |    SwayDB[String](config = myConfig, ???, ???, ???, ???)
            |}
            |
            |""".stripMargin
        )
      )
    )
}
