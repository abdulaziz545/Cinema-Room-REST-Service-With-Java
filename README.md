# Cinema-Room-REST-Service-With-Java
## Project Overview
This project is implemented as a multi-stage RESTful service using Spring Boot. The theater consists of 9 rows and 9 seats per row, offering users an interactive API experience for booking and managing movie tickets.

## Stages Breakdown
### Stage 1: View Available Seats
- Implemented the /seats endpoint.
- Returns a JSON response showing all available seast with their respective prices.
- Prices are determined based on row number:
    - Rows 1-4: $10
    - Rows 5-9: $8

### Stage 2: Purchase Tickets
- Added the /purchase endpoint with POST request handling.
- User can buy a ticket by specifying a row and column.
- On success, a response with seat detials and price is returned.
- Includes error handling:
  - Seat already purchased: 400 error
  - Invalid seat coordinates: 400 error

### Stage 3: Refund tickets
- Enhanced the /purchase endpoint to return a unique token for each transaction.
- Added the /return endpoint to refund a ticket using the provided token.
- Successfully refunded seats become available again.

### Stage 4: Manager Statistics
- Introduced the /stats endpoint (GET) to show:
  - Current income
  - Number of available seats
  - Number of purchased tickets
  - This endpoint is password-protected using a query parameter "password"

## Technologies Used
-  Java
-  Spring Boot
-  JSON-based API
-  UUIDs for secure ticket management
  





