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

package swaydb.io.docs

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object FaultTolerantDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Fault tolerant")
      ),
      <.p(
        "Fatal server crashes, bad sectors on disk, other hardware failure can cause file corruption ",
        " on any file system."
      ),
      <.p(
        "SwayDB's goal with handling failure to is to detect and report failure accurately so that it's easy to debug and recover data. "
      ),
      <.h3("Handling file corruption"),
      <.h4(RouterController.router.link(Page.Segment)("Segment (.seg)")),
      <.p(
        "Every ",
        RouterController.router.link(Page.Segment)("Segment"),
        " file contains a CRC checksum for the last 7 bytes of the index block to detect corruption. "
      ),
      <.p(
        "Any corruption to the file will fail the CRC check and the errors will get reported pointing to the corrupted offset in the file. "
      ),
      <.p(
        "Compactions to the corrupted Segment are also stopped to avoid any further corruption of data."
      ),

      <.h4(RouterController.router.link(Page.Map)("Map (.log)")),
      <.p(
        "Every ",
        RouterController.router.link(Page.Map)("Map"),
        " entry contains a CRC checksum header. "

      ),
      <.p(
        "Failure to read the log file gets reported with the location of the failure during database initialisation."
      ),
      <.p(
        "Recovery strategy for corrupted entries can be configured via ",
        RouterController.router.link(Page.RecoveryMode)("recoveryMode"),
        "."
      ),

      <.h3("Handling missing files"),
      <.p(
        "On database reboot all files are validated before successfully initialising the database. ",
        "Missing Segment & Map files with the location will get reported."
      )

    )
  }
}
