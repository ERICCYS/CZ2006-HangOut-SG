# SkyForce Web Service API
Eric Ma - CZ2006 SkyForce

## Introduction
I will do the Introduction in the form of Q and A

`FE and BE`
- Q: How the frontend connect to the backend?

- A: Through RESTful API

`RESTful API`
- Q: What is RESTful API?

- A: (short answer) a form of API featuring V1.1 HTTP request including GET POST PUT DELETE

`FE call BE`
- Q: How the frontend call the backend?
- A: Through HTTP Request

`HTTP Request`
- Q: What is a HTTP Request?
- A: Include:
  1. Request Method
  2. Request URL
  3. Request Header
  4. Request Parameter
  5. Path Variable
  6. Request Body

`About this doc`
- Q: What this document will provide?
- A: HTTP Request for every function provided here.



### Customer Sign up (Add Customer)

Method: POST

URL: "http://localhost:9090/api/customer"

Request Header:
None

Request Param:
None

Path Variable:
None

Request Body:

```json
{
    "firstName" : "<firstName>",
    "lastName" : "<lastName>",
    "gender" : "<MALE/FEMALE>",
    "email": "<email>",
    "password": "<password>",
    "avatar": "<url>",
    "dob" : "<date>",
    "halaPreference": <true/false>,
    "vegPreference": <true/false>,
    "regionalPreference": "None"
}
```

Request Body Example:

```json
{
    "firstName" : "abcdefg",
    "lastName" : "ABCDEFG",
    "gender" : "FEMALE",
    "email": "fordemo@mail.com",
    "password": "Ma12345!",
    "avatar": "https://www.greencointoken.com/assets/images/avatar.png",
    "dob" : "2000-09-09",
    "halaPreference": false,
    "vegPreference": true,
    "regionalPreference": "None"
}
```


### Customer Sign in

Method: GET

URL: "http://localhost:9090/api/customer/signin"

Request Header:
None

Request Param:
```json
{
  "email": "<customer's email>",
  "password": "<customer's password>"
}
```

Path Variable:
None

Request Body:
None

### Customer Change Information


Method: PUT

URL: "http://localhost:9090/api/customer"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "customerId": "<customerId>"
}
```

Path Variable:
None

Request Body:
{
    "firstName" : "<firstName>",
    "lastName" : "<lastName>",
    "gender" : "<MALE/FEMALE>",
    "email": "<email>",
    "password": "<password>",
    "avatar": "<url>",
    "dob" : "<date>",
    "halaPreference": <true/false>,
    "vegPreference": <true/false>,
    "regionalPreference": "None"
}

Request Body Example:
{
    "firstName" : "abcdefg",
    "lastName" : "ABCDEFG",
    "gender" : "FEMALE",
    "email": "fordemo@mail.com",
    "password": "Ma12345!",
    "avatar": "https://www.greencointoken.com/assets/images/avatar.png",
    "dob" : "2000-09-09",
    "halaPreference": false,
    "vegPreference": true,
    "regionalPreference": "None"
}



### Customer Delete Account ()

Method: DELETE

URL: "http://localhost:9090/api/customer"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "customerId": "<customerId>"
}
```

Path Variable:
None

Request Body:
None

### Vendor Sign up (Add Vendor)

Method: POST

URL: "http://localhost:9090/api/vendor"

Request Header:
None

Request Param:
None

Path Variable:
None

Request Body:
```json
{
    "firstName" : "<firstName>",
    "lastName" : "<lastName>",
    "gender" : "<MALE/FEMALE>",
    "email": "<email>",
    "password": "<password>",
    "shops": []
}
```

Request Body Example:
```json
{
    "firstName" : "dcba",
    "lastName" : "DCBA",
    "gender" : "MALE",
    "email": "abcde@demomail.com",
    "password": "12qweASD!@#",
    "shops": []
}
```

### Vendor Sign In

Method: GET

URL: "http://localhost:9090/api/vendor/signin"

Request Header:
None

Request Param:
```json
{
  "email": "<customer's email>",
  "password": "<customer's password>"
}
```
Path Variable:
None

Request Body:
None

### Change Vendor Information

Method: PUT

URL: "http://localhost:9090/api/vendor"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "vendorId": "<vendorId>"
}
```

Path Variable:
None

Request Body:
```json
{
    "firstName" : "<firstName>",
    "lastName" : "<lastName>",
    "gender" : "<MALE/FEMALE>",
    "email": "<email>",
    "password": "<password>",
    "shops": []
}
```

Request Body Example:
```json
{
    "firstName" : "dcba",
    "lastName" : "DCBA",
    "gender" : "MALE",
    "email": "abcde@demomail.com",
    "password": "12qweASD!@#",
    "shops": []
}
```

### Delete Vendor

Method: DELETE

URL: "http://localhost:9090/api/vendor"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "vendorId": "<vendorId>"
}
```

Path Variable:
None

Request Body:
None

### Vendor Add Shop

Method: POSt

URL: "http://localhost:9090/api/vendor/shop"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "vendorId": "<vendorId>"
}
```

Path Variable:
None

Request Body:
```json
{
  "name": "<shopName>",
  "contactNumber": "<contactNumber>",
  "contactEmail": "<contactEmail>",
  "verified": <true/false>,
  "category": "<category>",
  "products": []
}
```

Request Body Example:
```json
{
  "name": "<KFC>",
  "contactNumber": "12345678",
  "contactEmail": "ntu@kfcemail.com",
  "verified": true,
  "category": "RESTAURANT",
  "products": []
}
```

### Vendor delete a shop

Method: DELETE

URL: "http://localhost:9090/api/vendor/shop"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "vendorId": "<vendorId>",
  "shopId": "<shopId>"
}
```

Path Variable:
None

Request Body:
None

### Get All Shops

Method: GET

URL: "http://localhost:9090/api/shops"

Request Header:
None

Request Param:
None

Path Variable:
None

Request Body:
None


### Get a particular Shop

Method: GET

URL: "http://localhost:9090/api/shop"

Request Header:
None

Request Param:
```json
{
  "shopId": "<shopId>"
}
```

Path Variable:
None

Request Body:
None

### Update a Shop

Method: PUT

URL: "http://localhost:9090/api/shop"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "shopId": "<shopId>"
}
```
Path Variable:
None

Request Body:
```json
{
  "name": "<shopName>",
  "contactNumber": "<contactNumber>",
  "contactEmail": "<contactEmail>",
  "verified": <true/false>,
  "category": "<category>",
  "products": []
}
```

Request Body Example:
```json
{
  "name": "<KFC>",
  "contactNumber": "12345678",
  "contactEmail": "ntu@kfcemail.com",
  "verified": true,
  "category": "RESTAURANT",
  "products": []
}
```

### Add a product to a shop (Done By Vendor)

Method: POST

URL: "http://localhost:9090/api/shop/product"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "shopId": "<shopId>"
}
```

Path Variable:
None

Request Body:
```json
{
  "name": "<productName>",
  "description": "<productDescription>",
  "productImg": ["<imgUrl>", "<imgUrl>"]
}
```
Request Body Example:
```json
{
  "name": "Happy Meal",
  "description": "Enjoy ur happy meal",
  "productImg": ["https://www.eatthis.com/wp-content/uploads/2016/12/mcdonalds-fast-food-500x366.jpg", "https://www.eatthis.com/wp-content/uploads/2016/12/mcdonalds-fast-food-500x366.jpg"]
}
```


### Delete a product from a shop (Done By Vendor)

Method: DELETE

URL: "http://localhost:9090/api/shop/product"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "shopId": "<shopId>",
  "productId": "<productId>"
}
```

Path Variable:
None

Request Body:
None

### Customer Add a Plan

Method: POST

URL: "http://localhost:9090/api/customer/plan"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "customerId": "<customerId>"
}
```

Path Variable:
None

Request Body:
```json
{
  "name": "<planname>",
  "date": "<date>",
  "planItems": []
}
```
Request Body Example:
```json
{
  "name": "Today Plan",
  "date": "2019-03-12",
  "planItems": []
}
```

To be continued ... 
