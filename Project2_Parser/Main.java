public class Main {
    public static void main (String[] args) {
        if (args.length != 1) {
            System.out.println("ERROR: Please run with the file name as the only argument.");
            System.exit(0);
        }
        Scanner scanner = new Scanner(args[0]);
        Parser parser = new Parser(scanner);
        parser.parse();
        parser.procedure.print();
    }
}


// Stack of Maps
// String -> Core
// Don't call new token too soon/ forget to call it
// Pushing/popping when entering/exiting scope