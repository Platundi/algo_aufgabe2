// import necessary packages
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args)
        throws IOException
    {
        int n = 80;
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
        List<PQElement> output = new ArrayList<>();
        for (int i = 0; i < array.length && !pq.isEmpty(); i++) {
            PQElement reise = pq.extractElement();
            output.add(reise);
            for (int j = 0; j < output.size(); j++) {
                if (Objects.equals(output.get(j).getData(), reise.getData()) && output.get(j).getPrio() < reise.getPrio()) {
                    output.remove(reise);
                }
            }
        }
        if (n < output.size()) {
            System.out.println("Die " + n + " günstigsten Reisen:");
            for (int i = 0; i < n; i++) {
                System.out.println((i + 1) + ". " + output.get(i).getData() + " " + output.get(i).getPrio());
            }
        } else {
            System.out.println("Info: Es gibt nur " + output.size() + " Reiseziele");
            System.out.println("Die " + output.size() + " günstigsten Reisen:");
            for (int i = 0; i < output.size(); i++) {
                System.out.println((i + 1) + ". " + output.get(i).getData() + " " + output.get(i).getPrio());
            }
        }
    }
}
