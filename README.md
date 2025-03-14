#Car Management

Car management is a modern RESTful web service developed using Jakarta EE 11 preview.
It manages a collection of cars and provides CRUD functionality(Creat, Read, Update, Delete).
It also provides the ability to filter cars based on various attributes.

### Features
- **CRUD Operations**: Create, read, update, and delete cars.
- **Filtering**: Filter cars based on company, model, and year range.
- **Validation**: Data validation using Jakarta Bean Validation.
- **Error Handling**: Custom exception classes and ExceptionMappers for error handling.
- **Testing**: Unit tests for service and mapper classes.
- **Containerization**: Docker and Docker Compose for easy deployment.

### Technologies Used
- **Jakarta EE 11 Preview**
- **Jakarta Persistence**
- **Jakarta Bean Validation**
- **Jakarta Data**
- **Maven**
- **Docker**
- **PostgreSQL**

## Instructions for Building and Running the Application

### Prerequisites
- **Docker**: Ensure Docker is installed on your machine.
- **Docker Compose**: Ensure Docker Compose is installed on your machine.
- **Maven**: Ensure Maven is installed on your machine.

### Steps to Build and Run
1. **Clone the Repository**:
   ```sh
   git clone https://github.com/Oggeboii/JEE.git
   cd JEE
2. **Clone the Repository**:
   ```sh
   mvn package
3. **Build and Start Containers with Docker Compose**:
   ```sh
   docker-compose build
   docker-compose up
### Accessing the Application
The application will be accessible at http://localhost:8080.
