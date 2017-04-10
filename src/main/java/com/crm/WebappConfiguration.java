package com.crm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = { "com.crm" })
@EnableWebMvc
@EnableTransactionManagement
public class WebappConfiguration extends WebMvcConfigurerAdapter
{
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass( JstlView.class );
		viewResolver.setPrefix( "/WEB-INF/pages/" );
		viewResolver.setSuffix( ".jsp" );

		return viewResolver;
	}

	// @Bean
	// public PlatformTransactionManager transactionManager( DataSource dataSource ) {
	// return new DataSourceTransactionManager( dataSource );
	// }
	//
	// @Bean
	// public DataSource dataSource() {
	// final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
	// dsLookup.setResourceRef( true );
	//
	// return dsLookup.getDataSource( "jdbc/supporttool" );
	// }

	// @Override
	// public void addViewControllers( ViewControllerRegistry registry ) {
	// registry.addViewController( "/app/home/login" ).setViewName( "LoginView" );
	// }

	@Override
	public void addResourceHandlers( ResourceHandlerRegistry registry ) {
		registry.addResourceHandler( "/resources/**" ).addResourceLocations( "/resources/" );
	}

	// @Bean
	// public JdbcTemplate jdbcTemplate() {
	// return new JdbcTemplate( dataSource() );
	// }
	//
	// @Bean
	// public NamedParameterJdbcOperations namedParameterJdbcOperations() {
	// return new NamedParameterJdbcTemplate( dataSource() );
	// }
	//
	// @Bean
	// public String gameSupportLocation() {
	// return "/usr/local/virgo/support";
	// }
	//
	// @Bean
	// public Jackson2ObjectMapperBuilder jacksonBuilder() {
	// Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	// builder.dateFormat( new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ" ) );
	//
	// return builder;
	// }
}
