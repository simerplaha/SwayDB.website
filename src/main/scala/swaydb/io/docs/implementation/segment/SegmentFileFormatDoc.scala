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

package swaydb.io.docs.implementation.segment

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.Page
import swaydb.io.common.LinkIn

object SegmentFileFormatDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Segment file format")
      ),
      <.div(^.className := "row")(
        <.div(^.className := "col-sm-8")(
          <.p(
            "The default Segment file storage format for ",
            LinkIn(Page.PersistentSegment),
            "s. ",
            LinkIn(Page.MemorySegment),
            "s can also use this file format to achieve higher in-memory compression.",
          ),

          <.p(
            LinkIn(Page.Group),
            "ing can be used to create a tree structure similar to binary tree within the Segment to provide better read performance."
          ),

          <.h4(<.u("Values")),
          <.p(
            "Values are stored at the head of the file in the same sorted order as keys.",
          ),
          <.p(
            "Duplicate values within the Segment can be detected and compressed by setting the configuration ",
            LinkIn(Page.CompressDuplicateValues),
            " to true. Enabling this will only require a single value entry to be written for duplicate values (given they are in sequence).",
          ),
          <.h4(<.u("Key entries/index")),
          <.p(
            "Key index entries are stored after the value bytes. All index entries are prefix compressed with their previous index entry ",
            "to get maximum compression within index entries."
          ),
          <.p(
            "Each index entry stores the following data",
            <.ul(
              <.li("Storage ID"),
              <.li("Key"),
              <.li("Expiration"),
              <.li("Value position & length")
            ),
            <.strong(<.i("Duplicate data within index entries are always compressed!")),
          ),
          <.p(
            "For example:",
            " If two key entries have the same expiration then the second key entry will not store it's expiration and will read previous key-values",
            " expiration during runtime. The same compression strategy is applied for all information stored in the index entry."
          ),
          <.p(
            "Currently there are 2650 storage IDs, which means an index entry can be stored in 2650 possible formats, which ",
            "means there are 2650 possible ways an index entry can be compressed with it's previous index entry."
          ),

          <.h4(<.u("Footer")),
          <.p("Segment footer stores CRC, bloomFilter, segment's file format ID and other information about the Segment which is used to perform reads & compaction.")
        ),
        <.div(^.className := "col-sm-4")(
          <.img(^.src := "/img/segment_format.png"),
        )
      ),

      <.h3("Grouping and compressing using LZ4 or Snappy"),
      <.p(
        "Key-values can be ",
        LinkIn(Page.Group),
        "ed to create sub-segments within the Segment which can then be compressed by specifying ",
        LinkIn(Page.GroupingStrategy),
        "."
      ),
      <.p(
        "Segments files are only compressible via ",
        LinkIn(Page.Group),
        "ing."
      ),
      <.p(
        "To compress all key-values within a Segment create a single ",
        LinkIn(Page.Group),
        " via the ",
        LinkIn(Page.GroupingStrategy),
        "."
      )
    )
}
