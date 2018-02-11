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

package swaydb.io.docs.examples

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.Main

object CreatingTablesDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(^.id := "creating-tables", "Creating tables")
      ),
      ExamplesDoc.cloneSnippet,

      <.h3("Creating Tables using keys & values"),
      <.a(
        ^.href := "https://github.com/simerplaha/SwayDBApps/blob/master/src/test/scala/creatingtables/TableSpec.scala",
        ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - TableSpec.scala")),
        ^.target := "blank",
        ^.className := "btn btn-xs btn-info",
        "View test"
      ),

      <.h3("Creating Tables using a Set database with partial data ordering"),
      <.a(
        ^.href := "https://github.com/simerplaha/SwayDBApps/blob/master/src/test/scala/creatingtables/set/SetTableSpec.scala",
        ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - SetTableSpec.scala")),
        ^.target := "blank",
        ^.className := "btn btn-xs btn-info",
        "View test"
      )

    )
  }
}
