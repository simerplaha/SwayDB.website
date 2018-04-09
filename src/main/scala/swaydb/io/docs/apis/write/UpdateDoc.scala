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

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}

object UpdateDoc {

  def apply(showNote: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("update (range)")
      ),
//      WriteAPIDoc.note(showNote),
      <.p(
        "Update all key-values within the range."
      ),
      <.p(
        "Note: ",
        <.span(^.className := "snippet", "until"),
        " is exclusive."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.update(from = 1, until = 1000000, value = "updated value")
            |
            |""".stripMargin
        )
      ),
      PutDoc.atomicWrite(<.span(^.className := "snippet", "update (range)"))
    )
  }
}