### Get Customer by customer id

#### Function call
```java
getCustomer(1l);
```

#### Function

```java
public void getCustomer(Long customerId) {
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "customer";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("customerId", customerId.toString());
    Request request = new Request.Builder().url(httpBuilder.build()).build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                try {
                    JSONObject customer = new JSONObject(myResponse);
                    // Deal with this object, below is an example
                    textView.setText(customer.toString());

                } catch (JSONException e) {
                    textView.setText("Error");
                }
            }
        }
    });
}
```



### Get Vendor by vendor id

#### Function call
```java
getVendor(1l);
```

#### Function

```java
public void getVendor(Long vendorId) {
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "vendor";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("vendorId", vendorId.toString());
    Request request = new Request.Builder().url(httpBuilder.build()).build();


    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                try {
                    JSONObject vendor = new JSONObject(myResponse);
                    textView.setText(vendor.toString());

                } catch (JSONException e) {
                    textView.setText("Error");
                }
            }
        }
    });
}
```


### Get All shops

#### Function call
```java
getAllShops();
```

#### Function

```java
public void getAllShops() {
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "shops";
    Request request = new Request.Builder().url(url).build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                try {
                    JSONArray object = new JSONArray(myResponse);
                    textView.setText("Shops are " + object);
                } catch (JSONException e) {
                    textView.setText("Error");
                }
            }
        }
    });
}
```

### Get Shop by shop id

#### Function call
```java
getShopByShopId(1l);
```

#### Function

```java
public void getShopByShopId(final Long shopId) {
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "shop";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("shopId", shopId.toString());

    Request request = new Request.Builder().url(httpBuilder.build()).build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                System.out.println(myResponse);
                try {
                    JSONObject object = new JSONObject(myResponse);

                    textView.setText("Shop with id = " + shopId + " is " + object);

                } catch (JSONException e) {
                    textView.setText("Error");
                }
            }
        }
    });
}
```

### Get Customer Plan by vendor id

#### Function call
```java
getCustomerPlan(1l);
```

#### Function

```java
public void getCustomerPlan(Long customerId) {
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "customer";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("customerId", customerId.toString());
    Request request = new Request.Builder().url(httpBuilder.build()).build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                System.out.println(myResponse);
                try {
                    JSONObject customer = new JSONObject(myResponse);
                    JSONArray plans = (JSONArray) customer.get("plans");

                    // Deal with this object, below is an example
                    textView.setText(plans.toString());

                } catch (JSONException e) {
                    textView.setText("Error");
                }
            }
        }
    });
}
```

### Sign in Customer given email and password (Similar to sign in as vendor)

#### Function call
```java
signInCustomer("MAXIAO@email.com", "maxiao");
```

#### Function

```java
public void signInCustomer(String email, String password) {
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "customer/signin";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("email", email);
    httpBuilder.addQueryParameter("password", password);
    Request request = new Request.Builder().url(httpBuilder.build()).build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                System.out.println(myResponse);
                accessToken = myResponse;
                textView.setText("Customer Access Token is " + myResponse);

                // Able to get the access token.
            }
        }
    });
}
```

#### here is function of sign in Vendor

```java
public void signInVendor(String email, String password) {
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "vendor/signin";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("email", email);
    httpBuilder.addQueryParameter("password", password);
    Request request = new Request.Builder().url(httpBuilder.build()).build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                System.out.println(myResponse);
                accessToken = myResponse;
                textView.setText("Vendor Access Token is " + myResponse);
                // Able to get the access token.
            }
        }
    });
}
```

### Sign up Customer given customer related info

#### Function call
```java
// Example
JSONObject newCustomer = new JSONObject();
try {
   newCustomer.put("firstName", "Eric");
   newCustomer.put("lastName", "Ma");
   newCustomer.put("gender", "MALE");
   newCustomer.put("email", "MAXIAO@email.com");
   newCustomer.put("password", "maxiao");
   newCustomer.put("avatar", "https://cdn3.iconfinder.com/data/icons/vector-icons-6/96/256-512.png");
   newCustomer.put("dob", "1999-09-09");
   newCustomer.put("halaPreference", "false");
   newCustomer.put("vegPreference", "false");
   newCustomer.put("regionalPreference", "None");
} catch(JSONException e){
   e.printStackTrace();
}
signUpCustomer(newCustomer.toString());
```

#### Function

```java
public void signUpCustomer(String newCustomer) {
    RequestBody body = RequestBody.create(JSON, newCustomer);
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "customer";
    Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                textView.setText("Customer Posted Successfully, here is the access token " + myResponse);
            }
        }
    });
}
```

#### Here is an example of sign up vendor
```java
public void signUpVendor(String newVendor) {
    RequestBody body = RequestBody.create(JSON, newVendor);
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "vendor";
    Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                textView.setText("Vendor Posted Successfully, here is the access token " + myResponse);
            }
        }
    });
}
```

### Update Customer info

#### Function call
```java
JSONObject updatedCustomer = new JSONObject();
try {
   updatedCustomer.put("firstName", "Eric");
   updatedCustomer.put("lastName", "Maaaaaa");
   updatedCustomer.put("gender", "MALE");
   updatedCustomer.put("email", "MAXIAO@email.com");
   updatedCustomer.put("password", "maxiao");
   updatedCustomer.put("avatar", "https://cdn3.iconfinder.com/data/icons/vector-icons-6/96/256-512.png");
   updatedCustomer.put("dob", "1989-09-09");
   updatedCustomer.put("halaPreference", "true");
   updatedCustomer.put("vegPreference", "false");
   updatedCustomer.put("regionalPreference", "None");
} catch (JSONException e) {
   e.printStackTrace();
}
// This is supposed to be got from the context
Long customerId = 3l;
updateCustomerInfo(customerId, updatedCustomer.toString());

```

#### Function

```java
public void updateCustomerInfo(Long customerId, String updatedCustomer) {
    RequestBody body = RequestBody.create(JSON, updatedCustomer);
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "customer";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("customerId", customerId.toString());
    Request request = new Request.Builder()
            .url(httpBuilder.build())
            .put(body)
            .addHeader("Authorization", accessToken)
            .build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                textView.setText("Customer Updated Successfully, here is the new customer " + myResponse);
            }
        }
    });
}
```

### Vendor add shop

#### Function call
```java
JSONObject newShop = new JSONObject();
try {
   newShop.put("name", "LongJohn");
   newShop.put("contactNumber", "10293847");
   newShop.put("contactEmail", "slivers@ntumail.com");
   newShop.put("category", "RESTAURANT");
   newShop.put("location", "NTU");
   JSONArray carParks = new JSONArray();
   carParks.put("HLM");
   newShop.put("carParkNumbers", carParks);
} catch(JSONException e){
   e.printStackTrace();
}
vendorAddShop(1l, newShop.toString());
```

#### Function

```java
public void vendorAddShop(Long vendorId, String newShop) {
    RequestBody body = RequestBody.create(JSON, newShop);
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "vendor/shop";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("vendorId", vendorId.toString());
    Request request = new Request.Builder()
            .url(httpBuilder.build())
            .post(body)
            .addHeader("Authorization", vendorAT)
            .build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                textView.setText("Vendor add shop Successfully, here is the new vendor info " + myResponse);
            }
        }
    });
}
```

### Customer add reservation

#### Function call
```java
JSONObject newReservation = new JSONObject();
try {
   newReservation.put("shopId", "3");
   newReservation.put("arrivalTime", "2019-03-30 11:50:00");
} catch(JSONException e){
   e.printStackTrace();
}
customerMakeReservation(3l, 3l, newReservation.toString());
```

#### Function

```java
public void customerMakeReservation(Long customerId, Long shopId, String newReservation) {
    RequestBody body = RequestBody.create(JSON, newReservation);
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "reservation";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("customerId", customerId.toString());
    httpBuilder.addQueryParameter("shopId", shopId.toString());
    Request request = new Request.Builder()
            .url(httpBuilder.build())
            .post(body)
            .addHeader("Authorization", accessToken)
            .build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                textView.setText("Customer add reservation Successfully, here is the new reservation " + myResponse);
            }
        }
    });
}
```

### Vendor get all related reservations

#### Function call
```java
vendorGetAllReservations(1l);
```

#### Function

```java
public void vendorGetAllReservations(Long vendorId) {
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "reservation-vendor";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("vendorId", vendorId.toString());
    Request request = new Request.Builder().url(httpBuilder.build()).build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                textView.setText("Vendor now have reservation " + myResponse);
                // Able to get the access token.
            }
        }
    });
}
```

### Customer get all related reservations

#### Function call
```java
customerGetAllReservation(3l);
```

#### Function

```java
public void customerGetAllReservation(Long customerId) {
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "reservation-customer";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("customerId", customerId.toString());
    Request request = new Request.Builder().url(httpBuilder.build()).build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                textView.setText("Customer now have reservation " + myResponse);
                // Able to get the access token.
            }
        }
    });
}
```

### Customer add plan

#### Function call
```java
JSONObject newPlan = new JSONObject();
try {
    newPlan.put("name", "test Plan");
    newPlan.put("date", "2019-03-30");
    JSONArray planItems = new JSONArray();
    // put some items here
    newPlan.put("planItems", planItems);
} catch(JSONException e){
    e.printStackTrace();
}
customerCreatePlan(3l, newPlan.toString());
```

#### Function

```java
public void customerCreatePlan(Long customerId, String newPlan) {
    RequestBody body = RequestBody.create(JSON, newPlan);
    OkHttpClient client = new OkHttpClient();
    String url = baseUrl + "customer/plan";
    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
    httpBuilder.addQueryParameter("customerId", customerId.toString());
    Request request = new Request.Builder()
            .url(httpBuilder.build())
            .post(body)
            .addHeader("Authorization", accessToken)
            .build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String myResponse = response.body().string();
                textView.setText("Customer add plan Successfully, here is the new customer Info " + myResponse);
            }
        }
    });
}
```
