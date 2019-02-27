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

object MaxSegmentsOpenDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("maxSegmentsOpen: Int")
      ),
      <.p(
        "Specifies the number of ",
        RouterController.router.link(Page.Segment)("Segments"),
        " to keep open at once for a persistent database. ",
        "This configuration is not required for a Memory database."
      ),
      <.p(
        "It should be set based on the number of opened files allowed by the operating system. ",
        " This limit does not include ",
        RouterController.router.link(Page.LevelZero)("LevelZero's"),
        " opened ",
        RouterController.router.link(Page.Map)("Map"),
        " files when should be accounted for in the ",
        RouterController.router.link(Page.Acceleration)("acceleration"),
        " configuration."
      ),
      <.p(
        "For example, if the operation system allows for a maximum of 1000 files be opened and ",
        "the acceleration allows for a maximum of 10 ",
        RouterController.router.link(Page.Map)("Maps"),
        " be opened in ",
        RouterController.router.link(Page.LevelZero)("LevelZero"),
        " then ",
        <.span(^.className:="snippet", "maxSegmentsOpen"),
        " should be set to 990 (1000 - 10) or 800 to also account for the ",
        RouterController.router.link(Page.SegmentsOpenCheckDelay)("segmentsOpenCheckDelay"),
        "."
      ),
      <.p(
        "The interval at which the Segments are closed can be configured via ",
        RouterController.router.link(Page.SegmentsOpenCheckDelay)("segmentsOpenCheckDelay"),
        "."
      )
    )
}
