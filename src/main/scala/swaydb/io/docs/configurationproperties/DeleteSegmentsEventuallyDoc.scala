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
import swaydb.io.common.{LinkIn, Snippet}
import swaydb.io.{Page, RouterController}

object DeleteSegmentsEventuallyDoc {

  def name = "deleteSegmentsEventually: Boolean"

  def link =
    RouterController.router.link(Page.DeleteSegmentsEventually)(name)

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),
      <.p(
        "If true and after successful compaction ",
        LinkIn(Page.Segment, Page.Segment.name + "s"),
        " are deleted eventually when the ",
        LinkIn(Page.MaxSegmentsOpen),
        " limit is reached. If false, Segments are deleted immediately."
      ),

      <.h3("Why?"),
      <.p(
        "Immediately deleting a ",
        LinkIn(Page.Segment, Page.Segment.name),
        " while a read is in progress ",
        " can cause reads to fail and which would result in the read being retried. "
      ),
      <.p(
        "Setting this config to ",
        Snippet("true"),
        " reduces the number of read retires since the Segments are not immediately deleted."
      )
    )
}
