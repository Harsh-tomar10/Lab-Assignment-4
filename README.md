# Lab-Assignment-4

Student Record Management System
Objective:

To implement a Student Management System that allows persistent storage of student records using file handling.

To use Java Collections Framework (ArrayList, HashMap) to manage, sort, and manipulate records efficiently.

Key Features:

File Handling: Read from and write to a text file (students.txt) to store student records persistently.

Collections: Use ArrayList<Student> to store multiple student objects.

Sorting: Implement sorting of student records by marks using Comparator.

Iterator: Display records using Iterator for traversing collections.

CRUD Operations: Add, view, search, delete, and sort student records via a menu-driven interface.

Class Design:

Student Class: Contains fields rollNo, name, email, course, and marks. Includes methods to display details, convert to/from string (for file operations).

FileUtil Class: Handles reading and writing student data to a file using BufferedReader and BufferedWriter.

StudentManager Class: Manages student records, performs CRUD operations, sorting, and provides iterator-based display.

Main Class (StudentRecordSystem): Implements menu-driven interface to interact with the system.

Key Java Concepts Used:

Serialization: Student implements Serializable to facilitate saving/loading objects.

Collections API: ArrayList used to store student objects.

File I/O: BufferedReader, BufferedWriter, FileReader, FileWriter for persistent storage.

Comparator: To sort students by marks.

Iterator: To traverse the collection while displaying or deleting records.

Exception Handling: Handles IOException, FileNotFoundException.

Flow of the Program:

Load existing records from students.txt.

Display menu: Add, View, Search, Delete, Sort, Save & Exit.

Perform selected operations on the ArrayList<Student> collection.

Save updated records back to the file upon exit.

Advantages:

Persistent storage ensures data is not lost after program termination.

Collections provide efficient data storage and manipulation.

Menu-driven interface makes it user-friendly.

Sorting and searching improve usability for large datasets.

Enhancements Possible:

Use HashMap<Integer, Student> to prevent duplicate roll numbers and faster searching.

Save data in structured formats like CSV or JSON.

Implement multithreading for responsive file operations.
