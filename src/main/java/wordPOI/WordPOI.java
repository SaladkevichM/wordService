package wordPOI;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.POIXMLProperties;
import org.apache.poi.POIXMLProperties.CustomProperties;
import org.apache.poi.POIXMLProperties.ExtendedProperties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties;

/**
 * Hello world!
 *
 */
public class WordPOI  
{
    public static void main( String[] args ) throws InvalidFormatException, IOException
    {	
		
	System.out.println("Working Directory = " + System.getProperty("user.dir"));
	
	String docIn = System.getProperty("user.dir").concat("\\").concat("RKK.docx");
	String docOut = System.getProperty("user.dir").concat("\\").concat("RKK_out.docx");
	
	XWPFDocument doc = new XWPFDocument(OPCPackage.open(docIn));	
	POIXMLProperties props = doc.getProperties();
	CustomProperties cp = props.getCustomProperties();
	
	if (cp != null) {
	    
	    List<CTProperty> ctProperties = cp.getUnderlyingProperties().getPropertyList();
	    for (CTProperty ctp : ctProperties) {
		
		System.out.println(ctp.getName());
		
		if (ctp.getName().equals("regnum")) {
		    ctp.setLpwstr("Test123321qweqwezzzzz");
		}
		

	    }
	}
	
	doc.enforceUpdateFields();
	
	/*
	for (XWPFParagraph p : doc.getParagraphs()) {
	    List<XWPFRun> runs = p.getRuns();
	    if (runs != null) {
	        for (XWPFRun r : runs) {
	            String text = r.getText(0);
	            if (text != null && text.contains("~b РЕГ.НОМ e~")) {
	                text = text.replace("~b РЕГ.НОМ e~", "haystack");
	                r.setText(text, 0);
	            }
	        }
	    }
	}
	for (XWPFTable tbl : doc.getTables()) {
	   for (XWPFTableRow row : tbl.getRows()) {
	      for (XWPFTableCell cell : row.getTableCells()) {
	         for (XWPFParagraph p : cell.getParagraphs()) {
	            for (XWPFRun r : p.getRuns()) {
	              String text = r.getText(0);
	              if (text != null && text.contains("~b РЕГ.НОМ e~")) {
	                text = text.replace("~b РЕГ.НОМ e~", "haystack");
	                r.setText(text,0);
	              }
	            }
	         }
	      }
	   }
	}*/
	
	doc.write(new FileOutputStream(docOut));
	doc.close();
	
    }
}
