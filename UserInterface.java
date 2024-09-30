import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {


    public void start() {

        Adventure adventure = new Adventure();

        System.out.println("Hello and welcome to the text adventure game! Please begin the game by giving your character a name:");
        Scanner scanner = new Scanner(System.in);
        String playerName;
        String name = scanner.nextLine();
        playerName = name;
        System.out.println("Your characters name is " + playerName + ". The objective for " + playerName + " is to reach room 5 without losing 100 HP.");
        System.out.println("To move around, use commands: 'go north', 'go east', go west', 'go south'.");
        System.out.println("You can at all times write 'help', 'look' or 'exit', 'backpack'.");
        System.out.println("If there are any items in the room your character is in, you can always pick them up and drop them whereever you like. Simply write: 'take <item>' or 'drop <item>'.");
        System.out.println(playerName + " starts in room 1. Tip: Take a look around to see if there is anything useful for " + playerName + " to use.");

        Scanner input = new Scanner(System.in);
        String useraction = "";

        while (!useraction.equalsIgnoreCase("exit")) {
            useraction = input.nextLine();

            // Denne kode herover viser hvordan vi tager et item op, ved hjælp af at skrive "take" først.
            if (useraction.startsWith("take ")) {
                String[] parts = useraction.split("take ", 2);
                if (parts.length < 2) {
                    System.out.println("Take what?"); // Hvis man bare skriver "take" uden noget efterfølgende, genkender koden at man vil samle noget op, men ikke hvad man vil samle op.
                } else {
                    String itemName = parts[1].trim(); // dette fjerner eventuelle unødvendige mellemrum.
                    Item item = adventure.getPlayer().getCurrentRoom().takeItem(itemName); // Her finder vi item og fjerner det igen fra Listen, hvis man tager det op.
                    if (item != null) {
                        System.out.println("You took " + item.getItemName());
                        adventure.getPlayer().addItemToInventory(item);
                    } else {
                        System.out.println("No such item in this room. ");
                    }
                }
            }
            else if (useraction.startsWith("drop ")) {
                String[] parts = useraction.split("drop ", 2);
                if (parts.length < 2) { // Ligesom før med at skulle "take", så er det bare drop.
                    System.out.println("Drop what?");
                } else {
                    String itemName = parts[1].trim();
                    Item itemToDrop = null;
                    for (Item item : adventure.getPlayer().getItemArrayList()) {
                        if (item.getItemName().equalsIgnoreCase(itemName)) {
                            itemToDrop = item;
                            break;
                        }
                    }
                    if (itemToDrop != null) {
                        adventure.getPlayer().getCurrentRoom().dropItem(itemToDrop);
                        adventure.getPlayer().getItemArrayList().remove(itemToDrop);
                        System.out.println("You dropped " + itemName + " in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                    } else {
                        System.out.println("You don't have that item.");
                    }
                }
            } else {

                switch (useraction) {

                    case "inventory", "i", "backpack", "b", "inv" -> {
                        System.out.println(playerName + " has " + adventure.getPlayer().getItemArrayList() + " items in the backpack.");
                    }

                    case "go n", "go north", "n", "north" -> {
                        adventure.getPlayer().getN();
                        break;
                    }

                    case "go s", "go south", "s", "south" -> {
                        adventure.getPlayer().getS();
                        break;
                    }

                    case "go e", "go east", "e", "east" -> {
                        adventure.getPlayer().getE();
                        break;
                    }

                    case "go w", "go west", "w", "west" -> {
                        adventure.getPlayer().getW();
                        break;
                    }


                    case "look", "l", "Look" -> {
                        List<Item> items = adventure.getPlayer().getCurrentRoom().getItems();
                        if (items.isEmpty()) {
                            System.out.println("There is nothing useful for " + playerName + " to pick up in this room.");
                        } else {
                            System.out.println("You see the following items in the current room:");
                            for (Item item : items) {
                                System.out.println(item);
                            }
                        }
                    }

                    case "help" -> {
                        System.out.println("To move around, write either 'go north', 'go east', go west', 'go south'.");
                        System.out.println("You can at all times write 'help', 'look' or 'exit'.");
                    }

                    default -> {
                        System.out.println("Unknown command");
                    }

                }
                System.out.println(playerName + " is now in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                System.out.println(adventure.getPlayer().getCurrentRoom().getRoomDescription());

            }
        }
    }
















                /*

                case "help" -> {
                    System.out.println("To move around, write either 'go north', 'go east', go west', 'go south'.");
                    System.out.println("You can at all times write 'help', 'look' or 'exit'.");
                }

                case "look" -> {
                    System.out.println(currentRoom);
                }

                case "go south", "s", "south", "go s" -> {
                    if (currentRoom.getS() == null) {
                        System.out.println(playerName + " can't move that way.");
                    } else {
                        currentRoom = currentRoom.getS();
                        System.out.println(currentRoom);
                    }
                }

                case "go north", "n", "north", "go n" -> {
                    if (currentRoom.getN() == null) {
                        System.out.println(playerName + " can't move that way.");
                    } else {
                        currentRoom = currentRoom.getN();
                        System.out.println(currentRoom);
                    }
                }

                case "go west", "w", "west", "go w" -> {
                    if (currentRoom.getW() == null) {
                        System.out.println(playerName + " can't move that way.");
                    } else {
                        currentRoom = currentRoom.getW();
                        System.out.println(currentRoom);
                    }
                }

                case "go east", "e", "east", "go e" -> {
                    if (currentRoom.getE() == null) {
                        System.out.println(playerName + " can't move that way.");
                    } else {
                        currentRoom = currentRoom.getE();
                        System.out.println(currentRoom);
                    }
                }
            }
        }
    }
*/

}
