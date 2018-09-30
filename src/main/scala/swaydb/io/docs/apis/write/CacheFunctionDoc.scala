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
import swaydb.io.common.{Info, LinkIn, LinkOut, Snippet}

object CacheFunctionDoc {

  val experimentalInfo =
    Info(
      <.strong(
        "This feature is experimental and will be usable after ",
        LinkOut("https://github.com/simerplaha/SwayDB/issues/16", """"Issue #16""""),
        " is fixed."
      )
    )

  def apply(showNote: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.CacheFunction.name)
      ),
      experimentalInfo,
      <.p(
        "Stores input function in-memory which can then be used to apply updates using ",
        LinkIn(Page.UpdateFunction),
        " & ",
        LinkIn(Page.UpdateRangeFunction),
        "."
      ),
      <.p(
        <.strong("Note: "),
        Snippet("functionId"),
        " cannot contain the character '|'."
      ),
      <.p(
        <.i(
          <.strong(
            "Cache functions are immutable and should always be present in-memory even on database restart."
          )
        )
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |db.cacheFunction(functionId = "myFunctionId", function = oldValue => newValue)
            |
            |""".stripMargin
        )
      ),
      whenToUse
    )
  }

  val whenToUse: VdomElement =
    <.div(
      <.h3("When to use ", Snippet("cacheFunction"), "?"),
      <.p(
        "In comparision to basic get & update (",
        LinkIn(Page.Get),
        ", ",
        LinkIn(Page.Update),
        "/",
        LinkIn(Page.UpdateRange),
        ") ",
        Snippet("cacheFunctions"),
        " have a very low read, write & space requirement.",
      ),
      <.h4("Writing"),
      "When applied ",
      Snippet("cacheFunction"),
      " store the only ",
      Snippet("functionId"),
      " in the database keeping all the function in-memory. The physical values are updated during the ",
      LinkIn(Page.Compaction),
      " process.",
      <.h4("Reading"),
      <.p(
        "On read if the compaction is not complete, the values are re-constructed by applying the function to old values in real-time."
      )
    )
}
