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
import swaydb.io.Main

object ScalaJSScalaNativeDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Scala.js & Scala Native")
      ),
      <.p(
        "The API module compiles to Scala.js."
      ),
      <.p(
        "All modules will be eventually compiled to Scala.js & Scala Native to run ",
        " entirely on Node.js and native. This can be easily achieved as currently the only external dependencies are: ",
        <.ul(
          <.li(
            <.a(^.href := "https://github.com/alexandrnikitin/bloom-filter-scala",
              ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - bloom-filter-scala")),
              ^.target := "blank", "bloom-filter-scala")
          ),
          <.li(
            <.a(^.href := "http://www.scalatest.org/",
              ^.target := "blank",
              ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - scalatest")),
              "ScalaTest"
            ),
            " (already has Scala.js support)."
          )
        )
      )
    )
  }

}
