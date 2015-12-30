package xml;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import data.Address;
import data.Commune;
import data.District;
import data.Name;
import data.Province;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;


public class StudentXML {
	
    StreamResult result;
    
    private XMLStreamWriter xMLStreamWriter;
	public void createXMLFile(int nameID, int gender, Date birthday, int addressID, int nativeID) 
			throws ClassNotFoundException, SQLException, TransformerException, XMLStreamException {
		try {	         
			 XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			 String path = Paths.get(".").toAbsolutePath().normalize().toString();
			 String fs = System.getProperty("file.separator");
			 this.xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(new FileWriter(
						path + fs + "resources" + fs + "xml" + fs + this.getFileName(nameID)));
	         
	         this.xMLStreamWriter.writeStartDocument();
	         this.xMLStreamWriter.writeStartElement("student");

	         this.setName(nameID);
	         this.setGender(gender);
	         this.setBirthday(birthday);
	         this.setAddress(addressID);
	         this.setNative(nativeID);

	         this.xMLStreamWriter.writeEndElement();
	         this.xMLStreamWriter.writeEndDocument();

	      } catch (XMLStreamException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } finally {
	    	  this.xMLStreamWriter.flush();
		      this.xMLStreamWriter.close();
	      }
	}
	
	
	
	public String getFileName(int nameID) throws ClassNotFoundException, SQLException {
		Name name = new Name().searchNameByID(nameID);
		String n = null;
		if(name != null) {
			n = name.fullName;
		}
		return n+".xml";
		
	}
	
	private void setName(int nameID) throws ClassNotFoundException, SQLException, XMLStreamException, IOException {
		Name name = new Name().searchNameByID(nameID);
		String n = null;
		if(name != null) {
			n = name.fullName;
		}
		
		this.xMLStreamWriter.writeStartElement("name");			
        this.xMLStreamWriter.writeCharacters(n);
        this.xMLStreamWriter.writeEndElement();
	}
	
	private void setGender(int gender) throws XMLStreamException{
		String gen = null;
		if(gender == 1) {
			gen = "Nam";
		} else if(gender == 0) {
			gen = "Nữ";
		}
		
		this.xMLStreamWriter.writeStartElement("gender");			
        this.xMLStreamWriter.writeCharacters(gen);
        this.xMLStreamWriter.writeEndElement();
	}
	
	private void setBirthday(Date birthday) throws XMLStreamException {
		String birthdayString = null;
		if(birthday != null) {
			birthdayString = new SimpleDateFormat("dd/MM/yyyy").format(birthday);
		}
		
		this.xMLStreamWriter.writeStartElement("birthday");			
        this.xMLStreamWriter.writeCharacters(birthdayString);
        this.xMLStreamWriter.writeEndElement();
	}
	
	private void setAddress(int addressID) throws ClassNotFoundException, SQLException, XMLStreamException {
		String addressContent = this.setAddressContent(addressID);
		this.xMLStreamWriter.writeStartElement("address");			
        this.xMLStreamWriter.writeCharacters(addressContent);
        this.xMLStreamWriter.writeEndElement();
	}
	
	private void setNative(int addressID) throws ClassNotFoundException, SQLException, XMLStreamException {
		String addressContent = this.setAddressContent(addressID);
		this.xMLStreamWriter.writeStartElement("native");			
        this.xMLStreamWriter.writeCharacters(addressContent);
        this.xMLStreamWriter.writeEndElement();
	}
	
	private String setAddressContent(int addressID) throws ClassNotFoundException, SQLException {
		Address address = new Address().seachAddressByID(addressID);
		String addressContent = "";
		if(address != null) {
			if(address.houseNumber != null) {
				addressContent += "Nhà số " + address.houseNumber + ", ";
			}
			if(address.street != null) {
				addressContent += address.street + ", ";
			} 
			if(address.hamlet != null) {
				addressContent += "xóm " + address.hamlet + ", ";
			}
			if(address.communeID != 0) {
				int communeID = address.communeID;
				Commune commune = new Commune().seachCommuneByID(communeID);
				if(commune != null) {
					addressContent += "xã(phường) " + commune.name + ", ";
				}
			}
			if(address.districtID != 0) {
				int districtID = address.districtID;
				District district = new District().seachDistrictByID(districtID);
				if(district != null) {
					addressContent += "huyện(quận) " + district.name + ", ";
				}
			} 
			if(address.provinceID != 0) {
				int provinceID = address.provinceID;
				Province province = new Province().seachProvinceByID(provinceID);
				if(province != null) {
					addressContent += "tỉnh(TP) " + province.name + ", ";
				}
			}
		}
		
		if(addressContent.length() > 1) {
			return addressContent.substring(0, addressContent.length()-2);
		} else {
			return addressContent;
		}
	}
}
