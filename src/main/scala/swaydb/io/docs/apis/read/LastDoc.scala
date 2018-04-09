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

object LastDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("last")
      ),
      <.p("Returns the last key-value in the database."),
      <.p(
        <.span(^.className := "snippet", "last"),
        " - uses ",
        <.strong("reverse"),
        " iteration that are much slower then forward iterations. The performance of ",
        <.span(^.className := "snippet", "last"),
        " depends on the number of deleted key-values in upper ",
        RouterController.router.link(Page.Level)("Levels"),
        ". If there are fewer deleted key-values then the performance of ",
        <.span(^.className := "snippet", "last"),
        " is comparable to ",
        RouterController.router.link(Page.Head)(<.span(^.className := "snippet", Page.Head.name)),
        ". For memory databases ",
        RouterController.router.link(Page.Head)(<.span(^.className := "snippet", Page.Head.name)),
        " and ",
        <.span(^.className := "snippet", "last"),
        " have similar performance."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.last
            |db.lastOption
            |
            |//or to fetch last key only
            |db.keys.last
            |db.keys.lastOption
            |
            |""".stripMargin
        )
      )
    )
  }

}
