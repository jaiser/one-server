/**
 * Copyright (C), 2020, 中电福富信息科技有限公司
 * FileName: TestServiceImpl
 * Author:   xujiajun
 * Date:     2020/5/14 0:57
 */
package com.jaiser.oneserver.web.service.impl;

import com.jaiser.oneserver.web.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: 
 * @Author xujiajun
 * @Date 2020/5/14  0:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl implements TestService {
}