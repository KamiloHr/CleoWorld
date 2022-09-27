package com.vertice.Cleo;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.vertice.Cleo.modelos.Empleado;
import com.vertice.Cleo.modelos.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class CleoApplication {
	@RequestMapping("/hola")
	public String test(){
		return "Hola";
	}
	@RequestMapping("/testEmpleado")
	public String testDos(){
		Empleado Dario = new Empleado();
		Dario.setCorreo("gd@gmail.com");
		return Dario.getCorreo();
	}
	@RequestMapping("/prueba")
	@ResponseBody
	public String verEmpresa(){
		Empresa emp=new Empresa();
		emp.setNombre("KamiloHr");
		return emp.getNombre();
	}
	public static void main(String[] args) {
		SpringApplication.run(CleoApplication.class, args);
	}
	//
}
