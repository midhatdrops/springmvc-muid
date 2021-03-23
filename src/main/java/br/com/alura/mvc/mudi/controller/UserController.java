package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.models.Pedido;
import br.com.alura.mvc.mudi.models.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("user")
public class UserController {

  @Autowired
  private PedidoRepository pedidoRepository;

  @GetMapping("pedidos")
  public ModelAndView home(Model model, Principal principal) {
    List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
    ModelAndView mv = new ModelAndView("usuario/home");
    mv.addObject("pedidos", pedidos);
    return mv;
  }

  @GetMapping("pedidos/{status}")
  public ModelAndView statusFilter(@PathVariable("status") String status, Model model, Principal principal) {
    List<Pedido> pedidos = pedidoRepository.findByStatusAndUser(StatusPedido.valueOf(status.toUpperCase()),
        principal.getName());
    ModelAndView mv = new ModelAndView("usuario/home");
    mv.addObject("pedidos", pedidos);
    mv.addObject("status", status);
    return mv;
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public String onError() {
    return "redirect:/usuario/home";
  }
}
