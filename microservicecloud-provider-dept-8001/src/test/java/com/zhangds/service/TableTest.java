package com.zhangds.service;

import com.alibaba.fastjson.JSON;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;
import utils.ConvertUtil;
import utils.JsonUtil;
import utils.TableUtil;
import utils.XmlUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Create by zhangds
 * 2019-11-08 16:54
 **/
public class TableTest {
    @Test
    public void POI2Word() {
        String path = "C://Users//zhangds//Desktop//tdocx.docx";
        TableTest readWordTable = new TableTest();

        try (FileInputStream fileInputStream = new FileInputStream(path);
             XWPFDocument document = new XWPFDocument(fileInputStream);) {
            List<XWPFTable> tables = document.getTables();
            for (XWPFTable table : tables) {
                //html格式输出
                //System.out.println(TableUtil.readTable(table));
                //xml格式输出
                //XmlUtil.out2Xml(table);
                //json格式输出
                List<Map<String, String>> listMap = JsonUtil.out2JSON(table);
                System.out.println(listMap);
                System.out.println(JSON.toJSONString(listMap));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
