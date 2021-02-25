package com.foo.cte.response;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tnaw on 2/2/17.
 */
public class SurveyResponse {


    private Map<String, Object> responseMap;
    private ResponseStatus status;

    public SurveyResponse(final Map<String, Object> responseMap, ResponseStatus status) {
        this.responseMap = checkNotNull(responseMap);
        this.status = checkNotNull(status);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public Map<String, Object> getPayload() {
        return responseMap;
    }


}
