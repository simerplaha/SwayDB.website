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

package swaydb.io.docs.implementation.meters

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}
import swaydb.io.docs.implementation.Level0Doc

object LevelMeterDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("LevelMeter")
      ),

      <.p(
        "Contains information about the current state of a ",
        RouterController.router.link(Page.Level)("Level"),
        "."
      ),

      <.p(
        "LevelMeter can be fetched from a database instance by invoking ",
        <.span(^.className := "snippet", "db.level1Meter"),
        " for the state of Level1 or ",
        <.span(^.className := "snippet", "db.levelMeter(levelNumber: Int)"),
        " for the state of any ",
        RouterController.router.link(Page.Level)("Level"),
        "."
      ),

      <.pre(
        <.code(^.className := "scala")(
          """
            |case class LevelMeter(segmentsCount: Int,
            |                      levelSize: Long)
          """.stripMargin
        )
      ),
      <.h4("segmentsCount: Int"),
      <.p(
        "The current total number of ",
        RouterController.router.link(Page.Segment)("Segments"),
        " in the ",
        RouterController.router.link(Page.Level)("Level"),
        "."
      ),

      <.h4("levelSize: Long"),
      <.p(
        "Total size (in bytes) of all the ",
        RouterController.router.link(Page.Segment)("Segments"),
        " in the ",
        RouterController.router.link(Page.Level)("Level"),
        "."
      )

    )
}
