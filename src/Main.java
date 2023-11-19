import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    private static String inputString(String prompt) {
        System.out.print(prompt);
        String returnText;
        while (true) {
            String text = scanner.nextLine();

            if (text.isEmpty()) {
                System.out.println("Значение не должно быть пустым!");
            } else {
                returnText = text;
                break;
            }
        }
        return returnText;
    }

    private static long inputLong(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextLong()) {
            System.out.println("Введите число!");
            scanner.nextLine();
        }
        return scanner.nextLong();
    }

    public static void main(String[] args) {

        Product[] products = new Product[100];
        products[0] = new Product("Хлеб ржаной", 60, 20, 1);
        products[1] = new Product("Туалетная бумага", 10, 200, 2);
        products[2] = new Product("Колбаса", 300, 30, 3);
        products[3] = new Product("Пепси 2л", 120, 20, 4);
        products[4] = new Product("Вода Asu", 60, 20, 5);
        products[5] = new Product("Йогурт Нежный 5%", 35, 40, 6);
        products[6] = new Product("Лапша быстрого приготовления", 120, 20, 7);
        products[7] = new Product("Молоко в коробке", 60, 50, 8);
        products[8] = new Product("Яйца", 13, 300, 9);
        products[9] = new Product("Хлеб белый", 30, 40, 10);
        products[10] = new Product("Масло сливочное", 140, 25, 11);
        products[11] = new Product("Кетчуп", 80, 15, 12);
        products[12] = new Product("Хлопья", 300, 10, 13);

        boolean endPurchase = false;

        while (!endPurchase) {
            System.out.println("Кто вы? (1)Продавец или (2)Покупатель");
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                scanner.nextLine();
                switch (num) {
                    case 1:
                        // Действия для продавца
                        break;
                    case 2:
                        Client client = new Client();
                        client.firstName = inputString("Введите ваше имя: ");
                        client.firstName = inputString("Введите вашу фамилию: ");
                        client.age = (byte) inputLong("Введите ваш возраст: ");
                        client.card.firstName = client.firstName;
                        client.card.lastName = client.lastName;
                        scanner.nextLine();
                        client.card.cardNumber = inputLong("Введите номер своей карточки: ");
                        scanner.nextLine();
                        client.card.password = inputString("Введите пароль для вашей карточки: ");
                        client.card.balance = 0;
                        boolean endBuyerOptions = false;
                        while (!endBuyerOptions) {
                            System.out.println("""
                                    1.Взять продукт
                                    2.Убрать продукт
                                    3.Проверить баланс
                                    4.Добавить депозит
                                    5.Снять деньги
                                    6.Проверить все транзакции
                                    7.Закончить покупку
                                    8.Уйти""");
                            if (scanner.hasNextInt()) {
                                int buyerNum = scanner.nextInt();
                                scanner.nextLine();

                                switch (buyerNum) {
                                    case 1:
                                        System.out.println("Все доступные продукты: ");
                                        for (Product product : products) {
                                            if (product != null) {
                                                System.out.println(product);
                                            }
                                        }

                                        boolean continueAdding = true;
                                        while (continueAdding) {
                                            System.out.print("Сколько продуктов хотите взять: ");
                                            if (scanner.hasNextInt()) {
                                                int countNum = scanner.nextInt();
                                                scanner.nextLine();

                                                for (int i = 0; i < countNum; i++) {
                                                    boolean foundProduct = false;
                                                    while (!foundProduct) {
                                                        System.out.print("Введите ID продукта: ");
                                                        if (scanner.hasNextInt()) {
                                                            int id = scanner.nextInt();
                                                            scanner.nextLine();

                                                            for (Product product : products) {
                                                                if (product != null && product.id == id) {
                                                                    boolean productInBasket = false;
                                                                    if(product.price > 100){
                                                                        while (true){
                                                                            System.out.println("Введите пароль от вашей карточки: ");
                                                                            String password = scanner.nextLine();
                                                                            if(password.equals(client.card.password) ){
                                                                                while (true) {
                                                                                    System.out.println("Сколько количество этого продукта хотите взять: ");
                                                                                    if (scanner.hasNextInt()) {
                                                                                        int countOfProduct = scanner.nextInt();
                                                                                        if(product.count == 0) {
                                                                                            foundProduct = true;
                                                                                            System.out.println("Товар закончился! Недоступен для покупки.");
                                                                                            break;
                                                                                        } else {
                                                                                            if (countOfProduct > product.count) {
                                                                                                System.out.println("Всего продукта " + product.count + " вы не сможете взять " + countOfProduct);
                                                                                            } else {
                                                                                                Product newProduct = new Product(product.productName, product.price, product.count, product.id);
                                                                                                newProduct.count = countOfProduct;
                                                                                                for (Product product1 : client.basket.products) {
                                                                                                    if (product1 != null && product1.id == id) {
                                                                                                        product1.count += countOfProduct;
                                                                                                        productInBasket = true;
                                                                                                        break;
                                                                                                    }
                                                                                                }
                                                                                                if (!productInBasket) {
                                                                                                    client.basket.addProduct(newProduct);
                                                                                                }
                                                                                                foundProduct = true;
                                                                                                product.count -= countOfProduct;
                                                                                                client.basket.addProduct(newProduct);

                                                                                                System.out.println("Продукт успешно добавлен в корзину!");
                                                                                                break;
                                                                                            }
                                                                                        }

                                                                                    } else {
                                                                                        System.out.println("Введите число!");
                                                                                        scanner.nextLine();
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                System.out.println("Неправильный пароль!");
                                                                            }
                                                                        }

                                                                    }else {
                                                                        while (true) {
                                                                            System.out.println("Сколько количество этого продукта хотите взять: ");
                                                                            if (scanner.hasNextInt()) {
                                                                                int countOfProduct = scanner.nextInt();
                                                                                if(product.count == 0) {
                                                                                    foundProduct = true;
                                                                                    System.out.println("Товар закончился! Недоступен для покупки.");
                                                                                    break;
                                                                                } else {
                                                                                    if (countOfProduct > product.count) {
                                                                                        System.out.println("Всего продукта " + product.count + " вы не сможете взять " + countOfProduct);
                                                                                    } else {
                                                                                        Product newProduct = new Product(product.productName, product.price, product.count, product.id);
                                                                                        newProduct.count = countOfProduct;
                                                                                        for (Product product1 : client.basket.products) {
                                                                                            if (product1 != null && product1.id == id) {
                                                                                                product1.count += countOfProduct;
                                                                                                productInBasket = true;
                                                                                                break;
                                                                                            }
                                                                                        }
                                                                                        if (!productInBasket) {
                                                                                            client.basket.addProduct(newProduct);
                                                                                        }
                                                                                        foundProduct = true;
                                                                                        product.count -= countOfProduct;
                                                                                        client.basket.addProduct(newProduct);

                                                                                        System.out.println("Продукт успешно добавлен в корзину!");
                                                                                        break;
                                                                                    }
                                                                                }

                                                                            } else {
                                                                                System.out.println("Введите число!");
                                                                                scanner.nextLine();
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (!foundProduct) {
                                                                System.out.println("Продукт с таким ID не найден!");
                                                            }
                                                        } else {
                                                            System.out.println("Введите число!");
                                                            scanner.nextLine();
                                                        }
                                                    }
                                                }
                                                System.out.print("Хотите добавить еще продукты? (1 - да, 0 - нет): ");
                                                while (true) {
                                                    if (scanner.hasNextInt()) {
                                                        int wantMore = scanner.nextInt();
                                                        scanner.nextLine();
                                                        continueAdding = (wantMore == 1);
                                                        break;
                                                    } else {
                                                        System.out.println("Введите число!");
                                                        scanner.nextLine();
                                                    }
                                                }


                                            } else {
                                                System.out.println("Введите число!");
                                                scanner.nextLine();
                                            }
                                        }
                                        break;

                                    case 2:
                                    case 3:
                                        System.out.println("Ваш баланс составляет: " + client.getCardBalance());
                                        break;
                                    case 4:
                                        while (true) {
                                            System.out.print("Введите сумму: ");
                                            if (scanner.hasNextInt()) {
                                                int depositNum = scanner.nextInt();

                                                client.card.deposit(depositNum);
                                                System.out.println("Вы успешно добавили " + depositNum + " coма на ваш баланс!");
                                                break;
                                            } else {
                                                System.out.println("Введите число!");
                                            }
                                            scanner.nextLine();

                                        }
                                        break;
                                    case 5:
                                        while (true) {
                                            System.out.print("Введите сумму: ");
                                            if (scanner.hasNextInt()) {
                                                int withDrawNum = scanner.nextInt();
                                                if(client.card.balance < withDrawNum){
                                                    System.out.println("Недостаточно средств!");
                                                } else {
                                                    client.card.withdraw(withDrawNum);
                                                    System.out.println("Вы успешно сняли " + withDrawNum + " сома с вашего баланса!");
                                                }

                                                break;
                                            } else {
                                                System.out.println("Введите число!");

                                            }
                                            scanner.nextLine();
                                        }
                                        break;
                                    case 6:
                                        System.out.println("Все транзакции: ");
                                        String[] allTransactions = client.card.displayTransactionHistory();
                                        for (String allTransaction : allTransactions) {
                                            if(allTransaction != null){
                                                System.out.println(allTransaction);
                                            }
                                        }
                                        break;
                                    case 8:
                                        endPurchase = true;
                                        endBuyerOptions = true;
                                        break;
                                    default:
                                        System.out.println("Введите правильное число!");
                                }
                            } else {
                                System.out.println("Введите число!");
                                scanner.nextLine();

                            }
                        }
                        break;
                    default:
                        System.out.println("Введите правильное число!");
                }
            } else {
                System.out.println("Введите число!");
                scanner.nextLine();

            }
        }
    }
}
