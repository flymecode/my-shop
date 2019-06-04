package com.xupt.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.admin.exception.ExcelException;
import com.xupt.admin.mapper.UserMapper;
import com.xupt.admin.service.UserService;
import com.xupt.admin.validator.UserForm;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public ResultMap saveUser(User user) {
        Date date = new Date();
        user.setUpdated(date);
        if (user.getId() == null) {
            user.setCreated(date);
            userMapper.insertUser(user);
        } else {
            userMapper.updateUser(user);
        }
        return null;
    }

    @Override
    public ResultMap  listUsers(Integer page, Integer count, Integer draw, UserForm userForm) {
        ResultMap result = new ResultMap();
        PageHelper.offsetPage(page, count);
        List<User> users = userMapper.searchUsers(userForm);
        log.info(users.toString());
        PageInfo<User> pages = PageInfo.of(users);
        result.payload(pages.getList());
        result.put("draw",draw);
        result.put("recordsTotal",pages.getTotal());
        result.put("recordsFiltered",pages.getTotal());
        result.put("error","");
        return result;
    }

    @Override
    public ResultMap deleteUsers(String ids) {
        userMapper.deleteUsers(ids);
        return null;
    }

    @Override
    public ResultMap updateUser(Integer id) {
        ResultMap resultMap = new ResultMap();
        return resultMap;
    }

    @Override
    public ResultMap getUser(Integer id) {
        ResultMap resultMap = new ResultMap();
        User user = userMapper.getUser(id);
        return resultMap.payload(user);
    }

    @Override
    public ResultMap deleteUser(Long id) {
        ResultMap resultMap = new ResultMap();
        userMapper.deleteUser(id);
        resultMap.message("删除成功");
        return resultMap;
    }


    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<User> userList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new ExcelException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        User user;
        for (int r = 2; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
            Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
            if (row == null){
                continue;
            }
            //sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException
            user = new User();
            if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
                throw new ExcelException("导入失败(第"+(r+1)+"行,用户名请设为文本格式)");
            }
            String username = row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值
            if(username == null || username.isEmpty()){//判断是否为空
                throw new ExcelException("导入失败(第"+(r+1)+"行,用户名未填写)");
            }
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String password = row.getCell(1).getStringCellValue();
            if(password==null || password.isEmpty()){
                throw new ExcelException("导入失败(第"+(r+1)+"行,密码未填写)");
            }
            //完整的循环一次 就组成了一个对象
            user.setUsername(username);
            user.setPassword(password);
            userList.add(user);
        }
        for (User userResord : userList) {
            String name = userResord.getUsername();
            int cnt = userMapper.selectUserByName(name);
            if (cnt == 0) {
                userMapper.insertUser(userResord);
                System.out.println(" 插入 "+userResord);
            } else {
                userMapper.updateUserByName(userResord);
                System.out.println(" 更新 "+userResord);
            }
        }
        return notNull;
    }
}
