# Hotel Management System 🏨

A comprehensive console-based hotel management system developed in **Java 17**. This project utilizes the **MVC (Model-View-Controller)** architecture to ensure clean, organized, and scalable code, featuring data persistence through binary files.

---

## About the Project

This system was developed as a practical project to apply advanced Object-Oriented Programming concepts. It simulates the daily operations of a hotel reception, allowing full control over rooms, guests, and reservations, including cost calculations and extra services.

The project underwent a complete refactoring process, migrating from a legacy Portuguese structure to a professional English architecture, following industry standards.

---

## Key Features

### Room Management
* **Registration:** Add rooms of type **Standard** or **Luxury**.
* **Listing:** View all rooms and their current status.
* **Status Management:** Control the room lifecycle:
    * *Free*
    * *Occupied*
    * *Cleaning*
    * *Maintenance*

### Guest Management
* **Registration:** Complete registration with Name, Phone, Email, and Tax ID.
* **Validation:** ID validation system to prevent duplicates and invalid formats.
* **Search:** Quickly locate registered guests.

### Reservations & Checkout
* **Booking:** Link a registered guest to an available room.
* **Extra Services:** Add items like "Breakfast" to the total cost.
* **Automatic Calculation:** The system calculates the total value based on:
    * Room type (Standard vs. Luxury rates).
    * Number of days.
    * Consumed services.
* **Checkout:** Finalizes the reservation, frees the room, and generates the final amount to pay.

### Data Persistence
* All data (Rooms, Guests, Reservations) are automatically saved to local files (`.dat`).
* The system loads data automatically upon startup, ensuring no information is lost.

---

## Technologies & Concepts

* **Language:** Java JDK 17
* **Dependency Management:** Maven
* **Architecture:** MVC (Model-View-Controller)
* **Persistence:** Object Serialization (`java.io.Serializable`)
* **OOP Concepts:**
    * **Inheritance & Polymorphism:** `StandardRoom` and `LuxuryRoom` classes inheriting from `AbstractRoom`.
    * **Encapsulation:** Data protection with access modifiers.
    * **Abstraction:** Use of abstract classes and interfaces (`ExtraServiceBase`).
* **Exception Handling:** Custom exception classes (`InvalidTaxIdException`).
* **Clean Code:** Refactoring of naming conventions and packages to English.

---

## Project Structure

The code is organized following package best practices:

text
<details>
  <summary><strong> Project Structure</strong></summary>

src/main/java/hotel/

- base/ — Abstract classes and Enums  
- controllers/ — Business logic  
- entities/ — Concrete entities  
- exceptions/ — Custom exceptions  
- menus/ — Console UI  
- persistence/ — File saving/loading logic  
- reservations/ — Reservations and extra services  
- utils/ — Utilities  
- validation/ — Validation rules  
- HotelApp.java — Main Class

</details>

---

## Learnings

-The development of this project provided valuable insights into:
-Refactoring: Transforming legacy mixed-language code into a professional, standardized structure.
-Design Patterns: Practical application of MVC to separate the view (Menus) from logic (Controllers).
-File Manipulation: Persisting complex object states in Java without an external database.
-Java Stream API: Using streams for calculations and list filtering.
