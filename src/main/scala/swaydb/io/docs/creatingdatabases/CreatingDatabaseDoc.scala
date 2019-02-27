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
import swaydb.io.common.{LinkIn, Snippet}
import swaydb.io.{Page, RouterController}

object CreatingDatabaseDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Create databases")
      ),
      <.h3("Storage types"),
      <.p(
        "1. ",
        LinkIn(Page.Persistent),
        <.span(" - stores data to disk.")
      ),

      <.p(
        "2. ",
        LinkIn(Page.Memory),
        <.span(" - stores data in-memory.")
      ),

      <.p(
        "3. ",
        LinkIn(Page.EventuallyPersistent),
        <.span(" - stores data in-memory that is periodically persisted based on it's "),
        LinkIn(Page.ConfiguringLevels),
        "."
      ),

      <.p(
        "4. ",
        LinkIn(Page.Custom, Page.Custom.name + " configuration"),
        <.span(" - initialises custom configured Levels. See "),
        LinkIn(Page.ConfiguringLevels),
        "."
      ),

      <.h3("Zero databases"),
      <.p(
        "Both ",
        LinkIn(Page.Persistent),
        " & ",
        LinkIn(Page.Memory),
        " databases can also be initialised as single leveled, ",
        LinkIn(Page.LevelZero),
        " only databases with ",
        Snippet(".zero"),
        " extension."
      ),
      <.p(
        Snippet(".zero"),
        " databases are lightweight databases that do not run compaction and do not have any background process running. ",
        " They can be used to store static data or data that rarely changes."
      ),

      <.div(
        ^.className := "alert alert-info",
        <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
        <.i(
          """ Default databases were configured experimenting on a MacBook Pro laptop.
            |Further tuning of these configurations on production quality servers would
            |result in better performance.""".stripMargin
        )
      )
    )
}
