# <h1> Call Monitor</h1>

The purpose of the program is to monitor calling records data from a phone device.
The module contain several services to receive the data from the devices, validate and save them in a db, and expose abilities to query them and update them.
The service accepts JSON requests and responds with a JSON response.

<h1> How to use this application? </h1>

Clone the code and run it.
Go to your web browser and get into `$ http://localhost:8080/callmonitor`

______________________________________________________________________________________________________________________________  

<h3> Receiving Data </h3>

`$ http://localhost:8080/callmonitor`
<br>
<br>
and add json: <br>
{<br>
"time":"Date of the phone call record", <br>
"callType":"Incoming / Outgoing", <br>
"duration":"How much time the phone call took in seconds", <br>
"phoneNumber":"what is the other phone number we called or received a call from" <br>
}<br>
<br>
* If the phone number exists in the blacklist the program will through an exception because it means that from some reason we did got a call from a blocked number.
* If the phone number exists in the contact list then the field "savedContact" will be true.

______________________________________________________________________________________________________________________________  

<h3> To get records of a specific phone number:  </h3>

`$ http://localhost:8080/callmonitor/callrecords/number/{number}`
______________________________________________________________________________________________________________________________  

<h3> To get  all records that are bigger than some duration </h3>

`$ http://localhost:8080/callmonitor/callrecords/duration/{duration}`
______________________________________________________________________________________________________________________________  

<h3> To update new number </h3>

`$ http://localhost:8080/callmonitor/{phoneNumber}`
<br>
<br>
and add json: <br>
{ <br>
"newNumber":"New number to update" <br>
}
