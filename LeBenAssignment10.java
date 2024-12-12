/*
* Name: Ben LE 
* Class: CS1150 (M/W)
* Due: Nov 13, 2024
* Description: Assignment #10
* The program creates a set of Transaction objects each representing a purchase with details and stores them in an array. 
* It displays all the transactions and identifies the highest cost transaction. It also has a CreditCard class that holds 5 transactions.
* It attempts to add each transaction to the credit card and displays which ones were added. It then shows the most expensive one stored on the card. 
*/

public class LeBenAssignment10 {

    public static void main(String[] args) {
        // creating transactions and adding them to an array
        Transaction[] transactions = new Transaction[7];
        transactions[0] = new Transaction("Amazon", "Shopping", 127.50);
        transactions[1] = new Transaction("Ace", "Home", 290.00);
        transactions[2] = new Transaction("Chegg", "Education", 29.95);
        transactions[3] = new Transaction("Safeway", "Groceries", 145.50);
        transactions[4] = new Transaction("AirBnB", "Travel", 445.00);
        transactions[5] = new Transaction("Target", "Shopping", 12.00);
        transactions[6] = new Transaction("Lowes", "Home", 35.95);

        System.out.println("********************************************************");
        System.out.println("Part 1: Transactions In Array");
        System.out.println("********************************************************");
        System.out.printf("%-15s %-15s %-10s%n", "Merchant", "Category", "Amount");
        System.out.println("--------------------------------------------------------");

        // displaying transactions and find the most expensive one
        Transaction mostExpensiveTransaction = transactions[0];
        // looping through each transaction in the array, display it, and find the most expensive one. 
        for (Transaction transaction : transactions) {
            System.out.printf("%-15s %-15s %-10.2f%n", transaction.getMerchant(), transaction.getCategory(), transaction.getAmount());
            //updating most expensive transaction if current transaction amount is too high 
            if (transaction.getAmount() > mostExpensiveTransaction.getAmount()) {
                mostExpensiveTransaction = transaction;
            }
        }

        // displaying most expensive transaction 

        System.out.println("\nMost expensive transaction in the array of transactions");
        System.out.println("--------------------------------------------------------");
        System.out.printf("Merchant: %s\nCategory: %s\nAmount: %.2f%n",
                mostExpensiveTransaction.getMerchant(),
                mostExpensiveTransaction.getCategory(),
                mostExpensiveTransaction.getAmount());

        
        System.out.println("\n********************************************************");
        System.out.println("Part 2: Transactions In Credit Card Object");
        System.out.println("********************************************************");

        // create creditcard object that can hold up 5 transactions 

        CreditCard creditCard = new CreditCard(5);

        // adding transactions to CreditCard

        for (Transaction transaction : transactions) {
            boolean added = creditCard.addTransaction(transaction);
            System.out.printf("Was %s added to the credit card? %b%n", transaction.getMerchant(), added);
        }

        // displaying transactions in credit card and then finding the most expensive one 

        System.out.println("\nDisplaying transactions in the Credit Card");
        creditCard.displayAllTransactions();

        Transaction mostExpensiveOnCard = creditCard.findMostExpensiveTransaction();
        System.out.println("\nMost expensive transaction on credit card");
        System.out.println("-----------------------------------------");
        System.out.printf("Merchant: %s\nCategory: %s\nAmount: %.2f%n",
                mostExpensiveOnCard.getMerchant(),
                mostExpensiveOnCard.getCategory(),
                mostExpensiveOnCard.getAmount());
    }
}
// transaction Class 
class Transaction {
    private String merchant;
    private String category;
    private double amount;

    public Transaction(String merchant, String category, double amount) {
        this.merchant = merchant;
        this.category = category;
        this.amount = amount;
    }
// getters 
    public String getMerchant() {
        return merchant;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}

class CreditCard {
    private Transaction[] transactions;
    private int currentNumTransactions;

    // constructor to initialize CreditCard with max number of transcations 

    public CreditCard(int maxNumTransactions) {
        transactions = new Transaction[maxNumTransactions];
        currentNumTransactions = 0;
    }

    // method to add transaction to CreditCard 

    public boolean addTransaction(Transaction transactionToAdd) {
        if (currentNumTransactions < transactions.length) {
            transactions[currentNumTransactions] = transactionToAdd;
            currentNumTransactions++;
            return true;
        }
        return false;
    }

    // method to find most expensive transcation in CreditCard

    public Transaction findMostExpensiveTransaction() {
        Transaction mostExpensive = transactions[0];
        for (int i = 1; i < currentNumTransactions; i++) {
            if (transactions[i].getAmount() > mostExpensive.getAmount()) {
                mostExpensive = transactions[i];
            }
        }
        return mostExpensive;
    }

    // method to display transactions in CreditCard 

    public void displayAllTransactions() {
        System.out.printf("%-15s %-15s %-10s%n", "Merchant", "Category", "Amount");
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < currentNumTransactions; i++) {
            System.out.printf("%-15s %-15s %-10.2f%n",
                    transactions[i].getMerchant(),
                    transactions[i].getCategory(),
                    transactions[i].getAmount());
        }
    }
}
