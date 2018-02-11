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

object StoringDataInChunksDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Storing data in chunks")
      ),
      body
    )
  }

  def body =
    <.div(
      <.p(^.className := "heading")(
        "Splitting a 3.mb in-memory data-set into 3 slices of 1.mb each without creating 3 copies of the original array.",
        <.a(
          ^.href := "https://github.com/simerplaha/SwayDBApps/blob/master/src/test/scala/chunk/ChunkSpec.scala",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - Chunks")),
          ^.role := "button",
          ^.className := "btn btn-xs btn-info pull-right",
          ^.target := "blank",
          "View test"
        )
      ),

      <.pre(
        <.code(^.className := "scala")(
          """
            |import swaydb._
            |import swaydb.serializers.Default._
            |
            |val db = SwayDB.persistent[Int, Slice[Byte]](dir = dir).get
            |
            |val file: Array[Byte] = randomBytes(3.mb) //a 3.mb byte array
            |
            |val slices = Slice(file).groupedSlice(3) //splits of 3 slices of 1.mb each without creating copies of original array
            |
            |//batch write the file slices.
            |db.batchPut((1, slices(0)), (2, slices(1)), (3, slices(2)))
          """.stripMargin
        )
      )
    )
}
