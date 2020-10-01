# Enrollment

Enrollment is a spring boot Rest API where user can enroll/modify/delete the enrollee and also add/modify/delete dependents.

# API 

* [Add Enrollee](http://localhost:8080/enrollee)
    - POST
    - content-Type: application/json
    - Request Body: Enrollee
    - Sample Request
         ```
                {
                "name":"test",
                "dob":"04-19-2000",
                "activationStatus": "True",
                "dependents":[
                    {
                        "id":1,
                        "name":"testDependent",
                        "dob":"09-01-2010"
                    }
                ]
            }

* [Update Enrollee](http://localhost:8080//enrollee/{id})
    - PUT
    - content-Type: application/json
    - Request Body: Enrollee
    - Pass enrollee ID  as path Parameter
    - Sample Request
         ```
           {
                "activationStatus": "True",
                "phonenum":"9999999999"
            }

* [Delete Enrollee](http://localhost:8080//enrollee/{id})
        - DELETE
        - Just pass the id of enrollee as path parameter.

* [Add Enrollee Dependent](http://localhost:8080//enrollee/{id}/dependent)
    - POST
    - content-Type: application/json
    - Request Body: Dependent
    - Sample Request
         ```
                    {
                        "id":1,
                        "name":"testDependent",
                        "dob":"09-01-2010"
                    }
* [Update Enrollee Dependent](http://localhost:8080//enrollee/{id}/dependent/{did})
    - PUT
    - content-Type: application/json
    - Request Body: Dependent
    - Pass enrollee ID and dependent ID as path Parameter
    - Sample Request
         ```
                    {
                        "dob":"09-01-2011"
                    }

* [Delete Enrollee Dependent](http://localhost:8080//enrollee/{id}/dependent/{did})
        - DELETE
        - Just pass the id of enrollee and id of dependent as path parameter.
 


