package com.foo.cte;

import com.foo.cte.response.AdminResponse;
import com.foo.cte.response.FormsResponse;
import com.foo.cte.response.SurveyResponse;
import com.foo.cte.service.AdminService;
import com.foo.cte.service.FormService;
import com.foo.cte.service.SurveyService;
import com.google.inject.Inject;
import com.twitter.util.Future;


import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tnaw on 2/1/17.
 */
public class SurveyServiceController {

    private final FormService formsService;
    private final SurveyService surveyService;
    private final AdminService adminService;

    @Inject
    public SurveyServiceController(final FormService formsService, final SurveyService surveyService, final AdminService adminService) {
        this.formsService = checkNotNull(formsService);
        this.surveyService = checkNotNull(surveyService);
        this.adminService = checkNotNull(adminService);
    }

    public Future<SurveyResponse> submitSurvey(final String requestContent, final String userDN) throws IOException {
        return surveyService.submit(requestContent, userDN);

    }

    public Future<FormsResponse> getFormsTitles(int page, int startIndex, int maxPerPage, final String userDn) {
        return formsService.getFormsTitles(userDn);
    }

    public Future<FormsResponse> getForms(final String userDn) {
        return formsService.getForms(userDn);
    }

    public Future<FormsResponse> getForm(final String formId) {
        return formsService.getForm(formId);
    }

    public Future<AdminResponse> createOrUpdateForm(final String requestContent, final String userDN) {
        return adminService.createOrUpdateForm(requestContent, userDN);
    }

    public Future<AdminResponse> getForm(final String formId, final String userDN) {
        return adminService.getForm(formId, userDN);
    }

    public Future<AdminResponse> deleteForm(final String formId, final String userDN) {
        return adminService.deleteForm(formId, userDN);
    }

    public Future<AdminResponse> deleteSurveyForm(final String formId, final String userDN) {
        return adminService.deleteSurveyForm(formId, userDN);
    }

    public Future<AdminResponse> getSurvey(final String formId, final String userDN) {
        return adminService.getSurvey(formId, userDN);
    }

}
