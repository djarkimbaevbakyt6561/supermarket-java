import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Product[] products = new Product[100];
    static int productIndex = 13;

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

    private static int inputInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Введите число!");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private static Product[] deleteProduct(long id) {
        Product[] newProducts = new Product[100];
        int newIndex = 0;

        for (Product product : products) {
            if (product != null && !(product.id == id)) {
                product.id = newIndex + 1;
                newProducts[newIndex++] = product;
            }
        }

        productIndex = newIndex;
        return newProducts;
    }

    public static void main(String[] args) {


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
                        boolean endSalesManOptions = false;
                        System.out.println("Вы успешно вошли!");

                        while (!endSalesManOptions) {
                            System.out.println();
                            System.out.println("""
                                    1.Добавить продукт
                                    2.Удалить продукт
                                    3.Сьесть шоколадку 🍫!
                                    4.Выйти
                                    """);
                            if (scanner.hasNextInt()) {
                                int salesmanNum = scanner.nextInt();
                                switch (salesmanNum) {
                                    case 1:
                                        Product newProduct = new Product();
                                        scanner.nextLine();
                                        newProduct.productName = inputString("Введите название продукта: ");
                                        newProduct.price = inputInt("Введите цену: ");
                                        scanner.nextLine();
                                        newProduct.count = inputInt("Введите количество: ");
                                        newProduct.id = productIndex + 1;
                                        products[productIndex] = newProduct;
                                        productIndex++;
                                        System.out.println("Продукт успешно добавлен!");
                                        break;
                                    case 2:
                                        boolean basketNotEmpty = false;
                                        int allProductsInBasket = 0;
                                        for (Product product : products) {
                                            if (product != null) {
                                                basketNotEmpty = true;
                                                allProductsInBasket++;

                                            }
                                        }
                                        if (basketNotEmpty) {
                                            System.out.println("Продукты: ");
                                            for (Product product : products) {
                                                if (product != null) {
                                                    System.out.println(product);
                                                }
                                            }
                                            boolean continueRemoving = true;
                                            while (continueRemoving) {
                                                System.out.print("Сколько продуктов хотите убрать: ");
                                                if (scanner.hasNextInt()) {
                                                    int countNum = scanner.nextInt();
                                                    scanner.nextLine();
                                                    if (allProductsInBasket >= countNum) {
                                                        for (int i = 0; i < countNum; i++) {
                                                            boolean foundProduct = false;
                                                            while (!foundProduct) {
                                                                System.out.print("Введите ID продукта: ");
                                                                if (scanner.hasNextInt()) {
                                                                    int id = scanner.nextInt();
                                                                    scanner.nextLine();

                                                                    for (Product product : products) {
                                                                        if (product != null && product.id == id) {
                                                                            while (true) {
                                                                                System.out.print("Сколько количества продукта хотите удалить: ");
                                                                                if (scanner.hasNextInt()) {
                                                                                    int countOfProduct = scanner.nextInt();
                                                                                    if (countOfProduct > product.count) {
                                                                                        System.out.println("Всего продукта " + product.count + " вы не сможете удалить " + countOfProduct);
                                                                                    } else {
                                                                                        product.count -= countOfProduct;
                                                                                        if (product.count == 0) {
                                                                                            products = deleteProduct(product.id);
                                                                                        }
                                                                                        System.out.println("Успешно удалено " + countOfProduct + " " + product.productName + " из продуктов");
                                                                                        foundProduct = true;
                                                                                        break;
                                                                                    }
                                                                                } else {
                                                                                    System.out.println("Введите число!");
                                                                                    scanner.nextLine();
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
                                                        System.out.print("Хотите удалить еще продукты? (1 - да, 0 - нет): ");
                                                        while (true) {
                                                            if (scanner.hasNextInt()) {
                                                                int wantMore = scanner.nextInt();
                                                                continueRemoving = (wantMore == 1);
                                                                break;
                                                            } else {
                                                                System.out.println("Введите число!");
                                                                scanner.nextLine();
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("Вы не сможете удалить продуктов больше чем они есть!");
                                                    }
                                                } else {
                                                    System.out.println("Введите число!");
                                                    scanner.nextLine();
                                                }
                                            }
                                        } else {
                                            System.out.println("Продуктов нету!");
                                        }
                                    case 3:
                                        System.out.println("Ням ням! Очень вкусно!😋");
                                        break;
                                    case 4:
                                        System.out.println("Вы успешно вышли!");
                                        endSalesManOptions = true;
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
                    case 2:
                        Client client = new Client();
                        client.firstName = inputString("Введите ваше имя: ");
                        client.firstName = inputString("Введите вашу фамилию: ");
                        while (true) {
                            System.out.print("Введите ваш возраст: ");
                            if (scanner.hasNextByte()) {
                                client.age = scanner.nextByte();
                                break;
                            } else {
                                System.out.println("Введите реальный возраст!");
                                scanner.nextLine();
                            }
                        }
                        client.card.firstName = client.firstName;
                        client.card.lastName = client.lastName;
                        scanner.nextLine();
                        while (true) {
                            System.out.print("Введите номер карточки: ");
                            String cardNumber = scanner.nextLine();
                            if (cardNumber.length() == 16 && cardNumber.matches("\\d+")) {
                                client.card.cardNumber = cardNumber;
                                break;
                            } else {
                                System.out.println("Номер карточки должна состоят из 16 цифр!");
                            }
                        }
                        client.card.password = inputString("Введите пароль для вашей карточки: ");
                        client.card.balance = 0;
                        boolean endBuyerOptions = false;
                        System.out.println("Вы успешно вошли!");

                        while (!endBuyerOptions) {
                            System.out.println();
                            System.out.println("""
                                    1.Взять продукт
                                    2.Убрать продукт
                                    3.Проверить баланс
                                    4.Добавить депозит
                                    5.Снять деньги
                                    6.Проверить все транзакции
                                    7.Закончить покупку
                                    8.Выйти""");
                            if (scanner.hasNextInt()) {
                                int buyerNum = scanner.nextInt();
                                scanner.nextLine();

                                switch (buyerNum) {
                                    case 1:
                                        System.out.println("Все доступные продукты: ");
                                        int allAvailableProducts = 0;
                                        for (Product product : products) {
                                            if (product != null) {
                                                allAvailableProducts++;
                                                System.out.println(product);
                                            }
                                        }

                                        boolean continueAdding = true;
                                        while (continueAdding) {
                                            System.out.print("Сколько продуктов хотите взять: ");
                                            if (scanner.hasNextInt()) {
                                                int countNum = scanner.nextInt();
                                                scanner.nextLine();
                                                if (allAvailableProducts >= countNum) {
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
                                                                        if (product.price > 100) {
                                                                            while (true) {
                                                                                System.out.println("Введите пароль от вашей карточки: ");
                                                                                String password = scanner.nextLine();
                                                                                if (password.equals(client.card.password)) {
                                                                                    while (true) {
                                                                                        System.out.println("Сколько количество этого продукта хотите взять: ");
                                                                                        if (scanner.hasNextInt()) {
                                                                                            int countOfProduct = scanner.nextInt();
                                                                                            if (product.count == 0) {
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
                                                                                                    System.out.println("Продукт успешно добавлен в корзину!");
                                                                                                    break;
                                                                                                }
                                                                                            }

                                                                                        } else {
                                                                                            System.out.println("Введите число!");
                                                                                            scanner.nextLine();
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                } else {
                                                                                    System.out.println("Неправильный пароль!");
                                                                                }
                                                                            }

                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("Сколько количество этого продукта хотите взять: ");
                                                                                if (scanner.hasNextInt()) {
                                                                                    int countOfProduct = scanner.nextInt();
                                                                                    if (product.count == 0) {
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
                                                        scanner.nextLine();
                                                        if (scanner.hasNextInt()) {
                                                            int wantMore = scanner.nextInt();
                                                            continueAdding = (wantMore == 1);
                                                            break;
                                                        } else {
                                                            System.out.println("Введите число!");
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("Вы не может взять продуктов больше чем доступно продуктов!");
                                                }


                                            } else {
                                                System.out.println("Введите число!");
                                                scanner.nextLine();
                                            }
                                        }
                                        break;

                                    case 2:
                                        boolean basketNotEmpty = false;
                                        int allProductsInBasket = 0;
                                        for (Product product : client.basket.products) {
                                            if (product != null) {
                                                basketNotEmpty = true;
                                                allProductsInBasket++;

                                            }
                                        }
                                        if (basketNotEmpty) {
                                            System.out.println("Продукты которые в корзине: ");
                                            for (Product product : client.basket.products) {
                                                if (product != null) {
                                                    System.out.println(product);
                                                }
                                            }
                                            boolean continueRemoving = true;
                                            while (continueRemoving) {
                                                System.out.print("Сколько продуктов хотите убрать: ");
                                                if (scanner.hasNextInt()) {
                                                    int countNum = scanner.nextInt();
                                                    scanner.nextLine();
                                                    if (allProductsInBasket >= countNum) {
                                                        for (int i = 0; i < countNum; i++) {
                                                            boolean foundProduct = false;
                                                            while (!foundProduct) {
                                                                System.out.print("Введите ID продукта: ");
                                                                if (scanner.hasNextInt()) {
                                                                    int id = scanner.nextInt();
                                                                    scanner.nextLine();

                                                                    for (Product product : client.basket.products) {
                                                                        if (product != null && product.id == id) {
                                                                            while (true) {
                                                                                System.out.print("Сколько количества продукта хотите удалить: ");
                                                                                if (scanner.hasNextInt()) {
                                                                                    int countOfProduct = scanner.nextInt();
                                                                                    if (countOfProduct > product.count) {
                                                                                        System.out.println("Всего продукта " + product.count + " вы не сможете удалить " + countOfProduct);
                                                                                    } else {
                                                                                        product.count -= countOfProduct;
                                                                                        for (Product product1 : products) {
                                                                                            if (product1 != null && product1.id == product.id) {
                                                                                                product1.count += countOfProduct;
                                                                                            }
                                                                                        }
                                                                                        if (product.count == 0) {
                                                                                            client.basket.deleteProduct(product.id);
                                                                                        }
                                                                                        System.out.println("Успешно удалено " + countOfProduct + " " + product.productName + " из корзины");
                                                                                        foundProduct = true;
                                                                                        break;
                                                                                    }

                                                                                } else {
                                                                                    System.out.println("Введите число!");
                                                                                    scanner.nextLine();
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
                                                        System.out.print("Хотите удалить еще продукты? (1 - да, 0 - нет): ");
                                                        while (true) {
                                                            scanner.nextLine();
                                                            if (scanner.hasNextInt()) {
                                                                int wantMore = scanner.nextInt();
                                                                continueRemoving = (wantMore == 1);
                                                                break;
                                                            } else {
                                                                System.out.println("Введите число!");
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("Вы не сможете удалить продуктов больше чем они есть в корзине!");
                                                    }
                                                } else {
                                                    System.out.println("Введите число!");
                                                    scanner.nextLine();
                                                }
                                            }
                                        } else {
                                            System.out.println("В корзине пусто!");
                                        }
                                        break;
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
                                                if (client.card.balance < withDrawNum) {
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
                                            if (allTransaction != null) {
                                                System.out.println(allTransaction);
                                            }
                                        }
                                        break;
                                    case 7:
                                        int sum = 0;
                                        for (Product product : client.basket.products) {
                                            if (product != null) {
                                                sum += product.price * product.count;
                                            }
                                        }
                                        if (sum == 0) {
                                            System.out.println("В корзине пусто");
                                        } else {
                                            System.out.println("К оплате " + sum);
                                            boolean payLoopBoolean = false;
                                            while (!payLoopBoolean) {
                                                System.out.println("Как хотите оплатить: (1)Картой или (2)Наличными");
                                                if (scanner.hasNextInt()) {
                                                    int finishNum = scanner.nextInt();
                                                    switch (finishNum) {
                                                        case 1:
                                                            String result = client.payWithCard(sum);
                                                            if (result.equals("Оплачено успешно!")) {
                                                                System.out.printf("""
                                                                        Чек в SUPERMARKET %d %s %d
                                                                        Продукты:
                                                                        """, LocalDate.now().getDayOfMonth(), LocalDate.now().getMonth(), LocalDate.now().getYear());
                                                                for (Product product : client.basket.products) {
                                                                    if (product != null)
                                                                        System.out.println(product.productName + " ,цена: " + product.price + ", " + product.count + " шт");
                                                                }
                                                                System.out.println("Оплата прошла успешно!");
                                                                System.out.println();
                                                                endBuyerOptions = true;
                                                                endPurchase = true;
                                                            } else {
                                                                System.out.println(result);
                                                            }
                                                            payLoopBoolean = true;
                                                            break;
                                                        case 2:
                                                            boolean endPurchaseByCash = false;
                                                            while (!endPurchaseByCash) {
                                                                System.out.println("У вас есть деньги? (1)Да или (2)Нет");
                                                                if (scanner.hasNextInt()) {
                                                                    int moneyNum = scanner.nextInt();
                                                                    switch (moneyNum) {
                                                                        case 1:
                                                                            System.out.printf("""
                                                                                    Чек в SUPERMARKET %d %s %d
                                                                                    Продукты:
                                                                                    """, LocalDate.now().getDayOfMonth(), LocalDate.now().getMonth(), LocalDate.now().getYear());
                                                                            for (Product product : client.basket.products) {
                                                                                if (product != null)
                                                                                    System.out.println(product.productName + " ,цена: " + product.price + ", " + product.count + " шт");
                                                                            }
                                                                            System.out.println("Оплата прошла успешно!");
                                                                            System.out.println();
                                                                            endPurchaseByCash = true;
                                                                            endBuyerOptions = true;
                                                                            payLoopBoolean = true;
                                                                            break;
                                                                        case 2:
                                                                            System.out.println("Проваливай отсюда бомж! (Шутка)");
                                                                            System.out.println();
                                                                            endPurchaseByCash = true;
                                                                            endBuyerOptions = true;
                                                                            payLoopBoolean = true;
                                                                            break;
                                                                        default:
                                                                            System.out.println("Введите правильное число!");
                                                                            break;
                                                                    }
                                                                } else {
                                                                    System.out.println("Введите число!");
                                                                    scanner.nextLine();
                                                                }
                                                            }
                                                            break;
                                                        default:
                                                            System.out.println("Введите правильное число!");
                                                            break;
                                                    }

                                                } else {
                                                    System.out.println("Введите число!");
                                                    scanner.nextLine();
                                                }
                                            }
                                        }

                                        break;
                                    case 8:
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
