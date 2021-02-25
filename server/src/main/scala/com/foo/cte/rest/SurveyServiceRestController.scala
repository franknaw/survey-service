package com.foo.cte.rest

import java.io.InputStream
import java.util
import com.google.common.base.Optional
import com.twitter.util.Future
import com.typesafe.scalalogging.slf4j.LazyLogging
import SurveyServiceController
import com.foo.cte.response.{ResponseStatus, AdminResponse, SurveyResponse, FormsResponse}
import org.apache.commons.lang.StringUtils
import org.slf4j.LoggerFactory
import com.twitter.finatra.Controller
import com.foo.cte.server.security.UserAuthentication
import javax.inject.{ Inject, Singleton }
import scala.collection.JavaConverters._

class SurveyServiceRestController(service: SurveyServiceController) extends Controller with LazyLogging {

  private val LOG = LoggerFactory.getLogger(classOf[SurveyServiceRestController])
  private val FORMID = "formId"
  private val PAGE_PARAM_NAME = "page"
  private val START_PARAM_NAME = "startIndex"
  private val PAGE_SIZE_PARAM_NAME = "maxPerPage"

  /**
   * Documentation page.
   */
  get("/") { request =>
    render.static("/home.html").toFuture
  }


  /**
    * Submits a {@link SurveyRequest} Training Survey.
    */
  post("/survey") { request =>

    val future: Future[SurveyResponse] = service.submitSurvey(request.getContentString(), userDn)

    future flatMap { surveyResponse =>
      if (surveyResponse.getStatus == ResponseStatus.FAILURE) {
        render.status(500).json(surveyResponse.getPayload).toFuture
      } else {
        render.status(200).json(surveyResponse.getPayload).toFuture
      }
    } rescue {
      case e => render.status(500).json(errorMap(e.getMessage)).toFuture
    }
  }


  /**
   * List survey form titles and Ids
   */
  get("/survey") { request =>
    LOG.info("GET /survey")

    val page = request.getIntParam(PAGE_PARAM_NAME, -1)
    val startIndex = request.getIntParam(START_PARAM_NAME, 0)
    val maxPerPage = request.getIntParam(PAGE_SIZE_PARAM_NAME, 50)

    val future: Future[FormsResponse] = service.getFormsTitles(page, startIndex, maxPerPage, userDn)

    future flatMap { formsResponse =>
      if (formsResponse.getStatus == ResponseStatus.SUCCESS) {
        render.status(200).json(formsResponse.getPayload).toFuture
      } else {
        render.status(500).json(formsResponse.getPayload).toFuture
      }
    } rescue {
      case e => render.status(500).json(errorMap(e.getMessage)).toFuture
    }

  }

  /**
    * Return all forms - not used
    */
  get("/survey/forms") { request =>
    LOG.info("GET /survey")

    val future: Future[FormsResponse] = service.getForms(userDn)

    future flatMap { formsResponse =>
      if (formsResponse.getStatus == ResponseStatus.SUCCESS) {
        render.status(200).json(formsResponse.getPayload).toFuture
      } else {
        render.status(500).json(formsResponse.getPayload).toFuture
      }
    } rescue {
      case e => render.status(500).json(errorMap(e.getMessage)).toFuture
    }

  }

  /**
    * Return a form by Id
    */
  get("/survey/form/:formId") { request =>
    LOG.info("GET /survey")


    val formId = request.routeParams.getOrElse(FORMID, StringUtils.EMPTY)


    val future: Future[FormsResponse] = service.getForm(formId)

    future flatMap { formsResponse =>
      if (formsResponse.getStatus == ResponseStatus.SUCCESS) {
        render.status(200).json(formsResponse.getPayload).toFuture
      } else {
        render.status(500).json(formsResponse.getPayload).toFuture
      }
    } rescue {
      case e => render.status(500).json(errorMap(e.getMessage)).toFuture
    }

  }

  /**
    * Submits a {@link SurveyRequest} survey form to create.
    */
  post("/survey/admin") { request =>

    val future: Future[AdminResponse] = service.createOrUpdateForm(request.getContentString(), userDn)

    future flatMap { adminResponse =>
      if (adminResponse.getStatus == ResponseStatus.FAILURE) {
        render.status(500).json(adminResponse.getPayLoad).toFuture
      } else {
        render.status(200).json(adminResponse.getPayLoad).toFuture
      }
    } rescue {
      case e => render.status(500).json(errorMap(e.getMessage)).toFuture
    }
  }

  /**
    * Gets a {@link SurveyRequest} survey form.
    */
  get("/survey/admin/:formId") { request =>

    val formId = request.routeParams.getOrElse(FORMID, StringUtils.EMPTY)
    val future: Future[AdminResponse] = service.getForm(formId, userDn)

    future flatMap { adminResponse =>
      if (adminResponse.getStatus == ResponseStatus.FAILURE) {
        render.status(500).json(adminResponse.getPayLoad).toFuture
      } else {
        render.status(200).json(adminResponse.getPayLoad).toFuture
      }
    } rescue {
      case e => render.status(500).json(errorMap(e.getMessage)).toFuture
    }
  }

  /**
    * Gets a {@link SurveyRequest} survey form.
    */
  get("/survey/admin/survey/:formId") { request =>

    val formId = request.routeParams.getOrElse(FORMID, StringUtils.EMPTY)
    val future: Future[AdminResponse] = service.getSurvey(formId, userDn)

    future flatMap { adminResponse =>
      if (adminResponse.getStatus == ResponseStatus.FAILURE) {
        render.status(500).json(adminResponse.getPayLoad).toFuture
      } else {
        render.status(200).json(adminResponse.getPayLoad).toFuture
      }
    } rescue {
      case e => render.status(500).json(errorMap(e.getMessage)).toFuture
    }
  }

  /**
    * Deletes a {@link SurveyRequest} survey form.
    */
  delete("/survey/admin/:formId") { request =>

    val formId = request.routeParams.getOrElse(FORMID, StringUtils.EMPTY)
    val future: Future[AdminResponse] = service.deleteForm(formId, userDn)

    future flatMap { adminResponse =>
      if (adminResponse.getStatus == ResponseStatus.FAILURE) {
        render.status(500).json(adminResponse.getPayLoad).toFuture
      } else {
        render.status(200).json(adminResponse.getPayLoad).toFuture
      }
    } rescue {
      case e => render.status(500).json(errorMap(e.getMessage)).toFuture
    }
  }

  /**
    * Deletes a {@link SurveyRequest} survey.
    */
  delete("/survey/admin/survey/:formId") { request =>

    val formId = request.routeParams.getOrElse(FORMID, StringUtils.EMPTY)
    val future: Future[AdminResponse] = service.deleteSurveyForm(formId, userDn)

    future flatMap { adminResponse =>
      if (adminResponse.getStatus == ResponseStatus.FAILURE) {
        render.status(500).json(adminResponse.getPayLoad).toFuture
      } else {
        render.status(200).json(adminResponse.getPayLoad).toFuture
      }
    } rescue {
      case e => render.status(500).json(errorMap(e.getMessage)).toFuture
    }
  }


  /**
   * Gets the user dn
   *
   * @return the user dn
   */
  def userDn: String = "My USER DN"
//  def userDn: String = {
//    if (UserAuthentication.current.isEmpty) throw new IllegalArgumentException("Client certificate is required")
//    val result = UserAuthentication.current.map(auth => auth.user.trim).get
//    if (StringUtils.isEmpty(result)) throw new IllegalArgumentException("User DN is required")
//    result
//  }

  def errorMap(msg: String): scala.collection.Map[String, Any] = {
    Map("success" -> false, "messages" -> Array(msg))
  }

  def errorMap(msg: java.util.List[String]): scala.collection.Map[String, Any] = {
    Map("success" -> false, "messages" -> msg)
  }

}