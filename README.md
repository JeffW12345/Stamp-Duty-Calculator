# Java Stamp Duty Calculator

Web-based program that runs on localhost.

If you enter the value of a property, it will tell you the amount of LBBT (Scottish stamp duty) that is due on it.

Runs on localhost: http://127.0.0.1:8080/

Also contains a dynamic GET endpoint that returns JSON, accessable using taxName and propertyValue parameters: /search/:propertyValue/:taxType

For example http://127.0.0.1:8080/search?propertyValue=1000000&taxType=lbbt results in:

{
    "taxName": "lbbt",
    "taxAmount": "78,350.00",
    "propertyValue": "1,000,000.00"
}
