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

package swaydb.io

import japgolly.scalajs.react.vdom.VdomElement
import swaydb.io.Page._
import swaydb.io.docs._
import swaydb.io.docs.apis.iteration._
import swaydb.io.docs.apis.read._
import swaydb.io.docs.apis.write._
import swaydb.io.docs.apis.{read, _}
import swaydb.io.docs.configurationproperties._
import swaydb.io.docs.configurationproperties.acceleration._
import swaydb.io.docs.creatingdatabases._
import swaydb.io.docs.examples._
import swaydb.io.docs.performance._
import swaydb.io.docs.slice.{ByteSliceDoc, SliceDoc}
import swaydb.io.docs.terminology._
import swaydb.io.docs.terminology.meters.{Level0MeterDoc, LevelMeterDoc, MetersDoc}

object RootPages {
  val pages: Seq[Page] =
    Seq(
      Intro,
      Performance,
      Setup,
      QuickStart,
      CreateDatabases,
      API,
      CustomSerializers,
      CustomKeyOrdering,
      Slice,
      ConfiguringLevels,
      Terminology,
      Examples,
      ScalaJSNative,
      TestStatus,
      FaultTolerant,
      GitHubProjects,
      Goals
    )
}

sealed trait Page {
  val name: String
  val url: String

  def render(): VdomElement

  val subPages: Seq[Page]
}

object Page {

  object Intro extends Page {
    override val name: String = "Introduction"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "/"

    override def render(): VdomElement = IntroDoc()
  }

  object Performance extends Page {
    override val name: String = "Performance"
    override val subPages: Seq[Page] =
      Seq(
        MacBookPro,
        OtherMachines
      )

    override val url: String = "performance/"

    override def render(): VdomElement = PerformanceDoc()
  }

  object MacBookPro extends Page {
    override val name: String = "MacBook Pro (Mid 2014)"
    override val subPages: Seq[Page] =
      Seq(
        MemoryPerformance,
        PersistentMMAP,
        PersistentMMAPDisabled
      )

    override val url: String = "performance/macbook-pro-mid-2014/"

    override def render(): VdomElement = MacBookProDoc()
  }

  object OtherMachines extends Page {
    override val name: String = "Other Machines"
    override val subPages: Seq[Page] = Seq()

    override val url: String = "performance/other-machines/"

    override def render(): VdomElement = TODODoc()
  }

  object PersistentMMAP extends Page {
    override val name: String = "Persistent MMAP"
    override val subPages: Seq[Page] = Seq()

    override val url: String = "performance/macbook-pro-mid-2014/persistent-mmap/"

    override def render(): VdomElement = PersistentMMAPDoc()
  }

  object PersistentMMAPDisabled extends Page {
    override val name: String = "Persistent MMAP disabled"
    override val subPages: Seq[Page] = Seq()

    override val url: String = "performance/macbook-pro-mid-2014/persistent-mmap-disabled/"

    override def render(): VdomElement = PersistentMMAPDisabledDoc()
  }

  object MemoryPerformance extends Page {
    override val name: String = "Memory"
    override val subPages: Seq[Page] = Seq()

    override val url: String = "performance/macbook-pro-mid-2014/memory/"

    override def render(): VdomElement = MemoryPerformanceDoc()
  }

  object Setup extends Page {
    override val name: String = "Setup"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "setup/"

    override def render(): VdomElement = SetupDoc()
  }

  object Slice extends Page {
    override val name: String = "Slice[T]"
    override val subPages: Seq[Page] = Seq(ByteSlice)
    override val url: String = "slice/"

    override def render(): VdomElement = SliceDoc()
  }

  object ByteSlice extends Page {
    override val name: String = "Slice[Byte]"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "slice/byte-slice/"

    override def render(): VdomElement = ByteSliceDoc()
  }

  object CustomSerializers extends Page {
    override val name: String = "Custom serializers"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "custom-serializers/"

    override def render(): VdomElement = CustomSerializersDoc()
  }

  object QuickStart extends Page {
    override val name: String = "Quick start"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "quick-start/"

    override def render(): VdomElement = QuickStartDoc()
  }

  object CreateDatabases extends Page {
    override val name: String = "Create databases"
    override val subPages: Seq[Page] = Seq(Persistent, Memory, MemoryPersistent, Custom)
    override val url: String = "create-databases/"

    override def render(): VdomElement = CreatingDatabaseDoc()
  }

  object Persistent extends Page {
    override val name: String = "Persistent"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "create-databases/persistent/"

    override def render(): VdomElement = PersistentDatabaseDoc()
  }

  object Memory extends Page {
    override val name: String = "Memory"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "create-databases/memory/"

    override def render(): VdomElement = MemoryDatabaseDoc()
  }

  object MemoryPersistent extends Page {
    override val name: String = "Memory-persistent"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "create-databases/memory-persistent/"

    override def render(): VdomElement = MemoryPersistentDatabaseDoc()
  }

  object Custom extends Page {
    override val name: String = "Custom"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "create-databases/custom/"

    override def render(): VdomElement = CustomDatabaseDoc()
  }

  object API extends Page {
    override val name: String = "API"
    override val subPages: Seq[Page] =
      Seq(
        WriteAPI,
        ReadAPI,
        IterationAPI,
        RepairAppendix
      )
    override val url: String = "api/"

    override def render(): VdomElement = APIDoc()
  }

  object RepairAppendix extends Page {
    override val name: String = "repairAppendix"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/repairAppendix/"

    override def render(): VdomElement = RepairAppendixDoc()
  }

  object WriteAPI extends Page {
    override val name: String = "Write API"
    override val subPages: Seq[Page] =
      Seq(
        Put,
        Remove,
        BatchPut,
        BatchRemove,
        Batch
      )
    override val url: String = "api/write-api/"

    override def render(): VdomElement = WriteAPIDoc()
  }

  object Put extends Page {
    override val name: String = "put"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/write-api/put/"

    override def render(): VdomElement = PutDoc()
  }

  object Remove extends Page {
    override val name: String = "remove"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/write-api/remove/"

    override def render(): VdomElement = RemoveDoc()
  }

  object BatchPut extends Page {
    override val name: String = "batchPut"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/write-api/batchPut/"

    override def render(): VdomElement = BatchPutDoc()
  }

  object BatchRemove extends Page {
    override val name: String = "batchRemove"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/write-api/batchRemove/"

    override def render(): VdomElement = BatchRemoveDoc()
  }

  object Batch extends Page {
    override val name: String = "batch"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/write-api/batch/"

    override def render(): VdomElement = BatchDoc()
  }

  object ReadAPI extends Page {
    override val name: String = "Read API"
    override val subPages: Seq[Page] =
      Seq(
        Get,
        GetKey,
        GetKeyValue,
        Contains,
        MightContain,
        IsEmptyNonEmpty,
        Size,
        HeadAndLast,
        SizeOfSegments,
        Level0MeterReadAPI,
        Level1MeterReadAPI,
        LevelMeterReadAPI
      )
    override val url: String = "api/read-api/"

    override def render(): VdomElement = ReadAPIDoc()
  }

  object Get extends Page {
    override val name: String = "get"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/get/"

    override def render(): VdomElement = GetDoc()
  }

  object GetKey extends Page {
    override val name: String = "getKey"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/getKey/"

    override def render(): VdomElement = GetKeyDoc()
  }

  object GetKeyValue extends Page {
    override val name: String = "getKeyValue"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/getKeyValue/"

    override def render(): VdomElement = GetKeyValueDoc()
  }

  object Contains extends Page {
    override val name: String = "contains"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/contains/"

    override def render(): VdomElement = read.ContainsDoc()
  }

  object HeadAndLast extends Page {
    override val name: String = "head & last"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/head-last/"

    override def render(): VdomElement = HeadAndLastDoc()
  }

  object IsEmptyNonEmpty extends Page {
    override val name: String = "isEmpty & nonEmpty"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/isEmpty-nonEmpty/"

    override def render(): VdomElement = IsEmptyNonEmptyDoc()
  }

  object Level0MeterReadAPI extends Page {
    override val name: String = "level0Meter"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/level0Meter/"

    override def render(): VdomElement = read.Level0MeterDoc()
  }

  object Level1MeterReadAPI extends Page {
    override val name: String = "level1Meter"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/level1Meter/"

    override def render(): VdomElement = read.Level1MeterDoc()
  }

  object LevelMeterReadAPI extends Page {
    override val name: String = "levelMeter"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/levelMeter/"

    override def render(): VdomElement = read.LevelMeterDoc()
  }

  object MightContain extends Page {
    override val name: String = "mightContain"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/mightContain/"

    override def render(): VdomElement = read.MightContainDoc()
  }

  object Size extends Page {
    override val name: String = "size"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/size/"

    override def render(): VdomElement = read.SizeDoc()
  }

  object SizeOfSegments extends Page {
    override val name: String = "sizeOfSegments"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/read-api/sizeOfSegments/"

    override def render(): VdomElement = read.SizeOfSegmentsDoc()
  }

  object IterationAPI extends Page {
    override val name: String = "Iteration API"
    override val subPages: Seq[Page] = Seq(From, BeforeAndAfter, Until, AndThen)
    override val url: String = "api/iteration-api/"

    override def render(): VdomElement = IterationAPIDoc()
  }

  object From extends Page {
    override val name: String = "from"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/iteration-api/from/"

    override def render(): VdomElement = FromDoc()
  }

  object BeforeAndAfter extends Page {
    override val name: String = "before & after"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/iteration-api/beforeAfter/"

    override def render(): VdomElement = BeforeAndAfterDoc()
  }

  object Until extends Page {
    override val name: String = "until"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/iteration-api/until/"

    override def render(): VdomElement = UntilDoc()
  }

  object AndThen extends Page {
    override val name: String = "andThen"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "api/iteration-api/andThen/"

    override def render(): VdomElement = AndThenDoc()
  }

  object CustomKeyOrdering extends Page {
    override val name: String = "Custom key ordering"
    override val subPages: Seq[Page] = Seq()

    override val url: String = "custom-key-ordering/"

    override def render(): VdomElement = CustomKeyOrderingDoc()
  }

  object Examples extends Page {
    override val name: String = "Examples"
    override val subPages: Seq[Page] =
      Seq(
        CreatingTables,
        EventSourcing,
        StoringDataInChunks
      )
    override val url: String = "examples/"

    override def render(): VdomElement = ExamplesDoc()
  }


  object CreatingTables extends Page {
    override val name: String = "Creating tables"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "examples/creating-tables/"

    override def render(): VdomElement = CreatingTablesDoc()
  }

  object StoringDataInChunks extends Page {
    override val name: String = "Storing data in chunks"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "examples/storing-data-in-chunks/"

    override def render(): VdomElement = StoringDataInChunksDoc()
  }

  object EventSourcing extends Page {
    override val name: String = "Event-sourcing"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "examples/event-sourcing/"

    override def render(): VdomElement = EventSourcingDoc()
  }

  object Terminology extends Page {
    override val name: String = "Terminology"
    override val subPages: Seq[Page] =
      Seq(
        Level0,
        Level,
        TrashLevel,
        Segment,
        Appendix,
        Map,
        Meters,
        Compaction
      )
    override val url: String = "terminology/"

    override def render(): VdomElement = TerminologyDoc()
  }

  object Level0 extends Page {
    override val name: String = "Level0"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "terminology/level0/"

    override def render(): VdomElement = Level0Doc()
  }

  object Level extends Page {
    override val name: String = "Level"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "terminology/level/"

    override def render(): VdomElement = LevelDoc()
  }

  object TrashLevel extends Page {
    override val name: String = "Trash Level"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "terminology/trash-level/"

    override def render(): VdomElement = TrashLevelDoc()
  }

  object Appendix extends Page {
    override val name: String = "Appendix"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "terminology/appendix/"

    override def render(): VdomElement = AppendixDoc()
  }

  object Segment extends Page {
    override val name: String = "Segment"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "terminology/segment/"

    override def render(): VdomElement = SegmentDoc()
  }

  object Map extends Page {
    override val name: String = "Map"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "terminology/map/"

    override def render(): VdomElement = MapDoc()
  }

  object Compaction extends Page {
    override val name: String = "Compaction"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "terminology/compaction/"

    override def render(): VdomElement = CompactionDoc()
  }

  object Meters extends Page {
    override val name: String = "Meters"
    override val subPages: Seq[Page] = Seq(Level0Meter, LevelMeter)
    override val url: String = "terminology/meters/"

    override def render(): VdomElement = MetersDoc()
  }

  object Level0Meter extends Page {
    override val name: String = "Level0Meter"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "terminology/meters/level0Meter/"

    override def render(): VdomElement = Level0MeterDoc()
  }

  object LevelMeter extends Page {
    override val name: String = "LevelMeter"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "terminology/meters/levelMeter/"

    override def render(): VdomElement = LevelMeterDoc()
  }

  object ConfiguringLevels extends Page {
    override val name: String = "Configuring Levels"
    override val subPages: Seq[Page] =
      Seq(
        Dir,
        OtherDirs,
        MapSize,
        MMAP,
        RecoveryMode,
        Acceleration,
        SegmentSize,
        PushForward,
        CacheKeysOnCreate,
        BloomFilterFalsePositiveRate,
        Throttle,
        MMAPSegment,
        AppendixFlushCheckpointSize,
        MaxSegmentsOpen,
        SegmentsOpenCheckDelay,
        CacheSize,
        CacheCheckDelay
      )
    override val url: String = "configuring-levels/"

    override def render(): VdomElement = ConfiguringLevelsDoc()
  }

  object Dir extends Page {
    override val name: String = "dir"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/dir/"

    override def render(): VdomElement = DirDoc()
  }

  object OtherDirs extends Page {
    override val name: String = "otherDirs"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/otherDirs/"

    override def render(): VdomElement = OtherDirsDoc()
  }

  object MapSize extends Page {
    override val name: String = "mapSize"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/mapSize/"

    override def render(): VdomElement = MapSizeDoc()
  }

  object MMAP extends Page {
    override val name: String = "mmap"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/mmap/"

    override def render(): VdomElement = MmapDoc()
  }

  object RecoveryMode extends Page {
    override val name: String = "recoveryMode"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/recoveryMode/"

    override def render(): VdomElement = RecoveryModeDoc()
  }

  object Acceleration extends Page {
    override val name: String = "acceleration"
    override val subPages: Seq[Page] = Seq(Cruise, NoBrakes, Brake)
    override val url: String = "configuring-levels/acceleration/"

    override def render(): VdomElement = AccelerationDoc()
  }

  object Brake extends Page {
    override val name: String = "brake"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/acceleration/brake/"

    override def render(): VdomElement = BrakeDoc()
  }

  object NoBrakes extends Page {
    override val name: String = "noBrakes"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/acceleration/noBrakes/"

    override def render(): VdomElement = NoBrakeDoc()
  }

  object Cruise extends Page {
    override val name: String = "cruise"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/acceleration/cruise/"

    override def render(): VdomElement = CruiseDoc()
  }

  object SegmentSize extends Page {
    override val name: String = "segmentSize"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/segmentSize/"

    override def render(): VdomElement = SegmentSizeDoc()
  }

  object PushForward extends Page {
    override val name: String = "pushForward"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/pushForward/"

    override def render(): VdomElement = PushForwardDoc()
  }

  object BloomFilterFalsePositiveRate extends Page {
    override val name: String = "bloomFilterFalsePositiveRate"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/bloomFilterFalsePositiveRate/"

    override def render(): VdomElement = BloomFilterFalsePositiveRateDoc()
  }

  object CacheKeysOnCreate extends Page {
    override val name: String = "cacheKeysOnCreate"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/cacheKeysOnCreate/"

    override def render(): VdomElement = CacheKeysOnCreateDoc()
  }

  object Throttle extends Page {
    override val name: String = "throttle"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/throttle/"

    override def render(): VdomElement = ThrottleDoc()
  }

  object MMAPSegment extends Page {
    override val name: String = "mmapSegment"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/mmapSegment/"

    override def render(): VdomElement = MMAPSegmentDoc()
  }

  object AppendixFlushCheckpointSize extends Page {
    override val name: String = "appendixFlushCheckpointSize"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/appendixFlushCheckpointSize/"

    override def render(): VdomElement = AppendixFlushCheckpointSizeDoc()
  }

  object MaxSegmentsOpen extends Page {
    override val name: String = "maxSegmentsOpen"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/maxSegmentsOpen/"

    override def render(): VdomElement = MaxSegmentsOpenDoc()
  }

  object SegmentsOpenCheckDelay extends Page {
    override val name: String = "segmentsOpenCheckDelay"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/segmentsOpenCheckDelay/"

    override def render(): VdomElement = SegmentsOpenCheckDelayDoc()
  }

  object CacheSize extends Page {
    override val name: String = "cacheSize"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/cacheSize/"

    override def render(): VdomElement = CacheSizeDoc()
  }

  object CacheCheckDelay extends Page {
    override val name: String = "cacheCheckDelay"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "configuring-levels/cacheCheckDelay/"

    override def render(): VdomElement = CacheCheckDelayDoc()
  }

  object FaultTolerant extends Page {
    override val name: String = "Fault tolerant"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "fault-tolerant/"

    override def render(): VdomElement = FaultTolerantDoc()
  }

  object TestStatus extends Page {
    override val name: String = "Test status"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "test-status/"

    override def render(): VdomElement = TestStatusDoc()
  }

  object ScalaJSNative extends Page {
    override val name: String = "Scala.js & Scala Native"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "scalajs-scala-native/"

    override def render(): VdomElement = ScalaJSScalaNativeDoc()
  }

  object GitHubProjects extends Page {
    override val name: String = "GitHub projects"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "github-projects/"

    override def render(): VdomElement = GitHubProjectsDoc()
  }

  object Goals extends Page {
    override val name: String = "Goals"
    override val subPages: Seq[Page] = Seq()
    override val url: String = "goals/"

    override def render(): VdomElement = GoalsDoc()
  }

}
