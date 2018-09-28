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

object Level0MeterDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Level0Meter")
      ),

      <.p(
        "Contains information about the current state of ",
        RouterController.router.link(Page.Level0)("Level0"),
        "."
      ),

      <.div(
        ^.className := "alert alert-info",
        <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
        <.i(" All write requests also return Level0Meter which can be used to apply asynchronous back-pressure.")
      ),

      <.p(
        "Level0Meter can also be fetched from a database instance by invoking ",
        <.span(^.className := "snippet", "db.level0Meter"),
        "."
      ),

      <.pre(
        <.code(^.className := "scala")(
          """
            |class Level0Meter(val defaultMapSize: Long = 4.mb,
            |                  val currentMapSize: Long = 8.mb,
            |                  val mapsCount: Long = 4)
          """.stripMargin
        ),
      ),
      <.p(""),
      <.h4("defaultMapSize: Long"),
      <.p(
        "The original ",
        RouterController.router.link(Page.MapSize)("mapSize"),
        " specified during the database configuration.",
      ),

      <.h4("currentMapSize: Long"),
      <.p(
        "Size of current ",
        RouterController.router.link(Page.Map)("Map"),
        " which was set from the previous ",
        RouterController.router.link(Page.Acceleration)("Acceleration"),
        "."
      ),

      <.h4("mapsCount: Long"),
      <.p(
        "Current total number of ",
        RouterController.router.link(Page.Map)("Maps"),
        " in ",
        RouterController.router.link(Page.Level0)("Level0"),
        "."
      )
    )
}
