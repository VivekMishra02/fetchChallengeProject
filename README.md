Running Instructions:
1. The APIs are made using springboot project and I have used openapi dependency to generate model classes
 
2. Clone project from https://github.com/VivekMishra02/fetchChallengeProject.git
3. You should be having atleast java 17 setup and maven
4. Please find postman collections in attachment and set port in endpoint as per your system.
5. Docker file has docker setup
 

Run Below commands from root directory of project to run the application
1. mvn clean install (you can alternatively use idea IDE to run this part)
 
2. docker build -t  fetchApi .
 
3. docker images
 
4. docker run -p 8000:8080 fetchApi
 

Use Postman to hit the running service
Post EndPoint
 
Get endpoint:
 
