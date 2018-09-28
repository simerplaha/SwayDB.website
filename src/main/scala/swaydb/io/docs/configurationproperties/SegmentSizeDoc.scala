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
import swaydb.io.common.{LinkIn, Snippet}
import swaydb.io.{Page, RouterController}

object SegmentSizeDoc {

  def name = "segmentSize: Int"

  def link =
    RouterController.router.link(Page.SegmentSize)(name)

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),
      <.p(
        "Specifies the minimum size of each ",
        RouterController.router.link(Page.Segment)("Segment"),
        " in the ",
        RouterController.router.link(Page.Level)("Level"),
        "."
      ),
      <.p(
        "This configuration can be updated at any time. ",
        Snippet("segmentSize"),
        " can be increased or decreased in the future for changing database requirements."
      ),
      <.h3("Increasing"),
      <.p(
        <.strong("Increasing"),
        " the ",
        <.span(^.className := "snippet", "segmentSize"),
        """ on an existing Level in the database will trigger "Segment collapsing" for the Level which gets run on database reboot and during """,
        RouterController.router.link(Page.Compaction)("Compaction"),
        ". This merges all small Segments in the ",
        RouterController.router.link(Page.Level)("Level"),
        " into one or multiple larger Segments. ",
      ),
      <.p(
        "On database reboot small Segments are checked and are merged into larger Segments.",
      ),

      <.p(
        "Deleting majority of the key-values from a Level can also create smaller Segments that will eventually get merged into larger ",
        "Segments during the Compaction process."
      ),

      <.p(
        "Compressing Segments could also result in smaller Segments during the merge process depending the configured ",
        LinkIn(Page.GroupingStrategy),
        ". These Segments are also eventually merged into larger Segments."
      ),

      <.p(
        "If all the Segments in the Level are smaller than updated ",
        <.span(^.className := "snippet", "segmentSize"),
        ", then the small Segments will get merged into one of the ",
        " existing smaller Segment in the Level to create a larger Segment or Segments."
      ),

      <.h3("Decreasing"),
      <.p(
        <.strong("Decreasing"),
        " the ",
        <.span(^.className := "snippet", "segmentSize"),
        " on an existing Level in the database gets applied when new Segments get added to the Level during the ",
        RouterController.router.link(Page.Compaction)("Compaction"),
        " process which will eventually result in all large Segments being split in to multiple smaller Segments."
      )
    )
}
