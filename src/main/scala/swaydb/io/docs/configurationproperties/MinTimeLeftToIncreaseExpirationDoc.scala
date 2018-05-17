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

package swaydb.io.docs.configurationproperties

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.common.Snippet
import swaydb.io.{Page, RouterController}

object MinTimeLeftToIncreaseExpirationDoc {

  def name = "minTimeLeftToUpdateExpiration: FiniteDuration"

  def link =
    RouterController.router.link(Page.Dir)(name)

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(name)
      ),
      <.p(
        "Required property for all Levels. This property is used during compaction to account for latency ",
        "required when writing ",
        RouterController.router.link(Page.Segment)(Page.Segment.name),
        "s to disk or RAM. It set the minimum ",
        RouterController.router.link(Page.TimeLeft)(Page.TimeLeft.name),
        " until a key's expiration, before compaction can apply the update to ",
        <.u("increase the expiration"),
        ". This property is not required when decrementing expiration. Decreasing expiration times are applied instantly.",
      ),

      <.p(
        <.i(
          "If the key does not have the minimum time left, then the update to increase the expiration will not be applied."
        )
      ),

      <.h3(
        "Why is this property required when ",
        <.u("increasing"),
        " key expiration times ?"
      ),

      <.p(
        "SwayDB does not block on reads and writes therefore concurrent reads to a ",
        RouterController.router.link(Page.Segment)(Page.Segment.name),
        " can occur while merge is in progress. This property avoids reading stale data by accounting for latency required to ",
        "write Segments to disk or RAM."
      ),

      <.p(
        <.strong("For example: "),
        "If a ",
        RouterController.router.link(Page.Level)(Page.Level.name),
        " contains a key that has only ",
        <.u("1.second"),
        " left until expiration and a merge is updating the expiration to ",
        <.u("60.seconds"),
        ", suppose it took ",
        <.u("2.seconds"),
        " to write the updated Segment to disk or RAM, in this case, all threads concurrently reading the key during the 2",
        <.sup("nd"),
        " second will start receiving ",
        Snippet("None (expired)"),
        " response, but after the ",
        <.u("2.seconds"),
        " (on successfully writing the new Segment to disk or RAM) the key would re-appear again.",
        " Therefore this property is required to account for an approximate latency time required to write Segments to disk or RAM."
      )
    )
}
