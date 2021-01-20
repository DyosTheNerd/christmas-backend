package jp.co.axa.apidemo.controllers;

import com.lowagie.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


@RestController
@RequestMapping("/api/v1")
public class WishListController {


    @GetMapping("/wishlist/{wishlistID}/document")
    public ResponseEntity getWishListPDF(@PathVariable(name="wishlistID")Long employeeId, HttpServletResponse response) {


        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);


        IContext context = new IContext() {
            @Override
            public Locale getLocale() {
                return Locale.UK;
            }

            @Override
            public boolean containsVariable(String s) {
                return false;
            }

            @Override
            public Set<String> getVariableNames() {
                HashSet<String> hashset = new HashSet<String>();

                return hashset;
            }

            @Override
            public Object getVariable(String s) {
                return null;
            }
        };



        String html = templateEngine.process("templates//Wishlist",context);

        System.out.println(html);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();


        try {
            FileOutputStream fos = new FileOutputStream("D://temp//temp.pdf");
            renderer.createPDF(fos);
            renderer.finishPDF();
            fos.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }



        try {
            File file = new File("D://temp//temp.pdf");
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));


            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment;filename=wishlist.pdf")
                    .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                    .body(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;

    }

}
