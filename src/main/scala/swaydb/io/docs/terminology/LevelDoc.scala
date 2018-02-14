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

package swaydb.io.docs.terminology

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object LevelDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Level")
      ),

      <.p(
        "Responsible for",
        <.ul(
          <.li(
            "Merging new ",
            RouterController.router.link(Page.Map)("Maps"),
            " and ",
            RouterController.router.link(Page.Segment)("Segments"),
            " with existing Segments in the Level.",
          ),
          <.li("Collapsing multiple smaller Segments into a larger Segment."),
          <.li(
            "Serving read requests and forwarding read requests to the next Level ",
            "if the current Level does not contain the requested key."
          )
        ),
        <.p(
          "It also maintains an ",
          RouterController.router.link(Page.Segment)("Appendix"),
          " file which stores information about every Segment in the Level."
        )
      ),

      <.h3("How are Segments committed to a Level ?"),
      <.p(
        "During the ",
        RouterController.router.link(Page.Compaction)("Compaction"),
        " process ",
        RouterController.router.link(Page.Segment)("Segments"),
        " are pushed from upper Levels to lower Level which creates new Segments and old Segments are deleted from the Level. ",
        "Each Level maintains an ",
        RouterController.router.link(Page.Appendix)("Appendix"),
        " file that contains information about all the Segments that exists in that Level. "
      ),
      <.p(
        "In case of failure when writing to the Appendix file, the Level will revert the current Compaction by deleting ",
        "the newly created Segments. ",
        "If JVM gets terminated during the revert process, orphan Segments (Segments not in the appendix file) are deleted on ",
        "database reboot therefore, always reverting back to the previous stable state of the Level."
      ),
      <.p(
        "In case of Appendix file corruption, ",
        RouterController.router.link(Page.RepairAppendix)(<.span(^.className := "snippet", "repairAppendix")),
        " can be executed on the Level to re-create a new Appendix file."
      )
    )
}