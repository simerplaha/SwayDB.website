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

object RecoveryModeDoc {

  def name = "recoveryMode: RecoveryMode"

  def link =
    RouterController.router.link(Page.RecoveryMode)(name)

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),
      <.p(
        "On JVM shutdown all files are properly closed and all memory-mapped files are forced written disk to ensure that all entries ",
        "are written to disk. In case of unclean shutdown the following recovery modes can be used to recover entries if there was a data corruption.",
      ),
      <.h3("ReportFailure"),
      <.p(
        "Reports any failure or corruption of ",
        RouterController.router.link(Page.Map)("Map"),
        " files in ",
        RouterController.router.link(Page.Level0)("Level0"),
        " on startup and does not perform automatic recovery."
      ),

      <.h3("DropCorruptedTailEntries"),
      <.p(
        "Recovers entries before the corruption, dropping all entries after the corrupted entry in the ",
        RouterController.router.link(Page.Map)("Map"),
        " file and continues recovering other ",
        RouterController.router.link(Page.Map)("Map"),
        " files in ",
        RouterController.router.link(Page.Level0)("Level0"),
        "."
      ),

      <.h3("DropCorruptedTailEntriesAndMaps"),
      <.p(
        "Recovers entries before the corruption, dropping all entries after the corrupted entry in the ",
        RouterController.router.link(Page.Map)("Map"),
        " file and also dropping all the entries in other ",
        RouterController.router.link(Page.Map)("Map"),
        " files that were created after the corrupted entry in ",
        RouterController.router.link(Page.Level0)("Level0"),
        "."
      ),
    )
}
