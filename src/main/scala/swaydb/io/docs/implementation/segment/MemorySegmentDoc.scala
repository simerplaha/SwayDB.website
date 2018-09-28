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

package swaydb.io.docs.implementation.segment

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.Page
import swaydb.io.common.{LinkIn, LinkOut}

object MemorySegmentDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Memory Segment")
      ),
      <.p(
        "Memory Segments by default store key-values in an in-memory skip-list and do not perform any compression."
      ),

      <.h3("How to enable compression for Memory Segments ?"),
      <.p(
        "Compression can be enabled by specifying ",
        LinkIn(Page.GroupingStrategy),
        " for Memory Levels which will enable ",
        LinkIn(Page.SegmentFileFormat),
        "'s for all it's in-memory Segments and provide same compression as ",
        LinkIn(Page.PersistentSegment),
        "."
      ),
      <.h3("Limiting the number of uncompressed data in-memory"),
      <.p(
        LinkIn(Page.CacheSize),
        " can be specified to limit the number of uncompressed key-values in-memory.",
      ),
    )
}
