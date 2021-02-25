package com.foo.cte

import java.io.File

import com.google.inject.Guice
import com.twitter.finagle.http.filter.Cors
import com.typesafe.scalalogging.slf4j.LazyLogging
import SurveyModule
import com.foo.cte.server.security.AclRestFilter
import com.foo.cte.server.security.FileWhitelistImpersonationAccessManager
import com.foo.cte.server.security.WhitelistClientFilter
import com.foo.cte.server.Implicits.fileFlaggable

import com.foo.cte.rest.SurveyServiceRestController
import com.foo.cte.server.CteServer
import com.foo.cte.server.RestServer
import com.foo.cte.server.ThriftServer
import SurveyServiceThriftService

import scala.concurrent.duration.Duration

object SurveyServiceApp extends CteServer with LazyLogging {

  // Dependency injection
  val injector = Guice.createInjector(new SurveyModule())
  val surveyServiceController = injector.getInstance(classOf[SurveyServiceController])

  val whitelistFile = flag[File]("acl.whitelist.file", "ACL whitelist file for user impersonation")
  var accessManager: FileWhitelistImpersonationAccessManager = _

  premain {
    accessManager = new FileWhitelistImpersonationAccessManager(
      whitelistFile(), Duration(1, "minute"))
//      sarzFileRemovalService.startRemovalTimer()
  }

  /**
   * Gets Thrift Server
   */
  def thrift = Some(new ThriftServer(new SurveyServiceThriftService(),
    Seq(new WhitelistClientFilter(accessManager))))

  /**
   * Gets REST Server
   */
  def rest = Some(new RestServer(new SurveyServiceRestController(surveyServiceController),
    Seq(new Cors.HttpFilter(Cors.UnsafePermissivePolicy))))

//  def rest = Some(new RestServer(new SurveyServiceRestController(surveyServiceController),
//    Seq(new Cors.HttpFilter(Cors.UnsafePermissivePolicy), new AclRestFilter(accessManager))))

}
