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

package swaydb.io.docs.apis.read

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}

object MightContainDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("mightContain")
      ),
      <.p(
        "Checks Bloom filters only."
      ),
      <.p(
        "Returns false if the key definitely does not exist. "
      ),
      <.p(
        "Returns true if the key might exist. "
      ),
      <.p(
        <.strong("Note: "),
        "True is always returned for deleted keys that are not physically deleted from the database ",
        "i.e. delete keys in the upper Levels. ",
        "Deleted keys are physically deleted only in the last Level."
      ),
      <.p(
        "The accuracy of this function depends on the ",
        RouterController.router.link(Page.BloomFilterFalsePositiveRate)("'bloomFilterFalsePositiveRate: Double'"),
        " configuration setting."
      ),

      <.pre(
        <.code(^.className := "scala")(
          """
            |db.mightContain(1)
            |
            |""".stripMargin
        )
      )
    )
  }

}
