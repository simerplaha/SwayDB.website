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

package swaydb.io.docs.apis

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}
import swaydb.io.docs.apis.iteration.IterationAPIDoc
import swaydb.io.docs.apis.read.ReadAPIDoc
import swaydb.io.docs.apis.write.WriteAPIDoc

object APIDoc {

  def initialiseDB =
    <.p(
      ^.className := "heading",
      <.p("Example database type used in all API code snippets."),
      <.pre(
        <.code(^.className := "scala")(
          """
            |val db = SwayDB.persistent[Int, String](dir = "/Disk1/db")
            |
            |""".stripMargin
        )
      )
    )

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("APIs")
      ),
      initialiseDB,
      <.h4(
        <.u(
          RouterController.router.link(Page.WriteAPI)("Write API")
        )
      ),
      <.h4(
        <.u(
          RouterController.router.link(Page.ReadAPI)("Read API")
        )
      ),
      <.h4(
        <.u(
          RouterController.router.link(Page.IterationAPI)("Iteration API")
        )
      )
    )
  }

}
