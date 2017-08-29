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
	 * 创建PDF
	 * 
	 * @author maoxj
	 * @throws Exception
	 */
	public void createPdfFile() throws Exception {
		Document doc = new Document();
		FileOutputStream out = new FileOutputStream("d:/PrintInsuredProof.pdf");// 文件输出路径
		PdfWriter writer=PdfWriter.getInstance(doc, out);
		
		//页脚
		 
		
		
		doc.open();
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文
		////////////// 设置字体///////////// DEFAULTSIZE 划痕线+底线 STRIKETHRU 划痕线
		////////////// UNDERLINE 底线 NORMAL 标准 BOLD 加粗
		Font titleFont = new Font(bfChinese, 18, Font.BOLD);// 标题 加粗
		Font tableTitleFont = new Font(bfChinese, 10, Font.BOLD);// 表格标题
		Font conyentFont = new Font(bfChinese, 8, Font.BOLD);// 内容
		Font minFont = new Font(bfChinese, 9, Font.BOLD);// 内容

		Paragraph title = new Paragraph("浙江省社会保险参保证明（个人专用）", titleFont);
		title.setAlignment(Rectangle.ALIGN_CENTER);// 居中
		doc.add(title);

		Paragraph mintitle = new Paragraph(
				"\r\n验证码：1234567890                                                           ", minFont);
		mintitle.setAlignment(Rectangle.ALIGN_RIGHT);// 居右
		doc.add(mintitle);

		// 表格的处理是难点，特别是表格的跨行跨列
		// 可以使用跨行也可以使用表格嵌套
		int tCol = 12;
		PdfPTable table = new PdfPTable(tCol);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.setTotalWidth(500f);// 总宽度
		table.setWidths(
				new float[] { 0.2f, 0.2f, 0.27f, 0.27f, 0.27f, 0.27f, 0.27f, 0.27f, 0.27f, 0.27f, 0.18f, 0.18f });// 列宽度
		table.setWidthPercentage(100);// 占总宽度百分比
		table.setLockedWidth(true);// 是否锁定

		////////////// 第一行第一列
		String strTableTitle = "姓名：";
		Paragraph tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		PdfPCell cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(4);// 跨3列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		////////////// 第一行第2列
		strTableTitle = "社会保障号：";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(6);// 跨6列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		table.addCell(cell);

		////////////// 第一行第3列
		strTableTitle = "性别：";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);

		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		table.addCell(cell);

		////////////// 第2行第1列
		strTableTitle = "社会保险基本情况 ";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(12);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		////////////// 第3行
		strTableTitle = "  ";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "养老保险";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "医疗保险";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "工伤保险";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "生育保险";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "失业保险";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		////////////// 第4行
		strTableTitle = "当前参保状态";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "参保缴费";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "参保缴费";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "参保缴费";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "参保缴费";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "参保缴费";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		//// 换行
		strTableTitle = "最近24个月单位编号对应单位明细";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(12);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		//// 换行
		strTableTitle = "单位编号";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(6);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);
		strTableTitle = "单位名称";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(6);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		//// 换行
		strTableTitle = "最近24个月缴费情况（）";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(12);// 跨2列
		// cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		//// 换行
		strTableTitle = "年";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		// cell.setColspan(12);// 跨2列
		cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "月";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		// cell.setColspan(12);// 跨2列
		cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "单位编号";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		// cell.setColspan(12);// 跨2列
		cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "养老保险";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "医疗保险";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "失业保险";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		// cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "缴费状态";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		// cell.setColspan(12);// 跨2列
		cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "备注";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(2);// 跨2列
		cell.setRowspan(2);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "缴费基数(元)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(1);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(30);// 设置行高
		table.addCell(cell);

		strTableTitle = "个人缴费(元)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(1);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(30);// 设置行高
		table.addCell(cell);

		strTableTitle = "缴费基数(元)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(1);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "个人缴费(元)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(1);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "缴费基数(元)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(1);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		strTableTitle = "个人缴费(元)";
		tableTitle = new Paragraph(strTableTitle, tableTitleFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(1);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(2);// 设置边框宽度
		cell.setMinimumHeight(18);// 设置行高
		table.addCell(cell);

		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 11; j++) {

				strTableTitle = "/";
				tableTitle = new Paragraph(strTableTitle, tableTitleFont);
				cell = new PdfPCell(tableTitle);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
				cell.setColspan(1);// 跨2列
				cell.setRowspan(1);// 跨2行
				if (j == 10) {
					cell.setColspan(2);// 跨2列
				}

				cell.setBorderWidth(2);// 设置边框宽度
				cell.setMinimumHeight(18);// 设置行高
				table.addCell(cell);

			}

		}

		strTableTitle = "备注：";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(1);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(0);// 设置边框宽度
		// cell.setMinimumHeight(18);//设置行高
		table.addCell(cell);

		strTableTitle = "1.本证明信息为打印时证明地参保情况，供参考，由参保人自行保管。";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 水平居左
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(11);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(0);// 设置边框宽度
		// cell.setMinimumHeight(18);//设置行高
		table.addCell(cell);

		strTableTitle = " ";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(1);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(0);// 设置边框宽度
		// cell.setMinimumHeight(18);//设置行高
		table.addCell(cell);

		strTableTitle = "2.根据地税数据交换机制，会产生1到3个月数据延时到账情况。";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 水平居左
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(11);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(0);// 设置边框宽度
		// cell.setMinimumHeight(18);//设置行高
		table.addCell(cell);

		strTableTitle = " ";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(1);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(0);// 设置边框宽度
		// cell.setMinimumHeight(18);//设置行高
		table.addCell(cell);

		strTableTitle = "3.本参保证明已签具电子印章，社保经办机构不再另行签章。";
		tableTitle = new Paragraph(strTableTitle, minFont);
		cell = new PdfPCell(tableTitle);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 水平居左
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setColspan(11);// 跨2列
		cell.setRowspan(1);// 跨2行
		cell.setBorderWidth(0);// 设置边框宽度
		cell.setBorder(Rectangle.NO_BORDER);// 设置单元格无边框  
		// cell.setMinimumHeight(18);//设置行高
		table.addCell(cell);

		table.setSpacingBefore(5f);// 上留白
		table.setSpacingAfter(50f);// 下留白
		doc.add(table);
		
		Paragraph foot = new Paragraph("此参保证明打印共 1 页 第 1 页                                                          打印时间：2017年06月15日             ", conyentFont);
		foot.setAlignment(Rectangle.ALIGN_RIGHT);// 居右
		doc.add(foot);
		

		doc.close();
		System.out.println("PDF生成结束.....");

	}

}
