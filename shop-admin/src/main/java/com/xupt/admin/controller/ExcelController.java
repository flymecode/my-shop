package com.xupt.admin.controller;

import com.xupt.admin.service.UserService;
import com.xupt.admin.validator.UserForm;
import com.xupt.domain.user.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/4
 */
@Controller
public class ExcelController {

    @Autowired
    private UserService userService;


    @RequestMapping("/user/export")
    public void export(@RequestParam(value = "start",defaultValue = "0") Integer start, UserForm userForm,
                       @RequestParam(value = "length",defaultValue = "10") Integer length,
                       @RequestParam(value = "draw",defaultValue = "1") Integer draw,
                       HttpServletResponse response) throws IOException {
        List<User> users = (List<User>) userService.listUsers(start, length, draw, userForm).get("data");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("获取excel测试表格");
        //创建第一个单元格
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) (26.25 * 20));
        //为第一行单元格设值
        row.createCell(0).setCellValue("用户信息列表");
        /**为标题设计空间
         * firstRow从第1行开始
         * lastRow从第0行结束
         *从第1个单元格开始
         * 从第3个单元格结束
         */
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));
        row.createCell(0).setCellValue("用户Id");
        row.createCell(1).setCellValue("用户名");
        row.createCell(3).setCellValue("邮箱");
        row.createCell(4).setCellValue("电话");
        row.createCell(5).setCellValue("更新时间");

        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i + 2);
            User user = users.get(i);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getUsername());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getPhone());
            row.createCell(4).setCellValue(user.getUpdated());
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        //默认Excel名称
        response.setHeader("Content-disposition", "attachment;filename=user.xls");
        wb.write(os);
        os.flush();
        os.close();
    }

    @RequestMapping(value = "/import")
    public String exImport(@RequestParam(value = "filename") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = userService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/list";
    }
}
