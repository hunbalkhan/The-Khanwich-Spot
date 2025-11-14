# The Khanwich Spot  
### *Deli Order Management System — Capstone Project 2*  
**Year Up United — Application Development Track**  
**Student:** Hunbal Khan Durrani  
**Date:** November 2024  

---

## Overview

**The Khanwich Spot** is a Java-based point-of-sale (POS) project I built for my Year Up United Application Development capstone. It runs in the console and lets users:

- Build custom sandwiches  
- Choose signature sandwiches  
- Add drinks and chips  
- View order summaries  
- Generate detailed receipts saved as `.txt` files  

This project highlights the core Java and OOP concepts I’ve learned so far and shows how they can be applied in a realistic scenario.

---

## Goals of This Project

My main objectives for this capstone were to practice and demonstrate:

- **Object-Oriented Programming (OOP)**  
  Classes, inheritance, abstraction, and polymorphism

- **File Handling**  
  Saving receipts into a `/receipts` folder using Java I/O

- **Menu & User Input Design**  
  Clear console menus, safe input handling, and validation

- **Code Organization**  
  Structuring a multi-class Java project with packages

- **Problem Solving**  
  Pricing logic, topping rules, dynamic totals, etc.

---

## Features

### Custom Sandwich Builder  
Choose your:

- Size: *4", 8", 12"*  
- Bread type  
- Free toppings (veggies, sauces)  
- Premium toppings (meats, cheeses)  
- Toasting option  

Dynamic pricing updates based on size and toppings.

---

### Signature Sandwiches Included

- **BLT**  
- **Philly Cheese Steak**

After choosing one, you can still customize toppings.

---

### Additional Items

- Drinks (Small / Medium / Large with multiple flavors)  
- Chips (various flavors)

---

### Order Management

- Real-time price updates  
- Full order summary before checkout  
- Validates that an order isn’t empty  
- Clear console interface

---

### Receipt Generation

When an order is completed, the program auto-creates a `.txt` receipt containing:

- Order ID (timestamp)  
- All items purchased  
- Prices broken down  
- Free vs premium toppings  
- Final total  

Saved in the `receipts/` folder.

---

## Project Structure

<img width="279" height="456" alt="image" src="https://github.com/user-attachments/assets/b5d2763c-6fa1-49bf-a875-720e57bf8069" />

This structure keeps logic clean and organized across models, UI, and data handling.

---
## UML Architectural structure

What my previous design looked like when starting out in this project was this below

<img width="659" height="634" alt="image" src="https://github.com/user-attachments/assets/121a1ab0-ca2d-4518-9470-ce7dbd30ec7f" />

---

<img width="1089" height="760" alt="image" src="https://github.com/user-attachments/assets/890d8771-ad86-41d7-8a52-4a2ca0b58ded" />

Attached here is my actual UML design on the Object-Oriented-Programming.

### Architecture Overview

**Package Structure:**
- **models**: Core business objects (Product hierarchy, Order, Topping)
- **userinterface**: User interaction and menu navigation
- **data**: File I/O and receipt management

**Key Relationships:**
- **Inheritance**: Product → Sandwich/Drink/Chips demonstrates polymorphism
- **Composition**: Order aggregates Products; Sandwich aggregates Toppings
- **Dependencies**: UserInterface coordinates all components


---
## How It Works (Technical Summary)

- **Abstract classes** (`Product`, `SignatureSandwich`) define shared rules  
- **Inheritance** lets items like `Sandwich`, `Drink`, and `Chips` share behavior  
- **Polymorphism** allows the `Order` class to store all item types in one list  
- **Encapsulation** keeps data secure with getters and private fields  
- **Dynamic pricing** changes with sandwich size, extra toppings, and item type  
- **Input validation** prevents invalid entries and keeps the UI smooth

---

## How to Run the Program

1. Clone or download this repository  
2. Open it in your preferred Java-compatible IDE  
3. Make sure you’re using **Java 11+**  
4. Run `Main.java`  
5. Follow the step-by-step console instructions to place an order

---

## Sample Output

A clean preview of what the console shows:

<img width="300" height="550" alt="image" src="https://github.com/user-attachments/assets/8e10e49c-d240-4ec3-ac62-d3c17a17c0a8" />

Receipts saved as `.txt` files show an itemized breakdown.

---

<img width="343" height="894" alt="image" src="https://github.com/user-attachments/assets/d41d309a-ab6c-4b69-967d-2dd72e847ba8" />

---

<img width="551" height="918" alt="image" src="https://github.com/user-attachments/assets/bcff6245-03d9-42f0-9ce6-b27cc0e37c47" />

---

## Skills Demonstrated

- Java fundamentals  
- Object-oriented programming  
- Collections (`ArrayList`)  
- File I/O with `BufferedWriter`  
- Error handling  
- Console UI and validation  
- Clean coding and package organization  
- Real-world problem solving  
- Project documentation (like this README)

---

## Future Improvements

If expanded later, I’d like to add:

- Database storage for orders  
- Employee login accounts  
- Inventory tracking  
- Payment system simulation  
- Order editing before checkout  
- Daily/weekly sales reports  

---

## Acknowledgments

Huge thank you to **Year Up United**, my instructors, mentors, and peers for guiding me through this project and helping me grow as a developer.

---

## Contact

**Hunbal Khan**  
Year Up United — Application Development Track  
- Email: hunbal4@gmail.com 
- LinkedIn: https://www.linkedin.com/in/hunbal-khan-durrani/ 
- GitHub: hunbalkhan  




