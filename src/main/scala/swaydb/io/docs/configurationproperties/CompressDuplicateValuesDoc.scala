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

object CompressDuplicateValuesDoc {

  def name = "compressDuplicateValues: Boolean"

  def link =
    RouterController.router.link(Page.Dir)(name)

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),
      <.p(
        "This property is used for ",
        LinkIn(Page.PersistentSegment),
        "s.",
      ),
      <.p(
        "It can also be used by ",
        LinkIn(Page.MemorySegment),
        "s if ",
        LinkIn(Page.GroupingStrategy),
        " is configured."
      ),
      <.p(
        "It saves storage space by writing duplicate values only ones within a Segment. "
      ),
      <.p(
        "It specially useful when using ",
        LinkIn(Page.UpdateRange),
        " or applications that store a lot of duplicate values such as tick/measurement data."
      )
    )
}
