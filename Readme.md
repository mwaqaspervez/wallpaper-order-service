## Wallpaper Service

To build the project make sure you are in the root directory of the project that is named as wallpaper-order-service


### Installation

NOTE: Docker is required to run the below commands.

Build the application using docker by running the below command.

````docker-compose up --build -d````

Once the above process completes, Your api server will be running on port ``8080``

### Endpoints

#### Get the list of cryptocurrencies use:

``curl --location --request GET 'http://localhost:8080/api/orders' \
--header 'Content-Type: application/json' \
``

#### Get a single cryptocurrency use:

`` curl --location --request GET 'http://localhost:8080/api/orders/cubes' ``


#### Get highest normalized cryptocurrency:


`` curl --location --request GET 'http://localhost:8080/api/pi/orders/duplicates' ``
