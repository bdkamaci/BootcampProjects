# Hotel Management System

## Overview

The Hotel Management System is a Java application designed to streamline the management of hotels, reservations, and related operations. It provides functionality for managing hotels, rooms, pensions, reservations, seasons, and user accounts.

## Features

- **User Authentication**: Users can log in with their credentials to access the system.
- **Hotel Management**: Create, update, and delete hotel information such as name, address, contact details, and amenities.
- **Room Management**: Manage room details including type, capacity, availability, and amenities.
- **Pension Management**: Define pension types and their associated features.
- **Reservation Management**: Make and manage reservations, check room availability, and assign rooms to guests.
- **Season Management**: Define seasons for pricing and availability adjustments.
- **User Management**: Manage user accounts, roles, and permissions.

## Installation

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/bdkamaci/BootcampProjects/tree/main/TourismAgencySystem

2. Open the project in your preferred Java IDE.
3. Make sure you have the necessary dependencies configured.
4. Run the Main class to start the application.

## Usage

1. Launch the application by running the Main class.
2. Log in using your username and password.
3. Navigate through the different functionalities provided by the application using the menu or interface components.
4. Create, update, or delete hotel information, rooms, pensions, reservations, seasons, and user accounts as needed.

## Design Patterns Used

### Singleton Design Pattern
The Singleton design pattern is used in several classes within the application to ensure that only one instance of the class exists throughout the application's lifecycle. This pattern is particularly useful for managing resources that should be shared across different parts of the application.

In the Hotel Management System, the Singleton pattern is implemented in classes such as HotelManager, PensionManager, RoomManager, ReservationManager, and UserManager. These manager classes are responsible for handling the CRUD operations for hotels, pensions, rooms, reservations, and users, respectively. By implementing these classes as singletons, we ensure that there is only one instance of each manager class, preventing unnecessary resource consumption and ensuring consistent behavior across the application.

### Model-View-Controller (MVC):
The project follow the MVC pattern as it separates the GUI components (View) from the business logic (Model) and user input handling (Controller). For example, classes like LoginView, ReservationView, and RoomSaveView represent the View, while classes like UserManager, ReservationManager, and RoomManager handle the business logic.

### Separation of Concerns (SoC):
The project follow the SoC principle by separating different aspects of the application into distinct components or modules. For example, the UserManager class is responsible for user-related operations, while the ReservationManager class handles reservation-related tasks, demonstrating a clear separation of concerns.

## Dependencies

Java Development Kit (JDK)
Swing Framework (for GUI)
Postgres Connector (for database interaction)
Other dependencies as specified in the pom.xml or build.gradle file
