package userinterface;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import javax.swing.JFrame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import data.*;
import xml.StudentXML;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Interface {

	protected Shell shell;
	private Text text_4;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Interface window = new Interface();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(new Point(563, 332));
		shell.setMinimumSize(new Point(300, 100));
		shell.setText("Etranscript Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setTouchEnabled(true);
		composite.setLayout(null);
		
		Label lblTnhtp = new Label(composite, SWT.NONE);
		lblTnhtp.setBounds(10, 21, 55, 15);
		lblTnhtp.setText("T\u1EC9nh/TP:");
		
		Label lblHuynqun = new Label(composite, SWT.NONE);
		lblHuynqun.setBounds(10, 57, 84, 15);
		lblHuynqun.setText("Huy\u1EC7n/Qu\u1EADn:");
		
		Label lblXphng = new Label(composite, SWT.NONE);
		lblXphng.setBounds(10, 93, 70, 15);
		lblXphng.setText("X\u00E3/Ph\u01B0\u1EDDng:");
		
		Label lblTrng = new Label(composite, SWT.NONE);
		lblTrng.setBounds(315, 21, 55, 15);
		lblTrng.setText("Tr\u01B0\u1EDDng:");
		
		Label lblHTn = new Label(composite, SWT.NONE);
		lblHTn.setBounds(315, 60, 55, 15);
		lblHTn.setText("H\u1ECD t\u00EAn:");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(376, 57, 128, 21);
		
		Label lblNgySinh = new Label(composite, SWT.NONE);
		lblNgySinh.setBounds(309, 99, 61, 15);
		lblNgySinh.setText("Ng\u00E0y sinh:");
		
		CCombo combo = new CCombo(composite, SWT.BORDER);
		combo.setText("--Chọn tỉnh--");
		Province province = new Province();
		try {
			List<Province> provinceArray = province.getProvince();
			List<String> provinceNameArray = new ArrayList<String>();
			for(Province pr : provinceArray){
				provinceNameArray.add(pr.name);
			}
			combo.setItems(provinceNameArray.toArray(new String[0]));
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		combo.setBounds(108, 21, 124, 21);
		
		CCombo combo_3 = new CCombo(composite, SWT.BORDER);
		combo_3.setEnabled(false);
		combo_3.setBounds(376, 21, 128, 21);
		
		CCombo combo_2 = new CCombo(composite, SWT.BORDER);
		combo_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo_3.setEnabled(true);
				combo_3.setText("--Chọn trường--");
				School school = new School();
				int communeID = combo_2.getSelectionIndex()+1;
				try {
					List<School> schoolArray = school.getSchoolByCommuneID(communeID);
					List<String> schoolNameArray = new ArrayList<String>();
					for(School sch:schoolArray) {
						schoolNameArray.add(sch.name);
					}
					combo_3.setItems(schoolNameArray.toArray(new String[0]));
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		combo_2.setEnabled(false);
		combo_2.setBounds(108, 93, 124, 21);
		
		CCombo combo_1 = new CCombo(composite, SWT.BORDER);
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo_2.setEnabled(true);
				combo_2.setText("--Chọn xã--");
				Commune commune = new Commune();
				int districtID = combo_1.getSelectionIndex() + 1;
				try {
					List<Commune> communeArray = commune.getCommune(districtID);
					List<String> communeNameArray = new ArrayList<String>();
					for(Commune cm:communeArray) {
						communeNameArray.add(cm.name);
					}
					combo_2.setItems(communeNameArray.toArray(new String[0]));
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		combo_1.setEnabled(false);
		combo_1.setBounds(108, 57, 124, 21);
		
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo_1.setEnabled(true);
				combo_1.setText("--Chọn huyện--");
				District district = new District();
				int provinceID = combo.getSelectionIndex()+1;
				try {
					List<District> districtArray = district.getDistrictByProvinceID(provinceID);
					List<String> districtNameArray = new ArrayList<String>();
					for(District ds:districtArray) {
						districtNameArray.add(ds.name);
					}
					combo_1.setItems(districtNameArray.toArray(new String[0]));
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(376, 96, 128, 21);
		
		Button btnTmKim = new Button(composite, SWT.NONE);
		btnTmKim.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int provinceID = combo.getSelectionIndex() >=0 ? combo.getSelectionIndex()+1 : -1;
				int districtID = combo_1.getSelectionIndex() >=0 ? combo_1.getSelectionIndex()+1 : -1;
				int communeID = combo_2.getSelectionIndex() >=0 ? combo_2.getSelectionIndex()+1 : -1;
				int schoolID = combo_3.getSelectionIndex() >=0 ? combo_3.getSelectionIndex()+1 : -1;
				String name = text_4.getText() == "" ? null : text_4.getText();
				String birthdayString = text.getText() == "" ? null : text.getText();
				Date birthday;
				List<Name> nameArray = new ArrayList<Name>();
				List<Address> nativeArray = new ArrayList<Address>();
				List<Student> studentArray = new ArrayList<Student>();
				List<Student> studentArray1 = new ArrayList<Student>();
				
				try {
					if(birthdayString != null) {
						birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthdayString);
					} else {
						birthday = null;
					}
					
					if(provinceID >=0) {
						nativeArray = new Address().search(null, communeID, districtID, provinceID, null, null);
					}
					
					int flag = 0;
					if(name != null) {
						flag = 1;
						nameArray = new Name().search(-1, name);
						for(Name n:nameArray) {
							int nameID = n.id; 
							if(provinceID >= 0) {
								for(Address adr:nativeArray) {
									int nativeID = adr.id;
									studentArray1 = new Student().search(-1, nameID, -1, -1, nativeID, -1, -1, birthday, schoolID);
									studentArray.addAll(studentArray1);
								}
							} else {
								studentArray1 = new Student().search(-1, nameID, -1, -1, -1, -1, -1, birthday, schoolID);
								studentArray.addAll(studentArray1);
							}
						}
					} else if(provinceID >= 0) {
						flag = 1;
						for(Address adr:nativeArray) {
							int nativeID = adr.id;
							studentArray1 = new Student().search(-1, -1, -1, -1, nativeID, -1, -1, birthday, schoolID);
							studentArray.addAll(studentArray1);
						}
					} else if(birthdayString != null) {
						flag = 1;
						studentArray1 = new Student().search(-1, -1, -1, -1, -1, -1, -1, birthday, schoolID);
						studentArray.addAll(studentArray1);
					}
					
					if(flag == 1 && !studentArray.isEmpty()) {
						for(Student std:studentArray) {
							StudentXML studentXML = new StudentXML();
							studentXML.createXMLFile(std.nameID, std.gender, std.birthday, std.addressID, 
										std.nativeID);
							JOptionPane.showMessageDialog(new JFrame(), "File "+ studentXML.getFileName(std.nameID) 
								+ " đã được tạo" );
						}
						EtranscriptEdit.main(null);
					} else if(flag == 1){
						JOptionPane.showMessageDialog(new JFrame(), "Không tìm thấy kết quả!");
					}
				} catch (ClassNotFoundException | SQLException | TransformerException | XMLStreamException e1) {
					e1.printStackTrace();
				} catch (ParseException pe1) {
					JOptionPane.showMessageDialog(new JFrame(), "Định dạng ngày tháng bạn nhập chưa đúng!");
				}
				
			}
		});
		btnTmKim.setBounds(234, 153, 84, 35);
		btnTmKim.setText("T\u00ECm ki\u1EBFm");
		
		Label lblddmmyyyy = new Label(composite, SWT.NONE);
		lblddmmyyyy.setBounds(401, 118, 84, 15);
		lblddmmyyyy.setText("(dd/mm/yyyy)");   
	}
}
