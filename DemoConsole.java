public class DemoConsole {
    public static void main(String[] args) throws Exception {
        System.out.println("Text eingeben: ");
        String text = Console.readString();
        System.out.println("Gelesener Text: " + text);

        System.out.println("Text eingeben: ");
        char [] ca = Console.readCharArray();
        System.out.println("Gelesenes char-Feld: ");
        for(char celement: ca)
          System.out.print(celement);
        System.out.println();

        System.out.println("Boolean eingeben: ");
        boolean b = Console.readBoolean();
        System.out.println("Gelesener Wert: " + b);
    }
}
