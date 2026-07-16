package orderedarrayusage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Comparator;
import orderedarray.OrderedArray;
import orderedarray.OrderedArrayException;

/**
 * 
 * @author pozzato
 */
public class OrderedArrayUsageJava{

    private static final Charset ENCODING = StandardCharsets.UTF_8;

    private static void printArray (OrderedArray<Record> orderedArray) throws OrderedArrayException{
        Record currRec = null;
        int sizeArr;

        System.out.println("\nOrdered array of Records");
        sizeArr = orderedArray.size();
        for (int i=0;i<sizeArr;i++){
            currRec = orderedArray.get(i);
            System.out.println("<"+currRec.getStringField()+","+currRec.getIntegerField()+">\n");
        } // for
    }  // printArray

    private static void loadArray(String filepath, OrderedArray<Record> orderedArray) throws IOException,OrderedArrayException{
        System.out.print("LOADING DATA FROM FILE...");
        Path inputFilePath = Paths.get(filepath);
        try(BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)){
            String line = null;
            while((line = fileInputReader.readLine()) != null){
                String [] lineElements = line.split(",");
                Record recordToBeAdded = new Record(lineElements[0],Integer.parseInt(lineElements[1]));
                orderedArray.add(recordToBeAdded);
            }  // while
        } // end try
        System.out.println("DONE!");
    } // end loadArray
    

    private static void testWithComparisonFunction(String filepath,Comparator<Record> comparator) throws IOException,OrderedArrayException{
        OrderedArray<Record> orderedArray = new OrderedArray<>(comparator);
        loadArray(filepath, orderedArray);
        printArray(orderedArray);
    } // testWithComparisonFunction

    /**
     * @param args: the command line arguments. It should contain only one argument
     * specifying the file path of the data file (.csv)
     */
    public static void main (String [] args) throws IOException, OrderedArrayException, Exception{
        if (args.length != 1)
            throw new Exception("Usage: OrderedArrayUsageJava <data_file_name>");
        testWithComparisonFunction(args[0], new RecordComparatorInt());
        testWithComparisonFunction(args[0], new RecordComparatorString());
    }  // main

} // class