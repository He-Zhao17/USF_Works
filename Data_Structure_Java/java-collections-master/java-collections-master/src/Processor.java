import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/* The Processor class will open a file and tokenize it. */

public class Processor {
    public static final String[] stopwords = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "you're",
        "you've", "you'll", "you'd", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she",
        "she's", "her", "hers", "herself", "it", "it's", "its", "itself", "they", "them", "their", "theirs",
        "themselves", "what", "which", "who", "whom", "this", "that", "that'll", "these", "those", "am", "is", "are",
        "was", "were", "be", "been", "being", "have", "has", "had", "having", "do", "does", "did", "doing",
        "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for",
        "with", "about", "against", "between", "into", "through", "during", "before", "after", "above", "below", "to",
        "from", "up", "down", "in", "out", "on", "off", "over", "under", "again", "further", "then", "once", "here",
        "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some",
        "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "can", "will", "just", "don't",
        "should", "should've", "now", "aren't", "couldn't", "didn't", "doesn't",  "hasn't", "haven't", "isn't", "shouldn't",
        "wasn't", "weren't", "won't", "wouldn't"};



    String filename;

    public Processor(){
        this.filename = "";
    }

    public Processor(String fname) {
        this.filename = fname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String[] getStopwords() {
        return stopwords;
    }
    
    /* you do this one */
    public boolean isStopword(String word) {
        for (String stopword: stopwords) {
            if (stopword.equals(word)) {
                return true;
            }
        }
        return false;
    }

    /* you do this one. */
    /* let's assume a word is junk if it contains anything except a letter. */

    public boolean isJunk(String word) {
        char[] word_char = word.toCharArray();
        for (char wordchar: word_char) {
            if (Character.isLetter(wordchar)) {
                return false;
            }
        }
        return true;
    }

    /* you do this one. */
    /* remove trailing punctuation. You can assume that there is at most one punctuation character at the end of the word*/

    public String stripPunctuation (String w) {
        char[] w_char = w.toCharArray();
        if (!Character.isAlphabetic(w_char[w_char.length - 1]) && !Character.isDigit(w_char[w_char.length - 1])) {
            w = w.substring(0, w.length() - 1);
        }
        return w;
    }

/* you do this one */
    /* parseFile should take a filename as input, open the file, read in each token, convert to lower case,
        check to see if it's in the stopword list, check to see if it's junk, and strip off trailing punctuation.
        Return an ArrayList of Strings representing all words that meet these criteria. processed accordingly. */
    
    public ArrayList<String> parseFile() {
        File infile;
        Scanner sc;
        String word;
        ArrayList<String> words = new ArrayList<String>();
        try {
            infile = new File(filename);
            sc = new Scanner(infile);
            while(sc.hasNext()) {
                word = sc.nextLine().toLowerCase();
                String[] wordline = word.split(" ");
                for (String w: wordline) {
                    if (!w.equals("")) {
                        //strip the punction
                        w = stripPunctuation(w);
                        // check isjunk and isstopword
                        if (!isJunk(w) && !isStopword(w)) {
                            words.add(w);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error. Cannot read.");
            return words;
        }
        return words;
    }
}
