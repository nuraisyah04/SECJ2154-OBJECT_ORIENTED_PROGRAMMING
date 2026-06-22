# 🎓 SECJ2154: Object-Oriented Programming 

**Course:** Object-Oriented Programming (OOP) — Section 3, 2024/2025 Semester 2  
**Lecturer:** Dr. Muhammad Khatibsyarbini  
**Institution:** Universiti Teknologi Malaysia (UTM)

---

## Overview

This page documents my personal reflections on the lab exercises completed throughout the OOP course. Each lab built upon the last, taking me from foundational Java class design all the way through complex class relationships and array manipulation. Looking back, these exercises were instrumental in shifting my thinking from procedural to truly object-oriented programming.

---

## Lab Exercise 2 — Classes, Objects, and Encapsulation

### What the Lab Covered

This lab introduced the building blocks of OOP in Java. The exercises walked through creating a class (`Employee`, `Car`, `Book`, `Student`, etc.), declaring private instance variables, writing constructors, and implementing getter and setter methods. Later questions explored method overloading, static vs. non-static members, and access modifiers.

### What I Learned

**Encapsulation** was the biggest takeaway. I learned why fields should be declared `private` and only exposed through `public` getters and setters. Before this, I would sometimes make fields public out of convenience — this lab made me understand the risks of doing that in larger systems where uncontrolled access to data can cause bugs that are hard to trace.

**Constructors** were something I had a surface-level understanding of before, but implementing a parameterized constructor for the `Book` class (taking `title` and `author`) made it click. A constructor is not just a method with the class name — it is the designated place to guarantee an object starts in a valid state.

**Method overloading** in the `Message` class was a neat exercise. I now understand that Java distinguishes overloaded methods by their parameter signatures, not just their names, which gives a lot of flexibility in designing clean, readable APIs.

**Static vs. non-static** was the most conceptually tricky part. Implementing a static counter in the `User` class to track how many objects had been created clarified the difference: a static field belongs to the *class*, not to any single instance — and that distinction matters a lot when you want shared state across objects.

### Challenges I Faced

Initially, I mixed up the `this` keyword — I kept forgetting to use `this.name = name` inside setters when the parameter name matched the field name. This caused the field to remain at its default value. Debugging this was a good lesson in how Java resolves variable scope.

---

## Lab Exercise 3 — Share Store System (Class Design & Object Relationships)

### What the Lab Covered

This exercise required implementing a multi-class system from scratch: `Store`, `SpaceLot`, `Member`, and `Item`. The classes are interconnected — a `Store` holds a list of `SpaceLot` objects, each lot is assigned to a `Member`, and each lot stores a list of `Item` objects. The task required completing all constructors and method bodies from a skeleton.

### What I Learned

This lab was my first real experience translating a **class diagram into working Java code**. Reading the class overview and mapping each attribute and method to actual Java syntax taught me how system design documents translate to implementation — a skill that I now realise is essential for any team-based software project.

Working with `List<SpaceLot>` and `ArrayList` introduced me to Java's Collections framework. I learned that you declare the variable as the `List` interface but instantiate it as `ArrayList`, which is good practice because it keeps the code flexible if you ever want to swap the implementation.

The concept of **object referencing** became very concrete here. When `lot1.assignToMember(member1)` is called and I later retrieve that member via `lot.getAssignedMember().getInfo()`, I am chaining method calls across objects — the `SpaceLot` holds a reference to a `Member` object, not a copy of it. This was an important mental model to build.

I also practised writing **formatted output methods** like `getInfo()` and `getDetails()`, which return readable strings rather than printing directly. This taught me the value of separating data formatting from data printing — a cleaner design that makes the class more reusable.

### Challenges I Faced

I forgot to initialise the `ArrayList` inside the constructors initially, which caused `NullPointerException` when `addItem()` was called. The fix was simple — `items = new ArrayList<>()` in the constructor — but tracing a null pointer through chained calls was a debugging exercise in itself.

---

## Lab Exercise 4 — Class Relationships: Association, Aggregation, Composition, Inheritance & Polymorphism

### What the Lab Covered

A school management system (`Person`, `Student`, `Teacher`, `Course`, `Classroom`) was used to demonstrate all five major class relationships in OOP. The lab intentionally included errors and missing statements that needed to be identified and fixed, making it both a coding and a code-reading exercise.

### What I Learned

This lab unified a lot of concepts that had been taught separately. Seeing them all in one coherent system made the differences between the relationship types much clearer:

| Relationship | Example in Lab | Key Insight |
|---|---|---|
| **Inheritance** | `Student` and `Teacher` extend `Person` | Shared behaviour lives in the parent; specialised behaviour in the child |
| **Polymorphism** | `displayRole()` overridden in both subclasses | A `Person` reference can point to a `Student` or `Teacher` at runtime |
| **Association** | `Student` uses a `Course` | A loose, "uses-a" link; neither owns the other |
| **Aggregation** | `Course` has a `Teacher` | The `Teacher` can exist without the `Course` |
| **Composition** | `Classroom` creates its own `Course` | The `Course` is created inside `Classroom` and cannot exist independently |

**Polymorphism** was the most eye-opening part. Declaring `Person p1 = new Student(...)` and calling `p1.displayRole()` — and having it execute the `Student` version — demonstrated dynamic dispatch in action. This is not just a theoretical concept; it is what makes large systems extensible without changing existing code.

**Fixing the errors** was a valuable exercise on its own. The `Course` constructor was missing the `courseName` parameter, `showClassroomInfo()` was not calling `course.showCourseInfo()`, and the `Student` class was missing `enrollCourse()`. Tracking down these issues trained me to read error messages carefully and trace logic through multiple classes.

### Challenges I Faced

The composition vs. aggregation distinction was confusing at first. The key that helped me was asking: *"If the container object is destroyed, does the contained object also cease to exist?"* For `Classroom` and `Course`, the answer is yes — so it is composition. For `Course` and `Teacher`, no — a teacher exists outside any course. That question is now my go-to heuristic.

---

## Lab Exercise 5 — Arrays, ArrayLists, and Methods

### What the Lab Covered

This lab shifted focus to **data structures** within an OOP context. It covered 1D and 2D arrays, arrays of objects (`Student[]`), `ArrayList<String>`, anonymous arrays, and variable-length arguments (`varargs`). The exercises required spotting syntax errors, writing declarations, and implementing helper methods such as `findHighestScore()`, `printStudentInfo()`, and `sumSubjectMarks()`.

### What I Learned

The biggest lesson was understanding **how to pass arrays into methods and return values from them**. Writing `findHighestScore(int[] scores)` that iterates through the array and returns the maximum taught me to think about methods not just as actions but as computations — they receive data, process it, and return results without modifying anything outside their scope.

The **2D array** for `marks` (3 students × 3 subjects) made me think in a grid-like structure. The nested loop `for (int i = 0; i < marks.length; i++) { for (int j = 0; j < marks[i].length; j++) { ... } }` is a pattern I now feel comfortable writing.

The **three common array errors** I fixed were also very instructive:

1. `new double(4)` → should be `new double[4]` — parentheses vs. square brackets matters; Java is strict about this.
2. `points = {90, 85, 88}` after declaration → shorthand initialisation only works at the point of declaration, not later.
3. `(int... values, String title)` → varargs must always be the *last* parameter in a method signature.

The **enhanced for-each loop** used in `printStudentInfo()` also felt more natural to write than the traditional index-based loop, and I now prefer it whenever I do not need the index.

### Challenges I Faced

The anonymous array concept `new int[]{10, 20, 30, 40}` was unfamiliar — I had never seen it created inline without assigning it to a variable first. Once I understood that it is just an array with no name being passed directly to a method, it made sense. It is a useful shortcut when you only need the array in one place.

---

## Mini Project — Vehicle Rental System

### What the Project Covered

The mini project was a console-based **Vehicle Rental System** built entirely in Java — a single compilable file (`VehicleRentalSystem.java`) containing six classes: `Vehicle`, `Car`, `Motorcycle`, `Customer`, `Rental`, `VehicleRentalSystem`, plus two custom exceptions. The system lets users add/remove vehicles, register customers, rent and return vehicles, and view all active rentals through an interactive menu.

### What I Learned

**Inheritance and polymorphism in a real context.** `Car` and `Motorcycle` both extend `Vehicle` and each override `calculateRentalCost()` to add their own surcharge ($5/day for cars, $2/day for motorcycles). Seeing a `Vehicle` reference dynamically dispatch to the correct subclass method at runtime — the same call producing different cost results depending on the actual object type — made polymorphism feel genuinely useful rather than just theoretical.

**Custom exceptions.** Writing `VehicleInUseException` and `VehicleNotFoundException` — both extending `Exception` — taught me that custom exceptions are not just about throwing errors; they communicate *intent*. A caller catching `VehicleInUseException` knows exactly what went wrong without parsing an error message string. This is far cleaner than throwing a generic `RuntimeException` with a descriptive string.

**Class relationships at system scale.** The project used all three relationship types at once: `VehicleRentalSystem` *aggregates* `ArrayList<Vehicle>`, `ArrayList<Customer>`, and `ArrayList<Rental>` (they can exist independently); `Rental` *associates* a `Customer` with a `Vehicle` (neither owns the other); and `Car`/`Motorcycle` *inherit* from `Vehicle`. Having all of these in one codebase made it clear how different relationships serve different purposes in the same design.

**`ArrayList` for dynamic collections.** Using `ArrayList<Vehicle>` meant the system could grow without pre-declaring a fixed array size. Methods like `findVehicleByRegistrationNumber()` iterating over the list with a for-each loop — and returning `null` if not found — became the standard pattern for lookup throughout the system.

**Defensive programming with try-catch.** Every user-facing method (`rentVehicle`, `returnVehicle`, `removeVehicle`) wraps its logic in try-catch blocks, catching specific custom exceptions first and a generic `Exception` last as a safety net. Handling `InputMismatchException` in the main menu loop — resetting `choice = -1` to keep the loop running instead of crashing — taught me that robustness in interactive programs means anticipating every way a user can provide wrong input.

**`@Override` and `toString()`.** Every class overrides `toString()` to produce formatted output. This meant `System.out.println(vehicle)` automatically printed the right details for both `Car` and `Motorcycle` objects without any type-checking — a clean demonstration of polymorphism applied to output formatting.

### Challenges I Faced

The menu's `Scanner` handling required careful attention. After `scanner.nextInt()`, a leftover newline in the buffer would cause subsequent `scanner.nextLine()` calls to read an empty string. Adding `scanner.nextLine()` immediately after every `nextInt()` or `nextDouble()` to consume the newline was an easy fix to miss the first time and a frustrating bug to trace.

Designing the `Rental` class took some thought — it needed to hold references to both `Customer` and `Vehicle` without owning them, since both exist independently in the system's lists. Getting the association right (storing references, not copies) was the key decision that made return logic (`rentalToReturn.getVehicle().setAvailable(true)`) work correctly.

---

## Overall Reflection

Across these four lab exercises, I developed a progressively stronger grasp of Java and object-oriented design. If I were to summarise what changed most in my thinking:

**Before OOP labs:** I tended to write programs as sequences of steps, with data living in loose variables.

**After OOP labs:** I now think in terms of *objects with responsibilities*. Each class should know about its own data, expose only what is necessary, and interact with other classes through well-defined interfaces.

The most practically useful skills I take away are:

- Designing classes from a specification or diagram
- Implementing encapsulation correctly with private fields and public accessors
- Understanding when to use inheritance vs. composition
- Debugging multi-class systems by tracing object references
- Working with arrays and collections as data containers passed between methods

These labs have given me a solid foundation that I am confident will carry into more advanced courses in software engineering and system design.

---

*Last updated: June 2025*  
*ePortfolio maintained on GitHub — [Dr-Khatib/OOP-Section3-2425-2](https://github.com/Dr-Khatib/OOP-Section3-2425-2)*
