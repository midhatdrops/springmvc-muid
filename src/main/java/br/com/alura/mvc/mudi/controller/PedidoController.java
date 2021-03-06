package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoPedido;
import br.com.alura.mvc.mudi.models.Pedido;
import br.com.alura.mvc.mudi.models.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

  final String PEDIDO_FORMULARIO = "pedido/formulario";

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("formulario")
  public String formulario(RequisicaoPedido requisicao) {
    return PEDIDO_FORMULARIO;
  }

  @PostMapping("novo")
  public String novo(@Valid RequisicaoPedido requisicao, BindingResult result) {
    if (result.hasErrors()) {
      return PEDIDO_FORMULARIO;
    }
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Pedido pedido = requisicao.toPedido();
    User user = userRepository.findByUsername(username);
    pedido.setUser(user);
    Pedido novoPedido = pedidoRepository.save(pedido);
    System.out.println("Produto salvo! " + "[id: " + novoPedido.getId());
    return "redirect:/home";
  }

}
