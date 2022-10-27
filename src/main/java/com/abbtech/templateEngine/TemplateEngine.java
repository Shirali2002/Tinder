package com.abbtech.templateEngine;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Map;

public class TemplateEngine {

    private final Configuration config;

    public TemplateEngine() throws IOException, URISyntaxException {
        this.config = new Configuration(Configuration.VERSION_2_3_28) {{
            String path =
                    Paths.get(TemplateEngine.class.getResource("/WEB-INF").toURI())
                            .toFile().getAbsolutePath();
            setDirectoryForTemplateLoading(new File(path));
            setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
            setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            setLogTemplateExceptions(false);
            setWrapUncheckedExceptions(true);
        }};
    }

    public void render(String templateFile, Map<String, Object> data, HttpServletResponse resp)
            throws IOException {
        try {
            resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            config.getTemplate(templateFile).process(data, resp.getWriter());
        } catch (TemplateException ex) {
            ex.printStackTrace();
        }
    }

}
