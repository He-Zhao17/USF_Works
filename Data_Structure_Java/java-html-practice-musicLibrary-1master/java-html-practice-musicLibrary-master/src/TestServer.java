

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * This class uses Jetty & servlets to implement server serving hotel and review info
 */
public class TestServer {

    private static final int PORT = 8081;

    public TestServer() {

    }

    /**
     * Function that starts the server with correct servlets
     *
     * @throws Exception throws exception if access failed
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server(PORT); // jetty server

        ServletHandler handler = new ServletHandler();

        // FILL IN CODE:
        handler.addServletWithMapping(new ServletHolder(new TestServlet()), "/test");
        handler.addServletWithMapping(new ServletHolder(new Stage1()), "/stage1");
        handler.addServletWithMapping(new ServletHolder(new Stage2()), "/stage2");
        handler.addServletWithMapping(new ServletHolder(new Stage3()), "/stage3");
        //----------------
        handler.addServletWithMapping(new ServletHolder(new SearchByArtist()), "/SearchByArtist");
        handler.addServletWithMapping(new ServletHolder(new SearchByAlbum()), "/SearchByAlbum");
        server.setHandler(handler);

        server.start();
        server.join();
    }

}
