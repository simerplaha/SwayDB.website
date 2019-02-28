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

package swaydb.io.docs.posts

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.Page
import swaydb.io.common.LinkIn

object WhyConfigurableLevelsDoc {

  def apply(): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.WhyConfigurableLevels.name)
      ),
      <.p(
        "Different applications can have different read & write patterns and having configurable Levels to tune compaction, option to distribute data to multiple-disks & ",
        "ability combine both in-memory and persistent Levels can be very useful to build a high throughput database on a single machine."
      ),
      <.p(
        "For example, the following data storage requirements:"
      ),
      <.h3("Priority reads for latest data"),
      <.p(
        <.strong("Example applications: "),
        "News websites, real-time tracking, security, social media etc"
      ),
      <.p(
        "These applications might read the latest data more often than old data. For this requirement, compaction in ",
        " upper ",
        LinkIn(Page.Level),
        "s can run less often (",
        LinkIn(Page.Throttle),
        ") than lower Levels so that upper Levels are more stable and can perform faster IO."
      ),
      <.p(
        "Upper Levels can also be moved to a dedicated Disks to have dedicated IO for latest data using the configuration ",
        LinkIn(Page.OtherDirs),
        "."
      ),
      <.p(
        "They can also be given low or no compression requirement (",
        LinkIn(Page.GroupingStrategy),
        ") to provide fast data decompression."
      ),
      <.p(
        "But the lower Levels can be configured perform compaction more often and have a higher compression requirements to claim more disk space."
      ),
      <.p(
        "The opposite of above can be applied for applications that read older data more often."
      ),
      <.h3("Immutable data"),
      <.p(
        <.strong("Example applications: "),
        "Financial data, logs, measurement data/time-series, event-sourcing etc"
      ),
      <.p(
        "These applications do not require multiple Levels as the data rarely changes. Changing the database configuration to only 2 Levels (",
        LinkIn(Page.LevelZero),
        " & ",
        LinkIn(Page.Level),
        "1) can provide a higher throughput database. Since no merge process is triggered having multiple Levels is not required as that would just be wasting resources."
      ),
      <.p(
        "But if the application's data requirements changes, the database can be re-configured to add more Levels."
      ),

      <.p(
        <.u("Time-series data"),
        " such a weather data, electricity usage, sensor data etc can contain a lot duplicates. Duplicate data can be ",
        "detected and written only ones by enabling the configuration ",
        LinkIn(Page.CompressDuplicateValues),
        "."
      ),

      <.h3("Memory constraints"),
      <.p(
        "Some applications require in-memory databases for faster reads & writes but have less RAM limitations. These applications can take advantage of both ",
        "in-memory and persistent Levels (see ",
        LinkIn(Page.EventuallyPersistent),
        ") to write data to disk only when there is not enough RAM."
      ),
      <.p(
        "If persistent disk is not available on the machine then in-memory data can also be compressed using LZ4 and/or Snappy. See the configuration ",
        LinkIn(Page.GroupingStrategy),
        "."
      ),

      <.h3("Archived data"),
      <.p(
        "Some applications might have the requirement to store old data in highly compressed format to less expensive spinning disks."
      ),
      <.p(
        "These applications can configure the lowest Level to have different storage requirements. See ",
        LinkIn(Page.OtherDirs),
        " & ",
        LinkIn(Page.GroupingStrategy),
        "."
      )
    )
  }
}
