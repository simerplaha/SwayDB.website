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
import swaydb.io.Page
import swaydb.io.docs.apis.read.ExpirationDoc

object ExpireDoc {

  def apply(showNote: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.Expire.name)
      ),
      <.h3("Map"),
      <.p("Expire a single key-value."),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.expire(key = 1, after = 1.minute)
            |//or
            |db.expire(key = 1, at = 1.hour.fromNow)
            |//or
            |db.expire(key = 1, at = Deadline(5.minutes))
            |
            |""".stripMargin
        )
      ),

      <.p(
        "Expire multiple key-values atomically."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.expire(keys = (1, 1.second.fromNow), (2, 2.second.fromNow))
            |//or
            |db.expire(keys = Seq((1, 1.second.fromNow), (2, 2.second.fromNow)))
          """.stripMargin
        )
      ),

      <.h3("Set"),
      <.p("Expire a single item."),
      <.pre(
        <.code(^.className := "scala")(
          """
            |setDB.expire(elem = "some data", after = 1.minute)
            |//or
            |setDB.expire(elem = "some data", at = 1.hour.fromNow)
            |//or
            |setDB.expire(elem = "some data", at = Deadline(5.minutes))
            |
            |""".stripMargin
        )
      ),

      <.p(
        "Expire multiple items atomically."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |setDB.expire(elems = ("data one", 1.second.fromNow), ("data 2", 2.seconds.fromNow))
            |//or
            |setDB.expire(elems = Seq(("data one", 1.second.fromNow), ("data 2", 2.seconds.fromNow)))
          """.stripMargin
        )
      ),

      PutDoc.atomicWrite(<.span(^.className := "snippet", Page.Expire.name)),
      ExpirationDoc.alert
    )
  }
}
