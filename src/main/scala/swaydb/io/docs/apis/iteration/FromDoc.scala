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

package swaydb.io.docs.apis.iteration

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}

object FromDoc {

  def apply(showInfo: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("from, fromOrBefore & fromOrAfter")
      ),
      <.div(
        <.div(
          ^.className := "alert alert-info",
          <.strong("Note: "),
          <.i("All APIs expected from a Scala collection (foreach, map, fold etc) are supported. The following documents SwayDB specific APIs only."),
        ),
        <.p(
          <.i("APIs ending with "),
          <.span(^.className := "snippet", "*Right"),
          <.i(" perform reverse iteration (Note: Reverse iterations are much slower then forward iterations for persistent databases)."),
        )
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
            |db
            |  .from(10)
            |  .foreach(println)
            |
            |//for key only iterations
            |db
            |  .keys
            |  .fromOrBefore(10)
            |  .take(5)
            |
            |db
            |  .fromOrAfter(10)
            |  .foreachRight {
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
