package xml;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

public class XMLExport {

	public static void main(String[] args) throws ParseException, ClassNotFoundException, 
	SQLException, JAXBException, TransformerException, XMLStreamException {
		Date birthday = new SimpleDateFormat("dd-MM-yyyy").parse("02-07-1993");
		StudentXML studentXML = new StudentXML();
		studentXML.createXMLFile(1, 1, birthday, 2, 1);
	}

}
