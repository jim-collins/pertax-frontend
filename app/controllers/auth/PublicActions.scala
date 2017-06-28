/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers.auth

import config.ConfigDecorator
import models.PertaxContext
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.{Action, AnyContent, Result}
import uk.gov.hmrc.play.frontend.auth.DelegationAwareActions
import uk.gov.hmrc.play.frontend.controller.UnauthorisedAction
import util.LocalPartialRetriever

import scala.concurrent.Future


trait PublicActions extends DelegationAwareActions { this: I18nSupport =>

  def partialRetriever: LocalPartialRetriever
  def configDecorator: ConfigDecorator
  def messagesApi: MessagesApi

  def PublicAction(block: PertaxContext => Future[Result]): Action[AnyContent] = {
    UnauthorisedAction.async {
      implicit request =>
        block(PertaxContext(request, partialRetriever, configDecorator))
    }
  }

}