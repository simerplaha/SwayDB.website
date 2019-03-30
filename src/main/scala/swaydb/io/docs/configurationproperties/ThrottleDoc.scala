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

package swaydb.io.docs.configurationproperties

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object ThrottleDoc {

  def name = "throttle: LevelMeter => Throttle"

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),
      <.p(
        "Throttle controls the speed at which a ",
        RouterController.router.link(Page.Level)("Level"),
        " submits it's ",
        RouterController.router.link(Page.Segment)("Segments"),
        " to the next Level for ",
        RouterController.router.link(Page.Compaction)("Compaction"),
        "."
      ),

      <.p(
        "It's a function that given the current state of the Level (",
        RouterController.router.link(Page.LevelMeter)("LevelMeter"),
        ") returns the next delay and number of Segments to ",
        "push/compact into the next Level. ",
        "It's a always invoked after each ",
        RouterController.router.link(Page.Compaction)("Compaction"),
        " cycle."
      ),

      <.pre(
        <.code(^.className := "scala")(
          """
            |throttle =
            |  (levelMeter: LevelMeter) =>
            |    if (levelMeter.levelSize > 1.gb)
            |      Throttle(pushDelay = 1.second, segmentsToPush = 10)
            |    else
            |      Throttle(pushDelay = Duration.Zero, segmentsToPush = 0)
          """.stripMargin
        )
      ),
      <.h4("pushDelay"),
      <.p("Delay before executing the Push."),

      <.h4("segmentsToPush"),
      <.p("The number of Segments to push at once."),

      <.i(
        <.p(
          "In the above configuration when the ",
          RouterController.router.link(Page.Level)("Level's"),
          " total size ",
          " grows over 1.gb, 10 ",
          RouterController.router.link(Page.Segment)("Segments"),
          " are pushed to the next ",
          RouterController.router.link(Page.Level)("Level"),
          " after a delay of 1.second, else the Compaction is paused."
        ),
        <.p(
          RouterController.router.link(Page.LevelMeter)(
            <.span(^.className := "snippet", "levelMeter.segmentsCount")
          ),
          " returns the number of Segments in the Level which can also be used to ",
          " create a Throttle."
        )
      ),

      <.h3("Pausing Compaction"),
      <.p(
        "Returning a Throttle with ",
        <.span(^.className := "snippet", "segmentsToPush"),
        " set to 0 will pause the Compaction",
        " until a new ",
        RouterController.router.link(Page.Segment)("Segment"),
        " or ",
        RouterController.router.link(Page.Map)("Map"),
        " get added to the Level which will invoke the ",
        <.span(^.className := "snippet", "throttle"),
        " function again."
      ),
    )
}
