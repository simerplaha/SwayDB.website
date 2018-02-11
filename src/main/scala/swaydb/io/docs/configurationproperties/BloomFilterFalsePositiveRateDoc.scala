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

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Main, Page, RouterController}

object BloomFilterFalsePositiveRateDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("bloomFilterFalsePositiveRate: Double")
      ),
      <.p(
        <.a(^.href := "https://github.com/alexandrnikitin/bloom-filter-scala",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - bloom-filter-scala")),
          ^.target := "blank",
          "bloom-filter-scala"
        ),
        " library by ",
        <.a(
          ^.href := "https://github.com/alexandrnikitin",
          ^.target := "blank",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - @alexandrnikitin")),
          "@alexandrnikitin"
        ),
        " is used for creating Bloom filters."
      ),

      <.p(
        "A Bloom filter is created for each ",
        RouterController.router.link(Page.Segment)("Segment"),
        " and is persisted to the Segment's footer.",
        " Bloom filters are kept in memory for all open Segments and are removed if the Segment file is closed or deleted by the ",
        RouterController.router.link(Page.Compaction)("Compaction"),
        " process."
      ),

      <.p(
        <.i(
          "Note: The maximum number of concurrent opened Segments can be configured via the property ",
          RouterController.router.link(Page.MaxSegmentsOpen)("maxSegmentsOpen"),
          "."
        )
      ),

      <.p(
        "A lower false positive rate can be specified (currently defaulted to 0.1 for Map databases and 0.01 for Set databases) ",
        "depending on the application's requirement for querying non existent keys. ",
        "Bloom filters are kept in-memory as long as the Segment file is opened, ",
        "a lower false positive rate will have higher memory requirement which should be considered."
      ),

      <.p(
        RouterController.router.link(Page.ReadAPI)("Read API"),
        " exposes the library's ",
        RouterController.router.link(Page.MightContain)(
          <.span(^.className := "snippet", "mightContain")
        ),
        " function which can invoked on a database instance."
      )
    )
}