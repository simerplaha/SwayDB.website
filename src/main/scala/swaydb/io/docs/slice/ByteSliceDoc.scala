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

package swaydb.io.docs.slice

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Main, Page, RouterController}

object ByteSliceDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Slice[Byte]")
      ),
      <.p(^.className := "heading")(
        <.span(^.className := "snippet", "Slice[Byte]"),
        " is an ",
        <.span(^.className := "snippet", "Iterable[Byte]"),
        " that implements a subset of APIs provided by ",
        <.span(^.className := "snippet", "ByteArrayBuffer"),
        " that can be used for building ",
        RouterController.router.link(Page.CustomSerializers)("custom serializers"),
        "."
      ),

      <.p(
        "Supported APIs: ",
        <.span(^.className := "snippet", "addByte"),
        ", ",
        <.span(^.className := "snippet", "addBytes"),
        ", ",
        <.span(^.className := "snippet", "addInt"),
        ", ",
        <.span(^.className := "snippet", "readInt"),
        ", ",
        <.span(^.className := "snippet", "addIntSigned"),
        ", ",
        <.span(^.className := "snippet", "readIntSigned"),
        ", ",
        <.span(^.className := "snippet", "addIntUnsigned"),
        ", ",
        <.span(^.className := "snippet", "readIntUnsigned"),
        ", ",
        <.span(^.className := "snippet", "addLong"),
        ", ",
        <.span(^.className := "snippet", "readLong"),
        ", ",
        <.span(^.className := "snippet", "addLongUnsigned"),
        ", ",
        <.span(^.className := "snippet", "readLongUnsigned"),
        ", ",
        <.span(^.className := "snippet", "addLongSigned"),
        ", ",
        <.span(^.className := "snippet", "readLongSigned"),
        ", ",
        <.span(^.className := "snippet", "addString"),
        ", ",
        <.span(^.className := "snippet", "readString"),
        ", ",
        <.span(^.className := "snippet", "toByteBuffer"),
        ", ",
        <.span(^.className := "snippet", "toByteArrayOutputStream"),
        ", ",
        <.span(^.className := "snippet", "createReader")
      ),

      <.h3("createReader"),
      <.p(
        "Readers can be created on ",
        <.span(^.className := "snippet", "Slice[Byte]"),
        " to read bytes sequentially that maintain the position of the last read byte."
      ),

      <.h3("Example: "),
      <.p(
        "Write/Serialize a case class to ",
        <.span(^.className := "snippet", "Slice[Byte]"),
        " and read the case class back from the ",
        <.span(^.className := "snippet", "Slice[Byte]"),
        ".",
        <.a(
          ^.href := "https://github.com/simerplaha/SwayDBApps/blob/master/src/test/scala/slicereader/SliceReaderSpec.scala",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - SliceReaderSpec")),
          ^.role := "button",
          ^.className := "btn btn-xs btn-info pull-right",
          ^.target := "blank",
          "View test"
        )
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |import swaydb.data.slice.Slice
            |
            |//stores performance results of a program written in programming languages
            |case class ProgramPerformance(speedScore: Int,
            |                              linesOfCode: Long,
            |                              language: String)
            |
            |val program = ProgramPerformance(10, 10L, "Scala")
            |
            |//write case class to Slice[Byte] as a sequence of bytes.
            |val slice =
            |  Slice.create[Byte](100) //create an unknown size of Slice
            |    .addInt(program.speedScore)
            |    .addLong(program.linesOfCode)
            |    .addString(program.language)
            |    .close() //close so that Slice's toOffset is re-adjusted to the last written byte.
            |
            |//read Slice[Byte] to rebuild the case class
            |val reader = slice.createReader()
            |ProgramPerformance(
            |  speedScore = reader.readInt(),
            |  linesOfCode = reader.readLong(),
            |  language = reader.readString()
            |)
          """.stripMargin
        )
      )

    )
}
