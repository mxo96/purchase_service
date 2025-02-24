# Purchase Service

## Problem

Build a RESTful API endpoint that will accept requests with a body like the one shown below
and return pricing information for the new order

### Example

```javascript
Request body

{
   "order":{
      "items":[
         {
            "product_id":1,
            "quantity":1
         },
         {
            "product_id":2,
            "quantity":5
         },
         {
            "product_id":3,
            "quantity":1
         }
      ]
   }
}

Response

{
   "order_id":3412433,
   "order_price":12.50,
   "order_vat":1.25,
   "items":[
      {
         "product_id":1,
         "quantity":1,
         “price”":2.00,
         "vat":0.20
      },
      {
         "product_id":2,
         "quantity":5,
         “price”":“7.50”,
         "vat":0.75
      },
      {
         "product_id":3,
         "quantity":1,
         “price”":3.00,
         "vat":0.30
      }
   ]
}
```

## The Application

The application use a `postgres` DB to store all the product and order info.

In the root of the project there is a `db` folder with two scripts:

- `01_schema.sql`: initialize the db's schema
- `02_data.sql`: initialize some products example

It's a Spring Boot application that expose an `/api/v1/createOrder` POST endpoint that can be called with
the following sample body request:

```javascript
{
   "order":{
      "items":[
         {
            "product_id":1,
            "quantity":7
         },
         {
            "product_id":2,
            "quantity":5
         },
         {
            "product_id":3,
            "quantity":1
         }
      ]
   }
}
```

## How to run

There is a docker-compose.yml file used to:

- build the application
- start the postgres DB
- mount the db scripts
- mount the build.sh and test.sh scripts

#### Commands

- `docker compose up -d ` to run the docker compose in detached mode
- `docker run mytest ./scripts/build.sh`
- `docker run mytest ./scripts/test.sh`