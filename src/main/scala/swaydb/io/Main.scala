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

import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import swaydb.io.menu.{SideMenuComponent, TopMenuComponent}
import Page._
import japgolly.scalajs.react.Callback
import swaydb.io.ga.{GA, GoogleAnalytics}
import dom.ext._
import org.scalajs.dom.Element

object Main {

  val analytics = new GA("UA-113654756-1")

  /**
    * On a small screen this will close the side menu if open on click.
    */
  def hideSideBarIfOpen() =
    dom.document.getElementsByClassName("sidebar-show").foreach {
      node =>
        node.asInstanceOf[Element].classList.remove("sidebar-show")
    }

  val routerConfig = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._

    def buildStaticRoute(page: Page): dsl.Rule =
      staticRoute(page.url, page) ~> render(SideMenuComponent.component(page))

    def buildRoutes(page: Page): dsl.Rule =
      page.subPages.foldLeft(buildStaticRoute(page))(_ | buildRoutes(_))

    RootPages.pages
      .drop(1)
      .foldLeft(buildStaticRoute(RootPages.pages.head))(_ | buildRoutes(_))
      .notFound(redirectToPage(RootPages.pages.head)(Redirect.Replace))
      .renderWith(layout)
      .onPostRender {
        case (_, currentPage) =>
          Callback {
            hideSideBarIfOpen()
            analytics.pageView(currentPage)
            println("apdsosodoso")
          }
      }
  }

  def layout(router: RouterCtl[Page], r: Resolution[Page]) = {
    RouterController(router)
    <.div(
      TopMenuComponent.component(router),
      <.div(^.className := "container-fluid",
        <.div(^.className := "row",
          r.render()
        )
      )
    )
  }

  def main(args: Array[String]): Unit = {
    val router = Router(BaseUrl.until_#, routerConfig)
    router().renderIntoDOM(dom.document.body)
  }
}
