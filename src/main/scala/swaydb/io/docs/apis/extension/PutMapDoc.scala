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

package swaydb.io.docs.apis.extension

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.Page
import swaydb.io.common.{LinkIn, ScalaCode, Snippet}

object PutMapDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.PutMap.name)
      ),
      <.h3("Creating key-values"),
      ScalaCode(
        """
          |map.put(key = "key 1", value = "value 1")
          |map.put(key = "key 2", value = "value 2", expireAfter = 10.seconds)
        """.stripMargin
      ),
      <.p(
        "See ",
        LinkIn(Page.Put),
        ", ",
        LinkIn(Page.PutExpire),
        " & ",
        LinkIn(Page.BatchPut),
        "."
      ),
      <.h3("Creating a sub-map"),
      ScalaCode(
        """
          |map
          |  .maps
          |  .put(key = "sub map key", value = "sub map value")
        """.stripMargin
      ),
      note
    )

  val note: VdomElement =
    <.p(
      <.strong("Note: "),
      Snippet("put"),
      " on an existing map will replace all it's key-values and child maps."
    )

}
