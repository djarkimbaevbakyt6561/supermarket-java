import java.time.LocalDate;

public class Client implements CardInterface {
    String firstName;
    String lastName;
    byte age;
    Card card = new Card();
    Basket basket = new Basket();

    @Override
    public String payWithCard(double money) {
        if (money < card.balance) {
            return "Оплачено успешно!";
        } else {
            return "Недостаточно средств!";
        }
    }

    @Override
    public int getCardBalance() {
        return card.balance;
    }
}
