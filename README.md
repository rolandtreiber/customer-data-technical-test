# Technical test solution

## The approach
Before starting to tackle the implementation, I came up with the following rough list of TODOs:
- Create test dataset using chatgpt ✅
- Add test signatures for the following:
  - CustomerDataImportService - CSV file was read and parsed ✅
  - Customer data imported into the DB ✅
  - API returns the correct customer data ✅
  - Invalid Customer Ref returns an error ✅
- Create the CustomerDataImportService interface and define method signatures ✅
- Create the DB schema ✅
- Implement the CSV import functionality ✅
- Make the CustomerDataImportService tests pass ✅
- Implement the [GET]/customer-data/{customerRef} API endpoint ✅
- Test if all tests pass ✅
- Add test for saving customer record through the API ✅
- Add [POST]/customer-data endpoint to save a customer ✅
- Implement the test to read all the records from file and save them through the API ✅

I then followed through the blueprint to complete all requirements.

The CustomerService is responsible for reading and handling customer data as well as the conversion between the Entity and DAO.\
The controller exposes 2 endpoints to either read or save customer records. There are no update and delete endpoints available as they were not part of the requirements.

### Testing
Either by running the automated tests or using postman.
The postman collection is included (Customers.postman_collection.json)

### Endpoints
- [GET] /customers/{customerRef}
  - Sample Response:
  ```json
  {
  "customerRef": "99",
  "customerName": "Richard Smith",
  "addressLine1": "55 Test Road",
  "addressLine2": "Building A",
  "town": "Testington",
  "county": "Testershire",
  "country": "UK",
  "postcode": "PP01 5GT"
  }
  ```
- [POST] /customers
  - Request Payload (JSON):
  ```json
  {
    "customerRef": "99",
    "customerName": "Richard Smith",
    "addressLine1": "55 Test Road",
    "addressLine2": "Building A",
    "town": "Testington",
    "county": "Testershire",
    "country": "UK",
    "postcode": "PP01 5GT"
  }
  ```

Author: Roland Treiber