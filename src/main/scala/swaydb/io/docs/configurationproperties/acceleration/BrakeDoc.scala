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

object BrakeDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Accelerator.brake (blocking)")
      ),
      <.div(
        ^.className := "alert alert-info",
        <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
        <.i(
          " This setting will ",
          <.span(^.className := "snippet", "brake"),
          " (blocking back-pressure) and update the ",
          <.span(^.className := "snippet", "nextMapSize"),
          "."
        )
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |val db =
            |  SwayDB.memory[Long, String](
            |    mapSize = 4.mb,
            |    acceleration =
            |      Accelerator.brake(
            |        increaseMapSizeOnMapCount = 4,
            |        increaseMapSizeBy = 2,
            |        maxMapSize = 24.mb
            |        brakeOnMapCount = 6,
            |        brakeFor = 1.second,
            |        releaseRate = 100.milliseconds,
            |      )
            |  )
          """.stripMargin
        )
      ),

      <.p(
        "If the mapCount reaches 4, the 4",
        <.sup("th "),
        RouterController.router.link(Page.Map)("Map's"),
        " ",
        RouterController.router.link(Page.MapSize)("mapSize"),
        " will have the size 8.mb (doubled), the 5",
        <.sup("th"),
        " Map will have the size 16.mb and then 24.mb for all the subsequent Maps (",
        RouterController.router.link(Page.MapSize)("maxMapSize"),
        ")."
      ),

      <.p("If increasing the Map sizes does not control the overflow and the mapCount reaches 6, then write request threads will be blocked."),
      <.p(
        "The first write request will be blocked for ",
        <.span(^.className := "snippet", "1.second"),
        ", decrementing the next write request block by ",
        <.span(^.className := "snippet", "100.milliseconds"),
        " (",
        <.span(^.className := "snippet", "releaseRate"),
        ")",
        " until the ",
        <.span(^.className := "snippet", "brake"),
        " is complete."
      ),

      <.p("This will block a total of 10 write requests."),
    )
}
