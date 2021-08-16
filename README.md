# TTA
TraceTogether Alternative made using Java, C++, Android and MongoDB.

Project members are Alrik, Xuyuan, Jingda, Cheeheng

Project Report is available at:
    https://github.com/txuyuan/TTADocs/blob/main/TTA%20Report.pdf


### TTALink
> Datalink for TTA (Locations &amp; Warnings) | Server-side receiver and preliminary data processing | Interface for integration into client-side
> 
> https://github.com/txuyuan/TTALink
> 
> Module managed by Xuyuan (txuyuan)
>

### TTAClient
> Android-based client for TTA system. Includes warnings for potentially found matches and a map displaying available location history
> 
> https://github.com/JDAED/TTAClient
> 
> Module managed by Jingda (JDAED)
>

### TTAServer
> C++ based server-side program to process data on the server side
> 
> https://github.com/AKEevee/TTAServer
> 
> Module managed by Alrik (AKEevee)
>  


<br></br>
## TTALink Docs

### Client
me.xuyuan.client.Client

Client-side interface

Constructors:
> Client() - Default constructor, uses server address on (13/8/21)
> 
> Client(String address) - Specify server IP address

Methods:
> Send(List<Coordinate)
>> Takes input list as Coordinate data to send to server
>> 
>> Returns List<Coordinate> of found matches
>> 
>> Throws IOException - Exceptions in database/socket operations
>> 
  
### Coordinate
me.xuyuan.data.Coordinate

implements Serializable
  
Constructors:
> Coordinate(Long epoch, double latitude, double longtitude, UUID clientId, ObjectId id)
>> Input | Long epoch -> Unix epoch time (in seconds)
>> 
>> Input | UUID clientId -> Unique ID of client
>> 
>> Input | ObjectId id -> MongoDB Object (Database identifier). Clientside usage - Generate new random ObjectID()
>> 
> Coordinate(int year, int month, int day, int hour, int minute, double latitude, double longtitude, ObjectId id)
>> Input | Time values as ints (starting from 1). (E.g. 14th Aug 2021 0900hours = 2021, 8, 14, 9, 0)
>> 
>> Input | ObjectId id -> MongoDB Object (Database identifier). Clientside usage - Generate new random ObjectID()
>> 

Methods:
> getDocument()
>> Returns MongoDB Document with data values of Coordinate
> getCoordinate(Document doc)
>> Converts MongoDB Document into Coordinate
>> 
>> Ensure Document values are correct, or Coordinate will be corruptede and Exception will be printed
>> 
> toString()
>> Returns string with Coordinate values
>> 
>> Format: "ObjectId: " + objectId + ", Time: " + time + ", Latitude: " + lat + ", Longtitude: " + longt + ", ClientId: " + clientID
>> 
> print()
>> Prints out results of toString()
>> 
> getLatitude()
>> Returns | double
>> 
> getLongtitude()
>> Returns | double
>> 
> getEpoch()
>> Returns | long
>> 
> getClientId()
>> Returns | UUID (java.util.UUID)
>>
> getObjectId()
>> Returns | ObjectId (org.bson.types.ObjectId)
>>
> getYear()
>> Returns | int
>>
> getMonth()
>> Returns | int
>>
> getDay()
>> Returns | int
>>
> getHour()
>> Returns | int
>>
> getMinute()
>> Returns | int
>> 
