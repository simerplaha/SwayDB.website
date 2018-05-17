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

import japgolly.scalajs.react.vdom.{TagOf, VdomElement}
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import org.scalajs.dom.html
import swaydb.io.{Page, RouterController}

object PutDoc {

  def atomicWrite(api: VdomElement) =
    <.p(
      <.i(
        api,
        " can also be combined with other write operations and written atomically using ",
        RouterController.router.link(Page.Batch)(<.span(^.className := "snippet", "batch")),
        "."
      )
    )

  def apply(showNote: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.Put.name)
      ),
      <.h3("Key-value"),
      <.p("Insert a single key-value."),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.put(key = 1, value = "one")
            |
            |""".stripMargin
        )
      ),

      <.h3("Set"),
      <.p(
        "Insert a single item."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |setDB.put(elem = "some data")
            |
            |""".stripMargin
        )
      ),

      <.p(
        PutDoc.atomicWrite(<.span(^.className := "snippet", Page.Put.name))
      )
    )
  }

}
