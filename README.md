# Fetch_Receipt_Processor
# Receipt Rewards Service

This project is a Spring Boot web service that processes receipts and calculates reward points. Data is stored in memory (i.e., it is not persisted across application restarts).

The project includes a pre-built JAR file (generated in the `target` directory) and a Dockerfile to containerize the application.

## API Endpoints

- **POST /receipts/process**  
  Accepts a receipt JSON payload and returns a unique receipt ID.

- **GET /receipts/{id}/points**  
  Returns the calculated reward points for the receipt identified by `{id}`.

### Example Request Payload for Processing a Receipt

```json
{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    { "shortDescription": "Gatorade", "price": "2.25" },
    { "shortDescription": "Gatorade", "price": "2.25" },
    { "shortDescription": "Gatorade", "price": "2.25" },
    { "shortDescription": "Gatorade", "price": "2.25" }
  ],
  "total": "9.00"
}
```

## Repository Contents

- **`target/receiptrewards-0.0.1-SNAPSHOT.jar`**  
  The executable JAR file built by Maven.

- **`Dockerfile`**  
  Docker configuration to build and run the application container.

## Running the Application with Docker

Follow these steps to build the Docker image and run the container:

### Step 1: Clone the Repository

If you haven't already, clone the repository and navigate to the project folder:

```bash
git clone https://github.com/JPranathiReddy/Fetch_Receipt_Processor.git
cd receiptrewards
```

### Step 2: Build the Docker Image

From the project root (where the Dockerfile is located), run:

```bash
docker build -t receiptrewards-app .
```

### Step 3: Run the Docker Container

Once the Docker image is built, start the container:

```bash
docker run -p 8080:8080 receiptrewards-app
```

The application will now be accessible at:
http://localhost:8080

### Step 4: Test API Endpoints using curl or Postman.

### Process a Receipt

Use the following `curl` command to send a `POST` request to process a receipt:

```bash
curl -X POST http://localhost:8080/receipts/process \
     -H "Content-Type: application/json" \
     -d '{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    { "shortDescription": "Gatorade", "price": "2.25" },
    { "shortDescription": "Gatorade", "price": "2.25" },
    { "shortDescription": "Gatorade", "price": "2.25" },
    { "shortDescription": "Gatorade", "price": "2.25" }
  ],
  "total": "9.00"
}
```
## Response Example:

```json
{
  "id": "some-unique-receipt-id"
}
```
## Get Reward Points for a Receipt

Replace `{id}` with the ID returned from the previous step:

```bash
curl -X GET http://localhost:8080/receipts/{id}/points
```
