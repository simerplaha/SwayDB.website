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

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object SliceDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Slice[T]")
      ),
      <.p(^.className := "heading")(
        <.span(^.className := "snippet", "Slice[T]"),
        " is a wrapper type around ",
        <.span(^.className := "snippet", "Array[T]"),
        " that provides APIs to create, insert & read ",
        <.span(^.className := "snippet", "Array[T]"),
        " at specific to & from offsets without creating multiple copies of the original ",
        <.span(^.className := "snippet", "Array[T]"),
        "."
      ),
      <.p(
        "Internally, ",
        <.span(^.className := "snippet", "Slice[T]"),
        " is used extensively to reduce the amount of objects created for garbage collection."
      ),

      <.p(
        <.strong("Example use-case: "),
        RouterController.router.link(Page.StoringDataInChunks)("Storing data in chunks"),
        "."
      ),

      <.p(
        RouterController.router.link(Page.ByteSlice)(<.span(^.className := "snippet", "Slice[Byte]")),
        " provides APIs for writing ",
        RouterController.router.link(Page.CustomSerializers)("custom serializers"),
        "."
      ),
    )
}
