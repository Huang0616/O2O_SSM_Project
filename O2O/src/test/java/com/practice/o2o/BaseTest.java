package com.practice.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 初始化spring junit的配置的
 * junit启动时加载springIOC容器
 * @author fei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件的位置在哪
@ContextConfiguration({"classpath*:spring/spring-dao.xml","classpath*:spring/spring-service.xml"} )
public class BaseTest {

}
