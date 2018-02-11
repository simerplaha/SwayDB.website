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

package swaydb.io.docs.examples

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.{Main, Page, RouterController}

object EventSourcingDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Event-sourcing")
      ),
      <.p(
        <.a(
          ^.href := "https://martinfowler.com/eaaDev/EventSourcing.html",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - EventSourcing.html")),
          ^.target := "blank",
          "Event-sourcing"
        ),
        " is a software architecture where an application stores sequence of immutable Events of it's domain objects",
        ". The state of a domain object is reconstructed by replaying Events for the domain object's id."
      ),
      <.i(
        <.p(
          <.strong("Note: "),
          "SwayDB does not force immutable data. Events can still be deleted with the ",
          <.span(^.className := "snippet", RouterController.router.link(Page.Remove)("remove")),
          " or ",
          <.span(^.className := "snippet", RouterController.router.link(Page.BatchRemove)("batchRemove")),
          "."
        )
      ),
      <.p(
        "This test example demonstrates how Events can be stored in a Set database with partial ordering. Events for each ",
        " domain object eventually get stored as a group for efficient state reconstruction irrespective of their creation time (sequence number)."
      ),
      <.p(
        "It also demos how ",
        RouterController.router.link(Page.Batch)("batch"),
        " can be used to create pointer Events which allows for full database iteration ",
        " of Events in the order they were created."
      ),

      <.a(
        ^.href := "https://github.com/simerplaha/SwayDB.examples/blob/master/src/test/scala/eventsourcing/EventSourcingSpec.scala",
        ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - EventSourcingSpec.scala")),
        ^.target := "blank",
        ^.className := "btn btn-xs btn-info",
        "View test"
      )
    )
  }
}
