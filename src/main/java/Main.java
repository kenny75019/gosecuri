import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File htmlTemplateFile = new File("src/main/resources/html_template.html");
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        String listeStaff = "";
        List<String> staff = getStaffListe("src/main/resources/staff.txt");
        for (String s : staff) {
            listeStaff = listeStaff + "<li>"+ s + "</li>\n";
        }
        htmlString = htmlString.replace("$listeStaff", listeStaff);
        File newHtmlFile = new File("/var/www/html/new.html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString);

    }

    public static List<String> getStaffListe(String path) {
            final List<String> staff = new LinkedList<>();
            if(path == null) {
                throw new IllegalArgumentException();
            }
            try {
                Path path2 = Paths.get(path);
                List<String> lines = Files.readAllLines(path2);
                for (String line : lines) {
                    staff.add(line);
                }
                return staff;
            }
            catch(IOException exception) {
                return new LinkedList<>();
            }
        }
    }
