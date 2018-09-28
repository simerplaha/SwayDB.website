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

package swaydb.io.docs.implementation

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object Level0Doc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Level0")
      ),

      <.p(
        "Level0 is the root Level in a database's Level hierarchy. Its responsible for serving ",
        "all incoming write & read requests and forwarding read requests to the next ",
        RouterController.router.link(Page.Level)("Level"),
        " if it does not contain the requested key."),
      <.p(
        "It also maintains a sequence ",
        RouterController.router.link(Page.Map)("Maps"),
        " that store newly written key-values. When a Map",
        " is full, a new Map is created to store the next batch of write requests."
      ),
      <.p(
        "Maps that are full are read-only that eventually get pushed to the next ",
        RouterController.router.link(Page.Level)("Level"),
        " where they get converted into ",
        RouterController.router.link(Page.Segment)("Segments"),
        " during the ",
        RouterController.router.link(Page.Compaction)("Compaction"),
        " processes.",
      ),

      <.p(
        RouterController.router.link(Page.Map)("Map"),
        " files can be memory-mapped by configuring the property ",
        RouterController.router.link(Page.MMAP)("mmap"),
        "."
      ),

    )
}
