import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import models.{User, TopLevel, Error}

import scala.concurrent.Future

object Global extends GlobalSettings {

  def ensureMongoIndexes = {
    User.ensureIndexes
  }

  override def onStart(app: Application): Unit = {
    ensureMongoIndexes
  }

  override def onBadRequest(request: RequestHeader, error: String) = {
    Future.successful(BadRequest(Error.toTopLevelJson(Error(error))).as("application/vnd.api+json"))
  } 

  override def onError(request: RequestHeader, throwable: Throwable) = {
    Future.successful(InternalServerError(Error.toTopLevelJson(Error("Internal api error, try later"))).as("application/vnd.api+json"))
  }

  override def onHandlerNotFound(request: RequestHeader) = {
    Future.successful(NotFound(Error.toTopLevelJson(Error("Handler not found, check the API documentation"))).as("application/vnd.api+json"))
  }

}