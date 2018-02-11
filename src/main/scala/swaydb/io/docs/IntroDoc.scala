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

import com.karasiq.highlightjs.HighlightJS
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.{Main, Page, RouterController}

import scala.scalajs.js

object IntroDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2(^.id := "introduction", "Introduction")
      ),
      <.p(^.className := "heading")(
        <.img(^.className := "dark-logo", ^.src := "img/logo-dark.png"),
        "SwayDB is an embeddable, non-blocking, type-safe key-value store for ",
        <.strong("single or multiple disks"),
        <.span(" and "),
        <.strong("in-memory "),
        <.span("storage.")
      ),
      <.p(^.className := "heading")("It's an implementation of ",
        <.a(
          ^.href := "https://en.wikipedia.org/wiki/Log-structured_merge-tree",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - Log-structured merge-tree")),
          ^.target := "blank")("Log-structured merge-tree"),
        " written in Scala with asynchronous Leveled Compaction based on push-pull strategy built on the ",
        <.a(^.href := "https://en.wikipedia.org/wiki/Actor_model",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - Actor model")),
          ^.target := "blank")("Actor model"),
        "."
      ),

      <.h3(^.id := "type-safe", "Type-safe"),
      <.p(
        RouterController.router.link(Page.API)("APIs"),
        " are typed and are based on Scala's ",
        <.span(^.className := "snippet", "mutable.SortedMap[K, V]"),
        " and ",
        <.span(^.className := "snippet", "mutable.SortedSet[T]"),
        ". All APIs expected from a Scala collection like ",
        <.span(^.className := "snippet", "foreach"),
        ", ",
        <.span(^.className := "snippet", "map"),
        ", ",
        <.span(^.className := "snippet", "flatMap"),
        ", ",
        <.span(^.className := "snippet", "filter"),
        ", ",
        <.span(^.className := "snippet", "foldLeft"),
        ", ",
        <.span(^.className := "snippet", "foldRight"),
        ", ",
        <.span(^.className := "snippet", "reduce"),
        ", ",
        <.span(^.className := "snippet", "reduceRight"),
        " etc are supported including ",
        <.span(^.className := "snippet", "foreachRight"),
        " & ",
        <.span(^.className := "snippet", "mapRight"),
        "."
      ),
      <.p(
        "APIs ending with ",
        <.span(^.className := "snippet", "*Right"),
        " perform reverse iterations."
      ),

      <.h3(^.id := "non-blocking", "Non-blocking"),
      <.p(
        "Threads are never blocked. ",
        "Reads and writes occur independent to each other."
      ),

      <.h3(^.id := "back-pressure", "Back-pressure"),
      <.p(
        "Back-pressure is required when writes occur faster than the compaction process. ",
        "Each write request returns the state of ",
        RouterController.router.link(Page.Level0)("Level0"),
        " (",
        RouterController.router.link(Page.Level0Meter)("Level0Meter"),
        ") that can be used to implement asynchronous ",
        "back-pressure with external streaming libraries. An implementation of blocking back-pressure (",
        RouterController.router.link(Page.Brake)("Acceleration.brake"),
        ") is provided for quick starts."
      ),

      <.h3(^.id := "multi-disks", "Multiple disks"),
      <.p(
        RouterController.router.link(Page.Segment)("Segments"),
        " can be distributed to multiple disks. Depending on the size of each disk, ",
        RouterController.router.link(Page.OtherDirs)("distribution ratio"),
        " can be specified for each disk to store more Segments on larger disks."
      ),
      <.p("Distributing Segments over multiple disks would also result in increased read, write & compaction performance."),

      <.h3(^.id := "lazy-values", "Lazily fetched values"),
      <.p(
        """
          |Key only reads are supported where values are lazily fetched on request.
        """.stripMargin),

      <.h3(^.id := "configurable", "Configurable Levels"),
      <.p(
        "A SwayDB database instance is a hierarchy of persistent and in-memory Levels where each Level can be ",
        RouterController.router.link(Page.ConfiguringLevels)("configured"),
        " to have different file sizes, directories & compaction speeds (",
        RouterController.router.link(Page.Throttle)("Throttle"),
        ")."
      ),
      <.p(
        RouterController.router.link(Page.TrashLevel)("Trash Level"),
        " can be used to periodically delete Segments."
      ),

      <.h3(^.id := "cache-size", "Configurable cache size"),
      <.p(
        "Total size of in-memory key-values can be ",
        RouterController.router.link(Page.CacheSize)("configured"),
        ". On overflow, older key-values get dropped from the cache asynchronously."
      ),

      <.h3(^.id := "compaction", "Concurrent Leveled Compaction"),
      <.p(
        "Compaction is a processes where a ",
        RouterController.router.link(Page.Level)("Level"),
        " periodically sends it's ",
        RouterController.router.link(Page.Segment)("Segments"),
        " to the next Level for merge."
      ),
      <.p(
        "The speed/rate at which a Level pushes/merges it's Segments to the next Level is configurable via ",
        RouterController.router.link(Page.Throttle)("throttling"),
        "."
      ),
      <.p("Compaction uses an asynchronous push-pull approach built on the Actor model."),

      <.h3(^.id := "memory-mapped-files", "Memory-mapped files optional"),
      <.p(
        "Memory-mapped files are used by default as they have higher read and write performance but since they do not guarantee ",
        "writes on fatal server crashes and have a higher memory requirement, they can be ",
        RouterController.router.link(Page.MMAPSegment)("disabled"),
        "."
      ),
      <.p(
        "Memory-mapped files can be disabled for both ",
        RouterController.router.link(Page.MMAPSegment)("reads and writes or just for writes"),
        " which falls back to using ",
        <.span(^.className := "snippet", "java.nio.FileChannel"),
        ". ",
        "They can also be ",
        RouterController.router.link(Page.MMAP)("disabled"),
        " for ",
        RouterController.router.link(Page.Level0)("Level0's"),
        " ",
        RouterController.router.link(Page.Map)("Map"),
        " files"
      ),

      <.h3(^.id := "scala-streams", "Scala Streams"),
      <.p("Scala streams can be created by invoking ",
        <.span(^.className := "snippet", ".toStream"),
        " on a database instance."),

      <.h3(^.id := "bloom-filters", "Bloom filters"),
      <.p(
        "Bloom filters are used to determine if a key exists in a Segment (",
        <.a(
          ^.href := "https://github.com/alexandrnikitin/bloom-filter-scala",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - bloom-filter-scala")),
          ^.target := "blank",
          "bloom-filter-scala"
        ),
        " by ",
        <.a(
          ^.href := "https://github.com/alexandrnikitin",
          ^.target := "blank",
          ^.onClick --> Callback(Main.analytics.event("Outbound click", "alexandrnikitin")),
          "@alexandrnikitin"
        ),
        ")."
      ),

      <.h3("Fault tolerant"),
      <.p(
        "Fatal server crashes, bad sectors on disk, other hardware failure can cause file corruption ",
        " on any file system. ",
      ),
      <.p(
        "SwayDB's goal with handling failure to is to detect and report failure accurately so that it's easy to debug and recover data. ",
        RouterController.router.link(Page.FaultTolerant)("Read more"),
        "."
      ),
    )
}
