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

package swaydb.io.docs

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.Page
import swaydb.io.common.LinkIn

object GoalsDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Goals")
      ),
      <.h3("Speed & Storage"),
      <.p("- To get maximum write & read performance on a single machine for persistent (single/multiple disks) and in-memory storage types."),
      <.p(
        "- To have efficient storage implementation (persistent & in-memory) in order to reduce the cost of hosting a set of ",
        "data on a single machine without sacrificing read & write performance."
      ),

      <.h3("Durability"),
      <.p("Gracefully handle and recover from data corruption."),

      <.h3("Types, Types, Types!"),
      <.p("Type safe APIs to catch potential errors during compile time."),

      <.h3("No query language"),
      <.p(
        "Query languages add a big learning curve for us. SwayDB aims at ",
        " providing type-safe API mimicking Scala's collection library and ability run Scala code directly (",
        LinkIn(Page.CacheFunction),
        ") on the database - no query language to learn."
      ),

      <.h3("Support for other data structures"),
      <.p(
        <.span(^.className := "snippet", "List[T]"),
        ", ",
        <.span(^.className := "snippet", "Queue[T]"),
        ", ",
        <.span(^.className := "snippet", "Stack[T]"),
        ", ",
        <.span(^.className := "snippet", "IndexedSeq[T]"),
        " etc."
      ),

      <.h3("In-built typed APIs to support common database types"),
      <.ul(
        <.li("Event-sourcing"),
        <.li("Time-series"),
        <.li("Graph"),
        <.li("Logging (info, error, debug, warning)")
      ),

      <.h3("Auto-tuning"),
      <.p(
        "Currently the database configuration require static inputs for majority of the parameters other ",
        " than Accelerator & Throttle which can be configured dynamically during runtime."
      ),
      <.p(
        "Auto-tune configuration in real-time by accounting for variables such as ",
        <.ul(
          <.li("Cores available or threads available"),
          <.li("Current disk IOps"),
          <.li("Current frequency of read requests of each Level"),
          <.li("Current frequency of write requests"),
          <.li("Current pending Compactions"),
          <.li("Current Level sizes"),
          <.li("Most & least read & updated Levels and their Compaction progress"),
          <.li("Most & least read & updated Segments"),
          <.li("Number of in-memory Segments vs on-disk Segments"),
          <.li(
            "Skipping Levels even if Levels are non-empty ",
            "based on the current Compaction workload of the target Level."
          ),
          <.li("etc.")
        ),
        " Considering the above variables during runtime would result is much better performance ",
        " and also make the configuration APIs simpler."
      ),

      <.h3("Encryption"),
      <.p("Provide ability to encrypt keys & values stored on disk.")
    )
  }

}
