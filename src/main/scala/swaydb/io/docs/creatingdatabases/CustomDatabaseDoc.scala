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
import swaydb.io.{Page, RouterController}

object CustomDatabaseDoc {


  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Create custom databases")
      ),

      <.p("Databases can also be initialised on custom configurations. Refer to ",
        RouterController.router.link(Page.ConfiguringLevels)("Configuring Levels"),
        " documentation on how ",
        <.span(^.className := "snippet", "ConfigWizard "),
        "can be used to build custom Level hierarchies."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |import swaydb._
            |import swaydb.serializers.Default._
            |
            |//custom config
            |val myConfig =
            |  ConfigWizard
            |    .addPersistentLevel0(???, ???, ???, ???)
            |    .addMemoryLevel1(???, ???, ???, ???)
            |
            |//initialise database with custom config
            |val db =
            |    SwayDB[Long, String](config = myConfig, ???, ???, ???, ???)
            |
            |//or
            |val setDB =
            |    SwayDB[String](config = myConfig, ???, ???, ???, ???)
            |
            |""".stripMargin
        )
      )
    )
}
