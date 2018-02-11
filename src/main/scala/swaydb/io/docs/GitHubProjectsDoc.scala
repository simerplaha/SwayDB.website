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
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.Main

object GitHubProjectsDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("GitHub Projects")
      ),

      <.h3(
        <.u(
          <.a(
            ^.href := "https://github.com/simerplaha/SwayDB.examples",
            ^.target := "blank",
            ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - SwayDB.examples")),
            "SwayDB.examples"
          )
        )
      ),
      <.p("Examples demonstrating features and APIs."),

      <.h3(
        <.u(
          <.a(
            ^.href := "https://github.com/simerplaha/SwayDB.benchmark",
            ^.target := "blank",
            ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - SwayDB.benchmark")),
            "SwayDB.benchmark"
          )
        )
      ),
      <.p("Benchmarks for write and read performance."),

      <.h3(
        <.u(
          <.a(
            ^.href := "https://github.com/simerplaha/SwayDB.stress",
            ^.target := "blank",
            ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - SwayDB.stress")),
            "SwayDB.stress"
          )
        )
      ),
      <.p("Stress tests."),

      <.h3(
        <.u(
          <.a(
            ^.href := "https://github.com/simerplaha/SwayDB.io",
            ^.target := "blank",
            ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - SwayDB.io")),
            "SwayDB.io"
          )
        )
      ),
      <.p("Code for this website."),

      <.h3(
        <.u(
          <.a(
            ^.href := "https://github.com/simerplaha/SwayDB",
            ^.target := "blank",
            ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - SwayDB")),
            "SwayDB"
          )
        )
      ),
      <.p("Core database repo.")
    )
  }

}
