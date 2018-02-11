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

package swaydb.io.docs.performance

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.{Page, RouterController}

object MemoryPerformanceDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Memory database performance")
      ),
      <.p(
        "Database: ",
        RouterController.router.link(Page.Memory)("Memory database")
      ),

      <.h3("Sequential write & sequential read"),
      <.h4("Write (1.53 seconds)"),
      <.p("653,000 writes/second"),
      <.h4("Read during compaction (2.97 seconds)"),
      <.p("336,000 reads/second"),
      <.h4("Read after compaction (1.65 seconds)"),
      <.p("606,000 reads/second"),

      <.h3("Random write & random read"),
      <.h4("Write (3.19 seconds)"),
      <.p("313,000 writes/second"),
      <.h4("Read during compaction (12.94 seconds)"),
      <.p("77,000 reads/second"),
      <.h4("Read after compaction (6.29 seconds)"),
      <.p("158,000 reads/second"),

      <.h3("Sequential write & random read"),
      <.h4("Write (1.60 seconds)"),
      <.p("625,000 writes/second"),
      <.h4("Read during compaction (5.53 seconds)"),
      <.p("180,000 reads/second"),
      <.h4("Read after compaction (4.46 seconds)"),
      <.p("224,000 reads/second"),

      <.h3("Random write & sequential read"),
      <.h4("Write (3.35 seconds)"),
      <.p("298,000 writes/second"),
      <.h4("Read during compaction (6.05 seconds)"),
      <.p("165,000 reads/second"),
      <.h4("Read after compaction (4.61 seconds)"),
      <.p("216,000 reads/second"),

      <.h3("Forward iteration"),
      <.h4("During compaction (3.28 seconds)"),
      <.p("304,000 writes/second"),
      <.h4("After compaction (1.59 seconds)"),
      <.p("628,000 writes/second"),

      <.h3("Reverse iteration"),
      <.h4("During compaction (3.22 seconds)"),
      <.p("310,000 writes/second"),
      <.h4("After compaction (1.69 seconds)"),
      <.p("591,000 writes/second")


    )
  }
}
