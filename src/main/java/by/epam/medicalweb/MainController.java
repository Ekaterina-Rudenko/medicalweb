package by.epam.medicalweb;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 25 )
public class MainController extends HttpServlet {
    static Logger logger = LogManager.getLogger();
private static final String UPLOAD_DIR = "uploads";
    public MainController() {
    }

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        logger.log(Level.INFO, "first log from " + this.getServletName() + " Method " + request.getMethod());

        /* String paramNum = request.getParameter("number");
        int numResult = Integer.parseInt(paramNum);
        numResult *= 2;
        request.setAttribute("num_result", numResult)*/;

        //ContextParameters
        /*String mail = request.getServletContext().getInitParameter("admin_mail");
        request.setAttribute("e_mail", mail);*/

        //read properties
     /*   ResourceBundle bundle = ResourceBundle.getBundle("data/database");
        String dbDriver = bundle.getString("db.driver");
        request.setAttribute("db_driver", dbDriver);*/

       // request.getRequestDispatcher("pages/main.jsp").forward(request, response);
    }
    //UPLOAD FILE
    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("content");
        try(InputStream inputStream = part.getInputStream()){
            String submittedFileName = part.getSubmittedFileName();
            Path imagePath = new File(UPLOAD_DIR + submittedFileName).toPath();
            long bytes = Files.copy(
                    inputStream,
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING);
            request.setAttribute("upload_result", " successfully " + bytes + " bytes");
        } catch (IOException e){
            request.setAttribute("upload_result", " failed " + e);
        }
        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }*/


    //UPLOAD FILES
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationRoot = request.getServletContext().getRealPath("");
        String uploadFileDir = applicationRoot + File.separator + UPLOAD_DIR + File.separator;

        File fileSaveDir = new File(uploadFileDir);
        if(!fileSaveDir.exists()){
            fileSaveDir.mkdirs();
        }

        logger.log(Level.INFO, "Upload File Directory = " + fileSaveDir.getAbsolutePath());
        AtomicBoolean nameNeedDetecting = new AtomicBoolean(true);
        StringBuilder randFilename  = new StringBuilder();
        request.getParts()
                .stream()
                .forEach(part -> {
                    try{
                        if(nameNeedDetecting.compareAndSet(true, false)){
                            String name = part.getSubmittedFileName();
                            randFilename.append(UUID.randomUUID() + name.substring(name.lastIndexOf(".")));
                        }
                        part.write(uploadFileDir + randFilename);
request.setAttribute("upload_result", randFilename + " upload successfully");
                    } catch (IOException e){
                        logger.log(Level.ERROR, "Upload failed", e);
                        request.setAttribute("upload_result", randFilename + " upload failed ");
                    }
                });
        request.getRequestDispatcher("pages/main.jsp").forward(request, response);
    }

    public void destroy() {
    }
}