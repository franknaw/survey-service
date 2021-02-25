package com.foo.cte.client.thrift;

import javax.net.ssl.SSLContext

import com.foo.cte.server.client.ThriftClientFactory
import SurveyService
import SurveyService.ServiceIface

object SurveyServiceClientFactory extends ThriftClientFactory {

  /**
   * Factory which connects directly to the specified server connection info. Should only be used for testing.
   * This does not configure the client for SSL and the returned client cannot be used to connect to a service that
   * is running with SSL.
   *
   * @param hostName    Security service hostname/IP
   * @param port        Security service port
   * @param serviceName The name of the service using the client
   */
  def createClient(hostName: String, port: Int, serviceName: String): ServiceIface = {
    newConfiguredClient(serviceName, null)
      .newIface[ServiceIface]("inet!" + hostName + ":" + port, classOf[ServiceIface])
  }

  /**
   * Factory which connects directly to the specified server connection info. Should only be used for testing.
   * This configures the client with SSL and is to be used when connecting to service that is running with SSL.
   *
   * @param hostName    Security service hostname/IP
   * @param port        Security service port
   * @param serviceName The name of the service using the client
   * @param sslContext  The SSLContext to use
   */
  def createClient(hostName: String, port: Int, serviceName: String, sslContext: SSLContext): ServiceIface = {
    newConfiguredClient(serviceName, sslContext)
      .newIface[ServiceIface]("inet!" + hostName + ":" + port, classOf[ServiceIface])
  }

  /**
   * Factory which gets the list of servers from zookeeper and load balances
   * This does not configure the client for SSL and the returned client cannot be used to connect to a service that
   * is running with SSL.
   *
   * @param zkConnInfo  Zookeeper connection info. Format: IP1/DNS1:PORT,IP2/DNS2:PORT,...
   * @param zkAnncPt    Zookeeper announcement point. E.g.: /bedrock/service/security
   * @param serviceName The name of the service using the client.  This can be used by the service to see who's calling it,
   *                    but isn't actually used anywhere in this example-service.  To provide a client without the clientId,
   *                    see { @link #createClient(String, String) createClient} below.
   */
  def createClient(zkConnInfo: String, zkAnncPt: String, serviceName: String): ServiceIface = {
    newConfiguredClient(serviceName, null)
        .newIface[ServiceIface]("zk2!" + zkConnInfo + "!" + zkAnncPt + "/thrift", classOf[ServiceIface])
  }

  /**
   * Factory which gets the list of servers from zookeeper and load balances.
   * This configures the client with SSL and is to be used when connecting to service that is running with SSL.
   *
   * @param zkConnInfo  Zookeeper connection info. Format: IP1/DNS1:PORT,IP2/DNS2:PORT,...
   * @param zkAnncPt    Zookeeper announcement point. E.g.: /bedrock/service/security
   * @param serviceName The name of the service using the client.  This can be used by the service to see who's calling it,
   *                    but isn't actually used anywhere in this example-service.  To provide a client without the clientId,
   *                    see { @link #createClient(String, String) createClient} below.
   * @param sslContext  The SSLContext to use
   */
  def createClient(zkConnInfo: String, zkAnncPt: String, serviceName: String, sslContext: SSLContext): ServiceIface = {
    newConfiguredClient(serviceName, sslContext)
      .newIface[ServiceIface]("zk2!" + zkConnInfo + "!" + zkAnncPt + "/thrift", classOf[ServiceIface])
  }

  /**
   * Factory which gets the list of servers from zookeeper and load balances.
   * This does not configure the client for SSL and the returned client cannot be used to connect to a service that
   * is running with SSL.
   *
   * @param zkConnInfo Zookeeper connection info. Format: IP1/DNS1:PORT,IP2/DNS2:PORT,...
   * @param zkAnncPt   Zookeeper announcement point. E.g.: /bedrock/service/security
   */
  def createClient(zkConnInfo: String, zkAnncPt: String): ServiceIface = {
    return createClient(zkConnInfo, zkAnncPt, null, null)
  }

  /**
   * Factory which gets the list of servers from zookeeper and load balances.
   * This configures the client with SSL and is to be used when connecting to service that is running with SSL.
   *
   * @param zkConnInfo Zookeeper connection info. Format: IP1/DNS1:PORT,IP2/DNS2:PORT,...
   * @param zkAnncPt   Zookeeper announcement point. E.g.: /bedrock/service/security
   * @param sslContext  The SSLContext to use
   */
  def createClient(zkConnInfo: String, zkAnncPt: String, sslContext: SSLContext): ServiceIface = {
    return createClient(zkConnInfo, zkAnncPt, null, sslContext)
  }

}
