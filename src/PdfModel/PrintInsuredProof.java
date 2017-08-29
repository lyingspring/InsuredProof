package PdfModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import comm.oracle_jdbc;

public class PrintInsuredProof {
	/**
	 * @author maoxj
	 * @param args
	 */
	// public static void main(String[] args) {
	//
	// PrintInsuredProof tPDFPractise = new PrintInsuredProof();
	// try {
	// tPDFPractise.createPdfFile();
	//
	// // tPDFPractise.createPDFFile();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * ����PDF
	 * 
	 * @author maoxj
	 * @throws Exception
	 */
	public void createPdfFile(Long aac001,String file_url) throws Exception {
		oracle_jdbc jdbc = new oracle_jdbc();
		String sql = "select a.aac001," + " aac003," + "aae135," + "sbp_public.f_Get_Codeview('AAC004',aac004) aac004,"
				+ " decode(min(decode(b.aae140, '10', aac031, '11', aac031))," + "  '1'," + "  '�α��ɷ�',"
				+ " 'δ�α��ɷ�') yl," + " decode(max( decode(b.aae140, '20', c.aac031,0)),'1'," + "  '�α��ɷ�',"
				+ " 'δ�α��ɷ�') yb," + " decode(max( decode(b.aae140, '30', c.aac031,0)),'1'," + " '�α��ɷ�',"
				+ " 'δ�α��ɷ�') gs," + "   decode(max( decode(b.aae140, '40', c.aac031,0)),'1'," + " '�α��ɷ�',"
				+ " 'δ�α��ɷ�') sy," + " decode(max( decode(b.aae140, '50', c.aac031,0)),'1'," + " '�α��ɷ�',"
				+ " 'δ�α��ɷ�') shiy" + " from ac01 a, ac02 b, ac20 c where a.aac001 = b.aac001 and a.aac001="
				+aac001+ " and b.aaz159 = c.aaz159 group by a.aac001, aac003, aae135, aac004";

		List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();
		listmap = jdbc.createSQLQuery(sql);//��Ա������Ϣ�Ͳα�״̬
		if (listmap.isEmpty()) {
			throw new Exception("�Ҳ�������Ա�α���Ϣ��");
		}
		
		sql="select b.aab001 aab001,c.aab004 aab004 from personcollect a,ab01 b,ae10 c "
				+ "where a.aaz001=b.aaz001 and b.aaz001=c.aaz001 and max_aae003+200>=to_char(sysdate,'yyyymm') and  a.aac001="+aac001;
		List<HashMap<String, String>> listmap2 = new ArrayList<HashMap<String, String>>();
		jdbc.getConnection();//�����ٴο���
		listmap2 = jdbc.createSQLQuery(sql);//��λ�䶯��Ϣ
		if (listmap2.isEmpty()) {
			throw new Exception("�Ҳ�����λ�䶯��Ϣ��");
		}
		
		sql="select t.*,(select max(aab001) from ac43 a, ab01 b "
				+ "where a.aaz001 = b.aaz001 and a.aae140 in ('11', '10', '20') and a.aae003=t.aae001||t.aae003 and "
				+ "EAC003>0 and aae017='0' and a.aac001=t.aac001 ) aab001 from personmxcollect t "
				+ "where aac001="+aac001+" order by aae001,aae003";
		List<HashMap<String, String>> listmap3 = new ArrayList<HashMap<String, String>>();
		jdbc.getConnection();//�����ٴο���
		listmap3 = jdbc.createSQLQuery(sql);//�ɷ���Ϣ
		if (listmap3.isEmpty()) {
			throw new Exception("�Ҳ����α��ɷ���ϸ��");
		}
		
		

		Document doc = new Document();
		FileOutputStream out = new FileOutputStream(file_url);// �ļ����·�� d:/PrintInsuredProof.pdf
		PdfWriter writer = PdfWriter.getInstance(doc, out);

		// ҳ��

		doc.open();
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// ��������
		////////////// ��������///////////// DEFAULTSIZE ������+���� STRIKETHRU ������
		////////////// UNDERLINE ���� NORMAL ��׼ BOLD �Ӵ�
		Font titleFont = new Font(bfChinese, 18, Font.BOLD);// ���� �Ӵ�
		Font tableTitleFont = new Font(bfChinese, 10, Font.BOLD);// ������
		Font conyentFont = new Font(bfChinese, 8, Font.BOLD);// ����
		Font minFont = new Font(bfChinese, 9, Font.BOLD);// ����

		Paragraph title = new Paragraph("�㽭ʡ��ᱣ�ղα�֤��������ר�ã�", titleFont);
		title.setAlignment(Rectangle.ALIGN_CENTER);// ����
		doc.add(title);

		Paragraph mintitle = new Paragraph(
				"\r\n��֤�룺"+listmap.get(0).get("AAC001")+"                                                           ", minFont);
		mintitle.setAlignment(Rectangle.ALIGN_RIGHT);// ����
		doc.add(mintitle);

		// ���Ĵ������ѵ㣬�ر��Ǳ��Ŀ��п���
		// ����ʹ�ÿ���Ҳ����ʹ�ñ��Ƕ��
		int tCol = 12;
		PdfPTable table = new PdfPTable(tCol);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.setTotalWidth(500f);// �ܿ��
		table.setWidths(
				new float[] { 0.2f, 0.2f, 0.27f, 0.27f, 0.27f, 0.27f, 0.27f, 0.27f, 0.27f, 0.27f, 0.18f, 0.18f });// �п��
		table.setWidthPercentage(100);// ռ�ܿ�Ȱٷֱ�
		table.setLockedWidth(true);// �Ƿ�����

		////////////// ��һ�е�һ��
		String strTableTitle = "������"+listmap.get(0).get("AAC003");
		Paragraph tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		PdfPCell cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(4);// ��3��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		////////////// ��һ�е�2��
		strTableTitle = "��ᱣ�Ϻţ�"+listmap.get(0).get("AAE135");
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(6);// ��6��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		table.addCell(cell);

		////////////// ��һ�е�3��
		strTableTitle = "�Ա�"+listmap.get(0).get("AAC004");
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);

		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		table.addCell(cell);

		////////////// ��2�е�1��
		strTableTitle = "��ᱣ�ջ������ ";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(12);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		////////////// ��3��
		strTableTitle = "  ";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "���ϱ���";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "ҽ�Ʊ���";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "���˱���";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "��������";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "ʧҵ����";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		////////////// ��4��
		strTableTitle = "��ǰ�α�״̬";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = listmap.get(0).get("YL");//����״̬
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = listmap.get(0).get("YB");//ҽ��״̬
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = listmap.get(0).get("GS");//����״̬
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = listmap.get(0).get("SY");//����״̬
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = listmap.get(0).get("SHIY");//ʧҵ״̬
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		//// ����
		strTableTitle = "���24���µ�λ��Ŷ�Ӧ��λ��ϸ";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(12);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(17);// �����и�
		table.addCell(cell);

		//// ����
		strTableTitle = "��λ���";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(6);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(17);// �����и�
		table.addCell(cell);
		strTableTitle = "��λ����";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(6);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(17);// �����и�
		table.addCell(cell);
		///��λ�䶯��Ϣ
		for(int i=0;i<listmap2.size();i++){
			strTableTitle = listmap2.get(i).get("AAB001");
			tableTitle = new Paragraph(strTableTitle, tableTitleFont);
			cell = new PdfPCell(tableTitle);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
			cell.setColspan(6);// ��2��
			// cell.setRowspan(2);// ��2��
			cell.setBorderWidth(2);// ���ñ߿���
			cell.setMinimumHeight(18);// �����и�
			table.addCell(cell);
			strTableTitle = listmap2.get(i).get("AAB004");
			tableTitle = new Paragraph(strTableTitle, tableTitleFont);
			cell = new PdfPCell(tableTitle);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
			cell.setColspan(6);// ��2��
			// cell.setRowspan(2);// ��2��
			cell.setBorderWidth(2);// ���ñ߿���
			cell.setMinimumHeight(17);// �����и�
			table.addCell(cell);
		}
		

		//// ����
		strTableTitle = "���24���½ɷ������"+listmap3.get(0).get("AAE001")+"��"
		+listmap3.get(0).get("AAE003")+"�� -"+listmap3.get(23).get("AAE001")+"��"
				+listmap3.get(23).get("AAE003")+"��"+"��";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(12);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		//// ����
		strTableTitle = "��";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		// cell.setColspan(12);// ��2��
		cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "��";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		// cell.setColspan(12);// ��2��
		cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "��λ���";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		// cell.setColspan(12);// ��2��
		cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "���ϱ���";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(1);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "ҽ�Ʊ���";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(1);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "ʧҵ����";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(1);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "�ɷ�״̬";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		// cell.setColspan(12);// ��2��
		cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "��ע";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "�ɷѻ���(Ԫ)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(1);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(30);// �����и�
		table.addCell(cell);

		strTableTitle = "���˽ɷ�(Ԫ)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(1);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(30);// �����и�
		table.addCell(cell);

		strTableTitle = "�ɷѻ���(Ԫ)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(1);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "���˽ɷ�(Ԫ)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(1);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "�ɷѻ���(Ԫ)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(1);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "���˽ɷ�(Ԫ)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(1);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(16);// �����и�
		table.addCell(cell);

		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 11; j++) {
				switch (j) {
				case 0:
					strTableTitle=	listmap3.get(i).get("AAE001");
					break;
				case 1:
					strTableTitle=	listmap3.get(i).get("AAE003");
					break;
				case 2:
					strTableTitle=	listmap3.get(i).get("AAB001");
					break;
				case 3:
					strTableTitle=	listmap3.get(i).get("YLJS");
					break;
				case 4:
					strTableTitle=	listmap3.get(i).get("YLGRJF");
					break;
				case 5:
					strTableTitle=	listmap3.get(i).get("YBJS");
					break;
				case 6:
					strTableTitle=	listmap3.get(i).get("YBGRJF");
					break;
				case 7:
					strTableTitle=	listmap3.get(i).get("SYJS");
					break;
				case 8:
					strTableTitle=	listmap3.get(i).get("SYGRJF");
					break;
				case 9:
					strTableTitle=	listmap3.get(i).get("JFZT");
					break;
				case 10:
					strTableTitle=	listmap3.get(i).get("BZ");
					break;
				
				}
				//strTableTitle = "/";
				tableTitle = new Paragraph(strTableTitle, tableTitleFont);
				cell = new PdfPCell(tableTitle);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
				cell.setColspan(1);// ��2��
				cell.setRowspan(1);// ��2��
				if (j == 10) {
					cell.setColspan(2);// ��2��
				}

				cell.setBorderWidth(2);// ���ñ߿���
				cell.setMinimumHeight(16);// �����и�
				table.addCell(cell);

			}

		}

		strTableTitle = "��ע��";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(1);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(0);// ���ñ߿���
		// cell.setMinimumHeight(18);//�����и�
		table.addCell(cell);

		strTableTitle = "1.��֤����ϢΪ��ӡʱ֤���زα���������ο����ɲα������б��ܡ�";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(11);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(0);// ���ñ߿���
		// cell.setMinimumHeight(18);//�����и�
		table.addCell(cell);

		strTableTitle = " ";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(1);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(0);// ���ñ߿���
		// cell.setMinimumHeight(18);//�����и�
		table.addCell(cell);

		strTableTitle = "2.���ݵ�˰���ݽ������ƣ������1��3����������ʱ���������";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(11);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(0);// ���ñ߿���
		// cell.setMinimumHeight(18);//�����и�
		table.addCell(cell);

		strTableTitle = " ";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(1);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(0);// ���ñ߿���
		// cell.setMinimumHeight(18);//�����и�
		table.addCell(cell);

		strTableTitle = "3.���α�֤����ǩ�ߵ���ӡ�£��籣���������������ǩ�¡�";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(11);// ��2��
		cell.setRowspan(1);// ��2��
		cell.setBorderWidth(0);// ���ñ߿���
		cell.setBorder(Rectangle.NO_BORDER);// ���õ�Ԫ���ޱ߿�
		// cell.setMinimumHeight(18);//�����и�
		table.addCell(cell);

		table.setSpacingBefore(5f);// ������
		table.setSpacingAfter(50f);// ������
		doc.add(table);

		Paragraph foot = new Paragraph(
				"�˲α�֤����ӡ�� 1 ҳ �� 1 ҳ                                                          ��ӡʱ�䣺2017��06��15��             ",
				conyentFont);
		foot.setAlignment(Rectangle.ALIGN_RIGHT);// ����
		doc.add(foot);

		doc.close();
		System.out.println("PDF���ɽ���.....");

	}

}
