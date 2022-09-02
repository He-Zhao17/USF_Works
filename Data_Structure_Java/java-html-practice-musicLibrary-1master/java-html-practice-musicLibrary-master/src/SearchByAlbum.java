import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SearchByAlbum extends HttpServlet {
    private String message;

    public void init() throws ServletException {
        // Do required initialization
        message = "Search by artistslbum";
    }

    public String getContent(String htmlpath) {
        String result = "";
        try {
            Scanner sc = new Scanner(new File(htmlpath));

            while (sc.hasNextLine()) {
                result += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return result;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println(getContent("src/SbyAlbum.html"));
    }
}

