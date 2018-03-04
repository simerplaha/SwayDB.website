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

import java.nio.file.{Files, Paths, StandardCopyOption}

/**
  * A hack solution for SEO. GitHub pages doesn't allow multiple URLS for the same directory.
  *
  * Solution 1:
  * Using solution mentioned https://github.com/rafrex/spa-github-pages works but it creates temporary URLs with parameters
  * which search engine will display in search result. The reason I didn't use this solution is because it allows search
  * engines to index temporary URLs instead of the correct URLs.
  *
  * Solution 2:
  * Using solution mentioned here https://github.com/csuwildcat/sghpa works too but search engines will only see the root URL "/"
  * and never index any of the sub pages.
  *
  * Solution 4:
  * Using hashbang #! in the URL which alerts google to index the page. But this solution is specific to google only and
  * might not work for other search engines.
  *
  * Solution 4:
  * The goal with the URLs is to keep them clean and descriptive without any hacks for specific search engines and still
  * use GithubPages to host the website.
  * This class implements a hack solution for the original URLs to work on Github Pages. It creates directories for all Page URLs
  * and copies the root index.html into each of these directories for all valid URLs. This way the URLs are maintained and
  * are displayed correctly in search engines and hacks are only implemented in the code which can be changes if this
  * website ever moves to another hosting provider without effecting existing URLs indexed by search engines.
  */
object PageFolderGenerator extends App {

  val docs = Paths.get(new java.io.File(".").getCanonicalPath).resolve("docs")

  def genFolder(page: Page): Unit = {
    val pageDir = docs.resolve(page.url)
    if (Files.notExists(pageDir)) Files.createDirectories(pageDir)
    Files.copy(docs.resolve("index.html"), pageDir.resolve("index.html"), StandardCopyOption.REPLACE_EXISTING)
    page.subPages.foreach(genFolder)
  }

  def deleteFolder(page: Page): Unit = {
    val pageDir = docs.resolve(page.url)
    val pageIndexHTML = pageDir.resolve("index.html")
    page.subPages.foreach(deleteFolder)
    if (Files.exists(pageIndexHTML)) Files.delete(pageIndexHTML)
    if (Files.exists(pageDir)) Files.delete(pageDir)
  }

  //    RootPages.pages.filter(_.url.nonEmpty).foreach(genFolder)
  RootPages.pages.filter(_.url.nonEmpty).foreach(deleteFolder)

}
