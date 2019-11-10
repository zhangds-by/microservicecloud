package utils;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static utils.TableUtil.isOmitCell;

/**
 * Create by zhangds
 * 2019-11-08 17:10
 **/
public class XmlUtil {

    public static void out2Xml(XWPFTable table) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.setXmlVersion("1.0");
        document.setXmlStandalone(true);
        Element root = document.createElement("datasetName");       //创建根节点
        root.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        root.setTextContent("\n");
        document.appendChild(root);
        // 表格行数
        int tableRowsSize = table.getRows().size();
        Element pageElement = null;
        for (int i = 0; i < tableRowsSize; i++) {

            int tableCellsSize = table.getRow(i).getTableCells().size();
            for (int j = 0; j < tableCellsSize; j++) {
                if (TableUtil.isOmitCell(i, j)) {
                    continue;
                }
                XWPFTableCell tableCell = table.getRow(i).getCell(j);
                // 获取单元格的属性
                CTTcPr tcPr = tableCell.getCTTc().getTcPr();
                int colspan = TableUtil.getColspan(tcPr);
                if (colspan > 1) { // 合并的列
                } else { // 正常列
                }
                int rowspan = TableUtil.getRowspan(table, i, j);
                if (rowspan > 1) { // 合并的行
                } else {
                }
                String text = tableCell.getText();
                //以表头为键，单元格内容为值，添加到标签中
                if(i>0){
                    if(j==0){
                        pageElement = document.createElement(text);
                        pageElement.setAttribute(table.getRow(0).getCell(j).getText(), text);
                    }else{
                        //解决element添加多个属性
                        pageElement.setAttribute(table.getRow(0).getCell(j).getText(), text);
                    }
                }

            }
            //添加结点到root结点
            if (pageElement!=null){
                root.appendChild(pageElement);
            }
        }
        TransformerFactory transFactory = TransformerFactory.newInstance();     //开始把Document映射到文件
        Transformer transFormer = transFactory.newTransformer();

        DOMSource domSource = new DOMSource(document);                           //设置输出结果

        File file = new File("table.xml"); //生成xml文件

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream out = new FileOutputStream(file);          //文件输出流
        StreamResult xmlResult = new StreamResult(out);            //设置输入源

        transFormer.transform(domSource, xmlResult);              //输出xml文件
        System.out.println("文件路径为" + file.getAbsolutePath());               //测试文件输出的路径

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty("{/encoding/}", "GB2312/");
        ByteArrayOutputStream boc = new ByteArrayOutputStream();
        t.transform(new DOMSource(document), new StreamResult(boc));
    }
}
