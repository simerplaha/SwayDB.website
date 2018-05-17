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

package swaydb.io.docs.terminology

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.{Page, RouterController}

object IncreasingExpirationTimeDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.IncreasingExpiration.name)
      ),
      <.h4(
        <.u("Increasing"),
        " the expiration time for a key is not guaranteed to be applied. Decreasing expiration times are applied instantly."
      ),

      <.h3("Why ?"),
      <.p(
        "All ",
        RouterController.router.link(Page.Level)(Page.Level.name),
        " in SwayDB are independent and do not have knowledge about key-values in other Levels therefore ",
        <.u("increasing"),
        " a key or keys' ",
        RouterController.router.link(Page.Expiration)(Page.Expiration.name),
        " is dependant on the time taken for compaction & the configuration property ",
        RouterController.router.link(Page.MinTimeLeftToIncreaseExpiration)(Page.MinTimeLeftToIncreaseExpiration.name)
      ),
      <.p(
        <.strong("For example:"),
        " Suppose in a 7 Leveled database, a key exists in ",
        RouterController.router.link(Page.Level)(Page.Level.name),
        " 6 with expiration set to ",
        <.u("1.second"),
        " and an update is submitted to ",
        RouterController.router.link(Page.Level0)(Page.Level0.name),
        " to increase the expiration to ",
        <.u("1.minute"),
        ". Depending on the compaction speed (",
        RouterController.router.link(Page.Throttle)(Page.Throttle.name),
        ") the key in Level 6 could possibly get merged into Level 7 before the updated expiration in Level0",
        " reaches Level 6 during compaction hence expiring the target key."
      )
    )
  }
}
