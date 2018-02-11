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

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.{Main, Page, RouterController}

object CustomSerializersDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Custom serializers")
      ),
      <.p(
        "Default serializers for ",
        <.span(^.className := "snippet", "Int"),
        ", ",
        <.span(^.className := "snippet", "Long"),
        ", ",
        <.span(^.className := "snippet", "Char"),
        ", ",
        <.span(^.className := "snippet", "Double"),
        ", ",
        <.span(^.className := "snippet", "Float"),
        ", ",
        <.span(^.className := "snippet", "Short"),
        ", ",
        <.span(^.className := "snippet", "String"),
        ", ",
        <.span(^.className := "snippet", "Slice[Byte]"),
        ", ",
        <.span(^.className := "snippet", "Array[Byte]"),
        ", ",
        <.span(^.className := "snippet", "Unit"),
        " are already implemented and can be used with the following import. "
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |import swaydb.serializers.Default._
          """.stripMargin
        )
      ),

      <.p(
        "Custom serializers can be provided by implementing ",
        <.span(^.className := "snippet", "Serializer[T]"),
        "."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |import swaydb.data.slice.Slice
            |import swaydb.serializers.Serializer
            |
            |implicit object MySerializer extends Serializer[MyCaseClass] {
            |  override def write(data: MyCaseClass): Slice[Byte] =
            |    ???
            |
            |  override def read(data: Slice[Byte]): MyCaseClass =
            |    ???
            |}
            |
            |""".stripMargin
        )
      ),

      <.p(
        RouterController.router.link(Page.ByteSlice)(<.span(^.className := "snippet", "Slice[Byte]")),
        " implements functions for writing serializers for basic types."
      ),
      <.p(
        "External serializing libraries like the following can be used to implement custom serializers.",
        <.ul(
          <.li(
            <.a(
              ^.href := "https://github.com/suzaku-io/boopickle",
              ^.target := "blank",
              ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - Bookpickle")),
              "BooPickle"
            )
          ),
          <.li(
            <.a(
              ^.href := "https://github.com/EsotericSoftware/kryo",
              ^.target := "blank",
              ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - Kryo")),
              "Kryo"
            )
          )
        )
      )
    )
  }
}