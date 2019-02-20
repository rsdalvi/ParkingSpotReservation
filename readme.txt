
 An Restful Web Service for managing Parking Spot Reservation.
 
 ParkingSpot APIs
 
 1. GET /parkingSpots
	Get list of all parking spots.
 
 2. GET /parkingSpots/{id}
	Get details of given parking spot.
 
 3. GET /parkingSpots/near?lat={lat}&lon={lon}&rad={rad}
	Get nearby parking spot within given radius given in meter.
	
 4. POST /parkingSpots
	Create new parking spot.
 
 5. PUT /parkingSpots/{id}
	Update parking spot.
 
 6. DELETE /parkingSpots/{id}
	Delete parking spot by id.
 
 User APIs
 
 1. GET /users
	Get list of all users.
 
 2. GET /users/{id}
	Get details of given user.
 
 3. POST /users
	Create new user.
 
 4. PUT /users/{id}
	Update given user.
 
 5. DELETE /users/{id}
	Delete given user by id.
 
 Reservation APIs
 
 1. GET /users/{userId}/reservations
	Get list of all reservation done by given user.
 
 2. GET /users/{userId}/reservations/{id}
	Get details of given reservation.
 
 3. POST /users/{userId}/reservations
	Create new reservation for given user.
 
 4. PUT /users/{userId}/reservations/{id}
	Update given reservation.
 
 5. DELETE /users/{userId}/reservations/{id}
	Delete given reservation.
 
 6. GET /users/{userId}/reservations/{id}/cost
	Get the cost of given reservation.
 