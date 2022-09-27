package com.vertice.Cleo.controller;

import com.vertice.Cleo.modelos.Empleado;
import com.vertice.Cleo.modelos.Empresa;
import com.vertice.Cleo.modelos.MovimientosDinero;
import com.vertice.Cleo.service.EmpleadoService;
import com.vertice.Cleo.service.EmpresaService;
import com.vertice.Cleo.service.MovimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerJuanV {
    @Autowired
    MovimientoService movimientoService;
    @Autowired
    EmpleadoService empleadoService;

@GetMapping("/ViewMovimientos")
    public String viewMovimientos(Model model, @ModelAttribute("mensaje")String mensaje){
        List<MovimientosDinero> listaMovimientos= movimientoService.getAllMovimiento();//Se cargan los movimientos
        model.addAttribute("moveList", listaMovimientos); //se agrregan los movimientos en el modelo moveList
        model.addAttribute("mensaje", mensaje);
        Long sumaMonto=movimientoService.obtenerSumaMontos();
        model.addAttribute("SumaMontos",sumaMonto);
        return"verMovimientos"; //llamamos al HTML
    }

@GetMapping("/AgregarMovimientos")
    public String newMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovimientosDinero move= new MovimientosDinero();
        model.addAttribute("moveSave", move);
        model.addAttribute("mensaje", mensaje);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();
        Integer idEmpleado=movimientoService.IdPorCorreo(correo);
        model.addAttribute("idEmpleado",idEmpleado);
        return "agregarMovimiento";
    }

@PostMapping("/GuardarMovimiento")
    public String saveMovimiento(MovimientosDinero mov, RedirectAttributes redirectAttributes){
        if(movimientoService.saveOrUpdateMovimientosDinero(mov)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/ViewMovimientos";
        }
    redirectAttributes.addFlashAttribute("mensaje","saveOK");
        return "redirect:/ViewMovimientos";
        }
    
@GetMapping("/EditarMovimiento/{id}")
    public String editarMovimiento(Model model, @PathVariable Integer id,@ModelAttribute("mensaje") String mensaje){
        MovimientosDinero moveUpdate=movimientoService.getMovimientoById(id);
        model.addAttribute("moveUpdate",moveUpdate);
        model.addAttribute("mensaje",mensaje);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();
        Integer idEmpleado=movimientoService.IdPorCorreo(correo);
        model.addAttribute("idEmpleado",idEmpleado);
        return "editarMovimiento";
    }
@PostMapping("/ActualizarMovimiento")
    public String updateMove(@ModelAttribute("moveUpdate") MovimientosDinero move,RedirectAttributes redirectAttributes){
    if(movimientoService. saveOrUpdateMovimientosDinero(move)){
        redirectAttributes.addFlashAttribute("mensaje", "updateOK");
        return "redirect:/ViewMovimientos";
    }
    return "redirect:/EditarMovimiento";
    }
@GetMapping("/EliminarMovimiento/{id}")
    public String  eliminarMovimiento(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        if(movimientoService.deletedMovimiento(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","deletedOK");
            return "redirect:/ViewMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","deletedError");
        return "redirect:/ViewMovimientos";
    }
@GetMapping("/{id}/movimiento")
    public String movimientosByEmpleado(@PathVariable("id") Integer id, Model model){
        List<MovimientosDinero> movelist = movimientoService.movimientoPorEmpleado(id);
        model.addAttribute ("moveList",movelist);
        Long sumaMonto=movimientoService.MontosPorEmpleado(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos"; //Llamamos al HTML

    }
    @GetMapping("/{id}/movimientoEmpresa")
    public String movimientosByEmpresa(@PathVariable("id") Integer id, Model model){
        List<MovimientosDinero> movelist = movimientoService.obtenerPorEmpresa(id);
        model.addAttribute ("moveList",movelist);
        Long sumaMonto=movimientoService.MontosPorEmpresa(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos"; //Llamamos al HTML

    }
}