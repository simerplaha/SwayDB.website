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

package swaydb.io.docs.apis.stream

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}

object FromDoc {

  def reverse =
    <.p(
      <.i("Reverse iterations can be performed with "),
      <.span(^.className := "snippet", ".reverse"),
      <.i(". Note: For persistent databases reverse iterations are much slower then forward iterations."),
    )

  def apply(showInfo: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("from, fromOrBefore & fromOrAfter")
      ),
      <.div(
        <.div(
          ^.className := "alert alert-info",
          <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
          <.i(" All APIs expected from a Scala collection (foreach, map, fold etc) are supported. The following documents SwayDB specific APIs only."),
        ),
        reverse
      ).when(showInfo),
      <.p(
        <.ul(
          <.li(
            <.strong("from"),
            " - start iteration from the input key."
          ),
          <.li(
            <.strong("fromOrBefore"),
            " - start iteration from the input key if found, else starts from a nearest key that falls before the input key."
          ),
          <.li(
            <.strong("fromOrAfter"),
            " - start iteration from the input key if found, else starts from a nearest key that falls after the input key."
          )
        )
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |map
            |  .from(10)
            |  .foreach(println)
            |
            |//for key only iterations
            |map
            |  .keys
            |  .reverse
            |  .fromOrBefore(10)
            |  .take(5)
            |
            |map
            |  .fromOrAfter(10)
            |  .map {
            |    case (key, value) =>
            |      //do something
            |  }
            |
            |""".stripMargin
        )
      )
    )
  }

}
