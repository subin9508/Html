package com.itwill.lab05.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtil {
	// singleton 구현
	private static DataSourceUtil instance = null;
	private HikariConfig config;
	private HikariDataSource ds;
	
	private DataSourceUtil() {
		config = new HikariConfig(); // HikariCP의 설정(configuration)을 담당하는 객체 생성.
		
		// 커넥션 풀(데이터 소스) 환경 설정.
		config.setDriverClassName("oracle.jdbc.OracleDriver"); // 필수
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe"); // 필수
		config.setUsername("jspstudy"); // 필수
		config.setPassword("jspstudy"); // 필수
		
		// 데이터 소스 객체를 생성.
		ds = new HikariDataSource(config);
	}
	
	public static DataSourceUtil getInstance() {
		if(instance == null) {
			instance = new DataSourceUtil();
		}
		return instance;
	}
	
	public HikariDataSource getDataSource() {
		return ds;
	}
	
	
}
