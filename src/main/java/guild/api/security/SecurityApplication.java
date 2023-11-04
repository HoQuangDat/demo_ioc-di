package guild.api.security;

import guild.api.security.controller.DongCoMy;
import guild.api.security.controller.DongCoNhat;
import guild.api.security.controller.XeMay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = "guild.api.security.config")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);

		DongCoMy dongCoMy = new DongCoMy();
		DongCoNhat dongCoNhat = new DongCoNhat();
		//constructor
		XeMay xeMay = new XeMay(dongCoMy);
		//setter
		xeMay.setIDongCo(dongCoNhat);
		xeMay.getIDongCo().run();
	}
}
