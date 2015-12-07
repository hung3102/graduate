package userinterface;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class EtranscriptEdit {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text txtNam;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Text text_18;
	private Text text_19;
	private Text text_20;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EtranscriptEdit window = new EtranscriptEdit();
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
		shell = new Shell(SWT.CLOSE | SWT.MIN | SWT.TITLE);
		shell.setSize(new Point(979, 697));
		shell.setText("SWT Application");
		shell.setLayout(null);
	    
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayout(new FillLayout());		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
//		Composite composite = new Composite(shell, SWT.NONE);
		
		composite.setBounds(0, 0, 973, 691);
		composite.setTouchEnabled(true);
		composite.setLayout(null);
		
		Label lblBGioDc = new Label(composite, SWT.NONE);
		lblBGioDc.setBounds(406, 10, 178, 15);
		lblBGioDc.setText("B\u1ED8 GI\u00C1O D\u1EE4C V\u00C0 \u0110\u00C0O T\u1EA0O");
		
		Label lblHcB = new Label(composite, SWT.NONE);
		lblHcB.setBounds(380, 137, 249, 71);
		lblHcB.setFont(SWTResourceManager.getFont("Segoe UI", 40, SWT.BOLD));
		lblHcB.setToolTipText("");
		lblHcB.setText("H\u1ECCC B\u1EA0");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(390, 213, 178, 21);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Label lblHVTn = new Label(composite, SWT.NONE);
		lblHVTn.setBounds(412, 309, 146, 29);
		lblHVTn.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblHVTn.setText("H\u1ECD v\u00E0 t\u00EAn h\u1ECDc sinh");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(409, 343, 148, 31);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		text_1.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.ITALIC));
		
		Label lblS = new Label(composite, SWT.NONE);
		lblS.setBounds(354, 474, 30, 21);
		lblS.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD | SWT.ITALIC));
		lblS.setText("S\u1ED0");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(390, 474, 178, 21);
		text_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(42, 525, 847, 2);
		
		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(449, 31, 64, 2);
		
		Label lblHVTn_1 = new Label(composite, SWT.NONE);
		lblHVTn_1.setBounds(42, 553, 64, 15);
		lblHVTn_1.setText("H\u1ECD v\u00E0 t\u00EAn :");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(122, 550, 482, 21);
		
		Label lblGiiTnh = new Label(composite, SWT.NONE);
		lblGiiTnh.setBounds(703, 553, 55, 15);
		lblGiiTnh.setText("Gi\u1EDBi t\u00EDnh :");
		
		txtNam = new Text(composite, SWT.BORDER);
		txtNam.setBounds(776, 550, 113, 21);
		txtNam.setText("");
		
		Label lblNgySinh = new Label(composite, SWT.NONE);
		lblNgySinh.setBounds(40, 591, 66, 15);
		lblNgySinh.setText("Ng\u00E0y sinh :");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(122, 588, 43, 21);
		
		Label lblThng = new Label(composite, SWT.NONE);
		lblThng.setBounds(171, 591, 31, 15);
		lblThng.setText("th\u00E1ng");
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setBounds(208, 588, 43, 21);
		
		Label lblNm = new Label(composite, SWT.NONE);
		lblNm.setBounds(257, 591, 30, 15);
		lblNm.setText("n\u0103m");
		
		text_6 = new Text(composite, SWT.BORDER);
		text_6.setBounds(287, 588, 55, 21);
		
		Label lblNiSinh = new Label(composite, SWT.NONE);
		lblNiSinh.setBounds(42, 630, 55, 15);
		lblNiSinh.setText("N\u01A1i sinh :");
		
		text_7 = new Text(composite, SWT.BORDER);
		text_7.setBounds(122, 627, 665, 21);
		
		Label lblDnTc = new Label(composite, SWT.NONE);
		lblDnTc.setBounds(42, 669, 55, 15);
		lblDnTc.setText("D\u00E2n t\u1ED9c :");
		
		text_8 = new Text(composite, SWT.BORDER);
		text_8.setBounds(122, 666, 76, 21);
		
		Label lblThuciTng = new Label(composite, SWT.NONE);
		lblThuciTng.setBounds(257, 669, 105, 15);
		lblThuciTng.setText("Thu\u1ED9c \u0111\u1ED1i t\u01B0\u1EE3ng :");
		
		text_9 = new Text(composite, SWT.BORDER);
		text_9.setBounds(362, 666, 482, 21);
		
		Label lblChHin = new Label(composite, SWT.NONE);
		lblChHin.setBounds(42, 709, 80, 15);
		lblChHin.setText("Ch\u1ED7 \u1EDF hi\u1EC7n t\u1EA1i :");
		
		Label lblHTnNgi = new Label(composite, SWT.NONE);
		lblHTnNgi.setBounds(42, 743, 64, 15);
		lblHTnNgi.setText("H\u1ECD t\u00EAn cha :");
		
		text_10 = new Text(composite, SWT.BORDER);
		text_10.setBounds(126, 706, 763, 21);
		
		text_11 = new Text(composite, SWT.BORDER);
		text_11.setBounds(122, 740, 384, 21);
		
		Label lblHTnM = new Label(composite, SWT.NONE);
		lblHTnM.setBounds(42, 777, 64, 15);
		lblHTnM.setText("H\u1ECD t\u00EAn m\u1EB9 :");
		
		text_12 = new Text(composite, SWT.BORDER);
		text_12.setBounds(122, 774, 384, 21);
		
		Label lblNghNghip = new Label(composite, SWT.NONE);
		lblNghNghip.setBounds(544, 743, 81, 15);
		lblNghNghip.setText("Ngh\u1EC1 nghi\u1EC7p :");
		
		text_13 = new Text(composite, SWT.BORDER);
		text_13.setBounds(631, 740, 258, 21);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(544, 777, 81, 15);
		label_2.setText("Ngh\u1EC1 nghi\u1EC7p :");
		
		text_14 = new Text(composite, SWT.BORDER);
		text_14.setBounds(631, 777, 258, 21);
		
		Label lblHTnNgi_1 = new Label(composite, SWT.NONE);
		lblHTnNgi_1.setBounds(42, 810, 123, 15);
		lblHTnNgi_1.setText("H\u1ECD t\u00EAn ng\u01B0\u1EDDi gi\u00E1m h\u1ED9 :");
		
		text_15 = new Text(composite, SWT.BORDER);
		text_15.setBounds(178, 807, 335, 21);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(544, 810, 81, 15);
		label_3.setText("Ngh\u1EC1 nghi\u1EC7p :");
		
		text_16 = new Text(composite, SWT.BORDER);
		text_16.setBounds(631, 807, 258, 21);
		
		Label lblNgy = new Label(composite, SWT.NONE);
		lblNgy.setBounds(513, 881, 30, 15);
		lblNgy.setText("Ng\u00E0y");
		
		text_17 = new Text(composite, SWT.BORDER);
		text_17.setBounds(547, 878, 37, 21);
		
		Label lblThng_1 = new Label(composite, SWT.NONE);
		lblThng_1.setBounds(590, 881, 35, 15);
		lblThng_1.setText("th\u00E1ng");
		
		text_18 = new Text(composite, SWT.BORDER);
		text_18.setBounds(631, 878, 37, 21);
		
		Label lblNm_1 = new Label(composite, SWT.NONE);
		lblNm_1.setBounds(672, 881, 30, 15);
		lblNm_1.setText("n\u0103m");
		
		text_19 = new Text(composite, SWT.BORDER);
		text_19.setBounds(703, 878, 55, 21);
		
		Label lblHiuTrng = new Label(composite, SWT.NONE);
		lblHiuTrng.setBounds(615, 917, 76, 15);
		lblHiuTrng.setText("Hi\u1EC7u tr\u01B0\u1EDFng");
		
		text_20 = new Text(composite, SWT.BORDER);
		text_20.setBounds(544, 938, 243, 21);
		
		Label lblQuTrnhHc = new Label(composite, SWT.NONE);
		lblQuTrnhHc.setBounds(401, 1034, 228, 29);
		lblQuTrnhHc.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.BOLD));
		lblQuTrnhHc.setText("QU\u00C1 TR\u00CCNH H\u1ECCC T\u1EACP");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(42, 1093, 847, 211);
		table.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(118);
		tblclmnNewColumn.setText("         N\u0103m h\u1ECDc");
		
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(69);
		tblclmnNewColumn_1.setText("     L\u1EDBp");
		
		TableColumn tblclmnTnTrngHuyn = new TableColumn(table, SWT.NONE);
		tblclmnTnTrngHuyn.setWidth(465);
		tblclmnTnTrngHuyn.setText("                    T\u00EAn tr\u01B0\u1EDDng, huy\u1EC7n (qu\u1EADn, th\u1ECB x\u00E3, TP thu\u1ED9c t\u1EC9nh) t\u1EC9nh (TP)");
		
		TableColumn tblclmnHiuTrng = new TableColumn(table, SWT.NONE);
		tblclmnHiuTrng.setWidth(191);
		tblclmnHiuTrng.setText("                Hi\u1EC7u tr\u01B0\u1EDFng");
		
		scrolledComposite.setBounds(0, 0, 973, 665);
		scrolledComposite.setContent(composite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
