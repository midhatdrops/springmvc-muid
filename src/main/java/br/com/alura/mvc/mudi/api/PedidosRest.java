package br.com.alura.mvc.mudi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.models.Pedido;
import br.com.alura.mvc.mudi.models.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {
  @Autowired
  private PedidoRepository pedidoRepository;

  @GetMapping("{status}")
  public List<Pedido> getPedidosAguardando(@PathVariable("status") String status) {
    Sort order = Sort.by("id").descending();
    PageRequest pagination = PageRequest.of(0, 10, order);
    return pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()), pagination);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public String onError() {
    return "Status not found";
  }
}
