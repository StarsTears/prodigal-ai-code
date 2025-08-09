package com.prodigal.aicode.generator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.setting.yaml.YamlUtil;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Date;
import java.util.Map;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description MyBatis Flex 代码生成器
 * @since 2025/8/9
 * https://mybatis-flex.com/zh/others/codegen.html
 */
public class MyBatisCodeGenerator {
    private static final String[] TABLE_NAMES = {"user"};

    public static void main(String[] args) {
        //获取数据源信息
        Dict dict  = YamlUtil.loadByPath("application-local.yml");
        Map<String, Object> dataSourceConfig = dict.getByPath("spring.datasource");
        String url = String.valueOf(dataSourceConfig.get("url"));
        String username = String.valueOf(dataSourceConfig.get("username"));
        String password = String.valueOf(dataSourceConfig.get("password"));
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfig();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    private static GlobalConfig createGlobalConfig() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包，可以先生成到一个临时目录下，然后统一移动到正式的根包下
        //        globalConfig.setBasePackage("com.test");
        globalConfig.getPackageConfig().setBasePackage("com.prodigal.aicode.genresult");


        //设置表前缀和只生成哪些表
//        globalConfig.setTablePrefix("tb_");
//        globalConfig.setGenerateTable("tb_account", "tb_account_session");
        globalConfig.getStrategyConfig().setGenerateTable(TABLE_NAMES)
                //设置逻辑删除字段
                .setLogicDeleteColumn("isDelete");

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);
        //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(21);

        //设置生成 mapper
        globalConfig.enableMapper();
        globalConfig.enableMapperXml();
        //设置生成 service
        globalConfig.enableService();
        globalConfig.enableServiceImpl();
        //设置生成 controller
        globalConfig.enableController();
        //可以单独配置某个列
        globalConfig.getJavadocConfig().setAuthor("Lang").setSince(DateUtil.format(new Date(), "yyyy-MM-dd"));

        return globalConfig;
    }
}
