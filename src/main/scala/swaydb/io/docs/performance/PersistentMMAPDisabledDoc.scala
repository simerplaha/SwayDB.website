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

object PersistentMMAPDisabledDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Persistent memory-mapped disabled database performance")
      ),
      <.p(
        "Database: ",
        RouterController.router.link(Page.Persistent)("Persistent memory-mapped disabled")
      ),

      <.h3("Sequential write & sequential read"),
      <.h4("Write (6.15 seconds)"),
      <.p("162,000 writes/second"),
      <.h4("Read during compaction (12.64 seconds)"),
      <.p("79,000 reads/second"),
      <.h4("Read after compaction (8.75 seconds)"),
      <.p("114,000 reads/second"),

      <.h3("Random write & random read"),
      <.h4("Write (8.48 seconds)"),
      <.p("117,000 writes/second"),
      <.h4("Read during compaction (68.85 seconds)"),
      <.p("14,500 reads/second"),
      <.h4("Read after compaction (23.45 seconds)"),
      <.p("42,600 reads/second"),

      <.h3("Sequential write & random read"),
      <.h4("Write (6.10 seconds)"),
      <.p("163,900 writes/second"),
      <.h4("Read during compaction (67.81 seconds)"),
      <.p("14,700 reads/second"),
      <.h4("Read after compaction (28.58 seconds)"),
      <.p("34,900 reads/second"),

      <.h3("Random write & sequential read"),
      <.h4("Write (8.28 seconds)"),
      <.p("120,700 writes/second"),
      <.h4("Read during compaction (13.36 seconds)"),
      <.p("74,800 reads/second"),
      <.h4("Read after compaction (8.03 seconds)"),
      <.p("124,500 reads/second"),

      <.h3("Forward iteration"),
      <.h4("During compaction (14.00 seconds)"),
      <.p("71,400 writes/second"),
      <.h4("After compaction (9.20 seconds)"),
      <.p("108,600 writes/second"),

      <.h3("Reverse iteration"),
      <.p("Reverse iterations on persistent databases are slow. No benchmark.")
    )
  }
}
