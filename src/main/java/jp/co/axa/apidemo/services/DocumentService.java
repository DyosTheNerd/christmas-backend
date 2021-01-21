package jp.co.axa.apidemo.services;


import org.thymeleaf.context.IContext;

import java.io.OutputStream;

public interface DocumentService {

    void renderPDFIntoStreamForTemplate(String localResourceTemplate, OutputStream stream, IContext context);
}
