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
import swaydb.io.Page.WriteAPI
import swaydb.io.{Page, RouterController}

object WriteAPIDoc {

  def note(show: Boolean = true) =
    <.div(
      ^.className := "alert alert-info",
      <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
      <.i(
        " All write operations return ",
        RouterController.router.link(Page.Level0Meter)(<.span(^.className := "snippet", "Level0Meter")),
        " that can be used to apply back-pressure via external streaming libraries or ",
        <.strong(
          "blocking ",
          <.span(^.className := "glyphicon glyphicon-alert")
        ),
        " back-pressure (",
        RouterController.router.link(Page.Brake)(<.span(^.className := "snippet", "brake")),
        ") can be used."
      )
    ).when(show)

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(^.id := "download", "Write API")
      ),
      note(),
      WriteAPI.subPages.zipWithIndex map {
        case (page, index) =>
          <.h4(
            (index + 1) + ". ",
            RouterController.router.link(page)(<.span(^.className := "snippet", page.name))
          )
      } toTagMod
    )
  }

}
