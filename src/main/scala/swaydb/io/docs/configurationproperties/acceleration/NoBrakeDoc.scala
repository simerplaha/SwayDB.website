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

object NoBrakeDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Accelerator.noBrakes (non-blocking)")
      ),
      <.div(
        ^.className := "alert alert-info",
        <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
        <.i(
          " This setting only updates the ",
          <.span(^.className := "snippet", "nextMapSize"),
          " and disables ",
          <.span(^.className := "snippet", "brake"),
          ". It can be used for applying back-pressure with external streaming libraries."
        )
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |val db =
            |  SwayDB.memory[Long, String](
            |    mapSize = 4.mb,
            |    acceleration =
            |      Accelerator.noBrakes(
            |        onMapCount = 6,
            |        increaseMapSizeBy = 2,
            |        maxMapSize = 24.mb
            |      )
            |  )
            |""".stripMargin
        )
      ),
      <.p(
        "Since the ",
        RouterController.router.link(Page.MapSize)("mapSize"),
        " is set to 4.mb, ",
        " the 6",
        <.sup("th "),
        RouterController.router.link(Page.Map)("Map"),
        " will have the size 8.mb, the 7",
        <.sup("th"),
        " Map will have the size 16.mb and then 24.mb for all the subsequent Maps as 24.mb is the max limit in the above configuration.",
      ),
      <.p(
        "When the overflow is controlled (when mapCount is less than 6) new Maps switch back to using 4.mb file sizes."
      )
    )
}
