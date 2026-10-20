# 🛍️ ShopSphere — Java E-Commerce Web Application

ShopSphere is a web-based e-commerce application developed using Java, JSP, Servlets, Maven, and MySQL. It provides a platform for users to explore products, manage their shopping cart, place orders, and manage their accounts through a simple web interface.

## ✨ Features

### 👤 User Features
- User Registration and Login
- Browse and Explore Products
- View Product Details
- Add Products to Cart
- Manage Wishlist
- Checkout and Order Management
- View Order Details and Order History
- Manage User Profile

### 🛠️ Admin Features
- Manage Products
- View and Manage Users
- Access Sales Information

## 💻 Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core application logic |
| JSP | Dynamic web pages |
| Servlets | Request handling and server-side processing |
| HTML & CSS | Web page structure and styling |
| MySQL | Database management |
| Maven | Dependency and build management |
| Apache Tomcat | Web application server |
| Git & GitHub | Version control and source code hosting |

## 📂 Project Structure

```text
ShopSphere/
├── database/
│   └── shopsphere.sql
├── src/
│   └── main/
│       └── webapp/
│           ├── admin/
│           ├── fragments/
│           ├── WEB-INF/
│           ├── index.jsp
│           ├── products.jsp
│           ├── cart.jsp
│           ├── checkout.jsp
│           ├── login.jsp
│           └── register.jsp
├── pom.xml
├── .gitignore
└── README.md
```

## ⚙️ Prerequisites

Before running the project, install:

- Java JDK
- Apache Maven
- Apache Tomcat
- MySQL Server
- MySQL Workbench (optional)
- An IDE such as Visual Studio Code or IntelliJ IDEA

## 🚀 Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/nehamulanihub/ShopSphere.git
```

### 2. Open the Project

```bash
cd ShopSphere
```

### 3. Set Up the Database

1. Start your MySQL server.
2. Open MySQL Workbench.
3. Create a database using the name expected by the application's database configuration.
4. Import the SQL file located at `database/shopsphere.sql`.
5. Update the database connection settings in the project with your local MySQL username, password, database name, and port.

**Note:** Keep your database credentials private. Do not upload passwords or other sensitive information to GitHub.

### 4. Build the Project

Run the following command in the project directory:

```bash
mvn clean package
```

This command builds the application and generates a WAR file inside the `target` directory.

### 5. Deploy on Apache Tomcat

Deploy the generated WAR file to your compatible Tomcat server and start Tomcat.

Open the application in your browser using the URL corresponding to your deployment configuration. For example:

```text
http://localhost:8080/ShopSphere/
```

## 🗄️ Database

The project includes an SQL dump at:

`database/shopsphere.sql`

It contains the database structure and sample data exported for project setup. Import it into your local MySQL environment before running the application.

## 🎯 Project Objective

The main objective of ShopSphere is to demonstrate the development of a Java-based e-commerce application using server-side technologies, database integration, and Maven-based project management.

This project provides practical experience with web application development, CRUD operations, database connectivity, user workflows, and software deployment.

## 🔮 Future Enhancements

- Online payment gateway integration
- Product search and advanced filtering
- Product reviews and ratings
- Improved security and input validation
- Responsive UI improvements
- Email notifications and order updates

## 👩‍💻 Developer

**Neha Mulani**

GitHub: [@nehamulanihub](https://github.com/nehamulanihub)

## 📄 License

This project is available for learning and educational purposes. Add a formal open-source license if you intend to distribute it under specific reuse permissions.
