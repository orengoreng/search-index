# Search Index API
This application is a solution developed for the CSG Coding Challenge. Its primary functionality is to search for specific keywords within a text file and filter out the relevant results.

The code is designed with a focus on flexibility and maintainability, ensuring that it can be easily extended or adapted to accommodate future business requirements.

## Built
* [![springboot][springboot.js]][springboot-url]
* [![docker][docker.js]][docker-url]

## Getting Started
This application exposes two RESTful API endpoints to support its core functionality: uploading a text file and searching/indexing its contents.

1. [Upload File](http://localhost:8080/api/file/upload)
   
   Description: Accepts a text file upload and stores its contents for further processing.


      Endpoint: POST /upload
      Request:
          Headers:
          Content-Type: multipart/form-data
          Body:
              file: The text file to be uploaded.
      
      Response:

          200 OK – File uploaded successfully.
          400 Bad Request – Invalid or missing file.
          500 Internal Server Error – Server-side error during file processing.

2. [Search Index](http://localhost:8080/api/search)
   
   Description:  Performs a keyword search against the previously uploaded text content and returns the filtered results.

        Request:

          Query Parameters:
                keyword: The term to search for within the uploaded text.
                maxchar: To apply result filter by character length
       Response:

         200 OK – Returns a JSON array of matching lines or entries.
         400 Bad Request – Missing or malformed query.
         404 Not Found – No file uploaded or no results found.
         500 Internal Server Error – Error during search operation.

### Prequisites

To run this application in a containerized environment, you need to have the following tools installed:
#### 1. Git
Clone the Repository
Open your terminal or command prompt and run:

```sh
git clone https://github.com/orengoreng/search-index.git
```

Navigate to the Project Directory

```sh
cd search-index
```

#### 2. Docker
Docker is required to build and run the application in a container.
 

📥 Install Docker


Follow the official Docker installation guide for your operating system:

Windows & macOS:
     https://docs.docker.com/desktop/install/

#### 3. Maven

Follow the official Docker installation guide for your operating system:

https://maven.apache.org/install.html


### Installation
##### Build the Docker Image
Build project by maven.

```sh
mvn clean -DskipTests package 
```

Run the following command to build the Docker image, refers to the current directory (where the Dockerfile is):
      
```sh
docker build -f Dockerfile -t search-index:latest .
```

##### Run the Docker Container

```sh
docker run --name search-index -v /tmp:/tmp -d -p 8080:8080 search-index
```

### Usage

Confirm the application is up and running.

View and test all available API endpoints in Swagger UI interface.

[Swagger UI](http://localhost:8080/swagger-ui/index.html#)

[![Swagger UI][swagger-ui-screenshot]](http://localhost:8080/swagger-ui/index.html#)


<!-- ROADMAP -->
## Roadmap

- [ ] SearchService class interface extension for business requirement


<!-- MARKDOWN LINKS & IMAGES -->
[springboot.js]: https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white
[springboot-url]: https://spring.io/projects/spring-boot/
[docker.js]: https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white
[docker-url]: https://www.docker.com
[swagger-ui-screenshot]: images/swagger-ui-screenshot.png
