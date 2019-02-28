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

package swaydb.io.docs.apis.write

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Main, Page}
import swaydb.io.common.{Info, LinkIn, ScalaCode, Snippet}

object ApplyFunctionDoc {

  def apply(showNote: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.ApplyFunction.name)
      ),
      <.p(
        LinkIn(Page.RegisterFunction, "Registered functions"),
        " can be applied to keys or range of keys. "
      ),
      <.p(
        <.strong("Note:"),
        " Functions are applied atomically.",
        <.a(
          ^.href := "https://github.com/simerplaha/SwayDB.examples/blob/master/src/test/scala/function/LikesSpec.scala",
          ^.role := "button",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - LikesSpec")),
          ^.className := "btn btn-xs btn-info pull-right",
          ^.target := "blank",
          "View example test"
        ),
      ),

      ScalaCode(
        """
          |//apply function to a key
          |likesMap.applyFunction(key = "user1", functionID = "increment likes count")
          |
          |//apply function to a range of users
          |likesMap.applyFunction(from = "user1", to = "user100", functionID = "increment likes count")
          |
          |""".stripMargin
      ),

      <.p(
        PutDoc.atomicWrite(Snippet(Page.ApplyFunction.name))
      )
    )
  }
}