package x12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class X12toXML {
	
	private XMLStreamWriter xMLStreamWriter;
	
	private void createXML(File inFile) throws XMLStreamException, IOException, URISyntaxException {
		XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
		
		File outFile = new ChooseFile().saveXMLFile();
		this.xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(new FileWriter(outFile));
		
        this.xMLStreamWriter.writeStartDocument();
        this.xMLStreamWriter.writeStartElement("student");
		this.X12Parse(inFile);
		this.xMLStreamWriter.writeEndElement();
        this.xMLStreamWriter.writeEndDocument();

        this.xMLStreamWriter.flush();
        this.xMLStreamWriter.close();
	}
	
	private void X12Parse(File f1) throws URISyntaxException {
		X12 x12 = null;
		Cf cf835 = loadCf(); // candidate for dependency injection
		Parser parser = new X12Parser(cf835);
		
		try {
			x12 = (X12) parser.parse(f1);
			
			List<Loop> studentLoops = x12.findLoop("Student");
			for (Loop studentLoop : studentLoops) {
				for(Loop loop : studentLoop.childList()) {
					System.out.println(loop);
					for (Segment s : loop) {
						this.checkLabel(s, "N1", "name", 1); 
						this.checkLabel(s, "N2", "gender", 1);
						this.checkLabel(s, "N3", "birthday", 1);
						this.checkLabel(s, "N4", "address", 1);
						this.checkLabel(s, "N5", "native", 1);
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws XMLStreamException, IOException, URISyntaxException {
		X12toXML x12toXML = new X12toXML();
		File f1 = new ChooseFile().getEDIFile();
		x12toXML.createXML(f1);
	}

	// Alternately can be loaded using Spring/DI 
	private static Cf loadCf() {
		Cf cfX12 = new Cf("X12");
		Cf cfISA = cfX12.addChild("ISA", "ISA");
		Cf cfGS = cfISA.addChild("GS", "GS");
		Cf cfST = cfGS.addChild("ST", "ST", "835", 1);
		
		Cf cfStudent = cfST.addChild("Student", "STU");
		Cf cfName = cfStudent.addChild("Name", "N1");
		Cf cfGender = cfStudent.addChild("Gender", "N2");
		Cf cfBirthday = cfStudent.addChild("Birthday", "N3");
		Cf cfAddress = cfStudent.addChild("Address", "N4");
		Cf cfNative = cfStudent.addChild("Native", "N5");
		
		cfISA.addChild("GE", "GE");
		cfX12.addChild("IEA", "IEA");

		return cfX12;
	}
	
	private void checkLabel(Segment s, String label, String name, int elementpos) throws XMLStreamException {
		if (s.getElement(0).equals(label)) {
			this.xMLStreamWriter.writeStartElement(name);
			if(elementpos >=0 ) {
				this.xMLStreamWriter.writeCharacters(s.getElement(elementpos));
			}
			this.xMLStreamWriter.writeEndElement();
		}
	}
}
