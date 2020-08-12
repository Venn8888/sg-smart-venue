package com.sg.framework.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.io.ClassPathResource;

import com.sg.framework.exception.BusinessException;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TemplateEngine {
	public static final String TEMPLATE_PATH = "/";
	private final Map<String, Template> templates = new ConcurrentHashMap<String, Template>();

	private boolean freemarkerCaching = true;
	private String encoding="UTF-8";
	private Configuration cfg;
	
	
	public TemplateEngine() {
		super();
	}


	/**
	 * @param params 所有参数以MAP形式传递。
	 * @param fileName 模板文件名称
	 * @return
	 * @throws Exception 
	 * @throws BusinessException
	 */
	public String renderTemplate(Map<String, Object> params, String fileName) throws Exception {
		StringWriter sw = new StringWriter();
		String html = null;
		try {
//			if(!fileName.endsWith(".ftl")){
//				fileName = fileName + ".ftl";
//			}
			Template template = this.getTemplate(fileName);			
			template.process(params, sw);
			html = sw.toString();
		} catch (Exception e) {
			//IDEPlatform.logException(e);
			throw e;
		} finally {
			try {
				sw.close();
			} catch (IOException e) {
				//IDEPlatform.logException(e);
			}
		}
		
		return html;
	}
	
	
	/**
	 * @param params 所有参数以MAP形式传递。
	 * @param fileName 模板文件名称
	 * @return
	 * @throws Exception 
	 * @throws BusinessException
	 */
	public void generateTemplate(Map<String, Object> params, String fileName, String outputFile) throws Exception {
		StringWriter sw = new StringWriter();
		try {
//			if(!fileName.endsWith(".ftl")){
//				fileName = fileName + ".ftl";
//			}
			Template template = this.getTemplate(fileName);			
			template.process(params, sw);
			FileUtil.createFile(outputFile, sw.toString());
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				sw.close();
			} catch (IOException e) {}
		}
	}
	
	/**
	 * 获得@Template对像，根据全局配制决定是否对此对像进行缓存。
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private Template getTemplate(String fileName) throws IOException {
		Template template = null;
		if (freemarkerCaching && templates.containsKey(fileName)) {
			template = templates.get(fileName);
		} else {
			cfg = this.getConfiguration();
			try {
				template = cfg.getTemplate(fileName);
			} catch (IOException e) {
				throw e;
			}
			
			if (freemarkerCaching) {
				this.cacheTemplates(template, fileName);
			}
		}
		
		return template;
	}
	
	/**
	 * 根据配制文件定义常量进行缓存处理。
	 * 
	 * @param template 模块对像
	 * @param fileName 模板文件名称
	 */
	private void cacheTemplates(Template template, String fileName) {
		templates.put(fileName, template);
	}
	
	/**
	 * 获得@Configuration对像，保持当前对像只有一个实例。
	 * @return Configuration
	 * @throws IOException 
	 */
	private Configuration getConfiguration() throws IOException {
		if (cfg == null) {
			cfg = new Configuration();
			ClassPathResource resource = new ClassPathResource(TEMPLATE_PATH);
			String path = resource.getFile().getPath();
//			String path = getClass().getClassLoader().getResource(TEMPLATE_PATH).getPath();
//			String path = this.getProject().getFolder("template").getLocation().toString();
			cfg.setDirectoryForTemplateLoading(new File(path));
//			cfg.setClassForTemplateLoading(this.getClass(), TEMPLATE_PATH);
			cfg.setEncoding(Locale.getDefault(), encoding);
		} 
		
		return cfg;
	}
}
