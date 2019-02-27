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

object TransactionDoc {

  def guarantee =
    <.p(^.className := "heading",
      "Batch operations guarantee that all key-values are written atomically (All or none)."
    )

  def apply(showNote: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("transaction")
      ),
      guarantee,

      <.p(
        "The following code snippet shows how all ",
        RouterController.router.link(Page.WriteAPI)(<.span(^.className := "snippet", Page.WriteAPI.name)),
        "s can be combined to perform CRUD on single or multiple key-values and written as a single atomic entry with ",
        <.span(^.className := "snippet", Page.Transaction.name),
        "."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.commit(
            |      Batch.Put(key = 1, value = "one value"),
            |      Batch.Put(key = 1, value = "one value", expireAfter = 10.seconds),
            |      Batch.Remove(key = 1),
            |      Batch.Remove(from = 1, to = 100),
            |      Batch.Expire(key = 1, after = 10.seconds),
            |      Batch.Expire(from = 1, to = 100, after = 1.day),
            |      Batch.Update(key = 1, value = "value updated"),
            |      Batch.Update(from = 1, to = 100, value = "range update")
            |    )
          """.stripMargin
        )
      )
    )
  }

}
