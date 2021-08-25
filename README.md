# TTA
[![Java](https://img.shields.io/badge/Java-E87D25?style=flat-square&logo=java&logoColor=white)](https://www.java.com/en/)
[![MongoDB](https://img.shields.io/badge/MongoDB-3EA145?style=flat-square&logo=mongodb&logoColor=white)](https://www.mongodb.com)
[![SocketIO](https://img.shields.io/badge/SocketIO-87C23C?style=flat-square&logo=socketdotio&logoColor=white)](https://socket.io)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white)](https://www.apple.com/sg/ios/ios-14)
[![Ubuntu](https://img.shields.io/badge/Ubuntu-DE4815?style=flat-square&logo=ubuntu&logoColor=white)](https://ubuntu.com)
[![Google Cloud](https://img.shields.io/badge/Google_Cloud-D94830?style=flat-square&logo=googlecloud&logoColor=white)](https://cloud.google.com)

Alternative system to TraceTogether. 

Project members are Alrik, Xuyuan, Jingda, Cheeheng

Project Report is available at:
    <i>https://github.com/txuyuan/TTADocs/blob/main/Report.pdf</i>


<h3><a href="https://github.com/txuyuan/TTALink" target="blank">TTALink</a></h3>

> Datalink for TTA (Locations &amp; Warnings) | Server-side receiver and preliminary data processing | Interface for integration into client-side
> 
> Module managed by Xuyuan (txuyuan)
> 


<h3><a href="https://github.com/JDAED/TTAClient" target="blank">TTAClient</a></h3>

> Android-based client for TTA system. Includes warnings for potentially found matches and a map displaying available location history
> 
> Module managed by Jingda (JDAED)
>


<h3><a href="https://github.com/AKEevee/TTAServer" target="blank">TTAServer</a></h3>

> C++ based server-side program to process data on the server side
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
