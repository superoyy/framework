##  德古拉平台基础包
# 2019.11.4
相对于Hibernate4,Hibernate5和Springboot2.x 的集成，必须加上HibernateConfig的配置类，不然执行事务控制的时候出现no transaction process的异常。
配置：
hibernate.current_session_context_class=org.springframework.orm.hibernate5.SpringSessionContext
代码：

@Configuration
public class HibernateConfig {
    
    @Value("${spring.jpa.properties.hibernate.current_session_context_class}")
    public String current_session_context_class;
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
       LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
       sessionFactoryBean.setDataSource(dataSource);
       sessionFactoryBean.setPackagesToScan("xxx.xxx");//dao和entity的公共包
       Properties properties = new Properties();
       properties.setProperty("hibernate.current_session_context_class", current_session_context_class);
       sessionFactoryBean.setHibernateProperties(properties);
       return sessionFactoryBean;
    }
}
