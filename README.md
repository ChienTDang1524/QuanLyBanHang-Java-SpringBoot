cat > README.md << 'EOF'
#  Product Management System

A product management system built with Spring Boot, designed for managing products, categories, brands, and inventory status.

##  Project Description

This system provides a RESTful API for managing product information including categories, subcategories, brands, and product status. It uses Spring Boot framework with JPA for database operations and follows MVC architecture pattern.

## Technologies Used

- **Spring Boot** - Main framework
- **Spring Data JPA** - Database operations
- **Gradle** - Build tool and dependency management
- **Java** - Programming language
- **SQL Database** - Data storage
- **RESTful API** - Communication protocol

##  Project Structure
```
test01/
├── src/
│   ├── main/
│   │   ├── java/org/example/test01/
│   │   │   ├── controller/          # API Controllers
│   │   │   │   └── ProductController.java
│   │   │   ├── entity/              # Database Entities
│   │   │   │   ├── Product.java
│   │   │   │   ├── Category.java
│   │   │   │   ├── SubCategory.java
│   │   │   │   ├── Brand.java
│   │   │   │   └── Status.java
│   │   │   ├── repository/          # Data Access Layer
│   │   │   │   ├── ProductRepository.java
│   │   │   │   ├── CategoryRepository.java
│   │   │   │   ├── SubCategoryRepository.java
│   │   │   │   ├── BrandRepository.java
│   │   │   │   └── StatusRepository.java
│   │   │   ├── service/             # Business Logic
│   │   │   │   ├── ProductService.java
│   │   │   │   └── impl/
│   │   │   │       └── ProductServiceImpl.java
│   │   │   ├── model/               # DTOs
│   │   │   │   ├── request/
│   │   │   │   │   └── ProductRequestDTO.java
│   │   │   │   ├── response/
│   │   │   │   │   ├── ProductResponseDTO.java
│   │   │   │   │   ├── CategoryResponseDTO.java
│   │   │   │   │   ├── SubCategoryResponseDTO.java
│   │   │   │   │   └── StatusReponseDTO.java
│   │   │   │   └── exception/
│   │   │   │       └── ResourceNotFoundException.java
│   │   │   └── Test01Application.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   └── index.html
│   │       └── templates/
│   └── test/
│       └── java/org/example/test01/
│           └── Test01ApplicationTests.java
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── HELP.md
```

##  Features

- Product CRUD operations
- Category and subcategory management
- Brand management
- Product status tracking
- RESTful API endpoints
- Exception handling
- DTO pattern for request/response

##  Setup and Installation

### Prerequisites
- Java 17 or higher
- Gradle 7.x or higher
- Database (MySQL/PostgreSQL/H2)

### Installation Steps

1. Clone the repository
```bash
git clone <repository-url>
cd XuongDan/test01
```

2. Configure database connection in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project
```bash
./gradlew build
```

4. Run the application
```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

##  API Endpoints

### Product Management
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

## Architecture

The project follows a layered architecture:

1. **Controller Layer** - Handles HTTP requests and responses
2. **Service Layer** - Contains business logic
3. **Repository Layer** - Data access and persistence
4. **Entity Layer** - Database models
5. **DTO Layer** - Data transfer objects for API communication

##  License

This project is licensed under the MIT License.
EOF
