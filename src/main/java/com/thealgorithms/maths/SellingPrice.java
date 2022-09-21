/** 
To find selling price of an article with cost price and profit/loss percent.
*/

class Price
{
public static void main(double cp,double percent)
{
double sp = (1+percent/100)*cp; // Replace + with - for loss percent.
System.out.println("Selling Price="+sp);
}
}

// Guide:- https://www.geeksforgeeks.org/find-selling-price-from-given-profit-percentage-and-cost/
