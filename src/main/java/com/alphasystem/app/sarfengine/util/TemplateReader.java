/**
 *
 */
package com.alphasystem.app.sarfengine.util;

import com.alphasystem.ApplicationException;
import com.alphasystem.SystemException;
import com.alphasystem.sarfengine.xml.model.ConjugationTemplate;
import com.alphasystem.sarfengine.xml.model.ObjectFactory;
import com.alphasystem.util.ZipFileEntry;

import java.io.File;

import static com.alphasystem.util.AppUtil.createTempFile;
import static com.alphasystem.util.JAXBUtil.marshall;
import static com.alphasystem.util.JAXBUtil.unmarshal;
import static com.alphasystem.util.ZipUtil.archiveFile;
import static com.alphasystem.util.ZipUtil.extractFile;
import static java.lang.String.format;
import static org.apache.commons.io.FilenameUtils.getBaseName;

/**
 * @author sali
 */
public class TemplateReader {

    public static final String SARF_SUFFIX = "sarfx";
    public static final String DOCX_FILE_EXTENSION = "docx";
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    private static final String EXTENSION_SEPARATOR = ".";
    private static final String XML_SUFFIX = "xml";
    private static final String XML_FILE_EXTENSION = format("%s%s", EXTENSION_SEPARATOR, XML_SUFFIX);
    private static final String TEMPLATE_FILE_PREFIX = "template";
    private static final String DEFAULT_ZIP_FILE_ENTRY = format("%s%s", TEMPLATE_FILE_PREFIX, XML_FILE_EXTENSION);
    private static final String SARF_FILE_EXTENSION = format("%s%s", EXTENSION_SEPARATOR, SARF_SUFFIX);

    private static TemplateReader instance = new TemplateReader();

    /**
     * Do not let any one instantiate this class
     */
    private TemplateReader() {
    }

    public static TemplateReader getInstance() {
        return instance;
    }

    public static File getDocxFile(File srcFile) {
        return getFile(srcFile, SARF_SUFFIX, DOCX_FILE_EXTENSION);
    }

    public static File getSarfxFile(File srcFile) {
        return getFile(srcFile, DOCX_FILE_EXTENSION, SARF_SUFFIX);
    }

    private static File attachExtension(File srcFile, String srcExt) {
        File finalFile = null;
        if (srcFile != null) {
            String absolutePath = srcFile.getAbsolutePath();
            if (absolutePath.toLowerCase().endsWith(srcExt)) {
                // extension already there
                return srcFile;
            }
            File parentFile = srcFile.getParentFile();
            String fileNameWithoutExtension = getBaseName(absolutePath);
            String fileNameWithExtension = format("%s.%s",
                    fileNameWithoutExtension, srcExt);
            finalFile = new File(parentFile, fileNameWithExtension);
        }
        return finalFile;
    }

    private static File getFile(File srcFile, String srcExt, String destExt) {
        File destFile = null;
        srcFile = attachExtension(srcFile, srcExt);
        if (srcFile != null) {
            String absolutePath = srcFile.getAbsolutePath();
            String fileNameWithoutExtension = getBaseName(absolutePath);
            String name = format("%s.%s", fileNameWithoutExtension, destExt);
            destFile = new File(srcFile.getParentFile(), name);
        }
        return destFile;
    }

    /**
     * @param file
     * @return
     * @throws ApplicationException
     */
    public ConjugationTemplate readFile(File file) throws ApplicationException {
        if (!file.exists()) {
            throw new SystemException(format("Attempt to open non-existent file {%s}", file.getAbsolutePath()));
        }
        ConjugationTemplate template;
        File tempFile;
        try {
            tempFile = createTempFile(XML_SUFFIX);
            String pathname = file.getAbsolutePath();
            extractFile(pathname, DEFAULT_ZIP_FILE_ENTRY, tempFile);
            template = unmarshal(ConjugationTemplate.class, tempFile);
        } catch (ApplicationException e) {
            throw e;
        }
        return template;
    }

    /**
     * @param parentFolder
     * @param fileNamePrefix
     * @return
     * @throws ApplicationException
     */
    public ConjugationTemplate readFile(File parentFolder, String fileNamePrefix)
            throws ApplicationException {
        File file = new File(parentFolder, format("%s%s", fileNamePrefix, SARF_FILE_EXTENSION));
        return readFile(file);
    }

    /**
     * @param archiveFile file with extension "sarfx"
     * @param template
     * @throws ApplicationException
     */
    public void saveFile(File archiveFile, ConjugationTemplate template) throws ApplicationException {
        File tempFile = null;
        try {
            tempFile = createTempFile(XML_SUFFIX);
            marshall(tempFile, ConjugationTemplate.class.getPackage().getName(), OBJECT_FACTORY.createChart(template));
            archiveFile(archiveFile, new ZipFileEntry(tempFile, DEFAULT_ZIP_FILE_ENTRY));
        } catch (ApplicationException e) {
            throw e;
        } finally {

        }
    }

    /**
     * @param outDir
     * @param fileNamePrefix
     * @param template
     * @throws ApplicationException
     */
    public void saveFile(File outDir, String fileNamePrefix, ConjugationTemplate template) throws ApplicationException {
        File archiveFile = new File(outDir, format("%s%s", fileNamePrefix, SARF_FILE_EXTENSION));
        saveFile(archiveFile, template);
    }

}
