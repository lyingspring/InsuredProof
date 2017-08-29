package test_webservice;

import java.io.FileOutputStream;
import java.io.IOException;

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

public class PrintInsuredProof {
	/**
	 * @author maoxj
	 * @param args
	 */
	public static void main(String[] args) {

		PrintInsuredProof tPDFPractise = new PrintInsuredProof();
		try {
			tPDFPractise.createPdfFile();

			// tPDFPractise.createPDFFile();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����PDF
	 * 
	 * @author maoxj
	 * @throws Exception
	 */
	public void createPdfFile() throws Exception {
		Document doc = new Document();
		FileOutputStream out = new FileOutputStream("d:/PrintInsuredProof.pdf");// �ļ����·��
		PdfWriter writer=PdfWriter.getInstance(doc, out);
		
		//ҳ��
		 
		
		
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
				"\r\n��֤�룺1234567890                                                           ", minFont);
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
		String strTableTitle = "������";
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
		strTableTitle = "��ᱣ�Ϻţ�";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(6);// ��6��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		table.addCell(cell);

		////////////// ��һ�е�3��
		strTableTitle = "�Ա�";
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

		strTableTitle = "�α��ɷ�";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "�α��ɷ�";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "�α��ɷ�";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "�α��ɷ�";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(2);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		strTableTitle = "�α��ɷ�";
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
		cell.setMinimumHeight(18);// �����и�
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
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);
		strTableTitle = "��λ����";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ˮƽ����
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		cell.setColspan(6);// ��2��
		// cell.setRowspan(2);// ��2��
		cell.setBorderWidth(2);// ���ñ߿���
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		//// ����
		strTableTitle = "���24���½ɷ��������";
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
		cell.setMinimumHeight(18);// �����и�
		table.addCell(cell);

		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 11; j++) {

				strTableTitle = "/";
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
				cell.setMinimumHeight(18);// �����и�
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
		
		Paragraph foot = new Paragraph("�˲α�֤����ӡ�� 1 ҳ �� 1 ҳ                                                          ��ӡʱ�䣺2017��06��15��             ", conyentFont);
		foot.setAlignment(Rectangle.ALIGN_RIGHT);// ����
		doc.add(foot);
		

		doc.close();
		System.out.println("PDF���ɽ���.....");

	}

}
