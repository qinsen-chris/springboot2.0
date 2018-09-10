package com.example.demo.utils;

import com.lowagie.text.pdf.BaseFont;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.resource.XMLResource;
import org.xml.sax.InputSource;

import java.io.*;

/**
 * Create by qs on 2018/9/7
 * email:qinsen@chinaredsun.com
 */
public class PdfHelper {

    /**
     * 通过 xhtml 字符串， 生成 pdf文件
     * @param xhtmlContent xhtml 内容
     * @param pdfPath 生成pdf路径
     * @param templatePath xhtml 模板路径，如果 xhtml 中引用了 图片，则需要该路径指定资源文件地址
     * @return
     *  @author xxj 2017年4月27日
     */
    public static boolean craetePdfByXhtml(String xhtmlContent,String pdfPath,String templatePath) {
        //宋体（对应css中的 属性 font-family: SimSun; /*宋体*/）
        String font1 ="C:/Windows/Fonts/simsun.ttc";

        //判断系统类型，加载字体文件
        java.util.Properties prop = System.getProperties();
        String osName = prop.getProperty("os.name").toLowerCase();
        System.out.println(osName);
        if (osName.indexOf("linux")>-1) {
            font1="/usr/share/fonts/simsun.ttc";
        }
        if(!new File(font1).exists()){
            throw new RuntimeException("字体文件不存在,影响导出pdf中文显示！"+font1);
        }

        OutputStream os = null;
        try {
            String url=templatePath;
            if (url.indexOf("://") == -1) {
                File f = new File(url);
                if (f.exists()) {
                    url = f.toURI().toURL().toString();
                }
            }
            System.err.println("xhtml 地址："+url);
            System.err.println("pdf 地址："+pdfPath);

            os = new FileOutputStream(pdfPath);

            ITextRenderer renderer = new ITextRenderer();
            ResourceLoaderUserAgent callback = new ResourceLoaderUserAgent(renderer.getOutputDevice());
            renderer.getSharedContext().setUserAgentCallback(callback);
            callback.setSharedContext(renderer.getSharedContext());
            //添加中文字体
            renderer.getFontResolver().addFont(font1, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            renderer.setDocumentFromString(xhtmlContent,url);

            renderer.layout();
            renderer.createPDF(os);

            os.close();
            os = null;
            return true;
        } catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
    /**
     * 通过xhtml 文件生成
     * @param xhtmlFilePath xhtml 文件路径
     * @param pdfFilePath pdf文件路径
     *  @author xxj 2017年4月27日
     */
    public static boolean craetePdf(String xhtmlFilePath,String pdfFilePath) {
        String url=xhtmlFilePath;
        String pdf=pdfFilePath;

        OutputStream os = null;
        try {
            if (url.indexOf("://") == -1) {
                // maybe it's a file
                File f = new File(url);
                if (f.exists()) {
                    url = f.toURI().toURL().toString();
                }
            }
            System.err.println("xhtml 地址："+url);
            System.err.println("pdf 地址："+pdf);

            os = new FileOutputStream(pdf);

			/*
			 * standard approach ITextRenderer renderer = new ITextRenderer();
			 *
			 * renderer.setDocument(url); renderer.layout();
			 * renderer.createPDF(os);
			 */

            ITextRenderer renderer = new ITextRenderer();
            ResourceLoaderUserAgent callback = new ResourceLoaderUserAgent(renderer.getOutputDevice());
            callback.setSharedContext(renderer.getSharedContext());
            renderer.getSharedContext().setUserAgentCallback(callback);

            Document doc = XMLResource.load(new InputSource(url)).getDocument();

            renderer.setDocument(doc, url);

            renderer.layout();
            renderer.createPDF(os);

            os.close();
            os = null;
            return true;
        } catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
    /**
     * 资源加载代理
     * @author xxj 2017年4月26日
     *
     */
    private static class ResourceLoaderUserAgent extends ITextUserAgent {
        public ResourceLoaderUserAgent(ITextOutputDevice outputDevice) {
            super(outputDevice);
        }

        protected InputStream resolveAndOpenStream(String uri) {
            InputStream is = super.resolveAndOpenStream(uri);
            System.out.println("加载pdf资源文件： " + uri);
            return is;
        }
    }
}
