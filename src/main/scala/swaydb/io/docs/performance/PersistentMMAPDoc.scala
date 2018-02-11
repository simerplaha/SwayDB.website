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

object PersistentMMAPDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Persistent memory-mapped database performance")
      ),
      <.p(
        "Database: ",
        RouterController.router.link(Page.Persistent)("Persistent memory-mapped")
      ),

      <.h3("Sequential write & sequential read"),
      <.h4("Write (3.24 seconds)"),
      <.p("308,000 writes/second"),
      <.h4("Read during compaction (5.36 seconds)"),
      <.p("186,000 reads/second"),
      <.h4("Read after compaction (3.16 seconds)"),
      <.p("316,000 reads/second"),

      <.h3("Random write & random read"),
      <.h4("Write (5.03 seconds)"),
      <.p("198,000 writes/second"),
      <.h4("Read during compaction (22.39 seconds)"),
      <.p("44,000 reads/second"),
      <.h4("Read after compaction (11.57 seconds)"),
      <.p("86,000 reads/second"),

      <.h3("Sequential write & random read"),
      <.h4("Write (3.48 seconds)"),
      <.p("287,000 writes/second"),
      <.h4("Read during compaction (19.96 seconds)"),
      <.p("50,000 reads/second"),
      <.h4("Read after compaction (10.42 seconds)"),
      <.p("95,000 reads/second"),

      <.h3("Random write & sequential read"),
      <.h4("Write (5.06 seconds)"),
      <.p("197,000 writes/second"),
      <.h4("Read during compaction (8.79 seconds)"),
      <.p("113,000 reads/second"),
      <.h4("Read after compaction (3.16 seconds)"),
      <.p("316,000 reads/second"),

      <.h3("Forward iteration"),
      <.h4("During compaction (7.63 seconds)"),
      <.p("131,000 writes/second"),
      <.h4("After compaction (4.18 seconds)"),
      <.p("239,000 writes/second"),

      <.h3("Reverse iteration"),
      <.p("Reverse iterations on persistent databases are slow. No benchmark.")
    )
  }
}
