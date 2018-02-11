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

object MapSizeDoc {

  def name = "mapSize: Int"

  def link =
    RouterController.router.link(Page.MapSize)(name)

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),
      <.p(
        "Specifies the initial size of each ",
        RouterController.router.link(Page.Map)("Map"),
        " file in ",
        RouterController.router.link(Page.Level0)("Level0"),
        " before it's closed and submitted to ",
        RouterController.router.link(Page.Level)("Level1"),
        " for conversion into ",
        RouterController.router.link(Page.Segment)("Segment(s)"),
        "."
      ),
      <.p(
        "This property can also be updated during runtime with the ",
        RouterController.router.link(Page.Acceleration)("acceleration"),
        " configuration ."
      ),
      <.p(
        "Increasing the size of the Map during runtime can increase read & write throughput by reducing ",
        "the number of Maps in Level0, therefore reducing ",
        RouterController.router.link(Page.Compaction)("Compaction"),
        " workload.",
      ),
      <.p(
        "Currently the default setting uses ",
        RouterController.router.link(Page.NoBrakes)("Accelerator.noBrake"),
        " which disables blocking back-pressure ",
        RouterController.router.link(Page.Brake)("(Accelerator.brake)"),
        " but doubles the size of the Map if the number of in-memory Maps is greater then 5 with a maximum Map size set of 24.mb.",
      ),

    )
}
