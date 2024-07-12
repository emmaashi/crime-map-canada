import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChartData {

    public static List<Property> getProperties() {
        List<Property> properties = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("Files/Property.csv"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // Handle quotes in the data and replace commas with semicolons
                line = line.replaceAll(",", ";");

                String[] parts = line.split("\\s*;\\s*");

                if (parts.length >= 8) {
                    try {
                        String geography = parts[0].trim();
                        String category = parts[1].trim();
                        if (category.contains("city") || category.contains("City")) {
                            // Extract city name from geography
                            String[] geographyParts = geography.split("\\(");
                            if (geographyParts.length > 1) {
                                geography = geographyParts[0].trim();
                            }
                        }
                        String crime = parts[1].trim();
                        double year2008 = parseDouble(parts[2].trim());
                        double year2009 = parseDouble(parts[3].trim());
                        double year2010 = parseDouble(parts[4].trim());
                        double year2011 = parseDouble(parts[5].trim());
                        double year2012 = parseDouble(parts[6].trim());

                        Property property = new Property(geography, crime, year2008, year2009, year2010, year2011, year2012);
                        properties.add(property);
                    } catch (Exception e) {
                        System.err.println("Error parsing line: " + line);
                        e.printStackTrace();
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return properties;
    }

    private static double parseDouble(String s) {
        if (s.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.err.println("Number format error: " + s);
            return 0.0;
        }
    }
}