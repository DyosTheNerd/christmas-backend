package jp.co.axa.apidemo.services;

import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static String HTMLSUFFIX = ".html";

    @Override
    public void renderPDFIntoStreamForTemplate(String localResourceTemplate, OutputStream outputStream, IContext context) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(HTMLSUFFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);



        String html = templateEngine.process(localResourceTemplate,context);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();

        try {
            renderer.createPDF(outputStream);
            renderer.finishPDF();
            outputStream.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }


    }
}
