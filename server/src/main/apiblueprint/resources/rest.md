FORMAT: 1A

# Group Navigation

## Table of Contents

+ [Service Overview](home.html)
+ [RESTful API documention](rest.html)
+ [Thrift API documentation](thrift.html)

# Group RESTful API

The training survey API
## Submit Training Survey [/survey]

### Submit Training Survey [POST]
This endpoint accepts a survey submission.  A survey may contain one or more survey forms.

+ Request (application/json)

        {
          "survey": {
            "user": "The trainee",
            "organization": "The trainees organization",
            "entry date": "Survey submission date",
            "forms": [{
              "id": "Form Id",
              "name": "Form name",
              "questions": [{
                "question": "Survey question 1",
                "rating": "3"
              }, {
                "question": "Survey question 2",
                "rating": "2"
              }],
              "comment": {
                "question": "The comment question",
                "value": "The trainee comment"
              },
              "instructors": ["Instructor A", "Instructor B"]
            }, {
              "id": "Form Id",
              "name": "Form name",
              "questions": [{
                "question": "Survey question 1",
                "rating": "4"
              }, {
                "question": "Survey question 2",
                "rating": "5"
              }],
              "comment": {
                "question": "The comment question",
                "value": "The trainee comment"
              },
              "instructors": ["Instructor B"]
            }]
          }
        }

+ Response 200 (application/json)

        {
           "status" : 200,
           "success" : true,
           "messages" : [],
        }

## Get Form Titles [/survey{?page,startIndex,maxPerPage}]

### Form Titles [GET]

Use this endpoint to get a list of survey form titles and Ids.

Example:

```no-highlight
https://host/survey?page=1&maxPerPage=2
```
+ Parameters

   + page: `1` (integer, optional) - If results are split into multiple pages, this specifies which page to show
      + Default: `1`
    + startIndex: `1` (integer, optional) -Use as an alternative to the `page` parameter to specify that the results should start at whatever page contains the Nth item. :warning: If specified, `page` will be ignored.
      + Default: `1`
    + maxPerPage: `50` (integer, optional) - The maximum number of items to return per page of results
      + Default: `50`


+ Response 200 (application/json)

        Typical response

        {
        	"forms": [{
        	    "id": "Id A",
        		"name": "Form A"
        	}, {
        	    "id": "Id B",
        		"name": "Form B"
        	}],
            "status" : 200,
            "success" : true,
            "messages" : []
        }

## Get Form [/survey/{formId}]

### Form by Id [GET]

Use this endpoint to retrieve a specific survey form

Example:

```no-highlight
https://host/survey/{formId}
```
+ Parameters

    + formId: `93a6fd03-1c65-4918-96ee-f1a452699075` (string, required) - The formId ID of the survey form being retrieved

+ Response 200 (application/json)
        
        {
        	"form": {
        		"id": "Id A",
        		"name": "Form A",
        		"questions": ["Question A", "Question B"],
        		"comment": "The comment title",
        		"instructors": ["Instructor A", "Instructor B"]
        	}
            "status" : 200,
            "success" : true,
            "messages" : []
        }


## Create Survey Form [/survey/admin]

### Create Survey Form [POST]
This endpoint accepts specifications to create one or more survey forms.

+ Request (application/json)

        {
            "forms": [{
                "name": "Some title for the form or simply the source name",
                "questions": ["Survey question 1", "Survey question 2", "Survey question 3"],
                "comment": "The question or title for the comment field",
                "instructors": ["Instructor A", "Instructor B", "Instructor C"]
            }, {
                "name": "Some title for the form or simply the source name",
                "questions": ["Survey question 1", "Survey question 2", "Survey question 3"],
                "comment": "The question or title for the comment field",
                "instructors": ["Instructor A", "Instructor B", "Instructor C"]
            }]
        }

+ Response 200 (application/json)

        {
           "status" : 200,
           "success" : true,
           "messages" : [],
        }

## Update Survey Form [/survey/admin]

### Update Survey Form [PUT]
This endpoint modify's one or more survey forms.

+ Request (application/json)

        {
            "forms": [{
                "id": "The form id",
                "instructors": ["Instructor A", "Instructor B", "Instructor C"]
            }, {
                "id": "The form id",
                "name": "Some title for the form or simply the source name",
                "questions": ["Survey question 1", "Survey question 2", "Survey question 3"],
                "comment": "The question or title for the comment field",
                "instructors": ["Instructor A", "Instructor B", "Instructor C"]
            }]
        }

+ Response 200 (application/json)

        {
           "status" : 200,
           "success" : true,
           "messages" : [],
        }

## Get Survey Form [/survey/admin/{formId}]

### Get Survey Form [GET]
This endpoint gets a survey form by Id.

Example:

```no-highlight
https://host/survey/admin/{formId}
```
+ Parameters

    + formId: `93a6fd03-1c65-4918-96ee-f1a452699075` (string, required) - The formId ID of the survey form to be deleted

+ Response 200 (application/json)

        {
            "name": "Some title for the form or simply the source name",
            "questions": ["Survey question 1", "Survey question 2", "Survey question 3"],
            "comment": "The question or title for the comment field",
            "instructors": ["Instructor A", "Instructor B", "Instructor C"]
        }

## Delete Survey Form [/survey/admin/{formId}]

### Delete Survey Form [DELETE]
This endpoint deletes one or more survey forms.

Example:

```no-highlight
https://host/survey/admin/{formId}
```
+ Parameters

    + formId: `93a6fd03-1c65-4918-96ee-f1a452699075` (string, required) - The formId ID of the survey form to be deleted

+ Response 200 (application/json)

        {
           "status" : 200,
           "success" : true,
           "messages" : [],
        }


## TODO  - Training Survey Metrics [/survey/metrics]

### Training Survey Metrics [POST]
This endpoint provides training survey metrics.

+ Request (application/json)

        + Get metrics
        {
            "forms": [{
                "name": "Some title for the form or simply the source name",
                "questions": ["Survey question 1", "Survey question 2", "Survey question 3"],
                "comment": "The question or title for the comment field",
                "instructors": ["Instructor A", "Instructor B", "Instructor C"]
            }, {
                "name": "Some title for the form or simply the source name",
                "questions": ["Survey question 1", "Survey question 2", "Survey question 3"],
                "comment": "The question or title for the comment field",
                "instructors": ["Instructor A", "Instructor B", "Instructor C"]
            }]
        }

+ Response 200 (application/json)

        {
           "status" : 200,
           "success" : true,
           "messages" : [],
        }

