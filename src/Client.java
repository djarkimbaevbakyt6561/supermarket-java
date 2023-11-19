public class Client implements CardInterface {
    String firstName;
    String lastName;
    byte age;
    Card card = new Card();
    Basket basket = new Basket();
    @Override
    public String payWithCard(double money) {
        return null;
    }

    @Override
    public int getCardBalance() {
        return card.balance;
    }
}
