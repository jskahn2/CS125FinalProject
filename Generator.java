import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
/**
 * Generates an "indie band name" using files of nouns, verbs, and adjectives.
 *
 * @author Jordan Kahn, Isha Kukadia
 * @version 1
 */
public class Generator {
    // instance variables - replace the example below with your own
    //private String[] adjs = "Quiet", "Loud", "Tame", "Static", "Ridiculous";
    /**
     * list of verbs or adjectives.
     */
    private ArrayList<String> adjs;
    /**
     * list of nouns.
     */
    private ArrayList<String> nouns;
    /**
     * number of syllables for each adjective/verb.
     */
    private int numSyllablesAdj;
    /**
     * number of syllables for each noun.
     */
    private int numSyllablesNoun;
    /**
     * whether the user is using adjectives or verbs.
     */
    private boolean isAdj;
    /**
     * Constructor for the generator for all syllables
     * @param is whether the generator is using adjectives
     * @throws IOException if fails
     */
    public Generator(boolean is) throws IOException {
        adjs = new ArrayList<String>();
        nouns = new ArrayList<String>();
        isAdj = is;
        if isAdj {
            readAdjFiles();
        } else {
            readVerbFiles();
        }
        readNounFiles();
    }

    /**
     * Constructor for the generator for specified nouns and syllables.
     * @param adj the number of syllables of adjectives / verbs
     * @param noun the number of syllables of nouns
     * @param is whether the generator is using adjectives
     * @throws IOException if fails
     */
    public Generator(int adj, int noun, boolean is) throws IOException {
        adjs = new ArrayList<String>();
        nouns = new ArrayList<String>();
        isAdj = is;
        if isAdj {
            if (adj < 1 || adj > 4) {
                readAdjFiles();
            } else {
                readAdjFiles(adj);
            }
        } else {
            if (adj < 1 || adj > 4) {
                readVerbFiles();
            } else {
                readVerbFiles(adj);
            }
        }
        if (noun < 1 || noun > 4) {
            readNounFiles();
        } else {
            readNounFiles(noun);
        }
    }

    /**
     * Reads in all of the adjective files.
     * @throws IOException if fails
     */
    public void readAdjFiles() throws IOException {
        for (int i = 1; i < 5; i++) {
            Scanner fileScan = new Scanner(new File("resources/" + i + "syllableadjectives.txt"));
            while(fileScan.hasNextLine()) {
                adjs.add(fileScan.nextLine());
            }
        }
    }

    /**
     * Reads in all of the noun files.
     * @throws IOException
     */
    public void readNounFiles() throws IOException {
        for (int i = 1; i < 5; i++) {
            Scanner fileScan2 = new Scanner(new File("resources/" + i + "syllablenouns.txt"));
            while(fileScan2.hasNextLine()) {
                nouns.add(fileScan2.nextLine());
            }
        }
    }

    /**
     * Reads in the file of adjectives for specific syllable.
     * @param numadjs number of syllables for the adjective
     * @throws IOException
     */
    public void readAdjFiles(int numadjs) throws IOException {
        Scanner fileScan = new Scanner(new File("resources/" + numadjs + "syllableadjectives.txt"));
        while(fileScan.hasNextLine()) {
            adjs.add(fileScan.nextLine());
        }
    }

    /**
     * Reads in the file of nouns for specific syllable.
     * @param numnouns number of syllables for the nouns
     * @throws IOException
     */
    public void readNounFiles(int numnouns) throws IOException {
        Scanner fileScan2 = new Scanner(new File("resources/" + numnouns + "syllablenouns.txt"));
        while(fileScan2.hasNextLine()) {
            nouns.add(fileScan2.nextLine());
        }
    }

    /**
     * reads in all verb files.
     * @throws IOException
     */
    public void readVerbFiles() throws IOException {
        for (int i = 1; i < 5; i++) {
            Scanner fileScan2 = new Scanner(new File("resources/" + i + "syllableverbs.txt"));
            while(fileScan2.hasNextLine()) {
                adjs.add(fileScan2.nextLine());
            }
        }
    }

    /**
     * Reads in the file of verbs for specific syllable.
     * @param numverbs number of syllables for the nouns
     * @throws IOException
     */
    public void readVerbFiles(int numverbs) throws IOException {
        Scanner fileScan = new Scanner(new File("resources/" + numverbs + "syllableverbs.txt"));
        while(fileScan.hasNextLine()) {
            adjs.add(fileScan.nextLine());
        }
    }

    /**
     * Generates the band name String
     * @return the String band name
     */
    public String generate() {
        if (isAdj) {
            return adjs.get((int)(Math.random() * adjs.size())) + " " + nouns.get((int)(Math.random() * nouns.size()));
        }
        else {
            return adjs.get((int)(Math.random() * adjs.size())) + " the " + nouns.get((int)(Math.random() * nouns.size()));
        }
    }
}