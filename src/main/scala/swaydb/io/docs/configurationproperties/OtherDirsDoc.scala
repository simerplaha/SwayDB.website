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

object OtherDirsDoc {

  def name = "otherDirs: Seq[Dir]"

  def link =
    RouterController.router.link(Page.OtherDirs)(name)

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),
      <.p(
        "Secondary directories for a ",
        RouterController.router.link(Page.Level)("Level"),
        " that stores ",
        RouterController.router.link(Page.Segment)("Segments"),
        " based on the distribution ratio.",
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |case class Dir(path: Path = "/Disk2/db",
            |               distributionRatio: Int = 3)
          """.stripMargin
        ),
      ),
      <.h4("path"),
      <.p("Path of the secondary directory."),

      <.h4("distributionRatio"),
      <.p(
        "Specifies the overall number of Segments this directory should store. ",
      ),
      <.p(
        <.strong("For eg: "),
        """in the above snippet distributionRatio is 3 therefore, "/Disk2/db" will store 3 """,
        "Segments for every 1 Segment written to the root directory ",
        RouterController.router.link(Page.Dir)("(dir: Path)"),
        """. This will result in "/Disk2/db" storing 75% of the data and root directory will store the remaining 25%. """
      ),
      <.p(
        "Distribution ratio for the root directory ",
        RouterController.router.link(Page.Dir)("(dir: Path)"),
        " can also be provided by adding it to ",
        <.span(^.className := "snippet", "otherDirs"),
        "."
      ),
      <.div(
        ^.className := "alert alert-info",
        <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
        <.i(" This setting is used when a machine has disks of variable sizes. ")
      ),
    )
}
