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

package swaydb.io.docs.configurationproperties

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.Page
import swaydb.io.common.{ScalaCode, _}

object GroupingStrategyDoc {

  def apply(): VdomElement =
    <.div(
      <.div(^.className := "page-header",
        <.h2("groupingStrategy: Option[KeyValueGroupingStrategy]")
      ),
      <.p(
        "Optional property for all ",
        LinkIn(Page.Level),
        "s other than ",
        LinkIn(Page.Level0),
        "."
      ),
      Info(
        <.span(
          "This property can be configured for both Persistent and Memory ",
          LinkIn(Page.Level),
          "s to enable compression/grouping for the Level's ",
          LinkIn(Page.Segment),
          "s."
        )
      ),

      <.p(
        "This property sets the rules on how to create ",
        LinkIn(Page.Group),
        "s within a ",
        LinkIn(Page.Segment),
        " and how to compress those Groups.",
      ),
      <.p(
        "SwayDB support both ",
        LinkOut("https://github.com/lz4/lz4-java", "LZ4"),
        " and ",
        LinkOut("https://github.com/xerial/snappy-java", "Snappy"),
        ". Both can be combined to apply the best possible compression for a ",
        LinkIn(Page.Segment),
        ". An ",
        Snippet("UnCompressedGroup"),
        " can also be created which simply writes the key-values without any compression. "
      ),
      <.p(
        "All ",
        LinkOut("https://github.com/lz4/lz4-java", "LZ4"),
        " instances, compressors and decompressors are supported."
      ),
      <.p(
        <.div(
          "LZ4 Instances - ",
          Snippet("FastestInstance"),
          ", ",
          Snippet("FastestJavaInstance"),
          ", ",
          Snippet("NativeInstance"),
          ", ",
          Snippet("SafeInstance"),
          " & ",
          Snippet("UnsafeInstance"),
          "."
        ),
        <.div(
          "LZ4 Compressors - ",
          Snippet("FastCompressor"),
          " & ",
          Snippet("HighCompressor"),
          "."
        ),
        <.div(
          "LZ4 Decompressors - ",
          Snippet("FastDecompressor"),
          " & ",
          Snippet("SafeDecompressor"),
          "."
        )
      ),

      <.h3("Size based compression"),
      <.p(
        "The following configuration specifies that when a ",
        LinkIn(Page.Segment),
        "'s key-values reaches a minimum size of 1.mb, all the key-values should be ",
        LinkIn(Page.Group),
        "ed using LZ4 compression.",
      ),
      <.p(
        "It also set another parameter ",
        Snippet("minCompressionPercentage"),
        " which specifies the requirement for minimum compression saving % to achieve before creating the ",
        LinkIn(Page.Group),
        " which in this case is 10% for key entries and 20% for values."
      ),
      <.p(
        "If it results in low compression (less than the specified min compression %) then ",
        "the compression will be retried again when next key-value is merged into the Segment."
      ),
      ScalaCode(
        """
          |KeyValueGroupingStrategy.Size(
          |  size = 1.mb,
          |  indexCompression =
          |    Compression.LZ4(
          |      compressor = (LZ4Instance.FastestJavaInstance, LZ4Compressor.FastCompressor(minCompressionPercentage = 10.0)),
          |      decompressor = (LZ4Instance.NativeInstance, LZ4Decompressor.SafeDecompressor)
          |    ),
          |  valueCompression =
          |    Compression.LZ4(
          |      compressor = (LZ4Instance.SafeInstance, LZ4Compressor.HighCompressor(minCompressionPercentage = 20.0)),
          |      decompressor = (LZ4Instance.UnsafeInstance, LZ4Decompressor.FastDecompressor)
          |    ),
          |  groupGroupingStrategy = None //read more about this below
          |)
        """.stripMargin
      ),
      Info(
        <.span(
          "To compress all key-values in the ",
          LinkIn(Page.Segment),
          " set the size to be the same as the ",
          LinkIn(Page.SegmentSize),
          "."
        )
      ),

      <.h3("Count based compression"),
      <.p(
        "The following configuration specifies that when a ",
        LinkIn(Page.Segment),
        " has at least 1000 key-values, all the key-values should be ",
        LinkIn(Page.Group),
        "ed using ",
        LinkOut("https://github.com/xerial/snappy-java", "Snappy"),
        " compression with a minimum compression % saving of 10% for keys entries and 20% for values.",
      ),
      <.p(
        "Similar to size based compression, if low compression is acheived (less than the specified min compression %) then ",
        "the compression will be retried again when next key-value is merged into the Segment."
      ),
      ScalaCode(
        """
          |KeyValueGroupingStrategy.Count(
          |  count = 1000,
          |  indexCompression = Compression.Snappy(minCompressionPercentage = 10.0),
          |  valueCompression = Compression.Snappy(minCompressionPercentage = 20.0),
          |  groupGroupingStrategy = None //read more about this below
          |)
        """.stripMargin
      ),

      <.h3("Combining multiple compressions"),
      <.p(
        "The following configuration demos how multiple compressions can be combined to achieve the best possible compression for index, the same can be used for values. "
      ),
      <.p(
        "In this case, LZ4 compression is applied first to achieve a minimum of 20% compression. If LZ4 compression does not give 20% compression savings, then ",
        "Snappy is tried for 15% or else the Group is created without any compression."
      ),
      ScalaCode(
        """
          |indexCompressions =
          |  Seq(
          |    Compression.LZ4(
          |      compressor = (LZ4Instance.FastestJavaInstance, LZ4Compressor.FastCompressor(minCompressionPercentage = 20.0)),
          |      decompressor = (LZ4Instance.NativeInstance, LZ4Decompressor.SafeDecompressor)
          |    ),
          |    Compression.Snappy(minCompressionPercentage = 15.0),
          |    Compression.UnCompressedGroup
          |  )
        """.stripMargin
      ),

      <.h3("What is UnCompressedGroup ?"),
      <.p(
        "A ",
        LinkIn(Page.Group),
        " with compression set to ",
        Snippet("Compression.UnCompressedGroup"),
        " does not use any external compression other than the default internal compression used by ",
        LinkIn(Page.SegmentFileFormat),
        ".",
      ),
      ScalaCode(
        """
          |KeyValueGroupingStrategy.Size(
          |  size = 2.mb,
          |  indexCompression = Compression.UnCompressedGroup,
          |  valueCompression = Compression.UnCompressedGroup,
          |  groupGroupingStrategy = None
          |)
        """.stripMargin
      ),

      <.h3("groupGroupingStrategy: Option[GroupGroupingStrategy]"),
      <.p(
        LinkIn(Page.Group),
        "s can also be compressed similar to key-value compression mentioned above to create nested Groups. ",
        "This creates a tree like structure in Segments which provides better read performance."
      ),
      <.p(
        "The following configuration create a parent Group for every 1000 Groups added to the Segment without any compression using ",
        Snippet("Compression.UnCompressedGroup"),
        "."
      ),
      <.p(
        " This configuration will normally be helpful for very large ",
        LinkIn(Page.Segment),
        "s and is not currently configured in SwayDB's default configuration as the ",
        LinkIn(Page.SegmentSize),
        " is small (2.mb)."
      ),
      ScalaCode(
        """
          |groupGroupingStrategy =
          |  Some(
          |    GroupGroupingStrategy.Count(
          |      count = 1000,
          |      indexCompression = Compression.UnCompressedGroup,
          |      valueCompression = Compression.UnCompressedGroup
          |    )
          |  )
        """.stripMargin
      ),
    )
}
