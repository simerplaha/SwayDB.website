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

package swaydb.io.docs.apis

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object RepairAppendixDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("repairAppendix")
      ),
      <.div(
        ^.className := "alert alert-info",
        <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
        <.i(" Be sure to backup the database before executing this function.")
      ),
      <.p(
        <.span(^.className := "snippet", "repairAppendix"),
        " can be used to create a new ",
        RouterController.router.link(Page.Appendix)("Appendix"),
        " file for a ",
        RouterController.router.link(Page.Level)("Level"),
        " if the current Appendix file is unreadable."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |val repairResult: Try[RepairResult[Int]] =
            |  SwayDB.repairAppendix[Int](
            |    levelPath = "/Disk1/myDB/1", //root path of the level 1 folder.
            |    repairStrategy = AppendixRepairStrategy.Report
            |  )
            |""".stripMargin
        )
      ),
      <.h3("When can Appendix file become unreadable ?"),
      <.p(
        "This can occur due to bad sectors on disk or server crashes."
      ),
      <.p(
        "As mentioned in the ",
        RouterController.router.link(Page.Level)("Level"),
        " documentation - ",
        RouterController.router.link(Page.Segment)("Segments"),
        " that are not in the Level's Appendix file (orphan Segments) get deleted on database reboot.",
        " But when Appendix file becomes unreadable it becomes hard to distinguish between active and orphan Segments. ",
        "In this case ",
        <.span(^.className := "snippet", "repairAppendix"),
        " can be used to re-create the Appendix file."
      ),

      <.p(
        <.span(^.className := "snippet", "repairAppendix"),
        " returns a ",
        <.span(^.className := "snippet", "Try[RepairResult[T]]"),
        " because repairs can fail if the input ",
        <.span(^.className := "snippet", "levelPath"),
        " is incorrect."
      ),

      <.h3("RepairResult"),
      <.p(
        <.span(^.className := "snippet", "repairAppendix"),
        " can return one of the following results."
      ),
      <.h4("Failure(exception: Exception)"),
      <.p(
        "If the input ",
        <.span(^.className := "snippet", "levelPath"),
        " is incorrect or other IO exceptions."
      ),
      <.h4("Success(Repaired)"),
      <.p(
        "Returned when the repair was successful."
      ),
      <.h4("Success(OverlappingSegments)"),
      <.p(
        "Returned when the Level contains conflicting Segments with overlapping keys and ",
        <.span(^.className := "snippet", "AppendixRepairStrategy.Report"),
        " repair strategy is selected. ",
        "This result contains the following information about the overlapping Segments that can be used for manual inspection ",
        "before selecting one of the other repair strategies to automatically pick one of the overlapping Segments and discarding the other.",
        <.ul(
          <.li(
            <.u("path"), " - the path of the Segment."
          ),
          <.li(
            <.u("minKey"), " - smallest key in the Segment."
          ),
          <.li(
            <.u("maxKey"), " - largest key in the Segment."
          ),
          <.li(
            <.u("segmentSize"), " - size of the Segment in bytes."
          ),
          <.li(
            <.u("keyValueCount"), " - number of key-values in the Segment."
          )
        )
      ),

      <.h3("AppendixRepairStrategy"),
      <.p("One of the following repair strategies can be used to re-create the appendix file."),
      <.h4("Report"),
      <.p(
        "Successfully re-creates the appendix file if the Level contains no conflicting Segments with overlapping key ranges."
      ),

      <.h4("KeepOld (recommended default)"),
      <.p(
        "If the Level contains Segments with overlapping key ranges, this strategy keeps the older Segment and deletes the newer Segment."
      ),
      <.p(
        <.i(
          <.strong("Note: "),
          "This setting should always be preferred if the ",
          <.span(^.className := "snippet", "Report"),
          " setting results in ",
          <.span(^.className := "snippet", "RepairResult.OverlappingSegments"),
          ". It fixes the Level's Appendix file to create a stable Level state without any duplicate Segments with overlapping keys. ",
          "All redundant Segments (if any) will eventually get deleted as the Compaction continues."
        )
      ),

      <.h4("KeepNew"),
      <.p(
        "If the Level contains Segments with overlapping key ranges, this strategy keeps the newer Segment & deletes the older Segment."
      )

    )
  }

}
