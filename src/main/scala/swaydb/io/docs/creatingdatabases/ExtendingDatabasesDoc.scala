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

object ExtendingDatabasesDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.ExtendingDatabases.name)
      ),

      <.p(
        "Databases can be extended to enable advanced APIs ",
        " with ",
        <.u(<.strong("extensions")),
        "."
      ),

      <.h3("Why use extensions?"),
      <.p(
        "This extension enables creation of multiple Maps & nested Maps similar to ",
        Snippet("Tables"),
        " in SQL databases.",
        " Each Map can have it's own ",
        LinkIn(Page.CustomKeyOrdering, Page.CustomKeyOrdering.name.toLowerCase),
        " and also it's own ",
        LinkIn(Page.ReadAPI, Page.ReadAPI.name.toLowerCase),
        ", ",
        LinkIn(Page.WriteAPI, Page.WriteAPI.name.toLowerCase),
        " & ",
        LinkIn(Page.StreamAPI, Page.StreamAPI.name.toLowerCase),
        " operations."
      ),

      <.h3("Extending a database"),
      <.div(
        <.p(
          "In the following example a memory Map is created from ",
          Snippet(".extensions"),
          " package. This returns a rootMap from under which all the nested sub maps can be created.",
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
          """
            |object ExtendDB extends App {
            |
            |  import swaydb._
            |  import swaydb.serializers.Default._
            |  //including the above import all include the extension api.
            |
            |  //add .extend to enable extension
            |  val rootMap = extensions.memory.Map[String, String]().get
            |
            |  //Nested map hierarchy
            |  //rootMap
            |  //   |____ subMap1
            |  //            |____ subMap2
            |  //
            |  //now we can create nested maps.
            |  val subMap1 =
            |  rootMap
            |    .maps
            |    .put(key = "sub map 1", value = "another map").get
            |
            |  val subMap2 =
            |    subMap1
            |      .maps
            |      .put(key = "sub map 2", value = "another nested map").get
            |}
            |
            |""".stripMargin
        )
      ),

      <.h3("APIs"),
      <.p(
        "See ",
        LinkIn(Page.ExtensionAPI, "Extension API"),
        " to see the full list of APIs available from this extension."
      ),

      <.h3("Building custom extensions"),
      <.p(
        "To see how to build custom extensions see the module ",
        LinkOut("https://github.com/simerplaha/SwayDB/tree/master/extension/src/main/scala/swaydb/extension", "extension"),
        " for reference.",
      ),
      <.p(
        "This implementation can also be replicated to support other data structures such as ",
        Snippet("Queue[T]"),
        ", ",
        Snippet("List[T]"),
        ", ",
        Snippet("Stack[T]"),
        ", ",
        "inverted indexes",
        " etc."
      ),
    )
}
