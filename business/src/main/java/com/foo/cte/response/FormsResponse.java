package com.foo.cte.response;


import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tnaw on 2/1/17.
 */
public class FormsResponse {

    private Map<String, Object> responseMap;
    private ResponseStatus status;

    public FormsResponse(final Map<String, Object> responseMap, ResponseStatus status) {
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
