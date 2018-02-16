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

package swaydb.io.docs.apis.read

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object SizeDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("size")
      ),
      <.p(
        "Returns the count of all key-values in the database, including updated key-values & deleted key-values ",
        "that are not physically deleted i.e. key-values in upper ",
        RouterController.router.link(Page.Segment)("Levels"),
        "."
      ),
      <.p(
        <.i(
          "Note: For persistent databases this function requires all ",
          RouterController.router.link(Page.Segment)("Segment"),
          " files be opened to read the key-value count. ",
          "Time of response depends on the database size, number of in-memory Segments & number of Segments that ",
          "already have the key-value count set in memory (Eg: Compaction can set the key-value count of the Segment in memory)."
        )
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.size
            |
            |""".stripMargin
        )
      )

    )
  }

}
