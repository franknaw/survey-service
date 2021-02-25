package com.foo.cte.rest

import java.util.Properties

import com.google.inject.Guice
import com.twitter.finatra.FinatraServer
import com.twitter.finatra.test.FlatSpecHelper
import com.foo.cte.SurveyServiceApp._
import SurveyModule
import org.apache.commons.io.FileUtils
//import semantica.tools.sarzgenerator.DefaultSarzGenerationService

class SurveyServiceRestControllerSpec { // extends FlatSpecHelper {

//  val properties = new Properties()
//  properties.load(getClass.getClassLoader.getResourceAsStream("semantica-export.properties"))
//
//  val sarzGenerator = new DefaultSarzGenerationService
//  sarzGenerator.setOntologySar(FileUtils.toFile(getClass.getClassLoader.getResource("ontology.sar")))
//
//  val injector = Guice.createInjector(new ExportModule())
//  val exportService = injector.getInstance(classOf[ExportService])
//
//  val semanticaExportServiceManager = new SemanticaExportServiceManager(properties, sarzGenerator, exportService)
//
//  // Yes you should remove these..
//  // Example unit test of rest controller
//  val server = new FinatraServer
//  server.register(new SemanticaExportServiceRestController(semanticaExportServiceManager))

}
