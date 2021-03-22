package br.com.alura.mvc.mudi.controller;

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
@RequestMapping("/home")
public class HomeController {

  // Cria instâncias e injeta as depêndencias.
  @Autowired
  private PedidoRepository pedidoRepository;

  // Método retorna a string com o nome da View, usando a notação Get Mapping para
  // fazer o roteamento
  @GetMapping
  public ModelAndView home(Model model) {
    List<Pedido> pedidos = pedidoRepository.findAll();
    ModelAndView mv = new ModelAndView("home");
    mv.addObject("pedidos", pedidos);
    return mv;
  }

  @GetMapping("/{status}")
  public ModelAndView statusFilter(@PathVariable("status") String status, Model model) {
    List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
    ModelAndView mv = new ModelAndView("home");
    mv.addObject("pedidos", pedidos);
    mv.addObject("status", status);
    return mv;
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public String onError() {
    return "redirect:/home";
  }

}
