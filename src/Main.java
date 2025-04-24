// import necessary packages
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
        throws IOException
    {
        int N = 35;
        System.out.println("Arbeitsverzeichnis: " + new File(".").getAbsolutePath());
        String fileName = "reisendat.sec";

        // Code für Einlesen: https://www.geeksforgeeks.org/read-file-into-an-array-in-java/ (24.04.2025)
        // list that holds strings of a file
        List<PQElement> listOfPQElements
                = new ArrayList<>();

        // load data from file
        BufferedReader bf = new BufferedReader(
                new FileReader(fileName));

        // read entire line as string
        String line = bf.readLine();

        // checking for end of file
        while (line != null) {
            String[] geteilteLine = line.split(" ");
            listOfPQElements.add(new PQElement(geteilteLine[0], Double.parseDouble(geteilteLine[1])));
            line = bf.readLine();
        }

        // closing bufferreader object
        bf.close();

        // storing the data in arraylist to array
        PQElement[] array = listOfPQElements.toArray(new PQElement[0]);

        MinPQ pq = new MinPQ(array.length);
        for (PQElement e : array) {
            pq.insert(e.getData(), e.getPrio());
        }

        // N günstigste Reisen ausgeben
        System.out.println("Die " + N + " günstigsten Reisen:");
        for (int i = 0; i < N && !pq.isEmpty(); i++) {
            PQElement reise = pq.extractElement();
            System.out.println((i + 1) + ". " + reise.getData() + " " + reise.getPrio());
        }
    }
}
