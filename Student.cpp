#include <iostream>
#include <string>
using namespace std;

class Student {
private:
    int id;
    string name;
    double grade;

public:
    // Constructor
    Student(int id, string name, double grade) {
        this->id = id;
        this->name = name;
        this->grade = grade;
    }

    // Getters
    int getId() { return id; }
    string getName() { return name; }
    double getGrade() { return grade; }

    // Setters
    void setId(int id) { this->id = id; }
    void setName(string name) { this->name = name; }
    void setGrade(double grade) { this->grade = grade; }

    // Display
    void display() {
        cout << "ID: " << id << ", Name: " << name << ", Grade: " << grade << endl;
    }
};

// Example usage
int main() {
    Student s1(1, "Alice", 95.5);
    s1.display();
    return 0;
}
