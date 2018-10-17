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

package swaydb.io.docs.creatingdatabases

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object CreatingDatabaseDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Create databases")
      ),
      <.div(
        ^.className := "alert alert-info",
        <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
        <.i(
          """ Default databases were configured experimenting on a MacBook Pro laptop.
            |Further tuning of these configurations on production quality servers would
            |result in better performance.""".stripMargin
        )
      ),
      <.h3("Database types"),
      <.h4(<.u(RouterController.router.link(Page.Persistent)("Persistent"))),
      <.p("Stores data to disk."),

      <.h4(<.u(RouterController.router.link(Page.Memory)("Memory"))),
      <.p("Stores data in-memory."),

      <.h4(<.u(RouterController.router.link(Page.MemoryPersistent)("Memory-persistent"))),
      <.p("Stores data in-memory that is periodically persistent."),

      <.h4(<.u(RouterController.router.link(Page.Custom)("Custom"))),
      <.p("Custom configurations."),

      <.h3("Data structures"),
      <.h4("swaydb.Map[K, V]"),
      <.p(
        "APIs are similar to ",
        <.span(^.className := "snippet", "mutable.SortedMap[K, V]"),
        "."
      ),
      <.p("Values are fetched lazily. Keys and Values are stored independently to each other."),

      <.h4("swaydb.Set[T]"),
      <.p(
        "APIs are similar to ",
        <.span(^.className := "snippet", "mutable.SortedSet[T]"),
        ". "
      ),
      <.p("Data is stored in the same location."),
      <.p(
        "This type is useful for applications that do not require conditional/lazy value fetches. ",
        "For example: ",
        RouterController.router.link(Page.EventSourcing)("Event-sourcing"),
        " & Time-series data.",
      )



      //      <.p(
      //        ^.className := "heading",
      //        "Key only databases can be created by invoking ",
      //        <.span(^.className := "snippet", "db.keys"),
      //        " on any database instance which provides APIs similar to ",
      //        <.span(^.className := "snippet", "mutable.SortedSet[T]"),
      //        "."
      //      ),
      //      PersistentDatabaseDoc.apply(),
      //      MemoryDatabaseDoc.apply(),
      //      MemoryPersistentDatabaseDoc.apply(),
      //      CustomDatabaseDoc.apply(),
    )
}
