package com.ordermatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OrderMatcher {

    private static ArrayList<Order> orderbook;
    private static Order lastOrder;

    static {
        orderbook = new ArrayList<Order>();
    }

    public static void main(String[] args) {
        System.out.println("-------Execution Started-------");
        String in = "";
        Scanner sin = new Scanner(System.in);
        while (true) {
            in = sin.nextLine();
            if (in.equalsIgnoreCase("PRINT")) {
                printOrderBook();
            } else if (in.equalsIgnoreCase("EXIT")) {
                System.exit(0);
            } else {
                String[] type = in.split(" ");
                String[] vol_price = type[1].split("@");
                int volume = Integer.parseInt(vol_price[0]);
                int price = Integer.parseInt(vol_price[1]);
                if (volume < 0 || price < 0) {
                    System.out
                            .println("Volume and Price Must be Greater than 0 !!!");
                }
                switch (type[0].toUpperCase()) {
                    case "BUY":
                        Order buyorder = new Order(volume, price, "BUY");
                        if (buyorder.getVolume() <= 0 || buyorder.getPrice() <= 0) {
                            System.out.println("Order price and volume must be greater than 0");
                        } else {
                            insertOrder(buyorder);
                        }
                        break;
                    case "SELL":
                        Order sellorder = new Order(volume, price, "SELL");
                        if (sellorder.getVolume() <= 0 || sellorder.getPrice() <= 0) {
                            System.out.println("Order price and volume must be greater than 0");
                        } else {
                            insertOrder(sellorder);
                        }
                        break;
                    default:
                        System.out.println("Command is Invalid !!!");
                }
            }
        }
    }

    private static void printOrderBook() {

        System.out.println("----- SELL -----");
        for (Order o : orderbook) {
            if (o.getOrderType().equalsIgnoreCase("SELL")) {
                System.out
                        .println("SELL " + o.getVolume() + "@" + o.getPrice());
            }
        }
        System.out.println("----- BUY -----");
        for (Order o : orderbook) {
            if (o.getOrderType().equalsIgnoreCase("BUY")) {
                System.out.println("BUY " + o.getVolume() + "@" + o.getPrice());
            }
        }
    }

    private static void insertOrder(Order inorder) {
        orderbook.add(inorder);
        lastOrder = inorder;
        customSort();
        trade();
        removeEmptyOrders();
    }

    @SuppressWarnings("unchecked")
    private static void trade() {
        int k = 0;
        for (Order border : (ArrayList<Order>) orderbook.clone()) {
            int buyvolume = 0;
            int buyPrice = 0;

            for (int i = 0; i < orderbook.size(); i++) {
                if (border.getOrderType().equalsIgnoreCase("BUY")) {
                    buyvolume = border.getVolume();
                    buyPrice = border.getPrice();

                    if (buyvolume > 0 && buyPrice >= orderbook.get(i).getPrice()) {
                        if (orderbook.get(i).getOrderType().equalsIgnoreCase("SELL") && orderbook.get(i).getVolume() != 0) {
                            if (buyvolume > orderbook.get(i).getVolume()) {
                                buyvolume = buyvolume - orderbook.get(i).getVolume();

                                System.out.println("TRADE " + orderbook.get(i).getVolume() + "@"
                                        + (lastOrder.getOrderType().equalsIgnoreCase("BUY") ? orderbook.get(i).getPrice() : orderbook.get(k).getPrice()));

                                orderbook.get(i).setVolume(0);
                                orderbook.get(k).setVolume(buyvolume);
                            } else if (buyvolume == orderbook.get(i)
                                    .getVolume()) {

                                System.out.println("TRADE " + orderbook.get(i).getVolume() + "@"
                                        + (lastOrder.getOrderType().equalsIgnoreCase("BUY") ? orderbook.get(i).getPrice() : orderbook.get(k).getPrice()));
                                orderbook.get(i).setVolume(0);
                                orderbook.get(k).setVolume(0);

                            } else {

                                System.out.println("TRADE " + orderbook.get(k).getVolume() + "@"
                                        + (lastOrder.getOrderType().equalsIgnoreCase("BUY") ? orderbook.get(i).getPrice() : orderbook.get(k).getPrice()));
                                orderbook.get(k).setVolume(0);
                                orderbook.get(i).setVolume(
                                        orderbook.get(i).getVolume() - buyvolume);
                                buyvolume = 0;
                            }
                        }
                    }
                }
            }
            k++;
        }
    }

    private static void sortOrderBook(ArrayList<Order> allOrders) {
        Collections.sort(allOrders);
    }

    private static void sortBuyOrders(ArrayList<Order> buyorders) {
        Collections.sort(buyorders, Collections.reverseOrder());
    }

    private static void removeEmptyOrders() {
        for (int i = 0; i < orderbook.size(); i++) {
            if (orderbook.get(i).getVolume() == 0) {
                orderbook.remove(i);
                i--;
            }
        }
    }

    private static void customSort() {
        ArrayList<Order> buyorders = new ArrayList<Order>();
        ArrayList<Order> sellorder = new ArrayList<Order>();
        for (Order customorders : orderbook) {
            if (customorders.getOrderType().equalsIgnoreCase("BUY")) {
                buyorders.add(customorders);

            } else {
                sellorder.add(customorders);

            }
        }
        sortBuyOrders(buyorders);
        sortOrderBook(sellorder);

        orderbook.removeAll(orderbook);

        orderbook.addAll(buyorders);
        orderbook.addAll(sellorder);

    }

}
