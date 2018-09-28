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
import swaydb.io.common.{LinkIn, LinkOut, Snippet}

object GroupDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("Group")
      ),
      <.div(^.className := "row")(
        <.div(^.className := "col-sm-8")(
          <.p(
            "A Group is a just another key-value, which in itself can store a ",
            <.strong(<.u("sub-set")),
            " or ",
            <.strong(<.u("all")),
            " key-values within a ",
            LinkIn(Page.Segment),
            "."
          ),
          <.p(
            <.u(
              <.i(
                "Grouping is SwayDB's solution to provide configurable ",
                LinkOut("https://en.wikipedia.org/wiki/Disk_sector", "blockSize"),
                " and to provide a configurable way of creating tree like structure for efficient reads."
              )
            )
          ),

          <.p(
            "A Segment can contains one or many Groups and/or nested Groups."
          ),
          <.p(
            "Groups are only created if  ",
            LinkIn(Page.GroupingStrategy),
            " is specified for the ",
            LinkIn(Page.Level),
            " which can also be configured to use ",
            LinkOut("https://github.com/lz4/lz4-java", "LZ4"),
            " and/or ",
            LinkOut("https://github.com/xerial/snappy-java", "Snappy"),
            " for compressing a Group's key-values."
          ),
          <.p(
            "Each Group within the Segment can contain unique compression configuration. The compression used depends on compression options configured in ",
            LinkIn(Page.GroupingStrategy),
            "."
          ),
          <.h3(
            "How is a Group different from a normal key-value such as ",
            LinkIn(Page.Put),
            " or ",
            LinkIn(Page.Update),
            "?"
          ),
          <.p(
            "A Group can contain multiple key-values while a normal key-value contains a single key & value. ",
          ),
          <.p(
            "When a Group is read all it's keys are decompressed and read in one disk seek and values (on demand) are also decompressed and read in one disk seek. "
          ),
          <.h3("What is a Group's key?"),
          <.p(
            "A Group's key is a compressed version of the first and last key of the Group's key-values. ",
            "This allows for quick range checks to see if the key belongs to the Group's key range before the actual key-value is decompressed & read from the Group."
          ),

          <.h3("What is a Group's value?"),
          <.p(
            "A Group's value stores a header which contains all compression information about the Group. ",
            "After the header, it stores all compressed key-values for the Group in ",
            LinkIn(Page.SegmentFileFormat),
            ", which may also contain other Groups with more nested Groups."
          ),

          <.h3("Nested Groups"),
          <.p(
            "Since a Group is just another key-value, multiple Groups can also be nested and compressed to create a deep tree like structure by configuring ",
            Snippet("groupGroupingStrategy"),
            " in ",
            LinkIn(Page.GroupingStrategy),
            "."
          )
        ),
        <.div(^.className := "col-sm-4")(
          <.img(^.src := "/img/group_format.png"),
        )
      )
    )
}
