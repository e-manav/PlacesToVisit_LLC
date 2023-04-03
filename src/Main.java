import java.util.LinkedList;
import java.util.Scanner;

record Place(String place, int distance) {
    @Override
    public String toString() {
        return String.format("%s (%d)", place, distance);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Place> placesToVisit = new LinkedList<>();
        Place adelaide = new Place("Adelaide", 1374);
        addPlaces(placesToVisit, adelaide);
        addPlaces(placesToVisit, new Place("Alice Spring", 2771));
        addPlaces(placesToVisit, new Place("Brisbane", 917));
        addPlaces(placesToVisit, new Place("Darwin", 3972));
        addPlaces(placesToVisit, new Place("Melbourne", 877));
        addPlaces(placesToVisit, new Place("Perth", 3923));
        placesToVisit.addFirst(new Place("Sydney", 0));
        System.out.println(placesToVisit);
        printMenu();
        var iterator = placesToVisit.listIterator();
        boolean quitLoop = false;
        boolean forward = true;

        while (!quitLoop) {
            System.out.println("Enter value : ");
            String input = scanner.nextLine().toUpperCase().substring(0, 1);
            if (!iterator.hasPrevious()) {
                System.out.println("Originating : " + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()){
                System.out.println("Ending : "+ iterator.previous());
                forward = false;
            }

            switch (input) {
                case "F":
                    System.out.println("User wants to go forward");
                    if (!forward){
                        forward = true;
                        if (iterator.hasNext()){
                            iterator.next();
                        }
                    }
                    if (iterator.hasNext()){
                        System.out.println(iterator.next());
                    }
                    break;

                case "B":
                    System.out.println("User wants to go backward");
                    if (forward){
                        forward = false;
                        if (iterator.hasPrevious()){
                            iterator.previous();
                        }
                    }
                    if (iterator.hasPrevious()){
                        System.out.println(iterator.previous());
                    }
                    break;

                case "L":
                    System.out.println(placesToVisit);
                    break;

                case "M":
                    printMenu();

                default:
                    quitLoop = true;
                    break;
            }
        }

    }

    public static void addPlaces(LinkedList<Place> list, Place place) {
        if (list.contains(place)) {
            System.out.println("Found Duplicate :" + place);
            return;
        }
        for (Place p :
                list) {
            if (p.place().equalsIgnoreCase(place.place())) {
                System.out.println("Found Duplicate : " + place);
                return;
            }
        }

        int matchedIndex = 0;
        for (Place pl :
                list) {
            if (place.distance() < pl.distance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }
        list.add(place);
    }

    private static void printMenu() {
        System.out.println("""
                Available Options:
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit
                """);
    }
}