## Coordinate

*me.xuyuan.data.Coordinate*

*implements Serializable*

---

Object model to unify Coordinate data format. Use on both client and server to interpret stored and transmitted data.

***Values***

* `Long epoch` - Unix Epoch time (in seconds)
* `UUID clientId` - Unique UUID of client (username)
* `ObjectId id` - MongoDB ObjectID, added to add more coherence on server-side. 
* `Double lat` - Latitude value of Coordinate
* `Double longt` - Longtitude value of Coordinate


***Constructors***

* Coordinate(Long epoch, double latitude, double longtitude, UUID clientId, ObjectId id)
  * Input | Long epoch -> Unix epoch time (in seconds)
  * Input | UUID clientId -> Unique ID of client
  * Input | ObjectId id -> MongoDB Object (Database identifier). Clientside usage - Generate new random ObjectID()
* Coordinate(int year, int month, int day, int hour, int minute, double latitude, double longtitude, ObjectId id)
  * Input | Time values as ints (starting from 1). (E.g. 14th Aug 2021 0900hours = 2021, 8, 14, 9, 0)
  * Input | ObjectId id -> MongoDB Object (Database identifier). Clientside usage - Generate new random ObjectID()


***Methods***

* `getDocument()`
  * Returns MongoDB Document with data values of Coordinate
* `getCoordinate(Document doc)`
  * Converts MongoDB Document into Coordinate (Ensure Document values are correct, or Coordinate will be corruptede and Exception will be printed) 
* `toString()`
  * Returns string with Coordinate values
  * Format: "ObjectId: " + objectId + ", Time: " + time + ", Latitude: " + lat + ", Longtitude: " + longt + ", ClientId: " + clientID
* `print()`
  * Prints out results of toString()
* `getLatitude()`
  * Returns | double
* `getLongtitude()`
  * Returns | double
* `getEpoch()`
  * Returns | long
* `getClientId()`
  * Returns | UUID (java.util.UUID)
* `getObjectId()`
  * Returns | ObjectId (org.bson.types.ObjectId)
* `getYear()`
  * Returns | int
* `getMonth()`
  * Returns | int
* `getDay()`
  * Returns | int
* `getHour()`
  * Returns | int
* `getMinute()`
  * Returns | int