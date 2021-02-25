package com.foo.cte.config;

import java.io.File;
import java.net.URISyntaxException;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import static com.google.common.base.Preconditions.checkNotNull;

public class SurveyConfig {

    // Redis Properties
    private final String redisServer;
    private final String redisPort;
    private final long redisKeyExpirationInHours;

    // Federator Properties
    private final String federatorServiceName;
    private final String federatorHost;
    private final Integer federatorPort;
    private final String federatorZooKeeperAnnouncementPoint;

    // Key / Trust store Properties
    private final String keystore;
    private final String keystorePassword;
    private final String truststore;
    private final String truststorePassword;

    // Zoo Keeper Properties
    private final Boolean zookeperEnableAutoDiscovery;
    private final String zooKeeperUrl;

    private final Integer futureTimeOut;

    // Http Client Properties
    private final Integer maxConnectionRetries;
    private final Integer maxHostConnections;
    private final Integer requestTimeOut;
    private final Integer connectionTimeOut;

    // Q Properties
    private final String qSourceId;
    private final String qMapFile;
    private final String qProvider;

    // G Properties
    private final String gSourceId;
    private final String gMapFile;

    // Coherence Properties
    private final String coherenceSourceId;
    private final String coherenceMapFile;
    private final String coherenceUrl;
    private final String coherenceUri;

    private final String ogpSourceId;
    private final String ogpMapFile;
    private final String ogpUrl;
    private final String ogpUri;

    // SW Properties
    private final String swSourceId;
    private final String swMapFile;

    // RMT Properties
    private final String rmtServiceName;
    private final String rmtHost;
    private final Integer rmtPort;
    private final String rmtZooKeeperAnnouncementPoint;
    private final String rmtSourceId;
    private final String rmtMapFile;
    private final String rmtProvider;

    private final String dataSourceConfigFile;
    private final String sarzFileLocation;
    private final Long resultsQueryTimeout;
    private final String fileRemovalExt;
    private final Integer sarzFileLifeDays;
    private final Integer sarzFileRemovalInterval;
    private final String plexusMissionOptics;
    private final String plexusUrl;
    private final String plexusUri;
    private final String plexusMapFile;

    @Inject
    public SurveyConfig(
            @Named("redis.host") final String redisHost,
            @Named("redis.port") final String redisPort,
            @Named("redis.key.expiration") final Long redisKeyExpirationInHours,

            @Named("federator.service.name") final String federatorServiceName,
            @Named("federator.host") final String federatorHost,
            @Named("federator.port") final Integer federatorPort,
            @Named("federator.zookeeper.announcement.point") final String federatorZooKeeperAnnouncementPoint,

            @Named("keystore") final String keystore,
            @Named("keystore.password") final String keystorePassword,
            @Named("truststore") final String truststore,
            @Named("truststore.password") final String truststorePassword,

            @Named("zookeeper.enable.autodiscovery") final Boolean zookeeperEnableAutoDiscovery,
            @Named("zookeeper.url") final String zooKeeperUrl,

            @Named("future.timeout") final Integer futureTimeOut,
            @Named("max.connection.retries") final Integer maxConnectionRetries,
            @Named("max.host.connections") final Integer maxHostConnections,
            @Named("request.timeout") final Integer requestTimeOut,
            @Named("connection.timeout") final Integer connectionTimeOut,
            @Named("data.source.config.file") final String dataSourceConfigFile,

            @Named("q.source.id") final String qSourceId,
            @Named("q.map.file") final String qMapFile,
            @Named("q.provider") final String qProvider,

            @Named("g.source.id") final String gSourceId,
            @Named("g.map.file") final String gMapFile,

            @Named("coherence.url") final String coherenceUrl,
            @Named("coherence.uri") final String coherenceUri,
            @Named("coherence.source.id") final String coherenceSourceId,
            @Named("coherence.map.file") final String coherenceMapFile,

            @Named("ogp.source.id") final String ogpSourceId,
            @Named("ogp.map.file") final String ogpMapFile,
            @Named("ogp.url") final String ogpUrl,
            @Named("ogp.uri") final String ogpUri,

            @Named("sw.source.id") final String swSourceId,
            @Named("sw.map.file") final String swMapFile,

            @Named("rmt.serviceName") final String rmtServiceName,
            @Named("rmt.host") final String rmtHost,
            @Named("rmt.zookeeper.announcement.point") final String rmtZooKeeperAnnouncementPoint,
            @Named("rmt.port") final Integer rmtPort,
            @Named("rmt.source.id") final String rmtSourceId,
            @Named("rmt.map.file") final String rmtMapFile,
            @Named("rmt.map.file") final String rmtProvider,
            @Named("results.query.timeout") final Long resultsQueryTimeout,
            @Named("sarz.file.location") final String sarzFileLocation,
            @Named("sarz.file.life.days") final Integer sarzFileLifeDays,
            @Named("sarz.file.removal.ext") final String fileRemovalExt,
            @Named("sarz.file.removal.interval") final Integer sarzFileRemovalInterval,
            @Named("plexus.mission.optics") final String plexusMissionOptics,
            @Named("plexus.url") final String plexusUrl,
            @Named("plexus.uri") final String plexusUri,
            @Named("plexus.map.file") final String plexusMapFile) {

        // Zookeeper Properties
        this.zookeperEnableAutoDiscovery = zookeeperEnableAutoDiscovery;
        this.zooKeeperUrl = checkNotNull(zooKeeperUrl);

        // Federator Properties
        this.federatorServiceName = checkNotNull(federatorServiceName);
        this.federatorHost = checkNotNull(federatorHost);
        this.federatorPort = checkNotNull(federatorPort);
        this.federatorZooKeeperAnnouncementPoint = checkNotNull(federatorZooKeeperAnnouncementPoint);

        // Redis Properties
        this.redisServer = checkNotNull(redisHost);
        this.redisPort = checkNotNull(redisPort);
        this.redisKeyExpirationInHours = checkNotNull(redisKeyExpirationInHours);

        // Key / Trust store Properties
        this.keystore = checkNotNull(keystore);
        this.keystorePassword = checkNotNull(keystorePassword);
        this.truststore = checkNotNull(truststore);
        this.truststorePassword = checkNotNull(truststorePassword);

        this.futureTimeOut = checkNotNull(futureTimeOut);
        this.maxConnectionRetries = checkNotNull(maxConnectionRetries, "Missing max.connection.retries");
        this.maxHostConnections = checkNotNull(maxHostConnections, "Missing max.host.connections");
        this.requestTimeOut = checkNotNull(requestTimeOut, "Missing request.timeout");
        this.connectionTimeOut = checkNotNull(connectionTimeOut, "Missing connection.timeout");

        // Configuration for /data/sources endpoint
        this.dataSourceConfigFile = checkNotNull(dataSourceConfigFile);

        // Q Properties
        this.qSourceId = checkNotNull(qSourceId);
        this.qMapFile = checkNotNull(qMapFile);
        this.qProvider = checkNotNull(qProvider);

        // G Properties
        this.gSourceId = checkNotNull(gSourceId);
        this.gMapFile = checkNotNull(gMapFile);

        // Coherence Properties
        this.coherenceUrl = checkNotNull(coherenceUrl);
        this.coherenceUri = checkNotNull(coherenceUri);
        this.coherenceSourceId = checkNotNull(coherenceSourceId);
        this.coherenceMapFile = checkNotNull(coherenceMapFile);

        // OGP Properties
        this.ogpSourceId = checkNotNull(ogpSourceId);
        this.ogpMapFile = checkNotNull(ogpMapFile);
        this.ogpUrl = checkNotNull(ogpUrl);
        this.ogpUri = checkNotNull(ogpUri);

        // SW Properties
        this.swSourceId = checkNotNull(swSourceId);
        this.swMapFile = checkNotNull(swMapFile);

        // RMT Properties
        this.rmtServiceName = checkNotNull(rmtServiceName);
        this.rmtHost = checkNotNull(rmtHost);
        this.rmtPort = checkNotNull(rmtPort);
        this.rmtZooKeeperAnnouncementPoint = checkNotNull(rmtZooKeeperAnnouncementPoint);
        this.rmtSourceId = checkNotNull(rmtSourceId);
        this.rmtMapFile = checkNotNull(rmtMapFile);
        this.rmtProvider = checkNotNull(rmtProvider);

        this.resultsQueryTimeout = checkNotNull(resultsQueryTimeout, "Missing results.query.timeout");
        this.sarzFileLocation = checkNotNull(sarzFileLocation, "Missing sarz.file.location");
        this.fileRemovalExt = checkNotNull(fileRemovalExt);
        this.sarzFileLifeDays = checkNotNull(sarzFileLifeDays, "Missing sarz.file.life.days");
        this.sarzFileRemovalInterval = checkNotNull(sarzFileRemovalInterval, "Missing sarz.file.removal.interval");
        this.plexusMissionOptics = checkNotNull(plexusMissionOptics, "Missing plexus.mission.optics");
        this.plexusUrl = checkNotNull(plexusUrl, "Missing plexus.url");
        this.plexusUri = checkNotNull(plexusUri, "Missing plexus.uri");
        this.plexusMapFile = checkNotNull(plexusMapFile, "Missing plexus.map.file");

    }

    public Boolean getZooKeeperEnableAutoDiscovery() {
        return zookeperEnableAutoDiscovery;
    }

    public String getZooKeeperUrl() {
        return zooKeeperUrl;
    }

    public String getFederatorZooKeeperAnnouncementPoint() {
        return federatorZooKeeperAnnouncementPoint;
    }

    public String getFederatorServiceName() {
        return federatorServiceName;
    }

    public String getFederatorHost() {
        return federatorHost;
    }

    public Integer getFederatorPort() {
        return federatorPort;
    }

    public File getKeystore() throws URISyntaxException {
        return resourceFile(keystore);
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public File getTruststore() throws URISyntaxException {
        return resourceFile(truststore);
    }

    public String getTruststorePassword() {
        return truststorePassword;
    }

    private File resourceFile(final String name) throws URISyntaxException {
        return new File(getClass().getClassLoader().getResource(name).toURI());
    }

    public String getRedisServer() {
        return redisServer;
    }

    public String getRedisPort() {
        return redisPort;
    }

    public long getRedisKeyExpirationInHours() {
        return redisKeyExpirationInHours;
    }

    public Integer getFutureTimeOut() {
        return futureTimeOut;
    }

    /**
     * Returns the max number of retries for the connection if there is a timeout or IOException
     *
     * @return the max number of retries for the connection
     */
    public Integer getMaxConnectionRetries() {
        return maxConnectionRetries;
    }

    /**
     * Returns the max number of connections per host
     *
     * @return the max number of connections per host
     */
    public Integer getMaxHostConnections() {
        return maxHostConnections;
    }

    /**
     * Returns the request timeout
     *
     * @return the request timeout in seconds
     */
    public Integer getRequestTimeOut() {
        return requestTimeOut;
    }

    public String getCoherenceUrl() {
        return coherenceUrl;
    }

    public String getCoherenceUri() {
        return coherenceUri;
    }

    public String getOgpUrl() {
        return ogpUrl;
    }

    public String getOgpUri() {
        return ogpUri;
    }

    /**
     * Returns the connection timeout
     *
     * @return the connection timeout in seconds
     */
    public Integer getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public String getRmtServiceName() {
        return rmtServiceName;
    }

    public String getRmtHost() {
        return rmtHost;
    }

    public Integer getRmtPort() {
        return rmtPort;
    }

    public String getDataSourceConfigFile() {
        return dataSourceConfigFile;
    }

    public String getqSourceId() {
        return qSourceId;
    }

    public String getqMapFile() {
        return qMapFile;
    }

    public String getqProvider() {
        return qProvider;
    }

    public String getgSourceId() {
        return gSourceId;
    }

    public String getgMapFile() {
        return gMapFile;
    }

    public String getCoherenceSourceId() {
        return coherenceSourceId;
    }

    public String getCoherenceMapFile() {
        return coherenceMapFile;
    }

    public String getOgpSourceId() {
        return ogpSourceId;
    }

    public String getOgpMapFile() {
        return ogpMapFile;
    }

    public String getSwSourceId() {
        return swSourceId;
    }

    public String getSwMapFile() {
        return swMapFile;
    }

    public String getRmtSourceId() {
        return rmtSourceId;
    }

    public String getRmtMapFile() {
        return rmtMapFile;
    }

    public String getRmtProvider() {
        return rmtProvider;
    }

    public String getRmtZooKeeperAnnouncementPoint() {
        return rmtZooKeeperAnnouncementPoint;
    }

    public String getSarzFileLocation() {
        return sarzFileLocation;
    }

    public Long getResultsQueryTimeout() {
        return resultsQueryTimeout;
    }

    public String getFileRemovalExt() {
        return fileRemovalExt;
    }

    public Integer getSarzFileLifeDays() {
        return sarzFileLifeDays;
    }

    public Integer getSarzFileRemovalInterval() {
        return sarzFileRemovalInterval;
    }

    public String getPlexusMissionOptics() {
        return plexusMissionOptics;
    }

    public String getPlexusUrl() {
        return plexusUrl;
    }

    public String getPlexusUri() {
        return plexusUri;
    }

    public String getPlexusMapFile() {
        return plexusMapFile;
    }
}