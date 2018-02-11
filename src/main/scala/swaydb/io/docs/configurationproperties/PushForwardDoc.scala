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

package swaydb.io.docs.configurationproperties

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object PushForwardDoc {

  def name = "pushForward: Boolean"

  def link =
    RouterController.router.link(Page.PushForward)(name)

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),
      <.p(
        "If true, before writing a ",
        RouterController.router.link(Page.Map)("Map"),
        " or ",
        RouterController.router.link(Page.Segment)("Segments"),
        " to the ",
        RouterController.router.link(Page.Level)("Level"),
        ", the Level checks if it's currently empty ",
        "and if the next Level is also empty, if both the conditions are true then the Level forwards the ",
        "merge request to the next Level."
      ),
      <.p(
        """This property saves unnecessary merges when all the Levels are empty and Segments can
          |be pushed to the lowest Level immediately.
        """.stripMargin),
      <.p(
        """Normally pushForward is set to true for the top most Levels as they are emptied more frequently
          |and lower Levels are expected be mostly non-empty.""".stripMargin)
    )
}
