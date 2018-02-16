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
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Main, Page, RouterController}

object PersistentDatabaseDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Create persistent database")
      ),
      <.p(
        "Configuration used: ",
        <.a(
          ^.href := "https://github.com/simerplaha/SwayDB/blob/master/configs/src/main/scala/swaydb/configs/level/PersistentConfig.scala",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - PersistentConfig.scala")),
          ^.target := "blank",
          "PersistentConfig"
        )
      ),

      <.p(
        """An 8 leveled persistent database configured to store 2 times more data in "Disks/db". Read more on distribution ratio at """,
        RouterController.router.link(Page.OtherDirs)("otherDirs: Seq[Dir]"),
        "."
      ),

      <.h3("Key-value database"),
      <.pre(
        <.code(^.className := "scala")(
          """
            |import swaydb._
            |import swaydb.serializers.Default._
            |
            |val db =
            |  SwayDB.persistent[Long, String](
            |    dir = "/Disk1/db",
            |    otherDirs = Seq(Dir("/Disk2/db", distributionRatio = 2))
            |  )
            |
            |""".stripMargin
        )
      ),

      <.h3("Set database"),
      <.pre(
        <.code(^.className := "scala")(
          """
            |import swaydb._
            |import swaydb.serializers.Default._
            |
            |val db =
            |  SwayDB.persistentSet[String](
            |    dir = "/Disk1/db",
            |    otherDirs = Seq(Dir("/Disk2/db", distributionRatio = 2))
            |  )
            |
            |""".stripMargin
        )
      )
    )
}
