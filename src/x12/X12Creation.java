package x12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xml.ParseXML;

public class X12Creation {
	public static void main(String[] args) {

		Context c = new Context('~', '*', ':');

		X12 x12 = new X12(c);
		Loop loop_isa = x12.addChild("ISA");

		// Add ISA segment to the loop
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmm");
	    String datetime = dateFormat.format(new Date());
	    String date = datetime.substring(0, 8);
	    String time = datetime.substring(9, 13);
		loop_isa.addSegment("ISA*00**00**ZZ*SENDERID*ZZ*RECEIVERID*" + date + "*" + time 
				+ "*U*00401*0000000001*0*T*:");

		// Add GS child loop to ISA loop
		Loop loop_gs = loop_isa.addChild("GS");
		// Add GS segment directly as a string
		loop_gs.addSegment("GS*1212*SENDERID*RECEIVERID*" + date + "*" + time + "*000000001*X*00401");

		Loop loop_st = loop_gs.addChild("ST");
		loop_st.addSegment("ST*996*000000001");
		
		ParseXML studentParse = new ParseXML();
		HashMap<String, String> studentXML = studentParse.toMap("Đào Văn Hùng.xml");
		
		Loop loop_student = loop_st.addChild("Student");
		loop_student.addSegment("N1*"+studentXML.get("name"));
		loop_student.addSegment("N2*"+studentXML.get("gender"));
		loop_student.addSegment("N3*"+studentXML.get("birthday"));
		loop_student.addSegment("N4*"+studentXML.get("address"));
		loop_student.addSegment("N5*"+studentXML.get("native"));

		Loop loop_se = loop_gs.addChild("SE");
		loop_se.addSegment("SE*XX*000000001");

		Loop loop_ge = loop_isa.addChild("GE");
		loop_ge.addSegment("GE*1*000000001");

		Loop loop_iea = x12.addChild("IEA");
		loop_iea.addSegment("IEA*1*000000001");

		// Since the SE loop has the incorrect segment count let us fix that.
		Integer count = loop_st.size();
		count += 1; // In the loop hierarchy SE is not a child loop of ST. So
		// when we get the rows in ST loop it does not have the count of SE.
		// so add 1.

		// We can set the count directly, like
		// loop_se.getSegment(0).setElement(1, count.toString());
		// this is just to show how to use the findLoop()
		List<Loop> trailer = x12.findLoop("SE");
		trailer.get(0).getSegment(0).setElement(1, count.toString());
		
		//another way
		List<Segment> se = x12.findSegment("SE");
		se.get(0).setElement(1, count.toString());

		//another way
		loop_se.getSegment(0).setElement(1, count.toString());
		
		System.out.println(loop_st.size());
		System.out.println(x12.toString());
		System.out.println(x12.toXML());
	}
}
