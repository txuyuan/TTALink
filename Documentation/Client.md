## Client
*me.xuyuan.client.Client*

---

Client-side interface

***Constructors***

* Client() - Default constructor, uses server address on (13/8/21)
* Client(String address) - Specify server IP address

***Methods***

* Send(List<Coordinate)
  * Takes input list as Coordinate data to send to server 
  * Returns List<Coordinate> of found matches
  * Throws IOException - Exceptions in database/socket operations
