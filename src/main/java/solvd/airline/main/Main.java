package solvd.airline.main;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Main {

	public static void main(String[] args) {
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		//testing airline menu
		solvd.airline.menu.AirLineRouteMenu AirLinemenu = new solvd.airline.menu.AirLineRouteMenu(sqlSessionFactory);
		AirLinemenu.start();
	}
}
