FORMAT: 1A
NOTE: This file contains Markdown specifically designed to work with API Blueprint: https://github.com/apiaryio/api-blueprint

# Group Navigation

## Table of Contents

+ [Service Overview](home.html)
+ [RESTful API documention](rest.html)
+ [Thrift API documentation](thrift.html)
+ [Thrift Definition File](SurveyService.thrift)
+ [Javadoc](apidocs/index.html)

# Group Service Overview

## Service Dependencies
+ PostgreSQL for persistence.

## Chimera Training Survey Service

The Chimera training survey service facilitates the ability for training participants
to provide feedback for a particular training event.  The service dynamically provides
one or more survey forms, an admin functionality for creating forms and analytical endpoints
for providing metrics based on the survey results.  The survey form will follow a 5 point ordinal
rating scale along with a single user comments section per survey form.

## High Level Overview

+ A Survey will consist of the following.
    + One or more survey forms.
    + Text field for trainees organization.
    + Submit button for submitting the survey.

+ A Survey form will consist of  the following.
    + One or more 5 point rating scales.
    + One comments section.
    + Multiple select drop down to select one or more trainers.

+ The service has the following REST endpoints.
    + User Endpoints
        + Submit Survey -  Retrieve user survey submission.
    + Form Endpoints
        + Get Form by Id – Returns a survey form by id.
        + Get Forms  – Returns all survey forms.
        + Get Form Titles -  Retrieve list of form titles for displaying list of available forms.
    + Admin Endpoints
        + Create Form – Creates a single survey form.
        + Update Form – Edits a single survey form.
        + Delete Form – Deletes a single survey form.
    + Analytical Endpoints - Endpoints can be filtered by page ranges, date ranges and ordinal ratings.
        + Get Survey Form – Returns survey forms with applied filters.
        + Get Organization – Returns survey forms by organization with applied filters.
        + Get User – Returns survey forms by user with applied filters.
        + Get User Comments – Returns survey forms by user comment with applied filters.
        + Get  Instructors = Returns survey forms by instructors with applied filters.

