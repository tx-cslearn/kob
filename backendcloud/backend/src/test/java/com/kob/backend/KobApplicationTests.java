package com.kob.backend;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kob.backend.mapper.RecordMapper;
import com.kob.backend.pojo.Record;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
class KobApplicationTests {

    private RecordMapper recordMapper;

    @Autowired
    public void setRecordMapper(RecordMapper recordMapper){
    this.recordMapper =recordMapper;
    }
    @Test
     void testsearch(){
        IPage<Record> iPage = new Page<>(1,10);
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Record> records = recordMapper.selectPage(iPage,queryWrapper).getRecords();
        System.out.println(records.toString());
    }

}
