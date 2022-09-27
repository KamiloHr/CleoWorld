package com.vertice.Cleo.controller;

import com.vertice.Cleo.modelos.Empleado;
import com.vertice.Cleo.modelos.Empresa;
import com.vertice.Cleo.service.EmpleadoService;
import com.vertice.Cleo.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Controller
public class ControllerKamiloHr {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping({"/","/ViewEmpresas"})
    public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Empresa> listaEmpresas=empresaService.getAllEmpresa();//Cargamos las empresas
        model.addAttribute("enterpriseList",listaEmpresas);//agreegamos las empresas en el modelo enterpriseList
        model.addAttribute("mensaje",mensaje);
        return "verEmpresas";//Llamamos al Html
    }
    @GetMapping("/AgregarEmpresa")
    public String newEmpresa(Model model,@ModelAttribute("mensaje") String mensaje){
        Empresa enterprise= new Empresa();
        model.addAttribute("enterpriseSave",enterprise);
        model.addAttribute("mensaje",mensaje);
        return "agregarEmpresa";
    }
    @PostMapping("/GuardarEmpresa")
    public String saveEmpresa(Empresa emp, RedirectAttributes redirectAttributes) {
        if(empresaService.saveOrUpdateEmpresa(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/ViewEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/ViewEmpresas";
    }
    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id,@ModelAttribute("mensaje") String mensaje){
        Empresa enterpriseUpdate=empresaService.getEmpresaById(id);
        model.addAttribute("enterpriseUpdate",enterpriseUpdate);
        model.addAttribute("mensaje",mensaje);
        return "editarEmpresa";
    }
    @PostMapping("/ActualizarEmpresa")
    public String updateEnterprise(@ModelAttribute("enterpriseUpdate") Empresa enterprise,RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(enterprise)){
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/ViewEmpresas";
        }
        return "redirect:/EditarEmpresa/{id}";
    }
    @GetMapping("/EliminarEmpresa/{id}")
    public String  eliminarEmpresa(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        if(empresaService.deletedEmpresa(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","deletedOK");
            return "redirect:/ViewEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","deletedError");
        return "redirect:/ViewEmpresas";
    }
    @GetMapping("/ViewEmpleados")
    public String viewEmpleados(Model model , @ModelAttribute("mensaje") String mensaje){
        List<Empleado> listEmpleado= empleadoService.getAllEmpleado();
        model.addAttribute("listEmpleado",listEmpleado);
        model.addAttribute("mensaje",mensaje);
        return "verEmpleados";
    }
    @GetMapping("/AgregarEmpleado")
    public String nuevoEmpleado(Model model, @ModelAttribute("mensaje") String mensaje){
        Empleado saveEmpleado= new Empleado();
        model.addAttribute("saveEmpleado",saveEmpleado);
        model.addAttribute("mensaje",mensaje);
        List<Empresa> listaEmpresas= empresaService.getAllEmpresa();
        model.addAttribute("listaEmpresas",listaEmpresas);
        return "agregarEmpleado"; //Llamar HTML
    }
    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Empleado empleado, RedirectAttributes redirectAttributes) {
        String passEncriptada=passwordEncoder().encode(empleado.getPassword());
        empleado.setPassword(passEncriptada);
        if(empleadoService.saveOrUpdateEmpleado(empleado)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/ViewEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "rederict:/ViewEmpleados";
    }
    @GetMapping("/{id}/Empleados") //Filtrar los empleados por empresa
    public String verEmpleadosPorEmpresa(@PathVariable("id") Integer id, Model model){
        List<Empleado> listaEmpleado = empleadoService.obtenerPorEmpresa(id);
        model.addAttribute("listEmpleado",listaEmpleado);
        return "verEmpleados"; //Llamamos al html con el emplelist de los empleados filtrados
    }
    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empleado empleadoUpdate=empleadoService.getEmpleadoById(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("empleadoUpdate",empleadoUpdate);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas= empresaService.getAllEmpresa();
        model.addAttribute("listaEmpresas",listaEmpresas);
        return "editarEmpleado";
    }

    @PostMapping("/ActualizarEmpleado")
    public String updateEmpleado(@ModelAttribute("empleadoUpdate") Empleado empleado, RedirectAttributes redirectAttributes){
        Integer id =empleado.getId();
        String Oldpass=empleadoService.getEmpleadoById(id).getPassword();//consultamos la contraseña
        if(!empleado.getPassword().equals(Oldpass)){
            String passEncriptada=passwordEncoder().encode(empleado.getPassword());
            empleado.setPassword(passEncriptada);
        }
        if(empleadoService.saveOrUpdateEmpleado(empleado)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/ViewEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarEmpleado/{id}";

    }
    //Metodo para Encriptar Contraseñas
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empleadoService.deletedEmpleado(id)){
            redirectAttributes.addFlashAttribute("mensaje","deletedOK");
            return "redirect:/ViewEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deletedError");
        return "redirect:/ViewEmpleados";
    }
    //No autorizado
    @RequestMapping(value="/Denegado")
    public String denegado(){
        return "accessDenied";
    }
}
