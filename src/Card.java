import interfaces.Deposit;
import interfaces.TransactionalHistory;
import interfaces.WithDrawel;

public class Card implements Deposit, TransactionalHistory, WithDrawel {
    String firstName;
    String lastName;
    String cardNumber;
    int balance;
    String[] transactionHistory = new String[100];
    int index = 0;
    String password;

    public Card() {
    }

    public Card(String firstName, String lastName, String cardNumber, int balance, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.password = password;
    }

    @Override
    public void deposit(int cash) {
        balance += cash;
        transactionHistory[index] = "Было вложено " + cash + " депозита на баланс";
        index++;

    }

    public String[] displayTransactionHistory() {
        return transactionHistory;
    }

    public void withdraw(int cash) {
        balance -= cash;
        transactionHistory[index] = "Было снято " + cash + " денег из баланса";
        index++;
    }
}
