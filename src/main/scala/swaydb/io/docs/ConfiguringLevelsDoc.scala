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

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Page, RouterController}
import swaydb.io.docs.configurationproperties.MMAPSegmentDoc.name
import swaydb.io.docs.configurationproperties._
import swaydb.io.docs.configurationproperties.acceleration.AccelerationDoc

object ConfiguringLevelsDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(^.id := "configuring-levels", "Configuring Levels")
      ),
      <.p(
        <.i(
          """A SwayDB instance is a hierarchy of persistent and in-memory Levels where each Level is responsible for
            |maintaining a set of Segments (groups of key-values) that are periodically submitted to the next level for merge
            |until all Segments reach the last Level.
          """.stripMargin
        )
      ),
      <.p(
        "The overall goal with these configuration is to merge key-values from Level0 to the lowest Level efficiently in batches ",
        "with least amount IO and CPU usage so that majority of the resources are reserved for serving reads and write requests.",
      ),
      <.p(
        "The following example configuration demos ",
        <.u(<.b("all configuration parameters")),
        " used in SwayDB by creating a 4 leveled databases."
      ),

      <.ul(
        <.li("Level0 - Persistent"),
        <.li("Level1 - Memory"),
        <.li("Level2 - Persistent"),
        <.li("Level3 - Trash (Segments pushed to this Level are deleted.)")
      ),
      <.pre(
        <.code(^.className := "scala")(
          """
            |val config =
            |  ConfigWizard
            |    .addPersistentLevel0( //level0
            |      dir = "/Disk1/myDB",
            |      mapSize = 4.mb,
            |      mmap = true,
            |      recoveryMode = RecoveryMode.Report,
            |      acceleration =
            |        (level0Meter: Level0Meter) =>
            |          Accelerator.cruise(level0Meter)
            |    )
            |    .addMemoryLevel1( //level1
            |      segmentSize = 4.mb,
            |      pushForward = false,
            |      bloomFilterFalsePositiveRate = 0.1,
            |      throttle =
            |        (levelMeter: LevelMeter) =>
            |          if (levelMeter.levelSize > 1.gb)
            |            Throttle(pushDelay = Duration.Zero, segmentsToPush = 10)
            |          else
            |            Throttle(pushDelay = Duration.Zero, segmentsToPush = 0)
            |    )
            |    .addPersistentLevel( //level2
            |      dir = "/Disk1/myDB",
            |      otherDirs = Seq("/Disk2/myDB", "/Disk3/myDB"),
            |      segmentSize = 4.mb,
            |      mmapSegment = MMAP.WriteAndRead,
            |      mmapAppendix = true,
            |      appendixFlushCheckpointSize = 4.mb,
            |      pushForward = false,
            |      cacheKeysOnCreate = false,
            |      bloomFilterFalsePositiveRate = 0.1,
            |      throttle =
            |        (levelMeter: LevelMeter) =>
            |          if (levelMeter.segmentsCount > 100)
            |            Throttle(pushDelay = 1.second, segmentsToPush = 2)
            |          else
            |            Throttle(pushDelay = Duration.Zero, segmentsToPush = 0)
            |    )
            |    .addTrashLevel //level3
            |
            |import swaydb.order.KeyOrder.default //import default sorting
            |implicit val ec = SwayDB.defaultExecutionContext //import default ExecutionContext
            |
            |val db = //initialise the database with the above configuration
            |  SwayDB[Int, String](
            |    config = config,
            |    maxSegmentsOpen = 1000,
            |    cacheSize = 1.gb,
            |    cacheCheckDelay = 5.seconds,
            |    segmentsOpenCheckDelay = 5.seconds
            |  )
            |""".stripMargin
        )
      ),
      <.p(
        "Read about the role of each configuration property by browsing the navigation menu on the left."
      )
    )
}
