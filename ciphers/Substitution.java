import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.Scanner;

public class Substitution {

    public static void main(String[] args) {
        //initialising the options
        Options opts = new Options();
        opts.addOption("o", false, "keep non-letters as is, honor letter casing");
        opts.addOption("d", false, "decrypt");
        opts.addOption("debug", false, "provides extra information about the program status at every step, not needed for final use");

        //parsing arguments
        CommandLineParser clp = new DefaultParser();
        CommandLine cl = new CommandLine.Builder().build();

        //storing the mapping
        String mapping = "";
        boolean mappingIsShift = false;

        try {
            cl = clp.parse(opts, args);
            if (cl.getArgList().isEmpty()) {
                System.err.println("no mapping or shift given");
                help();
                System.exit(0);
            }

            mapping = cl.getArgs()[0];
            mappingIsShift = mapping.charAt(0) >= '0' && mapping.charAt(0) <= '9';

            if (!mappingIsShift && mapping.length() != 26) {
                System.err.println("given mapping is incomplete, it should be exactly 26 characters");
                help();
                System.exit(0);
            }

            if (cl.hasOption("debug")) {
                System.out.println("successfully parsed:");
                System.out.println("flags:");
                for (Option o : cl.getOptions()) {
                    System.out.println(o.toString());
                }
                System.out.println("additional arguments:");
                System.out.println(cl.getArgList());
            }
        } catch (ParseException e) {
            System.err.println("Invalid arguments:");
            help();
            System.exit(0);
        }

        //if we get here, the command line arguments were parsed successfully
        //so we move on to reading input
        Scanner s = new Scanner(System.in);
        String line;

        int shift = -69;
        if (mappingIsShift) {
            shift = Integer.parseInt(mapping) % 26;
        }

        do {
            line = s.nextLine();
            if (!cl.hasOption("o")) {
                line = line.toLowerCase();
            }
            //if the first character in the "mapping" argument is a number
            if (mappingIsShift) {
                encryptShift(line, shift, cl);
            }
            //in case the "mapping" argument is an actual mapping instead of a simple shift
            else {
                encryptMapping(line, mapping.toCharArray(), cl);
            }
        } while (s.hasNext());

        s.close();
    }

    private static void encryptShift(String original, int shift, CommandLine cl) {
        shift *= cl.hasOption("d") ? -1 : 1;
        StringBuilder encrypted = new StringBuilder();
        char[] og = original.toCharArray();
        for (char c : og) {
            if ('a' <= c && c <= 'z') {
                encrypted.append(c + shift > 'z' ? (char) (c + shift - 26) : (c + shift < 'a' ? (char) (c + shift + 26) : (char) (c + shift)));
            } else if (cl.hasOption("o")) {
                if ('A' <= c && c <= 'Z') {
                    encrypted.append(c + shift > 'Z' ? (char) (c + shift - 26) : (c + shift < 'A' ? (char) (c + shift + 26) : (char) (c + shift)));
                } else {
                    encrypted.append(c);
                }
            }
        }
        System.out.println(encrypted.toString());
    }

    private static void encryptMapping(String original, char[] mapping, CommandLine cl) {
        if (cl.hasOption("debug")) {
            System.out.println(original);
        }
        char[] map = Arrays.copyOf(mapping, mapping.length);
        if (cl.hasOption("d")) {
            map = invertMapping(mapping);
        }

        StringBuilder encrypted = new StringBuilder();
        char[] og = original.toCharArray();
        for (char c : og) {
            if ('a' <= c && c <= 'z') {
                encrypted.append(map[c - 'a']);
            } else if (cl.hasOption("o")) {
                if ('A' <= c && c <= 'Z') {
                    encrypted.append(Character.toUpperCase(map[c - 'A']));
                } else {
                    encrypted.append(c);
                }
            }
        }

        System.out.println(encrypted.toString());
    }

    private static char[] invertMapping(char[] mapping) {
        char[] inverted = Arrays.copyOf(mapping, mapping.length);
        for (int i = 0; i < mapping.length; i++) {
            inverted[mapping[i] - 'a'] = (char) (i + 'a');
        }

        return inverted;
    }


    private static void help() {
        System.out.println(
                "Usage: substitution [-o] [-d] mapping\n" +
                        "Where:\n" +
                        "-o: keep non-letters as is, honor letter casing\n" +
                        "-d: decrypt\n" +
                        "mapping: 26 letter char-mapping\n" +
                        "or an int-value\n" +
                        "1\n" +
                        "En/Decrypts stdin to stdout. Only letters are encrypted,\n" +
                        "all other characters are silently ignored, unless -o was\n" +
                        "specified, in which case they are used as-is.\n" +
                        "When -o is specified, letter casing is honored, otherwise all\n" +
                        "letters are converted to lower-case letters.\n" +
                        "Use an int-value to do a letter shift (% 26, 0: a = a)\n" +
                        "Shift 3 is the classical Caesar encryption"
        );
    }
}
