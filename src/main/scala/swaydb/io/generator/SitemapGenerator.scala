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

package swaydb.io.generator

import java.nio.file.Paths

import swaydb.io.{Page, RootPages}

import scala.xml.Elem

/**
  * Generates sitemap.xml file mentioned following the format mentioned https://support.google.com/webmasters/answer/183668?hl=en.
  */
object SitemapGenerator extends App {

  val sitemap = Paths.get(new java.io.File(".").getCanonicalPath).resolve("docs").resolve("sitemap.xml")

  def genXMLForPage(page: Page): Elem =
    <url>
      {if (page == Page.Intro)
      <image:image>
        <image:loc>https://github.com/simerplaha/SwayDB.io/blob/master/docs/img/logo-dark.png</image:loc>
        <image:caption>SwayDB logo</image:caption>
      </image:image>}<loc>
      http://www.swaydb.io/{page.url}
    </loc>
    </url>

  def genXMLForPages(page: Page): List[Elem] =
    genXMLForPage(page) :: (page.subPages.flatMap(genXMLForPages)(collection.breakOut): List[Elem])

  def start =
    <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
            xmlns:image="http://www.google.com/schemas/sitemap-image/1.1">
      {RootPages.pages.flatMap(genXMLForPages)}
    </urlset>

  scala.xml.XML.save(filename = sitemap.toString, xmlDecl = true, node = start, enc = "UTF-8")
}