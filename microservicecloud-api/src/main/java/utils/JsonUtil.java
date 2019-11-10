package utils;

import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by zhangds
 * 2019-11-08 17:10
 **/
public class JsonUtil {

    public static List<Map<String, String>> out2JSON(XWPFTable table) throws Exception {

        List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();

        // 表格行数
        int tableRowsSize = table.getRows().size();
        for (int i = 0; i < tableRowsSize; i++) {
            Map<String, String> dataMap = new HashMap<>();
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

                if (i>0){
                    dataMap.put(table.getRow(0).getCell(j).getText(), text);
                }
            }
            if (!dataMap.isEmpty()){
                dataList.add(dataMap);
            }
        }
        return dataList;
    }
}
