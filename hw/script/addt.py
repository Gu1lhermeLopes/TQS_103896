import requests

# Define the ticket data
ticket_data = {
    "origin": "City A",
    "destination": "City B",
    "departureDate": "2024-03-25",
    "departureTime": "08:00 AM",
    "arrivalDate": "2024-03-25",
    "arrivalTime": "12:00 PM",
    "price": 50.00
}

# Make a POST request to add the ticket
response = requests.post("http://localhost:8080/api/tickets/add", json=ticket_data)

# Check if the request was successful
if response.status_code == 200:
    print("Ticket added successfully.")
else:
    print("Failed to add ticket. Status code:", response.status_code)
    print("Response:", response.text)