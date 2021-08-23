import java.io.*;
import java.util.*;

public class UrlTable {
    private SortedMap<String, String> sortedTable;
    UrlTable(String fileName) throws IOException {
        sortedTable = new TreeMap<>(Comparator.comparingInt(Objects::hashCode));
        BufferedReader reader = new BufferedReader(new InputStreamReader(UrlTable.class.getResourceAsStream(fileName)));
        while (reader.ready()){
            sortedTable.put(reader.readLine(),reader.readLine());
        }
    }

    public SortedMap<String, String> getSortedTable() {
        return sortedTable;
    }

    @Override
    public String toString() {
        for(SortedMap.Entry<String,String> entry : sortedTable.entrySet()){
            System.out.println(entry.getKey() + "  " + entry.getKey().hashCode());
        }
        return super.toString();
    }


}
