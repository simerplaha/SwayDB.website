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

package swaydb.io.menu

import com.karasiq.highlightjs.HighlightJS
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.TagOf
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import org.scalajs.dom
import org.scalajs.dom.ext._
import org.scalajs.dom.html.LI
import swaydb.io.Page.Intro
import swaydb.io.{Page, RootPages, RouterController}

object SideMenuComponent {

  def initHighlightJs =
    Callback {
      dom.document.getElementsByTagName("code") foreach {
        node =>
          HighlightJS.highlightBlock(node.domToHtml.get)
      }
    }

  def isInSubPages(pageToFind: Page, inPage: Page): Boolean =
    if (inPage.subPages.contains(pageToFind))
      true
    else
      inPage.subPages.exists(isInSubPages(pageToFind, _))

  def renderPage(page: Page, selectedPage: Page): TagOf[LI] = {
    val isHighlighted = page == selectedPage || isInSubPages(selectedPage, page)

    def renderIcon = {
      if (isHighlighted)
        <.span(^.className := "glyphicon glyphicon-menu-down pull-right")
      else
        <.span(^.className := "glyphicon glyphicon-menu-right pull-right")
    }.when(page.subPages.nonEmpty)

    <.li((^.className := "active").when(isHighlighted),
      RouterController.router.link(page)(page.name, renderIcon),
      renderPages(page.subPages, selectedPage, subNav = true)
        .when(page.subPages.nonEmpty && isHighlighted)
    )
  }

  def renderPages(pages: Seq[Page], selectedPage: Page, subNav: Boolean = false) =
    <.ul(
      if (!subNav)
        ^.className := "nav nav-sidebar"
      else
        Seq(^.className := "nav nav-sidebar", ^.paddingLeft := "40px", ^.marginRight := "4px").toTagMod,
      (pages map (renderPage(_, selectedPage))).toTagMod
    )

  val component = ScalaComponent.builder[Page]("Menu")
    .render_P { page =>
      <.div(
        <.div(^.id := "sidenav", ^.className := "col-sm-3 col-md-2 sidebar collapse",
          renderPages(RootPages.pages, page),
          RouterController.router.link(Intro)(
            <.img(^.src := "/img/text-logo.png", ^.marginLeft := "-10px", ^.paddingTop := "10px", ^.paddingBottom := "100px")
          )

        ),
        <.div(^.className := "col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main", ^.paddingBottom := "100px",
          page.render()
        )
      )
    }.componentDidMount(_ => initHighlightJs)
    .componentDidUpdate(_ => initHighlightJs)
    .build
}
