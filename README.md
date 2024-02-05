# ITX Challenge - Retail Endpoint
====================

## Service Execution
--------------------------------

### Step 1:
Open DockerDesktop and wait for the engine to be running.

### Step 2:
In one terminal, run `docker-compose up` to start the containers, then, in a separate terminal, run the service with:
- Linux: `./gradlew bootRun`
- Windows: `gradlew bootRun`

You should be able to access the Swagger at `http://localhost:8080/swagger-ui/index.html#/` and the healthcheck endpoint at `http://localhost:8080/health`

## Postman
A collection of two endpoints are provided. 

### Get

This endpoint will return a page of items and has the following optional parameters:
- page: The page number offset (Default 0)
- pageSize: The size of each page (maximum is 100, default 20)
- name: The name to search for
- salesMin: The minimum sales to search for (inclusive)
- salesMax: The maximum sales to search for (exclusive)
- stockMin: The minimum stock to search for (inclusive and includes all stock if not specified)
- stockMax: The maximum stock to search for (exclusive and includes all stock if not specified)
- stockType: The type of stock to search for (SMALL, MEDIUM, LARGE)
- order: The order (may be more) to sort.

Sorting can be done by the following:
- ID
- NAME
- SALES
- STOCK

Stock will include all of the stock and you may add the `_INV` suffix to invert the sorting criteria. 

It will return a list of items that match the criteria (if it were to exist) and sorted accordingly.

### New Item

This endpoint will create a new item. It has been provided to easily add new items to the database without having to deal with any CLI and import SQLs. However, one is still provided if needed.



Due to time limitations, unit tests are not provided, however, Postman calls work out-of-the-box.
Any parameters to customize can be found in `application.yml`. A smoke test is provided to be used with Apache JMeter.

Known issue: stockMin criteria is very wonky for some reason, I have been unable to resolve it in the specified timeframe