package com.foo.cte.config;

import java.io.IOException;
import java.util.Properties;

import com.foo.cte.service.FormService;
import com.foo.cte.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;


public class SurveyModule extends AbstractModule {

    private Logger logger = LoggerFactory.getLogger(SurveyModule.class);

    private static final String PROPERTIES = "survey.properties";

    @Override
    protected void configure() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES));
            Names.bindProperties(binder(), props);
            requireBinding(SurveyConfig.class);
            requireBinding(FormService.class);
            requireBinding(SurveyService.class);
//            requireBinding(MongoDbSettings.class);
//            requireBinding(MongoDatabaseImpl.class);

            requireBinding(MongoDB.class);

//            Multibinder<IndexSarZGenerator> sarZGeneratorBinder = Multibinder.newSetBinder(binder(), IndexSarZGenerator.class);

//            sarZGeneratorBinder.addBinding().to(RMTSarZGenerator.class);
//            sarZGeneratorBinder.addBinding().to(PlexusSarZGenerator.class);

//            bind(BSXFederatorServiceApp.ServiceIface.class).toProvider(FederatorProvider.class);
//            bind(RmtDataStorage.ServiceIface.class).toProvider(RMTProvider.class);
//            bind(Redisson.class).toProvider(RedisClientProvider.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
