public class Main {
    public static void main (String[] args) {
        if (args.length != 2) {
            System.out.println("ERROR: Missing at least one argument. Please provide the '.code' file and the '.data' file.");
            System.exit(0);
        }
        Scanner scanner = new Scanner(args[0]);
        Scanner fileInput = new Scanner(args[1]);
        Parser parser = new Parser(scanner, fileInput);
        parser.parse();
        parser.procedure.execute();
    }
}