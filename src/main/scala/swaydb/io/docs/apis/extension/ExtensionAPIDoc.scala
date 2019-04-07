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

package swaydb.io.docs.apis.extension

import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import swaydb.io.Page
import swaydb.io.common.{LinkIn, ScalaCode, Snippet, SubPages}

object ExtensionAPIDoc {

  def apply(showInitialiseDB: Boolean = true): VdomElement = {
    <.div(
      <.div(^.className := "page-header",
        <.h2(Page.ExtensionAPI.name)
      ),
      <.div(
        ^.className := "alert alert-info",
        <.span(^.className := "glyphicon glyphicon-info-sign", ^.fontSize := "15px"),
        <.i(
          " All default database ",
          LinkIn(Page.WriteAPI, Page.WriteAPI.name.toLowerCase),
          ", ",
          LinkIn(Page.ReadAPI, Page.ReadAPI.name.toLowerCase),
          " & ",
          LinkIn(Page.StreamAPI, Page.StreamAPI.name.toLowerCase),
          " APIs are support for each nested Map.",
          " This documentation is for extension specific APIs only."
        )
      ),
      <.p(
        "See ",
        LinkIn(Page.ExtendingDatabases),
        " to read on how to enable extensions on a database."
      ),
      <.h3("Map structure"),
      <.p(
        "Each Map is divided into two parts.",
      ),
      <.h4("1. ", <.u("Key-values")),
      <.p(
        "Key-values accessed directly from a Map's instance."
      ),
      <.h4("2. ", <.u("Maps/sub-maps")),
      <.p(
        "Sub maps for a Map can be accessed by invoking ",
        Snippet(".maps"),
        " on a Map."
      ),

      <.h3("API"),
      SubPages(Page.ExtensionAPI)
    )
  }

}
