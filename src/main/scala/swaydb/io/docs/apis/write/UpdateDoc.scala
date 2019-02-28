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

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object UpdateDoc {

  def apply(showNote: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.Update.name)
      ),

      <.p(
        "Update a key's value without updating the expiration (",
        RouterController.router.link(Page.Expire)(<.span(^.className := "snippet", Page.Expire.name)),
        ")."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.update(key = 1, value = "updated")
            |
            |""".stripMargin
        )
      ),

      <.pre(
        <.code(^.className := "scala")(
          """
            |db.update(keyValues = (1, "one"), (2, "two"))
            |//or
            |db.update(keyValues = Seq((1, "one"), (2, "two")))
          """.stripMargin
        )
      ),

      <.p(
        PutDoc.atomicWrite(<.span(^.className := "snippet", Page.Update.name))
      )
    )
  }

}
