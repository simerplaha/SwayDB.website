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

package swaydb.io.docs.configurationproperties.acceleration

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object AccelerationDoc {

  def name = "acceleration: Level0Meter => Accelerator"

  def link =
    RouterController.router.link(Page.Acceleration)(name)

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),

      <.p(
        "'acceleration' defines settings for the write speed by controlling ",
        RouterController.router.link(Page.Map)("Maps"),
        " overflow in ",
        RouterController.router.link(Page.Level0)("Level0"),
        " during runtime.",
      ),

      <.p(
        "It's a function that given the current state of ",
        RouterController.router.link(Page.Level0)("Level0"),
        " ",
        RouterController.router.link(Page.Level0Meter)("(Level0Meter)"),
        " returns ",
        <.span(^.className := "snippet", "Accelerator"),
        " that contains the ",
        <.span(^.className := "snippet", "nextMapSize"),
        " and optional ",
        <.span(^.className := "snippet", "brake"),
        ". It's always invoked before creating a new ",
        RouterController.router.link(Page.Map)("Map"),
        " file in Level0."
      ),

      <.p(
        <.span(^.className := "snippet", "nextMapSize"),
        " overrides the pre-configured setting ",
        RouterController.router.link(Page.MapSize)("'mapSize: Int'"),
        " temporarily ",
        "until the overflow is controlled.",
      ),

      <.p(
        "Overflow can occur when writes occur faster than ",
        RouterController.router.link(Page.Compaction)("Compaction"),
        " can convert ",
        RouterController.router.link(Page.Map)("Maps"),
        " to ",
        RouterController.router.link(Page.Segment)("Segments"),
        ". In situations like these, even before applying back-pressure the size of next Map can be increased. "
      ),

      <.h3("Why increase mapSize ?"),
      <.p(
        "Increasing the mapSize reduces the total number of Maps in Level0 ",
        "therefore, reducing overall Compaction workload which gives Compaction a chance to catch up ",
        "without effecting write throughput and before applying back-pressure."
      ),

      <.pre(
        <.code(^.className := "scala")(
          """
            |case class Accelerator(nextMapSize: Long = 12.mb,
            |                       brake: Option[Brake])
          """.stripMargin
        )
      ),

      <.h4("nextMapSize"),
      <.p("Size of the next map file."),

      <.h4("brake"),
      <.p("Blocks current write Thread to apply back-pressure."),
      <.p(
        <.strong("Note: "),
        <.i("External streaming libraries should be used to apply asynchronous back-pressure instead.")
      ),

      <.h3("Pre-configured accelerations"),
      <.p(
        <.span(^.className := "snippet", "Accelerator"),
        " provides the following 3 types of implementations to calculate the ",
        <.span(^.className := "snippet", "nextMapSize"),
        " and optionally return ",
        <.span(^.className := "snippet", "brake"),
        "."
      ),
      <.h4("1. ", RouterController.router.link(Page.Cruise)("Accelerator.cruise (non-blocking)")),
      <.h4("2. ", RouterController.router.link(Page.NoBrakes)("Accelerator.noBrake (non-blocking)")),
      <.h4("3. ", RouterController.router.link(Page.Brake)("Accelerator.brake (blocking)"))
    )
}
