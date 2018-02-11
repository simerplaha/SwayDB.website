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

object SegmentsOpenCheckDelayDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("segmentsOpenCheckDelay: FiniteDuration")
      ),
      <.p(
        "Sets the interval at which ",
        RouterController.router.link(Page.MaxSegmentsOpen)("maxSegmentsOpen"),
        " limit checks are performed which closes the oldest opened ",
        RouterController.router.link(Page.Segment)("Segments"),
        " until the ",
        <.span(^.className := "snippet", "maxSegmentsOpen"),
        " limit is reached."
      ),
      <.p(
        "Limit queue checks and closing Segments are expensive operations. ",
        "Executing these checks after opening each ",
        RouterController.router.link(Page.Segment)("Segment"),
        " during read & compaction process (in the same Thread) showed read & compaction performance loss",
        " therefore they are executed on a background thread."
      ),

      <.p(
        "The current solution requires a fixed interval be set. Eventually this will be updated to be an auto configuration.",
      )
    )
}