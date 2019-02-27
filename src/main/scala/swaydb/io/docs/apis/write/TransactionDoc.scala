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
import swaydb.io.common.Snippet
import swaydb.io.{Page, RouterController}

object TransactionDoc {

  def guarantee =
    <.p(^.className := "heading",
      "Transactions guarantee that all operations are performed atomically (All or none)."
    )

  def apply(showNote: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("transaction")
      ),
      guarantee,

      <.p(
        "The following code snippet shows how all ",
        RouterController.router.link(Page.WriteAPI)(Snippet(Page.WriteAPI.name.toLowerCase + " APIs")),
        " can be combined to perform CRUD on single or multiple key-values and written as a single atomic entry."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.commit(
            |  Prepare.Put(key = 1, value = "one value"),
            |  Prepare.Put(key = 1, value = "one value", expireAfter = 10.seconds),
            |  Prepare.Remove(key = 1),
            |  Prepare.Remove(from = 2, to = 100),
            |  Prepare.Expire(key = 1, after = 10.seconds),
            |  Prepare.Expire(from = 2, to = 100, after = 1.day),
            |  Prepare.Update(key = 1, value = "value updated"),
            |  Prepare.Update(from = 2, to = 100, value = "range update"),
            |  Prepare.Function(key = 1, functionID = "increment likes"),
            |  Prepare.Function(from = 2, to = 100, functionID = "increment likes")
            |)
          """.stripMargin
        )
      )
    )
  }

}
