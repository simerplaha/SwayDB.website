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

package swaydb.io.docs

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.Main

object CustomKeyOrderingDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2("Custom key sorting")
      ),
      <.p(
        <.a(
          ^.target := "blank",
          ^.href := "https://github.com/simerplaha/SwayDB/blob/master/ordering/src/main/scala/swaydb/order/KeyOrder.scala",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - KeyOrder.scala")),
          "Default key ordering"
        ),
        " is used if no custom ordering is provided."
      ),

      <.p(
        "Typed ordering ",
        <.span(^.className := "snippet", "'Ordering[Slice[Byte]]'"),
        " for keys can be provided by leveraging Scala's ",
        <.span(^.className := "snippet", "Ordering.by[T, S]"),
        " with ",
        <.span(^.className := "snippet", "Serializer[S]"),
        "."
      ),

      <.h3("Example app for Set DB with partial data ordering"),

      <.p(
        "Suppose a database that stores Stock order information in a Set DB ",
        "requires a partial sort order by stockCode, purchaseTime & orderId ",
        "to analyse most recently purchased stocks."
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |case class StockOrder(stockCode: String,
            |                      orderId: Int,
            |                      price: Double,
            |                      purchaseTime: Long)
          """.stripMargin
        )
      ),

      <.p(
        "A typed custom sort order can be implemented as following",
        <.a(
          ^.href := "https://github.com/simerplaha/SwayDB.examples/blob/master/src/test/scala/stockordering/StockOrderingSpec.scala",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - StockOrderingSpec.scala")),
          ^.role := "button",
          ^.className := "btn btn-xs btn-info pull-right",
          ^.target := "blank",
          "View test"
        )
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |import scala.math.Ordered.orderingToOrdered
            |
            |implicit val stockOrderOrdering: Ordering[Slice[Byte]] =
            |  Ordering.by[Slice[Byte], StockOrder](StockOrderSerializer.read) {
            |    (order1: StockOrder, order2: StockOrder) =>
            |      (order1.stockCode, order1.purchaseTime, order1.orderId) compare
            |      (order2.stockCode, order2.purchaseTime, order2.orderId)
            |  }
          """.stripMargin
        )
      ),

      <.div(
        ^.className := "alert alert-info",
        <.strong("Note: "),
        <.i(
          "Typed sort ordering can be less performant (depending on the serializing speed) than ordering directly on raw bytes ",
          <.span(^.className := "snippet", "Ordering[Slice[Byte]]"),
          ". This sort order function is used frequently for reading & write data and during compaction.",
          " A slow ordering function can have noticeable performance impact."
        )
      )
    )
  }
}