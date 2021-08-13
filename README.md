# TTA
TraceTogether Alternative made using Java, C++, Android and MongoDB

Project members are Alrik, Xuyuan, Jingda, Cheeheng



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
> Coordinate(Long epoch, double latitude, double longtitude, UUID clientId, ObjectId id     
