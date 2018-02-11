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

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import swaydb.io.{Main, Page, RouterController}
import swaydb.io.Page.Intro

object TopMenuComponent {

  def onToggleSideNav: Callback =
    Callback {
      dom.document.getElementById("sidenav").classList.toggle("sidebar-show")
    }

  val component = ScalaComponent.builder[RouterCtl[Page]]("MainMenu")
    .render_P { ctl =>
      <.div(
        <.nav(^.className := "navbar navbar-fixed-top top-nav",
          <.div(^.className := "navbar-header",
            <.button(^.`type` := "button", ^.className := "navbar-toggle collapsed", ^.onClick --> onToggleSideNav,
              <.span(^.className := "icon-bar"),
              <.span(^.className := "icon-bar"),
              <.span(^.className := "icon-bar")
            ),
            <.span(
              <.a(
                ^.className := "logo pull-right",
                ^.padding := "9px 15px",
                ^.height := "50px",
                ^.href := "https://www.facebook.com/Swaydb01",
                ^.target := "blank",
                ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - facebook@SwayDB")),
                <.span(
                  <.img(
                    ^.src := "img/icon-fb.png"
                  )
                )
              )
            ),
            <.span(
              <.a(
                ^.className := "logo pull-right",
                ^.padding := "9px 0px",
                ^.height := "50px",
                ^.href := "https://twitter.com/SwayDB",
                ^.target := "blank",
                ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - twitter@SwayDB")),
                <.span(
                  <.img(
                    ^.src := "img/icon-twitter.png"
                  )
                )
              )
            ),
            <.span(
              <.a(
                ^.className := "logo pull-right",
                ^.padding := "9px 15px",
                ^.height := "50px",
                ^.href := "https://github.com/simerplaha/SwayDB",
                ^.target := "blank",
                ^.onClick --> Callback(Main.analytics.event("Outbound click", s"${this.getClass.getSimpleName} - github@SwayDB")),
                <.span(
                  <.img(
                    ^.src := "img/icon-github.ico"
                  )
                )
              )
            ),
            <.a(^.className := "navbar-brand logo", ^.href := "#",
              <.span(<.img(^.src := "img/logo.png")),
              //              <.span(^.className := "logo", "SwayDB"),
              <.span(RouterController.router.link(Intro)(<.img(^.src := "img/text-logo.png", ^.height := "25px"))),
            )
          )
        )
      )
    }
    .build
}
