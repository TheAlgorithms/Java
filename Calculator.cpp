#include <iostream>
using namespace std;

class Calculator {
public:
    int add(int a, int b) { return a + b; }
    int subtract(int a, int b) { return a - b; }
    int multiply(int a, int b) { return a * b; }
    double divide(int a, int b) {
        if (b == 0) {
            cout << "Error: Division by zero!" << endl;
            return 0;
        }
        return (double)a / b;
    }
};

// Example usage
int main() {
    Calculator calc;
    cout << "Add: " << calc.add(5, 3) << endl;
    cout << "Subtract: " << calc.subtract(5, 3) << endl;
    cout << "Multiply: " << calc.multiply(5, 3) << endl;
    cout << "Divide: " << calc.divide(5, 3) << endl;
    return 0;
}

