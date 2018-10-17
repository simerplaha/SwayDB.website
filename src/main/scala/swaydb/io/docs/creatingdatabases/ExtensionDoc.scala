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

package swaydb.io.docs.creatingdatabases

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^._
import swaydb.io.{Main, Page}
import swaydb.io.common._

object ExtensionDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.Extension.name)
      ),

      <.p(^.className := "heading")(
        "Databases can be extended to create custom APIs with ",
        //        LinkOut("https://github.com/simerplaha/SwayDB/tree/master/embedded/src/main/scala/swaydb/extension", "in-built"),
        "in-built",
        " or custom ",
        <.u(<.strong("extension")),
        " implementations."
      ),

      <.p(
        <.strong("Example use-case: "),
        "Extending a database to support multiple data structures such as ",
        Snippet("Map[K, V]"),
        ", ",
        Snippet("Set[T]"),
        ", ",
        Snippet("Queue[T]"),
        ", ",
        Snippet("List[T]"),
        " & ",
        Snippet("Stack[T]"),
        "."
      ),

      <.p(
        "A default SwayDB instance allows creation of only a single ",
        Snippet("Map[K, V]"),
        " or a ",
        Snippet("Set[T]"),
        ". This extension enables creation of multiple Maps & nested Maps and each Map can have it's own ",
        LinkIn(Page.CustomKeyOrdering, Page.CustomKeyOrdering.name.toLowerCase),
        " and also it's own ",
        LinkIn(Page.ReadAPI, Page.ReadAPI.name.toLowerCase),
        ", ",
        LinkIn(Page.WriteAPI, Page.WriteAPI.name.toLowerCase),
        " & ",
        LinkIn(Page.IterationAPI, Page.IterationAPI.name.toLowerCase),
        " operations."
      ),

      <.p(
        "This extension requires the key to be of type ",
        Snippet("swaydb.extension.Key[T]"),
        " and value should be of type ",
        Snippet("Option[V]")
      ),

      <.p(
        <.b(
          "See ",
          LinkIn(Page.ExtensionAPI, "Extension API"),
          " to see the full list of APIs available from this extension."
        )
      ),

      <.div(
        <.p(
          "In the following example an extended memory database is created and two nested sub maps are added to the rootMap.",
          <.a(
            ^.href := "https://github.com/simerplaha/SwayDB.examples/blob/master/src/test/scala/extended/ExtendedDBSpec.scala",
            ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - ExtendedDBSpec")),
            ^.role := "button",
            ^.className := "btn btn-xs btn-info pull-right",
            ^.target := "blank",
            "View test"
          )
        ),

        ScalaCode(
          """object ExtendDB extends App {
            |
            |  import swaydb._
            |  import swaydb.serializers.Default._
            |
            |  //including the above import all include the extension api.
            |  import swaydb.extension._
            |
            |  //add .extend to enable extension
            |  val rootMap = SwayDB.memory[Key[String], Option[String]]().get.extend.get
            |
            |  //Nested map hierarchy
            |  //rootMap
            |  //   |____ subMap1
            |  //            |____ subMap2
            |  //
            |  //now we can create nested maps.
            |  val subMap1 = rootMap.putMap(key = "sub map 1", value = "another map").get
            |  val subMap2 = subMap1.putMap(key = "sub map 2", value = "another nested map").get
            |}
          """.stripMargin
        )
      ),
      Info(
        <.span(
          "To see how to build custom extensions see the ",
          LinkOut("https://github.com/simerplaha/SwayDB/tree/master/embedded/src/main/scala/swaydb/extension", "default implementation"),
          " for reference.",
        )
      )
    )
}
