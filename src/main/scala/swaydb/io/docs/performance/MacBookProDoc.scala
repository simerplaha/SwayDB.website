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

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.{Main, Page, RouterController}

object MacBookProDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("MacBook Pro mid 2014")
      ),
      <.p(
        "Test run by: "
//        <.a(
//          ^.href := "https://github.com/simerplaha",
//          ^.target := "blank",
//          "@simerplaha"
//        )
      ),
      <.h3("Specs"),
      <.div("2.8 GHz Intel Core i7"),
      <.div("16GB (8GB available RAM)"),
      <.div("8 cores (4 physical cores)"),

      <.h3("Data set"),
      <.p("Tests were run on pre-serialized keys & values"),
      <.div(
        <.strong("Key"),
        " - Long (8 bytes)"
      ),
      <.div(
        <.strong("Value"),
        " - String (60 bytes)"
      ),
      <.div(
        <.strong("Entries"),
        " - 1 million"
      ),

      <.h3("Benchmark code"),
      <.p(
        <.a(
          ^.href := "https://github.com/simerplaha/SwayDB.benchmark",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - SwayDB.benchmark")),
          ^.target := "blank",
          "SwayDB.benchmark"
        )
      ),
      <.h3("Configurations used"),
      <.p("Defaults"),

      <.h3("Results"),
      <.p(
        RouterController.router.link(Page.MemoryPerformance)("Memory")
      ),
      <.p(
        RouterController.router.link(Page.PersistentMMAP)("Persistent memory-mapped")
      ),
      <.p(
        RouterController.router.link(Page.PersistentMMAPDisabled)("Persistent memory-mapped disabled")
      )
    )
  }
}
