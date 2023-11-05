# Postal Code to GPS coordinate converter

This service offeres an endpoint the returns the GPS coordinates for a given Austrian postal code. 

Example URL for the endpoint for the postal code 2443:

```
http://localhost:8080/plz2gps/2443
```

Data is read statically from a file, which containes the postal code to GPS coordinate data for Austrian postal codes.

To build a docker image from the sourcecode use:

```
docker build . -t java-online-training/plz2gps-service
```

To run the image use:

```
docker run -p8080:8080 java-online-training/plz2gps-service
```

 


