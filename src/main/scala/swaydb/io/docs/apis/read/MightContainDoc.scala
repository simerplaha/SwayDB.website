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

object MightContainDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("mightContain")
      ),
      <.p(
        "Checks Bloom filters only."
      ),
      <.p(
        "Returns false if the key definitely does not exist. "
      ),
      <.p(
        "Returns true if the key ",
        <.u("might"),
        " exist."
      ),
      <.p(
        <.strong("Note: "),
        "True is always returned for removed keys that are not physically removed from the database ",
        "i.e. removed keys in the upper Levels. ",
        <.u("Removed keys are only physically deleted in last Level. "),
        "True is also always returned for ",
        RouterController.router.link(Page.Segment)(Page.Segment.name + "s"),
        " that contain ",
        RouterController.router.link(Page.RemoveRange)(Page.RemoveRange.name),
        " key-values, as BloomFilters are not created for Segments that contain ",
        RouterController.router.link(Page.RemoveRange)(Page.RemoveRange.name),
        " key-values."
      ),
      <.p(
        "The accuracy of this function also depends on the ",
        RouterController.router.link(Page.BloomFilterFalsePositiveRate)("'bloomFilterFalsePositiveRate: Double'"),
        " configuration setting."
      ),

      <.pre(
        <.code(^.className := "scala")(
          """
            |db.mightContain(1)
            |
            |""".stripMargin
        )
      ),

      <.p(
        "Use ",
        RouterController.router.link(Page.Contains)(Page.Contains.name),
        " if 100% accuracy is required. "
      ),
      <.h3(
        <.span(^.className := "snippet", Page.MightContain.name),
        " VS ",
        <.span(^.className := "snippet", Page.Contains.name)
      ),

      <.p(
        <.span(^.className := "snippet", Page.MightContain.name),
        " requires a maximum of one disk seek to fetch the bloomFilter from disk for persistent ",
        RouterController.router.link(Page.Segment)(Page.Segment.name + "s"),
        " (if it's not already in-memory). ",
        "For memory Segments BloomFilters are always kept in-memory."
      ),
      <.p(
        <.span(^.className := "snippet", Page.Contains.name),
        " might require multiple-disk seeks depending on the position of the key in the persistent Segment's index. ",
        "For memory Segments contains is executed on the Segment's in-memory Map."
      )
    )
  }
}