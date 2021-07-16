import list.ArrayList;
import list.LinkedList;
import list.SimpleList;

public class Main {
    public static void main(String[] args) {
        SimpleList<String> list;

        list = new ArrayList<>(1);
        runCommands(list);

        list = new LinkedList<>();
        runCommands(list);

    }

    private static void runCommands(SimpleList<String> list){

        list.add("hi");
        list.add("there");
        list.add("how're");
        list.add("you?");

        System.out.println(list.contains("there"));
        System.out.println(list.contains("not there"));

        System.out.println(list.get(2));

        System.out.println(list.isEmpty());

        System.out.println(list.size());

        System.out.println(list.remove(3));
        System.out.println(list.remove(0));

        System.out.println(list.size());

    }
}
