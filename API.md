API Specs
=========

# Create an user
## Request
```
POST /users

{
    "email" : "paul@example.com",
    "password" : "6b3a55e0261b0304143f805a24924d0c1c44524821305f31d9277843b8a10f4e",
}
```
## Results
```
HTTP/1.1 201 Created
Content-Type: application/vnd.api+json

{
  "users": {
     "email":"paul@example.com"
  }
}
```
```
HTTP/1.1 409 Conflict
Content-Type: application/vnd.api+json

{
   "errors": {
      "title":"An user with email paul@example.com already exists"
   }
}
```
# Login
## Request
```
POST /login

{
    "email" : "paul@example.com",
    "password" : "6b3a55e0261b0304143f805a24924d0c1c44524821305f31d9277843b8a10f4e",
}
```
## Results
```
HTTP/1.1 200 OK
Content-Type: application/vnd.api+json

{
  "users": {
     "_id" : "548bf7d0e3bfc67d4d7c2cb6",
     "email":"paul@example.com"
  }
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/vnd.api+json

{
   "errors": {
      "title":"No user account for this email"
   }
}
```
```
HTTP/1.1 401 Unauthorized
Content-Type: application/vnd.api+json

{
   "errors": {
      "title":"Incorrect password"
   }
}
```
# Check if an email is already used
## Request
```
POST /email/paul@example.com

{
    "emails": {
       "email" : "paul@example.com",
       "state" : "registered"
    }
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/vnd.api+json

{
   "errors": {
      "title":"No user account associated to this email"
   }
}
```