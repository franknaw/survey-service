package com.foo.cte.thrift;

//import com.foo.cte.ExportService;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import static junit.framework.Assert.assertTrue;

public class SurveyServiceThriftServiceTest {

    // Yes you should remove this..
    // Example unit tests with mocks
    private static SurveyServiceThriftService surveyServiceThriftService;
   // private static ExportService mockSemanticaExportServiceMgr;

    @BeforeClass
    public static void setup() throws Exception {
       // mockSemanticaExportServiceMgr = Mockito.mock(ExportService.class);
        //semanticaExportServiceThriftService = new SemanticaExportServiceThriftService(mockSemanticaExportServiceMgr);
    }

//    @Test
//    public void testPing() throws Exception {
//        Future<String> stringFuture = semanticaExportServiceThriftService.ping();
//        String result = Await.result(stringFuture);
//        assertTrue(result.equalsIgnoreCase("pong"));
//    }
}
